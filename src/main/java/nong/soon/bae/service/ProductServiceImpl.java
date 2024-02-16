package nong.soon.bae.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

   // 내 이름 가져오기
   @Override
   public String selectMyName(String username) {
      return mapper.selectMyName(username);
   }   
   
   // 내 상점 정보 등록하기
   @Override
   public void shopListInsert(ShopListDTO SLdto) {
      mapper.shopListInsert(SLdto);
   }
   
   // 내 상점 테이블 만들기
   @Override
   public void createProduct(String username) {
      mapper.createProduct(username);
   }

   // 카테고리 대분류
   @Override
   public List<ProductCategoryDTO> selectCate1() {
      return mapper.selectCate1();
   }

   // 카테고리 중분류   
   @Override
   public List<ProductCategoryDTO> selectCate2(int cate1) {
      return mapper.selectCate2(cate1);
   }

   // 카테고리 소분류
   @Override
   public List<ProductCategoryDTO> selectCate3(int cate1, int cate2) {
      return mapper.selectCate3(cate1, cate2);
   }   
   
   //카테고리 단위값 가져오기
   @Override
   public ProductCategoryDTO selectCate4(int cate1, int cate2, int cate3) {
      return mapper.selectCate4(cate1, cate2, cate3);
   }
   
   // 상품 등록하기
   @Override
   public void productInsert(AllProductDTO APdto) {
      mapper.productInsert(APdto);
   }

   // 상품 등록한 직후의 productnum 구하기
   @Override
   public List<AllProductDTO> selectAllProductLastProductNum(String username) {
      return mapper.selectAllProductLastProductNum(username);
   }   

   // 상품 등록할 때 상품 리뷰 테이블 만들기
   @Override
   public void createReviews(String createReviewsProductnum) {
      mapper.createReviews(createReviewsProductnum);
   }   
   
   // 상점 주소 가져오는 코드
   @Override
   public String selectAddress(String username) {
      return mapper.selectAddress(username);
   }   

   // area1 값 가져오는 코드
   @Override
   public int selectArea1(String area1Address) {
      return mapper.selectArea1(area1Address);
   }

   // area2 값 가져오는 코드
   @Override
   public int selectArea2(String area2Address, int area1) {
      return mapper.selectArea2(area1, area2Address);
   }
   
   // 상품 등록할 때 이미지 넣기
   @Override
   public void imagesInsert(ImagesDTO Idto) {
      mapper.imagesInsert(Idto);
   }

   // 상품 등록할 때 username_product에 옵션들 넣기
   @Override
   public void optionInsert(ProductDTO Pdto) {
      mapper.optionInsert(Pdto);
   }

   // 상품 찜하기
   @Override
   public void InsertProductPick(MyPageDTO MPdto) {
      mapper.InsertProductPick(MPdto);
   }

   // 상품에 찜 +1 하기
   @Override
   public void allproductWishcntPlus(String productnum) {
      mapper.allproductWishcntPlus(productnum);
   }
   
   // 상품 찜 유무
   @Override
   public int selectPickCount(String username, String productnum) {
      return mapper.selectPickCount(username, productnum);
   }

   // 마이페이지에 상품 찜 삭제하기
   @Override
   public void deleteProductPick(String username, String productnum) {
      mapper.deleteProductPick(username, productnum);
   }

   // Allproduct 상품에 찜 -1 하기
   @Override
   public void allproductWishcntMinus(String productnum) {
      mapper.allproductWishcntMinus(productnum);
   }

   // 농부 팔로우하기
   @Override
   public void InsertUsernameFollow(MyPageDTO MPdto) {
      mapper.InsertUsernameFollow(MPdto);
   }   

   // 농부 팔로우하면 userdetails에 followers +1 하기
   @Override
   public void userdetailsUpdateFollowersPlus(String follow) {
      mapper.userdetailsUpdateFollowersPlus(follow);
   }   
   
   // 마이페이지 농부 구독 유무
   @Override
   public int selectFollowCount(String username, String follow) {
      return mapper.selectFollowCount(username, follow);
   }
   
   // 농부 팔로우 취소하기
   @Override
   public void deleteFollow(String username, String follow) {
      mapper.deleteFollow(username, follow);
   }
   
   // 농부 팔로우 취소하면 userdetails에 followers -1 하기
   @Override
   public void userdetailsUpdateFollowersMinus(String follow) {
      mapper.userdetailsUpdateFollowersMinus(follow);
   }   
   
   
   
   
   
   
   
   
   // FINISH
   
   // TEST

   
   
   
   
   
   
   
   
   
   // 상품 정보 페이지
   @Override
   public AllProductDTO selectProductInfo(String follow, String productnum) {
      return mapper.selectProductInfo(follow, productnum);
   }   
   
   // 상품 올린 사람의 주소, 이름, 팔로우 찾기   
   @Override
   public AllProductDTO selectProductNameAddressFollowers(String follow, String productnum) {
      return mapper.selectProductNameAddressFollowers(follow, productnum);
   }
   
   // 상품 옵션들 가져오기
   @Override
   public List<ProductDTO> selectProductOptionAll(String follow, String productnum) {
      return mapper.selectProductOptionAll(follow, productnum);
   }   
   
   // 상품 사진 가져오기
   @Override
   public List<AllProductDTO> selectProductImagesAll(String follow, String productnum) {
      return mapper.selectProductImagesAll(follow, productnum);
   }   


   
   
   
   // TEST
   
   
   
   
   
   // 내 상점 페이지에 필요한 정보들 가져오기
   @Override
   public ShopListDTO selectMyShop(String username) {
      return mapper.selectMyShop(username);
   }

   // 유저의 상품들 가져오기
   @Override
   public List<AllProductDTO> selectUsernameProduct(String username) {
      return mapper.selectUsernameProduct(username);
   }

   // 이름이랑 상품 가져오는 코드
   @Override
   public AllProductDTO selectAllProductPlusNameFollowers(String productnum) {
      return mapper.selectAllProductPlusNameFollowers(productnum);
   }

   // 상품 등록한 상점 area1 주소   
   @Override
   public AreaDTO selectArea1Address(int area1) {
      return mapper.selectArea1Address(area1);
   }

   // 상품 등록한 상점 area2 주소
   @Override
   public AreaDTO selectArea2Address(int area1, int area2) {
      return mapper.selectArea2Address(area1, area2);
   }

   // 상품 옵션 가져오기   
   @Override
   public List<ProductDTO> selectProductOption(String follow, String productnum) {
      return mapper.selectProductOption(follow, productnum);
   }
   
   // 상품 이미지 가져오기
   @Override
   public List<ImagesDTO> selectProductImages(String follow, String productnum) {
      return mapper.selectProductImages(follow, productnum);
   }



   // 상품 전체목록 보기   
   @Override
   public List<AllProductDTO> selectAllproduct() {
      return mapper.selectAllproduct();
   }








   
   @Override
   public String CheckMyShop(String username) {
      return mapper.CheckMyShop(username);
   }

   // 상품 리뷰쓰기
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
   
   // 상품 리뷰 가져오기
   @Override
   public List<ReviewsDTO> selectReviewsAll(String follow, String productnum) {
      return mapper.selectReviewsAll(follow, productnum);
   }   

   // 상품 리뷰 수
   @Override
   public int selectReviewsCount(String productnum) {
      return mapper.selectReviewsCount(productnum);
   }   
   
   // 장바구니 담기
   @Override
   public void insertShopping(MyPageDTO MPdto) {
      mapper.insertShopping(MPdto);
   }

   // shoplist에서 내 상점 정보 삭제하기   
   @Override
   public void deleteShoplist(String username) {
      mapper.deleteShoplist(username);
   }

   // productnum 값으로 리뷰 테이블 삭제하기
   @Override
   public void dropReviewsTable(String productnum) {
      mapper.dropReviewsTable(productnum);
   }

   // username으로 productnum 가져오기
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

   // 상품 판매시 재고 업데이트
   @Override
   public MyPageDTO selectMypage3(String username) {
      return mapper.selectMypage3(username);
   }

   @Override
   public void updateProductCount(String follow, int cnt) {
      mapper.updateProductCount(follow, cnt);
   }

   // 상품 조회수 증가
   @Override
   public void updateReadcntPlus(String productnum) {
      mapper.updateReadcntPlus(productnum);
   }

   // 상품 조회한 유저정보 넣기
   @Override
   public void productReadcntInsert(String username, String productnum) {
      mapper.productReadcntInsert(username, productnum);
   }

   // 오늘 상품 조회한 유저 찾기
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
   public List<String> selectReviewsUsername(String productnum) {
      return mapper.selectReviewsUsername(productnum);
   }

   @Override
   public List<ReviewsDTO> ReviewsInfoFinal(String productnum, String follow, String usernames) {
      return mapper.ReviewsInfoFinal(productnum, follow, usernames);
   }

   @Override
   public void myReviewsDelete(String productnum, String myName) {
      mapper.myReviewsDelete(productnum, myName);
   }

	@Override
	public AllProductDTO findProductInfoToReadcnt(String productnum) {
		return mapper.findProductInfoToReadcnt(productnum);
	}
	
}


   /* 가장 최근의 상품번호값 없으면 카운트0 있으면 상품 수
   @Override
   public int selectLastProductNumCnt(String keyword, String username) {
      return mapper.selectLastProductNumCnt(keyword, username);
   }
   
   // 상품번호가 있으면 상품번호 뽑아오는거
   @Override
   public List<AllProductDTO> selectLastProductNum(String keyword) {
      return mapper.selectLastProductNum(keyword);
   }
   
   @Override
   public List<String> selectOptionNum(String keyword, String username) {
      return mapper.selectOptionNum(keyword, username);
   }   
   */