package nong.soon.bae.contorller.user;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import nong.soon.bae.bean.AddressDTO;
import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ImagesDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.ShopListDTO;
import nong.soon.bae.service.ProductService;

@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService service;
	
	@Autowired
	private ArrayList<String> srcValues;
	
	@Autowired
	private ArrayList<String> realFiles;
	
	// 일단 상점 메인
	@RequestMapping("productMain")
	public String productMain(Model model, Principal principal) {
		String username = principal.getName();		
		
		// 내 이름 가져오기
		String myName = service.selectMyName(username);
		
		model.addAttribute("myName", myName);
		return "product/productMain";
	}	
	
	// 내 상점 정보 등록하기
	@RequestMapping("createProduct")
	public String createProduct(Model model, String myName) {
		model.addAttribute("myName", myName);
		return "product/createProduct";
	}	

	// 내 상점 정보 등록하기
	@RequestMapping("createProductPro")
	public String createProductPro(Principal principal, ShopListDTO SLdto, AddressDTO Adto) {
		String username = principal.getName();
		String address = Adto.getRoadAddress() + " " + Adto.getDetailAddress() + Adto.getExtraAddress();
		
		SLdto.setUsername(username);
		SLdto.setAddress(address);
		
		// 내 상점 정보 등록하기
		service.shopListInsert(SLdto);
		// 내 상점 테이블 만들기
		service.createProduct(username);		
		return "redirect:/product/productMain";
	}
	
	// FINISH
	
	
	
	// TEST
	
	// 상품 등록하기
	@RequestMapping("productWriteForm")
	public String productWriteForm(String myName, Model model, Principal principal, int cate2, int  cate3) {
		model.addAttribute("myName", myName);
		
		model.addAttribute("cate2", cate2);
		model.addAttribute("cate3", cate3);
		logger.info("cate2======="+cate2);
		logger.info("cate3======="+cate3);
		// TEST
		List<ProductCategoryDTO> cate1 = service.selectCate1();
		model.addAttribute("cate1", cate1);
		logger.info("cate1======="+cate1);
		return "/product/productWriteForm";
	}
	//
	@RequestMapping("productWriteForm2")
	public String selectCate2(Model model , int cate1) {
		List<ProductCategoryDTO> cate2 = service.selectCate2(cate1);
		model.addAttribute("cate2", cate2);
		logger.info("cate1======="+cate1);
		logger.info("cate2======="+cate2);
		return "/product/productWriteForm2";
	}
	
	@RequestMapping("productWriteForm3")
	public String selectCate3(Model model ,int cate1, int cate2) {
		List<ProductCategoryDTO> cate3 = service.selectCate3(cate1, cate2);
		model.addAttribute("cate3", cate3);
		logger.info("cate1======="+cate1);
		logger.info("cate2======="+cate2);
		logger.info("cate3======="+cate3);
		return "/product/productWriteForm3";
	}	
	//
	
	@RequestMapping("product")
	public String product(Model model ,int cate1, int cate2, int cate3) {
		
		model.addAttribute("cate1", cate1);
		model.addAttribute("cate2", cate2);
		model.addAttribute("cate3", cate3);
		logger.info("cate1======="+cate1);
		logger.info("cate2======="+cate2);
		logger.info("cate3======="+cate3);
		return "/product/product";
	}	
	
	// 상품 등록하기
	@RequestMapping("productWritePro")
	public String productWritePro(Principal principal, Model model, String[] fileNames,
								  int cate1, int cate2, int cate3, 
								  HttpServletRequest request, AllProductDTO APdto, 
								  @RequestParam("optionname") String[] optionname, 
								  @RequestParam("optiontotalprice") int[] optiontotalprice,
								  @RequestParam("optionProductCount") int[] optionProductCount) {

		String username = principal.getName();
		
		String categorynum = String.valueOf(cate1) + String.valueOf(cate2) + String.valueOf(cate3);
		
		logger.info("cate1======="+cate1);
		logger.info("cate2======="+cate2);
		logger.info("cate3======="+cate3);
		
		APdto.setCate1(cate1);
		APdto.setCate2(cate2);
		APdto.setCate3(cate3);		
		APdto.setUsername(username);
		APdto.setCatenum(categorynum);		
		APdto.setSeqnum("C_"+categorynum);
		
		// 상점 주소 가져오기
		String fullAddress = service.selectAddress(username);
		// 공백 기준으로 자르기
		String[] addressParts = fullAddress.split(" ");
		// area1 주소
		String area1Address = addressParts[0];
		// area2 주소
		String area2Address = addressParts[1];
		
		// area1 주소 번호로 가져오기
		int area1 = service.selectArea1(area1Address);
		// area2 주소 번호로 가져오기
		int area2 = service.selectArea2(area2Address, area1);
		
		APdto.setArea1(area1);
		APdto.setArea2(area2);
		

		
		// AllProduct 상품 등록하기
		service.productInsert(APdto);
		
		String productnum = service.selectAllProductLastProductNum(username).get(0).getProductnum();
		

		
		
		
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
					
					
					service.imagesInsert(Idto);
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
			Pdto.setOptionname(optionname[i]);
			Pdto.setPrice(optiontotalprice[i]);
			Pdto.setProductcount(optionProductCount[i]);
			Pdto.setCatenum(categorynum);
			Pdto.setSeqnum("C_"+categorynum);
			
			// username_product 옵션들 넣기
			service.optionInsert(Pdto);
		}
		
		return "/product/productWritePro";
	}	
	
	
	
	
	
	
	
	
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
	
	
	// TEST
	@RequestMapping("sample")
	public String selectCate1(Model model) {
		List<ProductCategoryDTO> cate1 = service.selectCate1();
		model.addAttribute("cate1", cate1);
		return "/product/sample";
	}

		
}


	/*
	// 24년 구하는 코드
	Date date = new Date();
	SimpleDateFormat smf = new SimpleDateFormat("yyyy/MM/dd");		
	String day = smf.format(date);		
	String year = day.split("/")[0].substring(2, 4);
	String keyword = "%" + year + categorynum + "%";
	String productnum = "";
	// 가장 최근의 상품번호값
	int productnumCnt = service.selectLastProductNumCnt(keyword, username);
	// 상품넘버가 없으면 productnum 만들기
	if(productnumCnt==0) {
		productnum = year + categorynum + "00001";
	// 상품넘버 있으면 기존 productnum에 +1하기
	}else {
		productnum = service.selectOptionNum(keyword, username).get(0);
		productnum = String.valueOf(Long.parseLong(productnum) + (long) 1);
	}
	*/

