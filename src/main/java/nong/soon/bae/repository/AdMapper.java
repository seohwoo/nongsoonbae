package nong.soon.bae.repository;

import java.util.HashMap;
import java.util.List;

import nong.soon.bae.bean.AdDTO;
import nong.soon.bae.bean.AllProductDTO;

public interface AdMapper {
	public List<AllProductDTO> myproduct (String username);
	public List<AdDTO> myAd(String username);
	public int myAdCnt (String username);
	public void submitAd (HashMap map);
	public List<AdDTO> adList();
	public List<AdDTO> adEndSoon();	
	int adStart (HashMap map);
	int updateStatus (String productnum);
	int adEnd(HashMap map);
	int reUpdateStatus(HashMap map);
	public int endSoonCnt ();
	public int submitCnt ();
	int adNo(HashMap map);
	
	public List<AdDTO> checkAd(HashMap map);
	
}
