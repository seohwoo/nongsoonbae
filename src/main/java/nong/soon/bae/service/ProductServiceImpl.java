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
   

   // 占쎄땀 占쎌뵠�뵳占� 揶쏉옙占쎌죬占쎌궎疫뀐옙
   @Override
   public String selectMyName(String username) {
      return mapper.selectMyName(username);
   }   
   
   // 占쎄땀 占쎄맒占쎌젎 占쎌젟癰귨옙 占쎈쾻嚥≪빜釉�疫뀐옙
   @Override
   public void shopListInsert(ShopListDTO SLdto) {
      mapper.shopListInsert(SLdto);
   }
   
   // 占쎄땀 占쎄맒占쎌젎 占쎈�믭옙�뵠�뇡占� 筌띾슢諭얏묾占�
   @Override
   public void createProduct(String username) {
      mapper.createProduct(username);
   }

   // 燁삳똾�믤�⑥쥓�봺 占쏙옙�겫袁⑥첒
   @Override
   public List<ProductCategoryDTO> selectCate1() {
      return mapper.selectCate1();
   }

   // 燁삳똾�믤�⑥쥓�봺 餓λ쵎�뀋�몴占�   
   @Override
   public List<ProductCategoryDTO> selectCate2(int cate1) {
      return mapper.selectCate2(cate1);
   }

   // 燁삳똾�믤�⑥쥓�봺 占쎈꺖�겫袁⑥첒
   @Override
   public List<ProductCategoryDTO> selectCate3(int cate1, int cate2) {
      return mapper.selectCate3(cate1, cate2);
   }   
   
   //燁삳똾�믤�⑥쥓�봺 占쎈뼊占쎌맄揶쏉옙 揶쏉옙占쎌죬占쎌궎疫뀐옙
   @Override
   public ProductCategoryDTO selectCate4(int cate1, int cate2, int cate3) {
      return mapper.selectCate4(cate1, cate2, cate3);
   }
   
   // 占쎄맒占쎈�� 占쎈쾻嚥≪빜釉�疫뀐옙
   @Override
   public void productInsert(AllProductDTO APdto) {
      mapper.productInsert(APdto);
   }

   // 占쎄맒占쎈�� 占쎈쾻嚥≪빜釉� 筌욊낱�뜎占쎌벥 productnum �뤃�뗫릭疫뀐옙
   @Override
   public List<AllProductDTO> selectAllProductLastProductNum(String username) {
      return mapper.selectAllProductLastProductNum(username);
   }   

   // 占쎄맒占쎈�� 占쎈쾻嚥≪빜釉� 占쎈르 占쎄맒占쎈�� �뵳�됰윮 占쎈�믭옙�뵠�뇡占� 筌띾슢諭얏묾占�
   @Override
   public void createReviews(String createReviewsProductnum) {
      mapper.createReviews(createReviewsProductnum);
   }   
   
   // 占쎄맒占쎌젎 雅뚯눘�꺖 揶쏉옙占쎌죬占쎌궎占쎈뮉 �굜遺얜굡
   @Override
   public String selectAddress(String username) {
      return mapper.selectAddress(username);
   }   

   // area1 揶쏉옙 揶쏉옙占쎌죬占쎌궎占쎈뮉 �굜遺얜굡
   @Override
   public int selectArea1(String area1Address) {
      return mapper.selectArea1(area1Address);
   }

   // area2 揶쏉옙 揶쏉옙占쎌죬占쎌궎占쎈뮉 �굜遺얜굡
   @Override
   public int selectArea2(String area2Address, int area1) {
      return mapper.selectArea2(area1, area2Address);
   }
   
   // 占쎄맒占쎈�� 占쎈쾻嚥≪빜釉� 占쎈르 占쎌뵠沃섎챷占� 占쎄퐫疫뀐옙
   @Override
   public void imagesInsert(ImagesDTO Idto) {
      mapper.imagesInsert(Idto);
   }

   // 占쎄맒占쎈�� 占쎈쾻嚥≪빜釉� 占쎈르 username_product占쎈퓠 占쎌긿占쎈�∽옙諭� 占쎄퐫疫뀐옙
   @Override
   public void optionInsert(ProductDTO Pdto) {
      mapper.optionInsert(Pdto);
   }

   // 占쎄맒占쎈�� 筌≪뮉釉�疫뀐옙
   @Override
   public void InsertProductPick(MyPageDTO MPdto) {
      mapper.InsertProductPick(MPdto);
   }

   // 占쎄맒占쎈�뱄옙肉� 筌∽옙 +1 占쎈릭疫뀐옙
   @Override
   public void allproductWishcntPlus(String productnum) {
      mapper.allproductWishcntPlus(productnum);
   }
   
   // 占쎄맒占쎈�� 筌∽옙 占쎌��눧占�
   @Override
   public int selectPickCount(String username, String productnum) {
      return mapper.selectPickCount(username, productnum);
   }

   // 筌띾뜆�뵠占쎈읂占쎌뵠筌욑옙占쎈퓠 占쎄맒占쎈�� 筌∽옙 占쎄텣占쎌젫占쎈릭疫뀐옙
   @Override
   public void deleteProductPick(String username, String productnum) {
      mapper.deleteProductPick(username, productnum);
   }

   // Allproduct 占쎄맒占쎈�뱄옙肉� 筌∽옙 -1 占쎈릭疫뀐옙
   @Override
   public void allproductWishcntMinus(String productnum) {
      mapper.allproductWishcntMinus(productnum);
   }

   // 占쎈꺗�겫占� 占쎈솋嚥≪뮇�뒭占쎈릭疫뀐옙
   @Override
   public void InsertUsernameFollow(MyPageDTO MPdto) {
      mapper.InsertUsernameFollow(MPdto);
   }   

   // 占쎈꺗�겫占� 占쎈솋嚥≪뮇�뒭占쎈릭筌롳옙 userdetails占쎈퓠 followers +1 占쎈릭疫뀐옙
   @Override
   public void userdetailsUpdateFollowersPlus(String follow) {
      mapper.userdetailsUpdateFollowersPlus(follow);
   }   
   
   // 筌띾뜆�뵠占쎈읂占쎌뵠筌욑옙 占쎈꺗�겫占� �뤃�됰즴 占쎌��눧占�
   @Override
   public int selectFollowCount(String username, String follow) {
      return mapper.selectFollowCount(username, follow);
   }
   
   // 占쎈꺗�겫占� 占쎈솋嚥≪뮇�뒭 �뿆�뫁�꺖占쎈릭疫뀐옙
   @Override
   public void deleteFollow(String username, String follow) {
      mapper.deleteFollow(username, follow);
   }
   
   // 占쎈꺗�겫占� 占쎈솋嚥≪뮇�뒭 �뿆�뫁�꺖占쎈릭筌롳옙 userdetails占쎈퓠 followers -1 占쎈릭疫뀐옙
   @Override
   public void userdetailsUpdateFollowersMinus(String follow) {
      mapper.userdetailsUpdateFollowersMinus(follow);
   }   
   
   
   
   
   
   
   
   
   // FINISH
   
   // TEST

   
   
   
   
   
   
   
   
   
   // 占쎄맒占쎈�� 占쎌젟癰귨옙 占쎈읂占쎌뵠筌욑옙
   @Override
   public AllProductDTO selectProductInfo(String follow, String productnum) {
      return mapper.selectProductInfo(follow, productnum);
   }   
   
   // 占쎄맒占쎈�� 占쎌궞�뵳占� 占쎄텢占쎌뿺占쎌벥 雅뚯눘�꺖, 占쎌뵠�뵳占�, 占쎈솋嚥≪뮇�뒭 筌≪뼐由�   
   @Override
   public AllProductDTO selectProductNameAddressFollowers(String follow, String productnum) {
      return mapper.selectProductNameAddressFollowers(follow, productnum);
   }
   
   // 占쎄맒占쎈�� 占쎌긿占쎈�∽옙諭� 揶쏉옙占쎌죬占쎌궎疫뀐옙
   @Override
   public List<ProductDTO> selectProductOptionAll(String follow, String productnum) {
      return mapper.selectProductOptionAll(follow, productnum);
   }   
   
   // 占쎄맒占쎈�� 占쎄텢筌욑옙 揶쏉옙占쎌죬占쎌궎疫뀐옙
   @Override
   public List<AllProductDTO> selectProductImagesAll(String follow, String productnum) {
      return mapper.selectProductImagesAll(follow, productnum);
   }   


   
   
   
   // TEST
   
   
   
   
   
   // 占쎄땀 占쎄맒占쎌젎 占쎈읂占쎌뵠筌욑옙占쎈퓠 占쎈툡占쎌뒄占쎈립 占쎌젟癰귣�諭� 揶쏉옙占쎌죬占쎌궎疫뀐옙
   @Override
   public ShopListDTO selectMyShop(String username) {
      return mapper.selectMyShop(username);
   }

   // 占쎌�占쏙옙占쎌벥 占쎄맒占쎈�뱄옙諭� 揶쏉옙占쎌죬占쎌궎疫뀐옙
   @Override
   public List<AllProductDTO> selectUsernameProduct(String username) {
      return mapper.selectUsernameProduct(username);
   }

   // 占쎌뵠�뵳袁⑹뵠占쎌삂 占쎄맒占쎈�� 揶쏉옙占쎌죬占쎌궎占쎈뮉 �굜遺얜굡
   @Override
   public AllProductDTO selectAllProductPlusNameFollowers(String productnum) {
      return mapper.selectAllProductPlusNameFollowers(productnum);
   }

   // 占쎄맒占쎈�� 占쎈쾻嚥≪빜釉� 占쎄맒占쎌젎 area1 雅뚯눘�꺖   
   @Override
   public AreaDTO selectArea1Address(int area1) {
      return mapper.selectArea1Address(area1);
   }

   // 占쎄맒占쎈�� 占쎈쾻嚥≪빜釉� 占쎄맒占쎌젎 area2 雅뚯눘�꺖
   @Override
   public AreaDTO selectArea2Address(int area1, int area2) {
      return mapper.selectArea2Address(area1, area2);
   }

   // 占쎄맒占쎈�� 占쎌긿占쎈�� 揶쏉옙占쎌죬占쎌궎疫뀐옙   
   @Override
   public List<ProductDTO> selectProductOption(String follow, String productnum) {
      return mapper.selectProductOption(follow, productnum);
   }
   
   // 占쎄맒占쎈�� 占쎌뵠沃섎챷占� 揶쏉옙占쎌죬占쎌궎疫뀐옙
   @Override
   public List<ImagesDTO> selectProductImages(String follow, String productnum) {
      return mapper.selectProductImages(follow, productnum);
   }



   // 占쎄맒占쎈�� 占쎌읈筌ｋ��걠嚥∽옙 癰귣떯由�   
   @Override
   public List<AllProductDTO> selectAllproduct() {
      return mapper.selectAllproduct();
   }








   
   @Override
   public String CheckMyShop(String username) {
      return mapper.CheckMyShop(username);
   }

   // 占쎄맒占쎈�� �뵳�됰윮占쎈쾺疫뀐옙
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
   
   // 占쎄맒占쎈�� �뵳�됰윮 揶쏉옙占쎌죬占쎌궎疫뀐옙
   @Override
   public List<ReviewsDTO> selectReviewsAll(String follow, String productnum) {
      return mapper.selectReviewsAll(follow, productnum);
   }   

   // 占쎄맒占쎈�� �뵳�됰윮 占쎈땾
   @Override
   public int selectReviewsCount(String productnum) {
      return mapper.selectReviewsCount(productnum);
   }   
   
   // 占쎌삢獄쏅떽�럡占쎈빍 占쎈뼖疫뀐옙
   @Override
   public void insertShopping(MyPageDTO MPdto) {
      mapper.insertShopping(MPdto);
   }

   // shoplist占쎈퓠占쎄퐣 占쎄땀 占쎄맒占쎌젎 占쎌젟癰귨옙 占쎄텣占쎌젫占쎈릭疫뀐옙   
   @Override
   public void deleteShoplist(String username) {
      mapper.deleteShoplist(username);
   }

   // productnum 揶쏅�れ몵嚥∽옙 �뵳�됰윮 占쎈�믭옙�뵠�뇡占� 占쎄텣占쎌젫占쎈릭疫뀐옙
   @Override
   public void dropReviewsTable(String productnum) {
      mapper.dropReviewsTable(productnum);
   }

   // username占쎌몵嚥∽옙 productnum 揶쏉옙占쎌죬占쎌궎疫뀐옙
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

   // 占쎄맒占쎈�� 占쎈솇筌띲끉�뻻 占쎌삺�⑨옙 占쎈씜占쎈쑓占쎌뵠占쎈뱜
   @Override
   public List<MyPageDTO> selectMypage3(String username) {
      return mapper.selectMypage3(username);
   }

   @Override
   public void updateProductCount(String follow, int cnt, String optionnum) {
      mapper.updateProductCount(follow, cnt, optionnum);
   }

   // 占쎄맒占쎈�� 鈺곌퀬�돳占쎈땾 筌앹빓占�
   @Override
   public void updateReadcntPlus(String productnum) {
      mapper.updateReadcntPlus(productnum);
   }

   // 占쎄맒占쎈�� 鈺곌퀬�돳占쎈립 占쎌�占쏙옙占쎌젟癰귨옙 占쎄퐫疫뀐옙
   @Override
   public void productReadcntInsert(String username, String productnum) {
      mapper.productReadcntInsert(username, productnum);
   }

   // 占쎌궎占쎈뮎 占쎄맒占쎈�� 鈺곌퀬�돳占쎈립 占쎌�占쏙옙 筌≪뼐由�
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


   /* 揶쏉옙占쎌삢 筌ㅼ뮄�젏占쎌벥 占쎄맒占쎈�배린�뜇�깈揶쏉옙 占쎈씨占쎌몵筌롳옙 燁삳똻�뒲占쎈뱜0 占쎌뿳占쎌몵筌롳옙 占쎄맒占쎈�� 占쎈땾
   @Override
   public int selectLastProductNumCnt(String keyword, String username) {
      return mapper.selectLastProductNumCnt(keyword, username);
   }
   
   // 占쎄맒占쎈�배린�뜇�깈揶쏉옙 占쎌뿳占쎌몵筌롳옙 占쎄맒占쎈�배린�뜇�깈 筌믩쵐釉섓옙�궎占쎈뮉椰꾬옙
   @Override
   public List<AllProductDTO> selectLastProductNum(String keyword) {
      return mapper.selectLastProductNum(keyword);
   }
   
   @Override
   public List<String> selectOptionNum(String keyword, String username) {
      return mapper.selectOptionNum(keyword, username);
   }   
   */