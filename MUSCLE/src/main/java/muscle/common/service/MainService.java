package muscle.common.service;

import java.util.List;
import java.util.Map;

public interface MainService {
	
	List<Map<String, Object>> selectMainList(Map<String, Object> commandMap) throws Exception;
	List<Map<String, Object>> selectNoticeList(Map<String, Object> commandMap) throws Exception;
	List<Map<String, Object>> mainSearch(Map<String, Object> map, String keyword) throws Exception; // 메인검색
	
	List<Map<String, Object>> mainSearchDeatail(Map<String, Object> map, String keyword, String[] category1Arr, String[] category2Arr, String[] sizeArr, String[] colorArr) throws Exception; 

}
