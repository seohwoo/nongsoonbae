package nong.soon.bae.contorller.user;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.MyPageDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.ShopListDTO;
import nong.soon.bae.bean.UsersDTO;
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
		model.addAttribute("username", username);
		return "product/productMain";
	}	
	
	// 개인 상점(테이블) 만들기
	@RequestMapping("createProduct")
	public String createProduct(Model model, Principal principal) {
		String username = principal.getName();
		model.addAttribute("username", username);
		return "product/createProduct";
	}
	
	// 내 상점 정보 등록하기, 개인 상점, 이미지 테이블 만들기
	@RequestMapping("createProductPro")
	public String createProductPro(Model model, Principal principal, ShopListDTO dto) {
		String username = principal.getName();
		model.addAttribute("username", username);
		dto.setUsername(username);
		
		service.allShopList(dto);
		service.createProduct(username);
		service.createImages(username);
		return "redirect:/product/productMain";
	}
	
	// 상품 등록하기
	@RequestMapping("productWriteForm")
	public String productWriteForm(HttpServletRequest request, Model model, Principal principal, 
								   @RequestParam(value="productnum", defaultValue="0")String productnum) {
		
		String username = principal.getName();
		model.addAttribute("username", username);
		model.addAttribute("productnum", productnum);
		return "product/productWriteForm";
	}

	// 상품 등록하기
	@RequestMapping("productWritePro")
	public String productWritePro(String content, String[] fileNames, HttpServletRequest request, List<MultipartFile> files, Model model, 
								  Principal principal, ProductDTO product, AllProductDTO dto, String cate3, 
								  @RequestParam("optionname") String[] optionname, 
								  @RequestParam("optiontotalprice") int[] optiontotalprice,
								  @RequestParam("optionProductCount") int[] optionProductCount) {
		
		String productnum = "";
		String username = principal.getName();
		
		product.setUsername(username);
		product.setProductnum(cate3); 		
		product.setSeqnum("C_"+cate3 );	
		String path = request.getServletContext().getRealPath("/resources/file/product/");
		
		// 상품재고수 다 더한 코드	
		int count = Arrays.stream(optionProductCount).sum();
		product.setProductcount(count);
		
		// 상품 추가된 옵션값 구한 코드
		String optionstatus = String.valueOf(optionname.length);
		product.setOptionstatus(optionstatus);
		
		// 판매자가 입력한 가격 중 최저가격 구하기
		int minValue = Arrays.stream(optiontotalprice).min().orElse(0);
		product.setTotalprice(minValue);
		
		
		
		/////////////////////////////////////////
		String fileRoot = request.getServletContext().getRealPath("/resources/summernoteImage/");
		String realRoot = request.getServletContext().getRealPath("/resources/realImage/");
		int cnt2 = 1;
		content = content.replace("src=\"/resources/summernoteImage/", "src=\"/resources/realImage/");
	    if(fileNames != null) {
	    	isFile(fileNames, content);
			for (String filename : realFiles) {
				try {
					File sourceFile = new File(fileRoot+filename);
					File targetDirectory = new File(realRoot);
					String ext = filename.substring(filename.lastIndexOf("."));
					String filenum = service.selectProductnum(username);
					String realname = filenum+"_"+cnt2+ext;
					Files.copy(sourceFile.toPath(), targetDirectory.toPath().resolve(realname), StandardCopyOption.REPLACE_EXISTING);
					cnt2++;
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
		model.addAttribute("content", content);
		///////////////////////////////////////
		
		
		int cnt = service.productInsert(product, files, path);
		int result = service.imagesInsert(files, path, username);
		
		// cate3 값을 cate1,2,3 으로 나눠서 AllproductDTO 에 넣는 코드
		int catego1 = cate3.charAt(0)-48;
		int catego2 = cate3.charAt(1)-48;
		int catego3 = cate3.charAt(2)-48;				
		dto.setCate1(catego1);
		dto.setCate2(catego2);
		dto.setCate3(catego3);
		
		// 상점의 주소를 AllProduct 테이블에 넣기
		List<AreaDTO> selectAddress = service.selectAddress(username);
		for (AreaDTO area : selectAddress) {
            if (area.getArea1() >= 1 && area.getArea2() >= 1) {
            	dto.setArea1(area.getArea1());
            	dto.setArea2(area.getArea2());
            }
        }
 	
		
		
		// 가장 최근에 등록한 productnum값 allproduct에 넣기
		if(cnt >0) {
			dto.setProductnum(service.selectProductnum(username));
			service.allProductInsert(dto);
		}
		
		// 옵션 값만큼 상품 등록하기 
		String OptionProductnum = service.selectProductnum(username);
		for (int i = 0; i < optionname.length; i++) {
		    product.setProductname(optionname[i]);
		    product.setTotalprice(optiontotalprice[i]);
		    product.setProductcount(optionProductCount[i]);
		    product.setProductnum(cate3);
		    product.setOptionstatus(OptionProductnum);
		    service.optionInsert(product);		    
		}		
		
		// productnum값으로 리뷰 테이블 만들기
		productnum = "P_"+service.selectProductnum(username);
		service.createReviews(productnum);
		
		model.addAttribute("username", username);
		model.addAttribute("cnt", cnt);
		model.addAttribute("result", result);		
		
		return "product/productWritePro";
	}
	 
	// FINISH

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
	
	@RequestMapping("myProduct")
	public String myProduct(Model model, Principal principal, ProductDTO dto) {
		String username = principal.getName();
		model.addAttribute("username", username);
		dto.setUsername(username);
		List<ProductDTO> productDTO = service.myProduct(username);
		model.addAttribute("productDTO", productDTO);
		return "product/myProduct";
	}
	
	@RequestMapping("productInfo")
	public String productInfo(Model model, Principal principal, ProductDTO productDTO, String productnum) {
		String username = principal.getName();
		model.addAttribute("username", username);
		productDTO.setUsername(username);
		productDTO = service.productInfo(productDTO);
		model.addAttribute("productDTO", productDTO);
		return "product/productInfo";
	}
	
	// 상품 상세보기 
	@RequestMapping("productDetail")
	public String productDetail(String productnum, Model model, Principal principal, ProductDTO productDTO, AreaDTO areaDTO) {
		String username = principal.getName();
		model.addAttribute("username", username);	
		productDTO.setUsername(username);
		
		productDTO = service.productDetail(productnum, username);
		
		// 상점 주소 가져오는 코드
		areaDTO = service.selectArea(productnum, username);
		
		// area1 가져오는 코드
		areaDTO.setArea1(areaDTO.getArea1());
		areaDTO.setArea2(areaDTO.getArea2());
		String areaName2 = service.selectAreaName2(areaDTO);
		
		// area2 가져오는 코드
		areaDTO.setArea1(areaDTO.getArea1());
		areaDTO.setArea2(0);
		String areaName1 = service.selectAreaName1(areaDTO);
		
		// 닉네임 가져오는 코드
		String name = service.selectName(username);

		// 상품의 옵션값 가져오는 코드
		String optionstatus = productnum;
		List<ProductDTO> option = service.selectOption(username, optionstatus);


		model.addAttribute("productnum", productnum);
		model.addAttribute("option", option);
		model.addAttribute("name", name);
		model.addAttribute("areaName1", areaName1);
		model.addAttribute("areaName2", areaName2);
		model.addAttribute("areaDTO", areaDTO);
		model.addAttribute("productDTO", productDTO);
		
		return "product/productDetail";
	}
	
	@RequestMapping("allProduct")
	public String allProduct(Model model, Principal principal, String productName, ProductDTO productDTO) {
		String username = principal.getName();
		model.addAttribute("username", username);
		
		List<AllProductDTO> allProductDTO = service.allProduct();
		model.addAttribute("allProductDTO", allProductDTO);
		return "product/allProduct";
	}
	
	
	@PostMapping("sample2")
	public String sample2(@RequestParam("files") List<MultipartFile> files) {
		return "product/sample2";
	}
						
	@RequestMapping("sample")
	public String sample() {
		return "product/sample";
	}
	
	// 찜하기
	@RequestMapping("productPick")
	public String productPick(Principal principal, String productnum) {
		String username = principal.getName();
		
		// 상품 찜하기 유무
		int count = service.selectProductPickCount(username, productnum);
		
		// 상품 찜 안돼있으면(0) 내 테이블에 상품 찜하고 판매자 상품 찜한 갯수에 +1
		if(count == 0) {
			service.productPick(username, productnum);
			service.updateProductWishcount(username, productnum);
		}else {
			// 상품 찜 돼있으면 내 테이블에 상품 찜 삭제하고 판매자 상품 찜한 갯수에 -1 
			service.productPickDelete(username, productnum);
			service.deleteProductWishcount(username, productnum);
		}
		return "redirect:/product/productMain"; 
	}	
	
	// 장바구니
	@RequestMapping("productShoppingCart")
	public String productShoppingCart(Principal principal, String productnum) {
		String username = principal.getName();
		
		// 장바구니 상품 유무
		int count = service.selectProductShoppingCartCount(username, productnum);
		
		// 장바구니에 상품 없으면(0) 장바구니에 상품 담기
		if(count == 0) {
			service.productShoppingCart(username, productnum);
		}else {
			// 장바구니에 상품 있으면 장바구니에 상품 삭제하기
			service.productShoppingCartDelete(username, productnum);
		}
		return "redirect:/product/productMain"; 
	}	
	
}

