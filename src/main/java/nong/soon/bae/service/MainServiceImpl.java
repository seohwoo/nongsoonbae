package nong.soon.bae.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductListDTO;
import nong.soon.bae.bean.UserGradeDTO;
import nong.soon.bae.repository.CategoryMapper;
import nong.soon.bae.repository.MainMapper;

@Service
public class MainServiceImpl implements MainService {

	@Autowired
	private MainMapper mapper;
	@Autowired
	private CategoryMapper cateMapper;
	@Autowired
	private SimpleDateFormat simpleDateFormat;
	@Autowired
	private HashMap<String, String> seasonCategoryMap;
	@Autowired
	private ArrayList<Integer> thislist;
	@Autowired
	private ArrayList<Integer> lastlist;
	@Autowired
	private ArrayList<Integer> oldestlist;
	@Autowired
	private ArrayList<ProductListDTO> productList;

	public String[] todayInfo() {
		Date date = new Date();
		String formatDate = simpleDateFormat.format(date);
		String[] today = formatDate.split("/");
		return today;
	}
	
	@Override
	public void seasonCategory(Model model, int categoryNum) {
		String month = todayInfo()[1].substring(1);
		String keyword = "%"+month+",%";
		int categorySize = 10;
		int cnt = mapper.seasonCategoryCnt(keyword);
		int maxCategoryNum = (int) (cnt / categorySize) + (cnt % categorySize == 0 ? 0 : 1);
		if(categoryNum < 1) {
			categoryNum = 1;
		}else if(categoryNum > maxCategoryNum) {
			categoryNum = maxCategoryNum;
		}
		List<ProductCategoryDTO> list = Collections.EMPTY_LIST;
		if (cnt > 0) {
			int start = (categoryNum-1)*categorySize+1;
			int end = categoryNum * categorySize;
			seasonCategoryMap.put("keyword", keyword);
			seasonCategoryMap.put("start", String.valueOf(start));
			seasonCategoryMap.put("end", String.valueOf(end));
			list = mapper.seasonCategory(seasonCategoryMap);
		}
		model.addAttribute("categoryList", list);
		model.addAttribute("month", month);
		model.addAttribute("categoryNum", categoryNum);
		model.addAttribute("maxCategoryNum", maxCategoryNum);
	}
	
	// 전체상품리스트
		@Override
		public void allproductlist(Model model,String sort, int pageNum) { //전체 품목 뽑기
			int pageSize = 12;
		    int startRow = (pageNum - 1) * pageSize + 1;
		    int endRow = pageNum * pageSize;
			int allCnt = cateMapper.allCnt();
			List<AllProductDTO> allprocuctList = Collections.EMPTY_LIST ;
			if(allCnt >0 ) {
				seasonCategoryMap.clear();
				seasonCategoryMap.put("start", String.valueOf(startRow));
				seasonCategoryMap.put("end", String.valueOf(endRow));
				 if ("readcnt".equals(sort)) {		
					 	allprocuctList = cateMapper.readListAll(seasonCategoryMap); // 인기순(조회수) 정렬
			        } else if ("wishcnt".equals(sort)) {
			            allprocuctList = cateMapper.wishListAll(seasonCategoryMap); // 찜 정렬
			        }else if ("cheap".equals(sort)) {
			            allprocuctList = cateMapper.cheapListAll(seasonCategoryMap); // 가격낮은순 정렬
			        } else {
			            allprocuctList = cateMapper.allproductList(seasonCategoryMap); // 기본(최신순) 정렬
			        }
				 	ArrayList<ProductListDTO> localProductList = cateshowProduct(allprocuctList);
			        model.addAttribute("productList", localProductList);
			    }
			
			model.addAttribute("productCnt",allCnt);
			model.addAttribute("pageNum",pageNum);
		    model.addAttribute("pageSize",pageSize);
		    model.addAttribute("sort",sort);
		    
		    int pageCount = allCnt / pageSize + ( allCnt % pageSize == 0 ? 0 : 1);
			 
	        int startPage = (int)(pageNum/10)*10+1;
			int pageBlock=10;
	        int endPage = startPage + pageBlock-1;
	        if (endPage > pageCount) {
				endPage = pageCount;
	        }				
	        model.addAttribute("pageCount",pageCount);
	        model.addAttribute("startPage",startPage);
	        model.addAttribute("pageBlock",pageBlock);
	        model.addAttribute("endPage",endPage);
		}
		
		@Override
		public void adallproductlist(Model model) {
			int adAllCnt = cateMapper.adAllCnt();
			List<AllProductDTO> adAllprocuct = Collections.EMPTY_LIST ;
			if(adAllCnt > 0) {
				adAllprocuct = cateMapper.adAllProduct();	
				ArrayList<ProductListDTO> localAdProductList = adshowProduct(adAllprocuct);
	            model.addAttribute("adList", localAdProductList);	
				
			}
			model.addAttribute("adCnt",adAllCnt);
			
		}

	@Override
	public void detailSeasonCategory(Model model, String cate1, String cate2, String cate3,int pageNum,String sort) {
		int pageSize = 2;
	    int startRow = (pageNum - 1) * pageSize + 1;
	    int endRow = pageNum * pageSize;
		seasonCategoryMap.put("cate1", cate1);
		seasonCategoryMap.put("cate2", cate2);
		seasonCategoryMap.put("cate3", cate3);
		seasonCategoryMap.put("start", String.valueOf(startRow));
		seasonCategoryMap.put("end", String.valueOf(endRow));
		
		String catename = mapper.findCatename(seasonCategoryMap);
		int cnt = mapper.seasonProductCnt(seasonCategoryMap);
		List<AllProductDTO> list = Collections.EMPTY_LIST;
		if(cnt > 0) {
			if ("readcnt".equals(sort)) {		
				list = mapper.readList(seasonCategoryMap); // 인기순(조회수) 정렬
	        } else if ("wishcnt".equals(sort)) {
	        	list = mapper.wishList(seasonCategoryMap); // 찜 정렬
	        }else if ("cheap".equals(sort)) {
	        	list = mapper.cheapList(seasonCategoryMap); // 가격낮은순 정렬
	        } else {
	        	list = mapper.seasonProduct(seasonCategoryMap);
		}
			ArrayList<ProductListDTO> localProductList = exshowProduct(list);
	        model.addAttribute("productList", localProductList); 
			
		model.addAttribute("catename", catename);
		model.addAttribute("productCnt", cnt);
		model.addAttribute("sort",sort);
		model.addAttribute("pageSize", pageSize);
		 	int pageCount = cnt / pageSize + (cnt % pageSize == 0 ? 0 : 1);
		    int startPage = (int) ((pageNum - 1) / 10) * 10 + 1;
		    int pageBlock = 10;
		    int endPage = startPage + pageBlock - 1;
		    if (endPage > pageCount) {
		        endPage = pageCount;
		    }
		    
		    model.addAttribute("pageCount", pageCount);
		    model.addAttribute("startPage", startPage);
		    model.addAttribute("pageBlock", pageBlock);
		    model.addAttribute("endPage", endPage);
		}
	}
	
	
	@Override
	public void adDetailSeason(Model model, String cate1, String cate2, String cate3) {
		seasonCategoryMap.put("cate1", cate1);
		seasonCategoryMap.put("cate2", cate2);
		seasonCategoryMap.put("cate3", cate3);
		int adCnt = mapper.adCnt(seasonCategoryMap);
		List<AllProductDTO> adList = Collections.EMPTY_LIST;
		if (adCnt >0) {
			adList = mapper.adDetailSeason(seasonCategoryMap);
			ArrayList<ProductListDTO> localAdProductList = adshowProduct(adList);
            model.addAttribute("adList", localAdProductList);
		}
		model.addAttribute("adCnt",adCnt);
	}

	
	@Override
	public void showChart(Model model, String cate1, String cate2, String cate3) {
		thislist.clear();
		lastlist.clear();
		oldestlist.clear();
		String thisYear = String.valueOf((Integer.parseInt(todayInfo()[0])));
		String lastYear = String.valueOf((Integer.parseInt(todayInfo()[0])-1));
		String oldestYear = String.valueOf((Integer.parseInt(todayInfo()[0])-2));
		seasonCategoryMap.put("cate1", cate1);
		seasonCategoryMap.put("cate2", cate2);
		seasonCategoryMap.put("cate3", cate3);
		String catename = "";
		int cnt = 0;
		if(mapper.findCatename(seasonCategoryMap)!=null) {
			catename = mapper.findCatename(seasonCategoryMap);
			seasonCategoryMap.put("catename", catename);
			cnt = mapper.isChart(seasonCategoryMap);
		}
		if(cnt>0) {
			int max = mapper.maxAvgPrice(catename);
			String strMax = String.valueOf(max);
			int yValue = Integer.parseInt(strMax.substring(0,1))+1;
			String chartY = String.valueOf(yValue);
			for (int i = 0; i < strMax.length()-1; i++) {
				chartY += "0";
			}
			yValue = Integer.parseInt(chartY);
			String keyword = "";
			for (int i = 1; i <= 12; i++) {
				keyword = "%" + thisYear + "년" + i + "월%";
				seasonCategoryMap.put("keyword", keyword);
				if(mapper.productChart(seasonCategoryMap) != null) {
					thislist.add((int) Double.parseDouble(mapper.productChart(seasonCategoryMap)));
				} else {
					thislist.add(0); 
				}
				keyword = "%" + lastYear + "년" + i + "월%";
				seasonCategoryMap.put("keyword", keyword);
				lastlist.add((int) Double.parseDouble(mapper.productChart(seasonCategoryMap)));
				keyword = "%" + oldestYear + "년" + i + "월%";
				seasonCategoryMap.put("keyword", keyword);
				oldestlist.add((int) Double.parseDouble(mapper.productChart(seasonCategoryMap)));
			}
			model.addAttribute("thisYear", thisYear);
			model.addAttribute("lastYear", lastYear);
			model.addAttribute("oldestYear", oldestYear);
			model.addAttribute("yValue", yValue);
			model.addAttribute("thislist", thislist);
			model.addAttribute("lastlist", lastlist);
			model.addAttribute("oldestlist", oldestlist);
		}
		model.addAttribute("catename", catename);
		model.addAttribute("isChart", cnt);
	}
	
	public void page(int pageSize, int pageNum) {
		int start = (pageNum-1)*pageSize+1;
		int end = pageNum * pageSize;
		seasonCategoryMap.put("start", String.valueOf(start));
		seasonCategoryMap.put("end", String.valueOf(end));
	}
	

	@Override
	public void showCategory(Model model, String cate1, String cate2, String cate3, int categoryNum) {
		int categorySize = 10;
		int cnt = mapper.chartCategoryCnt(cate1);
		int maxCategoryNum = (int) (cnt / categorySize) + (cnt % categorySize == 0 ? 0 : 1);
		ProductCategoryDTO prevCate = null;
		ProductCategoryDTO nextCate = null;
		if(categoryNum < 1) {
			categoryNum = 1;
		}else if(categoryNum > maxCategoryNum) {
			categoryNum = maxCategoryNum;
		}
		
		if(categoryNum > 1) {
			categoryNum--;
			page(categorySize, categoryNum);
			prevCate = mapper.nextCate(seasonCategoryMap);
			model.addAttribute("prevCate", prevCate);
			categoryNum++;
		}
		
		List<ProductCategoryDTO> list = Collections.EMPTY_LIST;
		if (cnt > 0) {
			seasonCategoryMap.put("cate1", cate1);
			page(categorySize, categoryNum);
			list = mapper.chartCategory(seasonCategoryMap);
			categoryNum++;
		}
		
		page(categorySize, categoryNum);
		if(mapper.nextCate(seasonCategoryMap) != null) {
			nextCate = mapper.nextCate(seasonCategoryMap);
			model.addAttribute("nextCate", nextCate);
		}
		
		if (cnt > 0) {
			categoryNum--;
		}
		model.addAttribute("cateList", list);
		model.addAttribute("cate1", cate1);
		model.addAttribute("isCate3", cnt);
		model.addAttribute("categoryNum", categoryNum);
		model.addAttribute("maxCategoryNum", maxCategoryNum);
	}

	@Override
	public void cateMenu(Model model) {
		List<ProductCategoryDTO> list = mapper.cateMenu();
		model.addAttribute("catemenu", list);
	}

	@Override
	public void findProduct(Model model, String userSearch, int searchNum) {
		int searchPageSize = 12;
		String keyword = "%" + userSearch + "%";
		seasonCategoryMap.put("keyword", keyword);
		int cnt = mapper.searchProductCnt(keyword);
		List<AllProductDTO> alprList = Collections.EMPTY_LIST;
		if(cnt > 0) {
			page(searchPageSize, searchNum);
			alprList = mapper.searchProduct(seasonCategoryMap);
			showProduct(alprList);
		}
		model.addAttribute("userSearch", userSearch);
		model.addAttribute("searchCnt", cnt);
		model.addAttribute("searchList", productList);
		model.addAttribute("searchNum",searchNum);
		
			int pageCount = cnt / searchPageSize + (cnt % searchPageSize == 0 ? 0 : 1);
		    int startPage = (int) ((searchNum - 1) / 10) * 10 + 1;
		    int pageBlock = 10;
		    int endPage = startPage + pageBlock - 1;
		    if (endPage > pageCount) {
		        endPage = pageCount;
		    }
		    
		    model.addAttribute("pageCount", pageCount);
		    model.addAttribute("startPage", startPage);
		    model.addAttribute("pageBlock", pageBlock);
		    model.addAttribute("endPage", endPage);
	}
	
	
	@Override
	public void findAdProduct(Model model, String userSearch) {
		List<AllProductDTO> adproductlist = Collections.EMPTY_LIST ;
		String keyword = "%" + userSearch + "%";
		seasonCategoryMap.put("keyword", keyword);
		int adCnt = mapper.searchAdProductCnt(keyword);
		if(adCnt >0) {
			adproductlist = mapper.searchAdProduct(keyword);
			ArrayList<ProductListDTO> localAdProductList = adshowProduct(adproductlist);
            model.addAttribute("adproductlist", localAdProductList);
		
		}
		model.addAttribute("adCnt",adCnt);
	}
	

	public ArrayList<ProductListDTO> exshowProduct(List<AllProductDTO> alprList) {
	    ArrayList<ProductListDTO> localProductList = new ArrayList<>();
	    for (AllProductDTO alprDTO : alprList) {
	    	seasonCategoryMap.put("username", alprDTO.getUsername());
			seasonCategoryMap.put("productnum", alprDTO.getProductnum());
			String keyword = alprDTO.getProductnum() + "_1%";
			seasonCategoryMap.put("keyword", keyword);
	        ProductListDTO dto = mapper.findProductListValue(seasonCategoryMap);
	        localProductList.add(dto);
	    }
	    return localProductList;
	}
	
	public ArrayList<ProductListDTO> cateshowProduct(List<AllProductDTO> alprList) {
	    ArrayList<ProductListDTO> localProductList = new ArrayList<>();
	    for (AllProductDTO alprDTO : alprList) {
	    	seasonCategoryMap.put("username", alprDTO.getUsername());
			seasonCategoryMap.put("productnum", alprDTO.getProductnum());
			String keyword = alprDTO.getProductnum() + "_1%";
			seasonCategoryMap.put("keyword", keyword);
	        ProductListDTO dto = mapper.findProductListValue(seasonCategoryMap);
	        localProductList.add(dto);
	    }
	    return localProductList;
	}

	public ArrayList<ProductListDTO> adshowProduct(List<AllProductDTO> alprList) {
	    ArrayList<ProductListDTO> localAdProductList = new ArrayList<>();
	    for (AllProductDTO alprDTO : alprList) {
	    	seasonCategoryMap.put("username", alprDTO.getUsername());
			seasonCategoryMap.put("productnum", alprDTO.getProductnum());
			String keyword = alprDTO.getProductnum() + "_1%";
			seasonCategoryMap.put("keyword", keyword);
	        ProductListDTO ad = mapper.findProductListValue(seasonCategoryMap);
	        localAdProductList.add(ad);
	    }
	    return localAdProductList;
	}
	
	/**
	 * main에 보여줄 값을 한번에 저장하기 위해 사용(일단보류)
	 * */
	public void showProduct(List<AllProductDTO> alprList) {
		productList.clear();
		ProductListDTO dto = null;
		for (AllProductDTO alprDTO : alprList) {
			seasonCategoryMap.put("username", alprDTO.getUsername());
			seasonCategoryMap.put("productnum", alprDTO.getProductnum());
			String keyword = alprDTO.getProductnum() + "_1%";
			seasonCategoryMap.put("keyword", keyword);
			dto = mapper.findProductListValue(seasonCategoryMap);
			productList.add(dto);		
		}
	}

	@Override
	public boolean isMembership(boolean isMembership, String username) {
		List<UserGradeDTO> list = mapper.isMembership(username).getGrade();
		for (UserGradeDTO dto : list) {
			if(dto.getGrade().equals("ROLE_MEMBERSHIP")) {
				isMembership = true;
				break;
			}
		}
		return isMembership;

	}

	

	
}
