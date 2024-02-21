package nong.soon.bae.contorller.membership;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.ImagesDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.service.MainService;
import nong.soon.bae.service.MembershipService;
import nong.soon.bae.service.ProductService;

@Controller
@RequestMapping("/membership/*")
public class MembershipController {
	
	@Autowired
	private MembershipService service;
	@Autowired
	private ProductService productService;
	@Autowired
	private MainService mainService;
	@Autowired
	private ArrayList<String> srcValues;
	@Autowired
	private ArrayList<String> realFiles;

	@RequestMapping("detailChart")
	public String detailChart(Model model, String year,String month,String value,String name) {
		service.findDetailChart(model, year, month, name, value);
		return "/membership/chart/detailChart";
	}
	
	@RequestMapping("userChart")
	public String userChart(Model model, String cate1, String cate2, String cate3) {
		service.showUserPriceChart(model, cate1, cate2, cate3);
		return "/membership/chart/realUserPriceChart";
	}
	
	@RequestMapping("chart")
	public String categoryChart(Model model, String cate1, String cate2, String cate3, String categoryNum, Principal pri) {
		boolean isMembership = false;
		if(categoryNum == null) {
			categoryNum = "1";
		}
		if(cate1 == null && cate2 == null && cate3 == null) {
			cate1 = "1";
			cate2 = "1";
			cate3 = "1";
		}
		if(pri != null) {
			isMembership = mainService.isMembership(isMembership, pri.getName());
		}
		mainService.cateMenu(model);
		mainService.showCategory(model, cate1, cate2, cate3, Integer.parseInt(categoryNum));
		model.addAttribute("isMembership", isMembership);
		return "all/main/categoryChart";
	}
	
	
	// 상품 등록하는 페이지
		@RequestMapping("write")
		public String productWriteForm(String myName, Model model, Principal principal) {
			model.addAttribute("myName", myName);
			
			// cate1 값 가져오기
			List<ProductCategoryDTO> cate1 = productService.selectCate1();
			model.addAttribute("cate1", cate1);
			return "/membership/product/productWriteForm";
		}
		
		// cate2 값 가져오기
		@RequestMapping("productWriteForm2")
		public String selectCate2(Model model , int cate1) {
			// cate2 값 가져오기
			List<ProductCategoryDTO> cate2 = productService.selectCate2(cate1);
			model.addAttribute("cate2", cate2);
			return "/membership/product/productWriteForm2";
		}
		
		
		// cate3 값 가져오기
		@RequestMapping("productWriteForm3")
		public String selectCate3(Model model ,int cate1, int cate2) {
			// cate3 값 가져오기
			List<ProductCategoryDTO> cate3 = productService.selectCate3(cate1, cate2);
			model.addAttribute("cate3", cate3);
			return "/membership/product/productWriteForm3";
		}	
		// cate3으로 input 가져오기
		@RequestMapping("productWriteForm4")
		public String selectCate4(Model model ,int cate1, int cate2, int cate3) {
			// cate3 값 가져오기
			ProductCategoryDTO dto = productService.selectCate4(cate1, cate2, cate3);
			model.addAttribute("catedto", dto);
			return "/membership/product/firstOption";
		}	
		
		@RequestMapping("changePrice")
		@ResponseBody
		public int changePrice(@RequestParam("optionPrice") int optionPrice,
							   @RequestParam("optionunit") int optionunit,
							   @RequestParam("cateunit") int cateunit,
							   @RequestParam("catename") String catename) {
			int result = service.findAvgPrice(optionPrice,optionunit,cateunit, catename);
			return result;
		}
		
		
		
		// 상품 등록하기
		@RequestMapping("productWritePro")
		public String productWritePro(Principal principal, Model model, String[] fileNames,
									  int cate1, int cate2, int cate3,
									  HttpServletRequest request, AllProductDTO APdto, 
									  @RequestParam("optionname") String[] optionname,
									  @RequestParam("optionunit") int[] optionunit,
									  @RequestParam("optionamount") int optionamount,
									  @RequestParam("optionrealunit") String optionrealunit,
									  @RequestParam("optiontotalprice") int[] optiontotalprice,
									  @RequestParam("optionProductCount") int[] optionProductCount) {

			String username = principal.getName();		
			String categorynum = String.valueOf(cate1) + String.valueOf(cate2) + String.valueOf(cate3);
			
			APdto.setCate1(cate1);
			APdto.setCate2(cate2);
			APdto.setCate3(cate3);		
			APdto.setUsername(username);
			APdto.setCatenum(categorynum);		
			APdto.setSeqnum("C_"+categorynum);
			
			// 상점 주소 가져오기
			String fullAddress = productService.selectAddress(username);
			// 공백 기준으로 자르기
			String[] addressParts = fullAddress.split(" ");
			// area1 주소
			String area1Address = addressParts[0];
			// area2 주소
			String area2Address = addressParts[1];
			
			// area1 주소 번호로 가져오기
			int area1 = productService.selectArea1(area1Address);
			// area2 주소 번호로 가져오기
			int area2 = productService.selectArea2(area2Address, area1);
			
			APdto.setArea1(area1);
			APdto.setArea2(area2);
			
			double avgPrice = 0;
			
			avgPrice = (double) optiontotalprice[0] / ((double) optionunit[0] / optionamount);
			APdto.setAvgprice((int) avgPrice);
			
			// AllProduct 상품 등록하기
			productService.productInsert(APdto);
			
			String productnum = productService.selectAllProductLastProductNum(username).get(0).getProductnum();
			String createReviewsProductnum = "P_"+productnum;
			
			// 상품 번호로 리뷰 테이블 만들기
			productService.createReviews(createReviewsProductnum);

			// 스마트 에디터?
			String content = APdto.getContent();
			String fileRoot = request.getServletContext().getRealPath("/resources/summernoteImage/");
			String realRoot = request.getServletContext().getRealPath("/resources/realImage/");
			int cnt = 1;
			content = content.replace("src=\"/resources/summernoteImage/", "src=\"/resources/realImage/");
			if(fileNames != null) {
		    	ImagesDTO  Idto = new ImagesDTO();
		    	Idto.setProductnum(productnum);
		    	Idto.setUsername(username);
		    	isFile(fileNames, content);
				
		    	for (String filename : realFiles) {
					try {
						File sourceFile = new File(fileRoot+filename);
						File targetDirectory = new File(realRoot);
						String ext = filename.substring(filename.lastIndexOf("."));
						String realname = productnum+"_"+cnt+ext;
						Idto.setFilename(realname);
						
						// 파일 넣기
						productService.imagesInsert(Idto);
						Files.copy(sourceFile.toPath(), targetDirectory.toPath().resolve(realname), StandardCopyOption.REPLACE_EXISTING);
						cnt++;
						content = content.replace(filename, realname);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}			
		    	for (String filename : fileNames) {
					File sourceFile = new File(fileRoot+filename);
					sourceFile.delete();
				}
		    }		

			// 옵션 넘어온 값만큼 반복
			for(int i = 0; i < optionname.length; i++) {
				ProductDTO Pdto = new ProductDTO();						
				Pdto.setUsername(username);
				Pdto.setProductnum(productnum);
				Pdto.setOptionname(optionname[i] +" ("+ optionunit[i] + optionrealunit + ")" );
				Pdto.setPrice(optiontotalprice[i]);
				Pdto.setProductcount(optionProductCount[i]);
				Pdto.setCatenum(categorynum);
				Pdto.setSeqnum("C_"+categorynum);
				
				// username_product 옵션들 넣기
				productService.optionInsert(Pdto);
			}
			
			return "redirect:/product/productMyShop?username=" + username;
		}	
		
		// FINISH
	
	
		 // 스마트 에디터?
		 public void isFile(String[] filenames, String content) {
			 srcValues.clear();
			 realFiles.clear();
			 Pattern pattern = Pattern.compile("src\\s*=\\s*\"([^\"]+)\"");
		     Matcher matcher = pattern.matcher(content);
		     while (matcher.find()) {
		         srcValues.add(matcher.group(1));
		     }
			 for (int i = 0; i < srcValues.size(); i++) {
				int lastSlashIndex = srcValues.get(i).lastIndexOf('/');
				if (lastSlashIndex != -1 && lastSlashIndex < srcValues.get(i).length() - 1) {
					srcValues.set(i, srcValues.get(i).substring(lastSlashIndex + 1));
				}
			 }
			 if(filenames.length != srcValues.size()) {
				 for (int i = 0; i < srcValues.size(); i++) {
					for (String filename : filenames) {
						if(filename.equals(srcValues.get(i))) {
							realFiles.add(srcValues.get(i));
						}
					}
				}
			 }else {
				 realFiles = srcValues;
			 }
		 }
		
		@RequestMapping(value = "uploadSummernoteImageFile", produces = "application/json", consumes = "multipart/form-data")
		public ResponseEntity<JsonNode> uploadSummernoteImageFile(@RequestPart("file") MultipartFile multipartFile,
		      HttpServletRequest request) {
		   ObjectMapper objectMapper = new ObjectMapper();
		   JsonNode responseJson;
		   String fileRoot = request.getServletContext().getRealPath("/resources/summernoteImage/");
			
		   try {
		      String originalFileName = multipartFile.getOriginalFilename();
		      String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		      String savedFileName = UUID.randomUUID() + extension;
		      Path targetPath = Path.of(fileRoot, savedFileName);
		      Files.copy(multipartFile.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
			
		      String imageUrl = request.getContextPath() + "/resources/summernoteImage/" + savedFileName;
		      responseJson = objectMapper.createObjectNode()
		            .put("url", imageUrl)
		            .put("responseCode", "success")
		            .put("fileName", savedFileName);
		      return ResponseEntity.ok(responseJson);
		   } catch (IOException e) {
		      responseJson = objectMapper.createObjectNode().put("responseCode", "error");
		      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseJson);
		   }
		}
		
	
		
	
	
}
