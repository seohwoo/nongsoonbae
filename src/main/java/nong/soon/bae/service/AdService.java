package nong.soon.bae.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public interface AdService {
	public void myproduct(Model model,String username);
	
	public void myAd(Model model,String username);
	
	public void submitAd (String username, String adSelect,int daySelect,int price);
	public void adList (Model model);
	public void adEndSoon (Model model);
	public void adStart(String productnum, int days);
	public void updateStatus (String productnum);
	public void adEnd (int num,String username,String productnum);
	public void reUpdateStatus (String username,String productnum);
	public void adNo (String productnum, String username,int num);
	
}
