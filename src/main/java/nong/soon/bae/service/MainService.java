package nong.soon.bae.service;

import org.springframework.ui.Model;

public interface MainService {

	public void seasonCategory(Model model, int categoryNum);
	public void detailSeasonCategory(Model model, String cate1, String cate2, String cate3);
	public void showChart(Model model, String cate1, String cate2, String cate3);
	public void showCategory(Model model, String cate1, String cate2, String cate3, int categoryNum);
	public void cateMenu(Model model);
	public void findProduct(Model model, String userSearch, int searchNum);
	public void findAdProduct (Model model, String userSearch);
	public boolean isMembership(boolean isMembership, String username);
}
