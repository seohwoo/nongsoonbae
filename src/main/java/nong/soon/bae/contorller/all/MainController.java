package nong.soon.bae.contorller.all;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.NoticeBoardDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.data.ApiKeys;
import nong.soon.bae.data.FileRoot;
import nong.soon.bae.service.AreaService;
import nong.soon.bae.service.CategoryService;
import nong.soon.bae.service.MainService;
import nong.soon.bae.service.NoticeService;
import nong.soon.bae.service.ProductService;

@RequestMapping("/nsb/*")
@Controller
public class MainController{

	@Autowired
	private ProductService productService;
	
	@Autowired
	private MainService service;
	
	@Autowired
	private AreaService areaservice;

	@Autowired
	private CategoryService cateservice;
	
	@Autowired
	private NoticeService noticeservice;
	
		
	@RequestMapping("main")
	public String main(Model model, String categoryNum, String cate1, String cate2, String cate3, String userSearch,
					@RequestParam(value="pageNum", defaultValue="1") int pageNum,
					@RequestParam(value="sort", required=false) String sort) {
		NoticeBoardDTO notice = noticeservice.showNewNotice();
		model.addAttribute("notice",notice);
		if (categoryNum==null) {
			categoryNum = "1";
		}
		if(cate1 == null && cate2 == null) {
			cate1 = "0";
			cate2 = "0";
		}
		service.seasonCategory(model, Integer.parseInt(categoryNum));
		if (cate1.equals("0") && cate2.equals("0")) { //전체항목리스트 
			service.adallproductlist(model); //광고
			service.allproductlist(model,sort,pageNum); 
		}
		
		if(cate1!=null && cate2!=null && cate3!=null ) {
			service.detailSeasonCategory(model, cate1, cate2, cate3,pageNum,sort);
			service.adDetailSeason(model, cate1,cate2,cate3);
		}
		
	    // 정룡
	    List<AllProductDTO> APdto = productService.allProductSelect();
	        for (AllProductDTO dto : APdto) {
	           String usernames = dto.getUsername();
	           String productnum = dto.getProductnum();
	           productService.updateAllProductGrade200(productnum, usernames);
	    }				
		return "all/main/main";
	}
	
	
	@RequestMapping("result")
	public String result(Model model, String userSearch, String searchNum) {
		if(searchNum == null) {
			searchNum = "1";
		}
		System.out.println();
		service.findProduct(model, userSearch, Integer.parseInt(searchNum));
		service.findAdProduct (model, userSearch);
		return "all/main/result";
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
			isMembership = service.isMembership(isMembership, pri.getName());
		}
		service.cateMenu(model);
		service.showCategory(model, cate1, cate2, cate3, Integer.parseInt(categoryNum));
		service.showChart(model, cate1, cate2, cate3);
		model.addAttribute("isMembership", isMembership);
		return "all/main/categoryChart";
	}
	
	//카테고리별 분류
	@RequestMapping("menu")
	public String main(Model model, String cate1, String cate2,
					@RequestParam(value="sort", required=false) String sort,
					@RequestParam(value="pageNum", defaultValue="1") int pageNum) {	
		List<ProductCategoryDTO> catelist = cateservice.cateMenu(model);
		model.addAttribute("catelist",catelist);
		if (cate1 == null && cate2 == null) { //전체항목리스트 
			cateservice.adallproductlist(model); //광고
			cateservice.allproductlist(model,sort,pageNum); 
			model.addAttribute("isCateSelect", 0);
		} if (cate1 != null && cate2 == null) {
			cateservice.catelistdeatil(model, cate1);  //cate1 리스트
			cateservice.adcateprodutlist(model, cate1); //광고
			cateservice.cateprodutlist(model, cate1, pageNum, sort);//cate1  상품리스트
			model.addAttribute("isCateSelect", 1);
		}if(cate1 != null && cate2 != null ){
			cateservice.catelistdeatil(model, cate1);
			cateservice.adcateproductdetail (model,cate1 ,cate2); // cate2 광고 상품리스트 
			cateservice.cateproductlistdetail (model,cate1 ,cate2,pageNum,sort); // cate2 상품리스트 
			model.addAttribute("isCateSelect", 2);
		}
		if(cate1==null) {
			cate1 = "0";
			model.addAttribute("cate1", Integer.parseInt(cate1));
		}
		return "all/main/categorymain";
	}
	
	
	//지역별 분류
	@RequestMapping("area")
	public String area(Model model, @RequestParam(value="areaNum", defaultValue="1") int areaNum,  
						@RequestParam(value="sort", required=false) String sort,
						@RequestParam(value="pageNum", defaultValue="1") int pageNum,
						String area1, String area2 ) {
		areaservice.arealist(areaNum,model);
		if (area1 == null && area2 == null) { //전체항목리스트 
			areaservice.allproductlist(model,sort,pageNum); 
			areaservice.adallproductlist(model);
			model.addAttribute("isAreaSelected", false);
		} 
		if (area1 != null && area2 == null) {
			areaservice.areaprodutlist(model, areaNum,pageNum,area1,sort); //area1  상품리스트
			areaservice.adareaprodutlist(model,area1); //area1 광고  상품리스트
			
			model.addAttribute("isAreaSelected", true);
		}
		if(area1==null) {
			area1 = "0";
			model.addAttribute("area1", Integer.parseInt(area1));
		}
		return "all/main/areamain";
	}
}
