package nong.soon.bae.service;

import org.springframework.ui.Model;

public interface MainService {

	public void seasonCategory(Model model, int categoryNum);
	public void detailSeasonCategory(Model model, String cate1, String cate2, String cate3);
	public void showChart(Model model, String cate1, String cate2, String cate3);
}
