package nong.soon.bae.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ImagesDTO;
import nong.soon.bae.bean.MyPageDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.ReviewsDTO;
import nong.soon.bae.bean.ShopListDTO;
import nong.soon.bae.repository.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {

   @Autowired
   private ProductMapper mapper;
   @Autowired
   private HashMap<String, String> productMap;
   @Autowired
	private SimpleDateFormat simpleDateFormat;
   

   // �궡 �씠由� 媛��졇�삤湲�
   @Override
   public String selectMyName(String username) {
      return mapper.selectMyName(username);
   }   
   
   // �궡 �긽�젏 �젙蹂� �벑濡앺븯湲�
   @Override
   public void shopListInsert(ShopListDTO SLdto) {
      mapper.shopListInsert(SLdto);
   }
   
   // �궡 �긽�젏 �뀒�씠釉� 留뚮뱾湲�
   @Override
   public void createProduct(String username) {
      mapper.createProduct(username);
   }

   // 移댄뀒怨좊━ ��遺꾨쪟
   @Override
   public List<ProductCategoryDTO> selectCate1() {
      return mapper.selectCate1();
   }

   // 移댄뀒怨좊━ 以묐텇瑜�   
   @Override
   public List<ProductCategoryDTO> selectCate2(int cate1) {
      return mapper.selectCate2(cate1);
   }

   // 移댄뀒怨좊━ �냼遺꾨쪟
   @Override
   public List<ProductCategoryDTO> selectCate3(int cate1, int cate2) {
      return mapper.selectCate3(cate1, cate2);
   }   
   
   //移댄뀒怨좊━ �떒�쐞媛� 媛��졇�삤湲�
   @Override
   public ProductCategoryDTO selectCate4(int cate1, int cate2, int cate3) {
      return mapper.selectCate4(cate1, cate2, cate3);
   }
   
   // �긽�뭹 �벑濡앺븯湲�
   @Override
   public void productInsert(AllProductDTO APdto) {
      mapper.productInsert(APdto);
   }

   // �긽�뭹 �벑濡앺븳 吏곹썑�쓽 productnum 援ы븯湲�
   @Override
   public List<AllProductDTO> selectAllProductLastProductNum(String username) {
      return mapper.selectAllProductLastProductNum(username);
   }   

   // �긽�뭹 �벑濡앺븷 �븣 �긽�뭹 由щ럭 �뀒�씠釉� 留뚮뱾湲�
   @Override
   public void createReviews(String createReviewsProductnum) {
      mapper.createReviews(createReviewsProductnum);
   }   
   
   // �긽�젏 二쇱냼 媛��졇�삤�뒗 肄붾뱶
   @Override
   public String selectAddress(String username) {
      return mapper.selectAddress(username);
   }   

   // area1 媛� 媛��졇�삤�뒗 肄붾뱶
   @Override
   public int selectArea1(String area1Address) {
      return mapper.selectArea1(area1Address);
   }

   // area2 媛� 媛��졇�삤�뒗 肄붾뱶
   @Override
   public int selectArea2(String area2Address, int area1) {
      return mapper.selectArea2(area1, area2Address);
   }
   
   // �긽�뭹 �벑濡앺븷 �븣 �씠誘몄� �꽔湲�
   @Override
   public void imagesInsert(ImagesDTO Idto) {
      mapper.imagesInsert(Idto);
   }

   // �긽�뭹 �벑濡앺븷 �븣 username_product�뿉 �샃�뀡�뱾 �꽔湲�
   @Override
   public void optionInsert(ProductDTO Pdto) {
      mapper.optionInsert(Pdto);
   }

   // �긽�뭹 李쒗븯湲�
   @Override
   public void InsertProductPick(MyPageDTO MPdto) {
      mapper.InsertProductPick(MPdto);
   }

   // �긽�뭹�뿉 李� +1 �븯湲�
   @Override
   public void allproductWishcntPlus(String productnum) {
      mapper.allproductWishcntPlus(productnum);
   }
   
   // �긽�뭹 李� �쑀臾�
   @Override
   public int selectPickCount(String username, String productnum) {
      return mapper.selectPickCount(username, productnum);
   }

   // 留덉씠�럹�씠吏��뿉 �긽�뭹 李� �궘�젣�븯湲�
   @Override
   public void deleteProductPick(String username, String productnum) {
      mapper.deleteProductPick(username, productnum);
   }

   // Allproduct �긽�뭹�뿉 李� -1 �븯湲�
   @Override
   public void allproductWishcntMinus(String productnum) {
      mapper.allproductWishcntMinus(productnum);
   }

   // �냽遺� �뙏濡쒖슦�븯湲�
   @Override
   public void InsertUsernameFollow(MyPageDTO MPdto) {
      mapper.InsertUsernameFollow(MPdto);
   }   

   // �냽遺� �뙏濡쒖슦�븯硫� userdetails�뿉 followers +1 �븯湲�
   @Override
   public void userdetailsUpdateFollowersPlus(String follow) {
      mapper.userdetailsUpdateFollowersPlus(follow);
   }   
   
   // 留덉씠�럹�씠吏� �냽遺� 援щ룆 �쑀臾�
   @Override
   public int selectFollowCount(String username, String follow) {
      return mapper.selectFollowCount(username, follow);
   }
   
   // �냽遺� �뙏濡쒖슦 痍⑥냼�븯湲�
   @Override
   public void deleteFollow(String username, String follow) {
      mapper.deleteFollow(username, follow);
   }
   
   // �냽遺� �뙏濡쒖슦 痍⑥냼�븯硫� userdetails�뿉 followers -1 �븯湲�
   @Override
   public void userdetailsUpdateFollowersMinus(String follow) {
      mapper.userdetailsUpdateFollowersMinus(follow);
   }   
   
   
   
   
   
   
   
   
   // FINISH
   
   // TEST

   
   
   
   
   
   
   
   
   
   // �긽�뭹 �젙蹂� �럹�씠吏�
   @Override
   public AllProductDTO selectProductInfo(String follow, String productnum) {
      return mapper.selectProductInfo(follow, productnum);
   }   
   
   // �긽�뭹 �삱由� �궗�엺�쓽 二쇱냼, �씠由�, �뙏濡쒖슦 李얘린   
   @Override
   public AllProductDTO selectProductNameAddressFollowers(String follow, String productnum) {
      return mapper.selectProductNameAddressFollowers(follow, productnum);
   }
   
   // �긽�뭹 �샃�뀡�뱾 媛��졇�삤湲�
   @Override
   public List<ProductDTO> selectProductOptionAll(String follow, String productnum) {
      return mapper.selectProductOptionAll(follow, productnum);
   }   
   
   // �긽�뭹 �궗吏� 媛��졇�삤湲�
   @Override
   public List<AllProductDTO> selectProductImagesAll(String follow, String productnum) {
      return mapper.selectProductImagesAll(follow, productnum);
   }   


   
   
   
   // TEST
   
   
   
   
   
   // �궡 �긽�젏 �럹�씠吏��뿉 �븘�슂�븳 �젙蹂대뱾 媛��졇�삤湲�
   @Override
   public ShopListDTO selectMyShop(String username) {
      return mapper.selectMyShop(username);
   }

   // �쑀���쓽 �긽�뭹�뱾 媛��졇�삤湲�
   @Override
   public List<AllProductDTO> selectUsernameProduct(String username) {
      return mapper.selectUsernameProduct(username);
   }

   // �씠由꾩씠�옉 �긽�뭹 媛��졇�삤�뒗 肄붾뱶
   @Override
   public AllProductDTO selectAllProductPlusNameFollowers(String productnum) {
      return mapper.selectAllProductPlusNameFollowers(productnum);
   }

   // �긽�뭹 �벑濡앺븳 �긽�젏 area1 二쇱냼   
   @Override
   public AreaDTO selectArea1Address(int area1) {
      return mapper.selectArea1Address(area1);
   }

   // �긽�뭹 �벑濡앺븳 �긽�젏 area2 二쇱냼
   @Override
   public AreaDTO selectArea2Address(int area1, int area2) {
      return mapper.selectArea2Address(area1, area2);
   }

   // �긽�뭹 �샃�뀡 媛��졇�삤湲�   
   @Override
   public List<ProductDTO> selectProductOption(String follow, String productnum) {
      return mapper.selectProductOption(follow, productnum);
   }
   
   // �긽�뭹 �씠誘몄� 媛��졇�삤湲�
   @Override
   public List<ImagesDTO> selectProductImages(String follow, String productnum) {
      return mapper.selectProductImages(follow, productnum);
   }



   // �긽�뭹 �쟾泥대ぉ濡� 蹂닿린   
   @Override
   public List<AllProductDTO> selectAllproduct() {
      return mapper.selectAllproduct();
   }








   
   @Override
   public String CheckMyShop(String username) {
      return mapper.CheckMyShop(username);
   }

   // �긽�뭹 由щ럭�벐湲�
   @Override
   public int reviewInsert(List<MultipartFile> filelist, ReviewsDTO Rdto, String path) {
      int check = 0;
      int files = 0;
      for (MultipartFile file : filelist) {
         if(!file.getOriginalFilename().equals("")) {
            files++;
         }
      }
      Rdto.setImagecount(files);
      check = mapper.reviewInsert(Rdto);  
      return check;
   }

   @Override
   public int ReviewsimagesInsert(List<MultipartFile> filelist, String path, String username, String productnum) {
      int result = 0;
      // int boardnum = mapper.maxNum();
      // String productnum = "111111";
      for (int i = 1; i <= filelist.size(); i++) {
         MultipartFile file = filelist.get(i-1);
         String filename = file.getOriginalFilename();
         if(!filename.equals("")) {
            String ext = filename.substring(filename.lastIndexOf("."));
            filename = "P_"+productnum+"_"+i+ext;
            File copy = new File(path+filename);
            result = mapper.ReviewsimagesInsert(filename, username, productnum);
            try {
               file.transferTo(copy);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      }
      return result;      
   }   
   
   // �긽�뭹 由щ럭 媛��졇�삤湲�
   @Override
   public List<ReviewsDTO> selectReviewsAll(String follow, String productnum) {
      return mapper.selectReviewsAll(follow, productnum);
   }   

   // �긽�뭹 由щ럭 �닔
   @Override
   public int selectReviewsCount(String productnum) {
      return mapper.selectReviewsCount(productnum);
   }   
   
   // �옣諛붽뎄�땲 �떞湲�
   @Override
   public void insertShopping(MyPageDTO MPdto) {
      mapper.insertShopping(MPdto);
   }

   // shoplist�뿉�꽌 �궡 �긽�젏 �젙蹂� �궘�젣�븯湲�   
   @Override
   public void deleteShoplist(String username) {
      mapper.deleteShoplist(username);
   }

   // productnum 媛믪쑝濡� 由щ럭 �뀒�씠釉� �궘�젣�븯湲�
   @Override
   public void dropReviewsTable(String productnum) {
      mapper.dropReviewsTable(productnum);
   }

   // username�쑝濡� productnum 媛��졇�삤湲�
   @Override
   public List<String> selectUsernameProductnum(String username) {
      return mapper.selectUsernameProductnum(username);
   }

   @Override
   public void allproductUpdateContent(AllProductDTO dto) {
      mapper.allproductUpdateContent(dto);
   }


   @Override
   public void deleteProductOption(String username, String productnum) {
      mapper.deleteProductOption(username, productnum);
   }

   @Override
   public void deleteAllproduct(String username, String productnum) {
      mapper.deleteAllproduct(username, productnum);
   }

   // �긽�뭹 �뙋留ㅼ떆 �옱怨� �뾽�뜲�씠�듃
   @Override
   public List<MyPageDTO> selectMypage3(String username) {
      return mapper.selectMypage3(username);
   }

   @Override
   public void updateProductCount(String follow, int cnt, String optionnum) {
      mapper.updateProductCount(follow, cnt, optionnum);
   }

   // �긽�뭹 議고쉶�닔 利앷�
   @Override
   public void updateReadcntPlus(String productnum) {
      mapper.updateReadcntPlus(productnum);
   }

   // �긽�뭹 議고쉶�븳 �쑀���젙蹂� �꽔湲�
   @Override
   public void productReadcntInsert(String username, String productnum) {
      mapper.productReadcntInsert(username, productnum);
   }

   // �삤�뒛 �긽�뭹 議고쉶�븳 �쑀�� 李얘린
   @Override
   public int selectTodayReadcntUsername(String username, String productnum, String todaydate) {
      return mapper.selectTodayReadcntUsername(username, productnum, todaydate);
   }

   @Override
   public void allproductDeleteMyUsername(String username) {
      mapper.allproductDeleteMyUsername(username);
   }

   @Override
   public void dropUsernameProduct(String username) {
      mapper.dropUsernameProduct(username);
   }

   @Override
   public List<ReviewsDTO> selectReviewsUsername(String productnum) {
      return mapper.selectReviewsUsername(productnum);
   }

   @Override
   public List<ReviewsDTO> ReviewsInfoFinal(String productnum, String follow, String usernames, String formatdate) {
      return mapper.ReviewsInfoFinal(productnum, follow, usernames, formatdate);
   }

   @Override
   public void myReviewsDelete(String productnum, String myName) {
      mapper.myReviewsDelete(productnum, myName);
   }

	@Override
	public AllProductDTO findProductInfoToReadcnt(String productnum) {
		return mapper.findProductInfoToReadcnt(productnum);
	}

	public String[] todayInfo() {
		Date date = new Date();
		String formatDate = simpleDateFormat.format(date);
		String[] today = formatDate.split("/");
		return today;
	}
	
	@Override
	public void findMyShopInfo(Model model, String username) {
		ShopListDTO dto = mapper.findMyShopInfo(username);
		int todayPrice = 0;
		if(mapper.findTodayPriceCnt(username) > 0) {
			todayPrice = mapper.findTodayPrice(username);
		}
		model.addAttribute("shopDTO", dto);
		model.addAttribute("todayPrice", todayPrice);
	}

	@Override
	public void findMySellChart(Model model, String username) {
		int cnt = mapper.isUserSell(username);
		String shopname = mapper.findByShopInfo(username).getShopname();
		List<String> sellDateList = Collections.EMPTY_LIST;
		ArrayList<Integer> sellPriceList = new ArrayList<Integer>();
		if(cnt > 0) {
			sellDateList = mapper.userSellDateList(username);
			productMap.put("username", username);
			for (String selldate : sellDateList) {
				productMap.put("selldate", selldate);
				int total = mapper.findSellDatePrice(productMap);
				sellPriceList.add(total);
			}
		}
		model.addAttribute("cnt", cnt);
		model.addAttribute("shopname", shopname);
		model.addAttribute("sellDateList", sellDateList);
		model.addAttribute("sellPriceList", sellPriceList);
	}

	@Override
	public void deleteReviewImages(String productnum, String myName, String path) {
		String username = mapper.findRealUsername(myName).getUsername();
		List<ImagesDTO> list = Collections.EMPTY_LIST;
		productMap.put("username", username);
		productMap.put("productnum", productnum);
		int cnt = mapper.findReviewImageCnt(productMap);
		if(cnt > 0) {
			list = mapper.findReviewImages(productMap);
			for (ImagesDTO dto : list) {
				File file = new File(path+dto.getFilename());
				if(file.isFile()) {
					file.delete();
				}
			}
			mapper.deleteReviewImages(productMap);
		}
	}
	
	   // 상품 내리기 (grade 200)
	   @Override
	   public void updateProductGrade200(String productnum) {
	      mapper.updateProductGrade200(productnum);
	   }

	   // allProduct username , productnum
	   @Override
	   public List<AllProductDTO> allProductSelect() {
	      return mapper.allProductSelect();
	   }
	   
	   // 상품 재고수 0이면 등급 200
	   @Override
	   public void updateAllProductGrade200(String productnum, String usernames) {
	      mapper.updateAllProductGrade200(productnum, usernames);
	   }	
	
	
}


   /* 媛��옣 理쒓렐�쓽 �긽�뭹踰덊샇媛� �뾾�쑝硫� 移댁슫�듃0 �엳�쑝硫� �긽�뭹 �닔
   @Override
   public int selectLastProductNumCnt(String keyword, String username) {
      return mapper.selectLastProductNumCnt(keyword, username);
   }
   
   // �긽�뭹踰덊샇媛� �엳�쑝硫� �긽�뭹踰덊샇 戮묒븘�삤�뒗嫄�
   @Override
   public List<AllProductDTO> selectLastProductNum(String keyword) {
      return mapper.selectLastProductNum(keyword);
   }
   
   @Override
   public List<String> selectOptionNum(String keyword, String username) {
      return mapper.selectOptionNum(keyword, username);
   }   
   */