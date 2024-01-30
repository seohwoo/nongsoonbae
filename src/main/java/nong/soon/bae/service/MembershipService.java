package nong.soon.bae.service;

import org.springframework.ui.Model;

public interface MembershipService {

	public void findDetailChart(Model model, String year, String month, String name, String value);
}
