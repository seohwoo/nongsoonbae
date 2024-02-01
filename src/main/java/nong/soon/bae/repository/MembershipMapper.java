package nong.soon.bae.repository;

import java.util.HashMap;
import java.util.List;

import nong.soon.bae.bean.ChartDTO;

public interface MembershipMapper {
	
	
	public int findDeatilChartCnt(HashMap<String, String> map);
	public List<ChartDTO> findDeatilChart(HashMap<String, String> map);
	public int maxAvgPrice(HashMap<String, String> map);
}
