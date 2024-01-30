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
import nong.soon.bae.bean.MyPageDTO;
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
	
	// �ϴ� ���� ����
	@RequestMapping("productMain")
	public String productMain(Model model, Principal principal) {
		String username = principal.getName();		
		String check = service.CheckMyShop(username);
		if(check == null) {
			model.addAttribute("status", 0);
		}else {
			model.addAttribute("status", 1);
			// �� �̸� ��������
			String myName = service.selectMyName(username);
			
			model.addAttribute("myName", myName);
			model.addAttribute("username", username);
		}
		
		return "product/productMain";
	}	
	
	// �� ���� ���� ����ϱ�
	@RequestMapping("createProduct")
	public String createProduct(Model model, String myName) {
		model.addAttribute("myName", myName);
		return "product/createProduct";
	}	

	// �� ���� ���� ����ϱ�
	@RequestMapping("createProductPro")
	public String createProductPro(Principal principal, ShopListDTO SLdto, AddressDTO Adto) {
		String username = principal.getName();
		String address = Adto.getRoadAddress() + " " + Adto.getDetailAddress() + Adto.getExtraAddress();
		
		SLdto.setUsername(username);
		SLdto.setAddress(address);
		
		// �� ���� ���� ����ϱ�
		service.shopListInsert(SLdto);
		// �� ���� ���̺� �����
		service.createProduct(username);		
		return "redirect:/product/productMain";
	}
	
	// FINISH
	
	
	
	// TEST
	
	// ��ǰ ����ϴ� ������
	@RequestMapping("productWriteForm")
	public String productWriteForm(String myName, Model model, Principal principal) {
		model.addAttribute("myName", myName);
		
		// cate1 �� ��������
		List<ProductCategoryDTO> cate1 = service.selectCate1();
		model.addAttribute("cate1", cate1);
		return "/product/productWriteForm";
	}
	
	// cate2 �� ��������
	@RequestMapping("productWriteForm2")
	public String selectCate2(Model model , int cate1) {
		// cate2 �� ��������
		List<ProductCategoryDTO> cate2 = service.selectCate2(cate1);
		model.addAttribute("cate2", cate2);
		return "/product/productWriteForm2";
	}
	
	// cate3 �� ��������
	@RequestMapping("productWriteForm3")
	public String selectCate3(Model model ,int cate1, int cate2) {
		// cate3 �� ��������
		List<ProductCategoryDTO> cate3 = service.selectCate3(cate1, cate2);
		model.addAttribute("cate3", cate3);
		return "/product/productWriteForm3";
	}	
	
	// ��ǰ ����ϱ�
	@RequestMapping("productWritePro")
	public String productWritePro(Principal principal, Model model, String[] fileNames,
								  int cate1, int cate2, int cate3, 
								  HttpServletRequest request, AllProductDTO APdto, 
								  @RequestParam("optionname") String[] optionname, 
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
		
		// ���� �ּ� ��������
		String fullAddress = service.selectAddress(username);
		// ���� �������� �ڸ���
		String[] addressParts = fullAddress.split(" ");
		// area1 �ּ�
		String area1Address = addressParts[0];
		// area2 �ּ�
		String area2Address = addressParts[1];
		
		// area1 �ּ� ��ȣ�� ��������
		int area1 = service.selectArea1(area1Address);
		// area2 �ּ� ��ȣ�� ��������
		int area2 = service.selectArea2(area2Address, area1);
		
		APdto.setArea1(area1);
		APdto.setArea2(area2);
				
		// AllProduct ��ǰ ����ϱ�
		service.productInsert(APdto);
		
		String productnum = service.selectAllProductLastProductNum(username).get(0).getProductnum();
		String createReviewsProductnum = "P_"+productnum;
		
		// ��ǰ ��ȣ�� ���� ���̺� �����
		service.createReviews(createReviewsProductnum);

		// ����Ʈ ������?
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
					
					// ���� �ֱ�
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

		// �ɼ� �Ѿ�� ����ŭ �ݺ�
		for(int i = 0; i < optionname.length; i++) {
			ProductDTO Pdto = new ProductDTO();						
			Pdto.setUsername(username);
			Pdto.setProductnum(productnum);
			Pdto.setOptionname(optionname[i]);
			Pdto.setPrice(optiontotalprice[i]);
			Pdto.setProductcount(optionProductCount[i]);
			Pdto.setCatenum(categorynum);
			Pdto.setSeqnum("C_"+categorynum);
			
			// username_product �ɼǵ� �ֱ�
			service.optionInsert(Pdto);
		}
		return "/product/productWritePro";
	}	
	
	// FINISH
	
	// ���� ���� �������� �ּ�
	@RequestMapping("productMyShop")
	public String selectMyShop(Principal principal, Model model, String username) {
		
		ShopListDTO SLdto = service.selectMyShop(username);
		List<AllProductDTO> APdto = service.selectUsernameProduct(username);
				
		// ���� �ּ� 
		String fullAddress = SLdto.getAddress();
		// ���� �������� �ڸ���
		String[] addressParts = fullAddress.split(" ");
		// area1 �ּ�
		String area1Address = addressParts[0];
		// area2 �ּ�
		String area2Address = addressParts[1];
		// �ּ� ����
		String address = area1Address + " " + area2Address;
		
		model.addAttribute("address" , address);
		model.addAttribute("SLdto", SLdto);
		model.addAttribute("APdto", APdto);
		return "/product/productMyShop";
	}
	
	// �ϴ� ���� ����
	@RequestMapping("allProduct")
	public String selectAllproduct(Model model) {
		List<AllProductDTO> APdto = service.selectAllproduct();
		model.addAttribute("APdto", APdto);
		return "/product/allProduct";
	}
	
	// ��ǰ ������
	@RequestMapping("productInfo")
	public String productInfo(String productnum, Model model, String follow) {
		// ��ǰ ���� ������
		AllProductDTO APdto = service.selectProductInfo(follow, productnum);
		// ��ǰ �ø� ����� �ּ�, �̸�, �ȷο� ã��
		AllProductDTO APdtoNAF = service.selectProductNameAddressFollowers(follow);
		// ��ǰ �ɼǵ� ��������
		List<ProductDTO> Pdto = service.selectProductOptionAll(follow, productnum);
		// ��ǰ ���� ��������
		List<AllProductDTO> Images = service.selectProductImagesAll(follow, productnum);
		
		
		// ���� �ּ� 
		String fullAddress = APdtoNAF.getAddress();
		// ���� �������� �ڸ���
		String[] addressParts = fullAddress.split(" ");
		// area1 �ּ�
		String area1Address = addressParts[0];
		// area2 �ּ�
		String area2Address = addressParts[1];
		// �ּ� ����
		String address = area1Address + " " + area2Address;		
		
		
		model.addAttribute("follow", follow);
		model.addAttribute("productnum", productnum);
		model.addAttribute("APdto", APdto);
		model.addAttribute("APdtoNAF", APdtoNAF);
		model.addAttribute("address", address);
		model.addAttribute("Pdto", Pdto);
		model.addAttribute("Images", Images);
		return "/product/productInfo";
	}

	// �ȷο��ϱ�
	@RequestMapping("followPro")
	public String followPro(String follow, Principal principal) {
		String username = principal.getName();
		
		MyPageDTO MPdto = new MyPageDTO();
		MPdto.setUsername(username);
		MPdto.setFollow(follow);
		
		service.InsertUsernameFollow(MPdto);
		return "redirect:/product/productMain";
	}	
	
	
	// ��ǰ ������ ¬?
	@RequestMapping("productDetail")
	public String productDetail(Principal principal, Model model, String productnum) {
		String username = principal.getName();
		AllProductDTO APdto = service.selectAllProductPlusNameFollowers(productnum);
		String follow = APdto.getUsername();
		AreaDTO Adto1 = service.selectArea1Address(APdto.getArea1());
		AreaDTO Adto2 = service.selectArea2Address(APdto.getArea1(), APdto.getArea2());
		List<ProductDTO> option = service.selectProductOption(follow, productnum);
		List<ImagesDTO> Idto = service.selectProductImages(follow, productnum);
		
		model.addAttribute("productnum", productnum);
		model.addAttribute("APdto", APdto);
		model.addAttribute("Adto1", Adto1);
		model.addAttribute("Adto2", Adto2);
		model.addAttribute("option", option);
		model.addAttribute("Idto", Idto);
		model.addAttribute("follow", follow);
		return "/product/productDetail";
	}

	// ���ϱ�
	@RequestMapping("productPickPro")
	public String productPickPro(Principal principal, Model model, String productnum, String follow, String optionnum) {
		String username = principal.getName();
		MyPageDTO MPdto = new MyPageDTO();
		MPdto.setUsername(username);
		MPdto.setFollow(follow);
		MPdto.setProductnum(productnum);
		MPdto.setOptionnum(optionnum);
		
		int pickCount = service.selectPickCount(username, productnum);
		if(pickCount==0) {
			service.InsertProductPick(MPdto);
			service.allproductWishcntPlus(productnum);
		}else {
			service.deleteProductPick(username, productnum);
			service.allproductWishcntMinus(productnum);
		}
		
		return "redirect:/product/productMain";
	}
	


	
	
	
	
	
	
	
	
	
	
	
	
	 // ����Ʈ ������?
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


	/*
	// 24�� ���ϴ� �ڵ�
	Date date = new Date();
	SimpleDateFormat smf = new SimpleDateFormat("yyyy/MM/dd");		
	String day = smf.format(date);		
	String year = day.split("/")[0].substring(2, 4);
	String keyword = "%" + year + categorynum + "%";
	String productnum = "";
	// ���� �ֱ��� ��ǰ��ȣ��
	int productnumCnt = service.selectLastProductNumCnt(keyword, username);
	// ��ǰ�ѹ��� ������ productnum �����
	if(productnumCnt==0) {
		productnum = year + categorynum + "00001";
	// ��ǰ�ѹ� ������ ���� productnum�� +1�ϱ�
	}else {
		productnum = service.selectOptionNum(keyword, username).get(0);
		productnum = String.valueOf(Long.parseLong(productnum) + (long) 1);
	}
	*/

