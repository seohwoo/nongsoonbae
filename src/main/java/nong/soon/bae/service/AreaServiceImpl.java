package nong.soon.bae.service;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.repository.AreaMapper;


@Service
public class AreaServiceImpl implements AreaService{

	@Autowired
	private AreaMapper mapper;
	@Autowired
	private HashMap<String, String> areaMap;
	
	@Override
	public List<AreaDTO> arealistmore(int area1) {
		return mapper.arealistmore(area1);
	}

	@Override
	public void areaDetail(Model model, String area1, String area2) {
		areaMap.put("area1", area1);
		areaMap.put("area2", area2);
		int cnt = mapper.productCnt(areaMap);
		List<AllProductDTO> list = Collections.EMPTY_LIST;
		if(cnt>0) {
			list=mapper.areaDetail(area1, area2);
		}
		model.addAttribute("list",list);
		model.addAttribute("cnt",cnt);	
		}

	
	@Override
	public int count() {
		return mapper.count();
	}
	
	/*
	@Override
	public void arealist(int pageNum, Model model) {
		int pageSize = 7;
	    int startRow = (pageNum - 1) * pageSize + 1;
	    int endRow = pageNum * pageSize;
	    int count = mapper.count();
	    List<AreaDTO> list = Collections.EMPTY_LIST;
	    if(count > 0 ) {
	    	/*arealistMap.put("start", startRow);
	    	arealistMap.put("end", endRow); 
	    	list = mapper.arealist(areaMap);	
	    }
	    model.addAttribute("list",list);
	    model.addAttribute("count",count);
	    model.addAttribute("pageNum",pageNum);
	    model.addAttribute("pageSize",pageSize);
	    int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
        int startPage = (int)(pageNum/10)*10+1;
		int pageBlock=10;
        int endPage = startPage + pageBlock-1;
        if (endPage > pageCount) {
			endPage = pageCount;
        }				
        model.addAttribute("pageCount",pageCount);
        model.addAttribute("startPage",startPage);
        model.addAttribute("pageBlock",pageBlock);
        model.addAttribute("endPage",endPage);
	}

	*/
}
