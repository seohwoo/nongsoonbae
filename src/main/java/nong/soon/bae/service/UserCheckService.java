package nong.soon.bae.service;


import org.springframework.ui.Model;

public interface UserCheckService {
	public void userlist(int pageNum , Model model); //일반 유저 목록
	public int userstop(String username); //유저 정지하기
	public void findUser(Model model, int pageNum, String userSearch); //검색 	
	public int blacklistInsert(String username, String reason); //정지 회원 블랙리스트 테이블에 저장 
	public void blacklist(int pageNum , Model model);//정지회원목록
	public int reuser(String username); //정지회원복구하기
	public int deleteblacklist (String username); //블랙리스트에서 제거하기 
	public void blackFindUser(Model model, int pageNum, String userSearch);//정지회원검색
	
	//~~여기까지 회원정지하기
	
	public void showCate(Model model); //카테고리 분류하기
	public int maxNum(); //대분류의 가장 큰 값 뽑기
	
	public int insertNewCate (int cateNum, String addCate); //대분류 카테고리 인서트
	public int addCateFile(String addCate, String realname); //대분류 사진 파일 인서트
	public int deleteCate (int cateNum, String addCate); //사진 저장 실패시 대분류 데이터 삭제
	
	public void showSubCate (Model model, int cate1Select); //중분류 목록 
	public int subMaxNum(int cate1Select); //해당하는 대분류의 가장 큰 중분류값
	
	public void showDetailCate(Model model,int cate1Select); //소분류 항목 목록
	public int subDetailMaxNum(int maxNum, int cate1Select); 
	public int deleteDetailCate (int detailNum, String addDetail); //사진 저장 실패시 소분류 데이터 삭제
	public int insertDetailCate(int cate1Select,int subMaxNum, int detailNum, String addDetail);//소분류 항목 추가하기
	public int addDetailFile (String realname, String addDetail); //소분류 사진 추가
	 
	
	public void showEtcCate (Model model,int cate1Select, int subMaxNum); //기타항목
	public String findEtcName(int cate1Select,int subMaxNum); //기타분류 이름 뽑기
	
	public int updateCateName (String newCateName,int cate1Select , int subMaxNum); //중분류 내용 업데이트
	public int updateEtcCate (int cate1Select, int etcNum, String etcName); //기타항목 새로 생성 
	
	
}
