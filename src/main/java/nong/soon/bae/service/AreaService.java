package nong.soon.bae.service;
import org.springframework.ui.Model;

public interface AreaService {
	
	public void allproductlist(Model model,String sort,int pageNum);
	public void adallproductlist(Model model); //������ü��ǰ����Ʈ
	public void areaprodutlist (Model model,int areaNum, int pageNum,String area1,String sort);
	
	public void arealist(int areaNum,Model model);	
	public void adareaprodutlist (Model model,String area1); //area1 ����
}
