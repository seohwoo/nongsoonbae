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
   

   // �뜝�럡�� �뜝�럩逾좑옙逾녑뜝占� �뤆�룊�삕�뜝�럩二у뜝�럩沅롧뼨�먯삕
   @Override
   public String selectMyName(String username) {
      return mapper.selectMyName(username);
   }   
   
   // �뜝�럡�� �뜝�럡留믣뜝�럩�젍 �뜝�럩�젧�솻洹⑥삕 �뜝�럥苡삣슖�돦鍮쒒뇡占썹뼨�먯삕
   @Override
   public void shopListInsert(ShopListDTO SLdto) {
      mapper.shopListInsert(SLdto);
   }
   
   // �뜝�럡�� �뜝�럡留믣뜝�럩�젍 �뜝�럥占쎈���삕占쎈턄占쎈눀�뜝占� 嶺뚮씭�뒧獄��뼅臾얍뜝占�
   @Override
   public void createProduct(String username) {
      mapper.createProduct(username);
   }

   // �뇖�궠�샑占쎈�ㅿ옙�뫁伊볩옙遊� �뜝�룞�삕占쎄껀熬곣뫁泥�
   @Override
   public List<ProductCategoryDTO> selectCate1() {
      return mapper.selectCate1();
   }

   // �뇖�궠�샑占쎈�ㅿ옙�뫁伊볩옙遊� 繞벿살탮占쎈�뗰옙紐닷뜝占�   
   @Override
   public List<ProductCategoryDTO> selectCate2(int cate1) {
      return mapper.selectCate2(cate1);
   }

   // �뇖�궠�샑占쎈�ㅿ옙�뫁伊볩옙遊� �뜝�럥爰뽳옙寃ヨ쥈�뫁泥�
   @Override
   public List<ProductCategoryDTO> selectCate3(int cate1, int cate2) {
      return mapper.selectCate3(cate1, cate2);
   }   
   
   //�뇖�궠�샑占쎈�ㅿ옙�뫁伊볩옙遊� �뜝�럥堉듿뜝�럩留꾣뤆�룊�삕 �뤆�룊�삕�뜝�럩二у뜝�럩沅롧뼨�먯삕
   @Override
   public ProductCategoryDTO selectCate4(int cate1, int cate2, int cate3) {
      return mapper.selectCate4(cate1, cate2, cate3);
   }
   
   // �뜝�럡留믣뜝�럥占쏙옙 �뜝�럥苡삣슖�돦鍮쒒뇡占썹뼨�먯삕
   @Override
   public void productInsert(AllProductDTO APdto) {
      mapper.productInsert(APdto);
   }

   // �뜝�럡留믣뜝�럥占쏙옙 �뜝�럥苡삣슖�돦鍮쒒뇡占� 嶺뚯쉳�궞占쎈쐩�뜝�럩踰� productnum 占쎈쨨占쎈뿫由��뼨�먯삕
   @Override
   public List<AllProductDTO> selectAllProductLastProductNum(String username) {
      return mapper.selectAllProductLastProductNum(username);
   }   

   // �뜝�럡留믣뜝�럥占쏙옙 �뜝�럥苡삣슖�돦鍮쒒뇡占� �뜝�럥瑜� �뜝�럡留믣뜝�럥占쏙옙 占쎈뎨占쎈맧�쑏 �뜝�럥占쎈���삕占쎈턄占쎈눀�뜝占� 嶺뚮씭�뒧獄��뼅臾얍뜝占�
   @Override
   public void createReviews(String createReviewsProductnum) {
      mapper.createReviews(createReviewsProductnum);
   }   
   
   // �뜝�럡留믣뜝�럩�젍 �썒�슣�닔占쎄틬 �뤆�룊�삕�뜝�럩二у뜝�럩沅롥뜝�럥裕� 占쎄턀�겫�뼔援�
   @Override
   public String selectAddress(String username) {
      return mapper.selectAddress(username);
   }   

   // area1 �뤆�룊�삕 �뤆�룊�삕�뜝�럩二у뜝�럩沅롥뜝�럥裕� 占쎄턀�겫�뼔援�
   @Override
   public int selectArea1(String area1Address) {
      return mapper.selectArea1(area1Address);
   }

   // area2 �뤆�룊�삕 �뤆�룊�삕�뜝�럩二у뜝�럩沅롥뜝�럥裕� 占쎄턀�겫�뼔援�
   @Override
   public int selectArea2(String area2Address, int area1) {
      return mapper.selectArea2(area1, area2Address);
   }
   
   // �뜝�럡留믣뜝�럥占쏙옙 �뜝�럥苡삣슖�돦鍮쒒뇡占� �뜝�럥瑜� �뜝�럩逾졿쾬�꼶梨룟뜝占� �뜝�럡�맜�뼨�먯삕
   @Override
   public void imagesInsert(ImagesDTO Idto) {
      mapper.imagesInsert(Idto);
   }

   // �뜝�럡留믣뜝�럥占쏙옙 �뜝�럥苡삣슖�돦鍮쒒뇡占� �뜝�럥瑜� username_product�뜝�럥�뱺 �뜝�럩湲욕뜝�럥占썩댙�삕獄�占� �뜝�럡�맜�뼨�먯삕
   @Override
   public void optionInsert(ProductDTO Pdto) {
      mapper.optionInsert(Pdto);
   }

   // �뜝�럡留믣뜝�럥占쏙옙 嶺뚢돦裕됮뇡占썹뼨�먯삕
   @Override
   public void InsertProductPick(MyPageDTO MPdto) {
      mapper.InsertProductPick(MPdto);
   }

   // �뜝�럡留믣뜝�럥占쎈콈�삕�굢占� 嶺뚢댙�삕 +1 �뜝�럥由��뼨�먯삕
   @Override
   public void allproductWishcntPlus(String productnum) {
      mapper.allproductWishcntPlus(productnum);
   }
   
   // �뜝�럡留믣뜝�럥占쏙옙 嶺뚢댙�삕 �뜝�럩占쏙옙�닱�뜝占�
   @Override
   public int selectPickCount(String username, String productnum) {
      return mapper.selectPickCount(username, productnum);
   }

   // 嶺뚮씭�쐠占쎈턄�뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕�뜝�럥�뱺 �뜝�럡留믣뜝�럥占쏙옙 嶺뚢댙�삕 �뜝�럡�뀭�뜝�럩�젷�뜝�럥由��뼨�먯삕
   @Override
   public void deleteProductPick(String username, String productnum) {
      mapper.deleteProductPick(username, productnum);
   }

   // Allproduct �뜝�럡留믣뜝�럥占쎈콈�삕�굢占� 嶺뚢댙�삕 -1 �뜝�럥由��뼨�먯삕
   @Override
   public void allproductWishcntMinus(String productnum) {
      mapper.allproductWishcntMinus(productnum);
   }

   // �뜝�럥爰쀯옙寃ュ뜝占� �뜝�럥�냻�슖�돦裕뉛옙�뮡�뜝�럥由��뼨�먯삕
   @Override
   public void InsertUsernameFollow(MyPageDTO MPdto) {
      mapper.InsertUsernameFollow(MPdto);
   }   

   // �뜝�럥爰쀯옙寃ュ뜝占� �뜝�럥�냻�슖�돦裕뉛옙�뮡�뜝�럥由�嶺뚮〕�삕 userdetails�뜝�럥�뱺 followers +1 �뜝�럥由��뼨�먯삕
   @Override
   public void userdetailsUpdateFollowersPlus(String follow) {
      mapper.userdetailsUpdateFollowersPlus(follow);
   }   
   
   // 嶺뚮씭�쐠占쎈턄�뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 �뜝�럥爰쀯옙寃ュ뜝占� 占쎈쨨占쎈맧利� �뜝�럩占쏙옙�닱�뜝占�
   @Override
   public int selectFollowCount(String username, String follow) {
      return mapper.selectFollowCount(username, follow);
   }
   
   // �뜝�럥爰쀯옙寃ュ뜝占� �뜝�럥�냻�슖�돦裕뉛옙�뮡 占쎈퓛占쎈쳛占쎄틬�뜝�럥由��뼨�먯삕
   @Override
   public void deleteFollow(String username, String follow) {
      mapper.deleteFollow(username, follow);
   }
   
   // �뜝�럥爰쀯옙寃ュ뜝占� �뜝�럥�냻�슖�돦裕뉛옙�뮡 占쎈퓛占쎈쳛占쎄틬�뜝�럥由�嶺뚮〕�삕 userdetails�뜝�럥�뱺 followers -1 �뜝�럥由��뼨�먯삕
   @Override
   public void userdetailsUpdateFollowersMinus(String follow) {
      mapper.userdetailsUpdateFollowersMinus(follow);
   }   
   
   
   
   
   
   
   
   
   // FINISH
   
   // TEST

   
   
   
   
   
   
   
   
   
   // �뜝�럡留믣뜝�럥占쏙옙 �뜝�럩�젧�솻洹⑥삕 �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕
   @Override
   public AllProductDTO selectProductInfo(String follow, String productnum) {
      return mapper.selectProductInfo(follow, productnum);
   }   
   
   // �뜝�럡留믣뜝�럥占쏙옙 �뜝�럩沅욑옙逾녑뜝占� �뜝�럡�뀬�뜝�럩肉뷴뜝�럩踰� �썒�슣�닔占쎄틬, �뜝�럩逾좑옙逾녑뜝占�, �뜝�럥�냻�슖�돦裕뉛옙�뮡 嶺뚢돦堉먪뵳占�   
   @Override
   public AllProductDTO selectProductNameAddressFollowers(String follow, String productnum) {
      return mapper.selectProductNameAddressFollowers(follow, productnum);
   }
   
   // �뜝�럡留믣뜝�럥占쏙옙 �뜝�럩湲욕뜝�럥占썩댙�삕獄�占� �뤆�룊�삕�뜝�럩二у뜝�럩沅롧뼨�먯삕
   @Override
   public List<ProductDTO> selectProductOptionAll(String follow, String productnum) {
      return mapper.selectProductOptionAll(follow, productnum);
   }   
   
   // �뜝�럡留믣뜝�럥占쏙옙 �뜝�럡�뀬嶺뚯쉻�삕 �뤆�룊�삕�뜝�럩二у뜝�럩沅롧뼨�먯삕
   @Override
   public List<AllProductDTO> selectProductImagesAll(String follow, String productnum) {
      return mapper.selectProductImagesAll(follow, productnum);
   }   


   
   
   
   // TEST
   
   
   
   
   
   // �뜝�럡�� �뜝�럡留믣뜝�럩�젍 �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕�뜝�럥�뱺 �뜝�럥�닡�뜝�럩�뭵�뜝�럥由� �뜝�럩�젧�솻洹ｏ옙獄�占� �뤆�룊�삕�뜝�럩二у뜝�럩沅롧뼨�먯삕
   @Override
   public ShopListDTO selectMyShop(String username) {
      return mapper.selectMyShop(username);
   }

   // �뜝�럩占썲뜝�룞�삕�뜝�럩踰� �뜝�럡留믣뜝�럥占쎈콈�삕獄�占� �뤆�룊�삕�뜝�럩二у뜝�럩沅롧뼨�먯삕
   @Override
   public List<AllProductDTO> selectUsernameProduct(String username) {
      return mapper.selectUsernameProduct(username);
   }

   // �뜝�럩逾좑옙逾녘쥈�뫗逾졾뜝�럩�굚 �뜝�럡留믣뜝�럥占쏙옙 �뤆�룊�삕�뜝�럩二у뜝�럩沅롥뜝�럥裕� 占쎄턀�겫�뼔援�
   @Override
   public AllProductDTO selectAllProductPlusNameFollowers(String productnum) {
      return mapper.selectAllProductPlusNameFollowers(productnum);
   }

   // �뜝�럡留믣뜝�럥占쏙옙 �뜝�럥苡삣슖�돦鍮쒒뇡占� �뜝�럡留믣뜝�럩�젍 area1 �썒�슣�닔占쎄틬   
   @Override
   public AreaDTO selectArea1Address(int area1) {
      return mapper.selectArea1Address(area1);
   }

   // �뜝�럡留믣뜝�럥占쏙옙 �뜝�럥苡삣슖�돦鍮쒒뇡占� �뜝�럡留믣뜝�럩�젍 area2 �썒�슣�닔占쎄틬
   @Override
   public AreaDTO selectArea2Address(int area1, int area2) {
      return mapper.selectArea2Address(area1, area2);
   }

   // �뜝�럡留믣뜝�럥占쏙옙 �뜝�럩湲욕뜝�럥占쏙옙 �뤆�룊�삕�뜝�럩二у뜝�럩沅롧뼨�먯삕   
   @Override
   public List<ProductDTO> selectProductOption(String follow, String productnum) {
      return mapper.selectProductOption(follow, productnum);
   }
   
   // �뜝�럡留믣뜝�럥占쏙옙 �뜝�럩逾졿쾬�꼶梨룟뜝占� �뤆�룊�삕�뜝�럩二у뜝�럩沅롧뼨�먯삕
   @Override
   public List<ImagesDTO> selectProductImages(String follow, String productnum) {
      return mapper.selectProductImages(follow, productnum);
   }



   // �뜝�럡留믣뜝�럥占쏙옙 �뜝�럩�쓧嶺뚳퐢占쏙옙嫄졾슖�댙�삕 �솻洹ｋ뼬�뵳占�   
   @Override
   public List<AllProductDTO> selectAllproduct() {
      return mapper.selectAllproduct();
   }








   
   @Override
   public String CheckMyShop(String username) {
      return mapper.CheckMyShop(username);
   }

   // �뜝�럡留믣뜝�럥占쏙옙 占쎈뎨占쎈맧�쑏�뜝�럥苡븀뼨�먯삕
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
   
   // �뜝�럡留믣뜝�럥占쏙옙 占쎈뎨占쎈맧�쑏 �뤆�룊�삕�뜝�럩二у뜝�럩沅롧뼨�먯삕
   @Override
   public List<ReviewsDTO> selectReviewsAll(String follow, String productnum) {
      return mapper.selectReviewsAll(follow, productnum);
   }   

   // �뜝�럡留믣뜝�럥占쏙옙 占쎈뎨占쎈맧�쑏 �뜝�럥�빢
   @Override
   public int selectReviewsCount(String productnum) {
      return mapper.selectReviewsCount(productnum);
   }   
   
   // �뜝�럩�궋�뛾�룆�뼺占쎈윞�뜝�럥鍮� �뜝�럥堉뽫뼨�먯삕
   @Override
   public void insertShopping(MyPageDTO MPdto) {
      mapper.insertShopping(MPdto);
   }

   // shoplist�뜝�럥�뱺�뜝�럡�맋 �뜝�럡�� �뜝�럡留믣뜝�럩�젍 �뜝�럩�젧�솻洹⑥삕 �뜝�럡�뀭�뜝�럩�젷�뜝�럥由��뼨�먯삕   
   @Override
   public void deleteShoplist(String username) {
      mapper.deleteShoplist(username);
   }

   // productnum �뤆�룆占썬굦紐드슖�댙�삕 占쎈뎨占쎈맧�쑏 �뜝�럥占쎈���삕占쎈턄占쎈눀�뜝占� �뜝�럡�뀭�뜝�럩�젷�뜝�럥由��뼨�먯삕
   @Override
   public void dropReviewsTable(String productnum) {
      mapper.dropReviewsTable(productnum);
   }

   // username�뜝�럩紐드슖�댙�삕 productnum �뤆�룊�삕�뜝�럩二у뜝�럩沅롧뼨�먯삕
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

   // �뜝�럡留믣뜝�럥占쏙옙 �뜝�럥�냷嶺뚮씞�걠占쎈뻣 �뜝�럩�궨占썩뫅�삕 �뜝�럥�뵜�뜝�럥�몥�뜝�럩逾졾뜝�럥諭�
   @Override
   public List<MyPageDTO> selectMypage3(String username) {
      return mapper.selectMypage3(username);
   }

   @Override
   public void updateProductCount(String follow, int cnt, String optionnum) {
      mapper.updateProductCount(follow, cnt, optionnum);
   }

   // �뜝�럡留믣뜝�럥占쏙옙 �댖怨뚰�э옙�뤂�뜝�럥�빢 嶺뚯빘鍮볟뜝占�
   @Override
   public void updateReadcntPlus(String productnum) {
      mapper.updateReadcntPlus(productnum);
   }

   // �뜝�럡留믣뜝�럥占쏙옙 �댖怨뚰�э옙�뤂�뜝�럥由� �뜝�럩占썲뜝�룞�삕�뜝�럩�젧�솻洹⑥삕 �뜝�럡�맜�뼨�먯삕
   @Override
   public void productReadcntInsert(String username, String productnum) {
      mapper.productReadcntInsert(username, productnum);
   }

   // �뜝�럩沅롥뜝�럥裕� �뜝�럡留믣뜝�럥占쏙옙 �댖怨뚰�э옙�뤂�뜝�럥由� �뜝�럩占썲뜝�룞�삕 嶺뚢돦堉먪뵳占�
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

	// �긽�뭹 �궡由ш린 (grade 200)
	@Override
	public void updateProductGrade200(String productnum) {
		mapper.updateProductGrade200(productnum);
	}

	// allProduct username , productnum
	@Override
	public List<AllProductDTO> allProductSelect() {
		return mapper.allProductSelect();
	}
	
	// �긽�뭹 �옱怨좎닔 0�씠硫� �벑湲� 200
	@Override
	public void updateAllProductGrade200(String productnum, String usernames) {
		mapper.updateAllProductGrade200(productnum, usernames);

	}
	
}


   /* �뤆�룊�삕�뜝�럩�궋 嶺뚣끉裕꾬옙�젎�뜝�럩踰� �뜝�럡留믣뜝�럥占쎈같由곤옙�쐡占쎄퉰�뤆�룊�삕 �뜝�럥�뵪�뜝�럩紐든춯濡녹삕 �뇖�궠�샍占쎈뮧�뜝�럥諭�0 �뜝�럩肉녑뜝�럩紐든춯濡녹삕 �뜝�럡留믣뜝�럥占쏙옙 �뜝�럥�빢
   @Override
   public int selectLastProductNumCnt(String keyword, String username) {
      return mapper.selectLastProductNumCnt(keyword, username);
   }
   
   // �뜝�럡留믣뜝�럥占쎈같由곤옙�쐡占쎄퉰�뤆�룊�삕 �뜝�럩肉녑뜝�럩紐든춯濡녹삕 �뜝�럡留믣뜝�럥占쎈같由곤옙�쐡占쎄퉰 嶺뚮�⑹탳�뇡�꼻�삕占쎄텕�뜝�럥裕됪ㅀ袁ъ삕
   @Override
   public List<AllProductDTO> selectLastProductNum(String keyword) {
      return mapper.selectLastProductNum(keyword);
   }
   
   @Override
   public List<String> selectOptionNum(String keyword, String username) {
      return mapper.selectOptionNum(keyword, username);
   }   
   */