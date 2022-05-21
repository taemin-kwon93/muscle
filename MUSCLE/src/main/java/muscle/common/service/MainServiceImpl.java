package muscle.common.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import muscle.common.dao.MainDAO;

@Service("mainService")
public class MainServiceImpl implements MainService {
	Logger log=Logger.getLogger(this.getClass());
	
	@Resource(name="mainDAO")
	private MainDAO mainDAO;

	@Override
	public List<Map<String, Object>> selectMainList(Map<String, Object> map) throws Exception {
		return mainDAO.selectMainList(map);
	}
	
	@Override
	public List<Map<String, Object>> selectNoticeList(Map<String, Object> map) throws Exception {
	   return mainDAO.selectNoticeList(map);
	}

	@Override
	public List<Map<String, Object>> mainSearch(Map<String, Object> map, String keyword) throws Exception { // ���� �˻� 
		map.put("keyword", keyword);
		return mainDAO.mainSearch(map);
	}
	@Override
	public List<Map<String, Object>> mainSearchDeatail(Map<String, Object> map, String keyword, String[] category1Arr,
			String[] category2Arr, String[] sizeArr, String[] colorArr) throws Exception {

		map.put("keyword", keyword);
		map.put("category1Arr", category1Arr);
		map.put("category2Arr", category2Arr);
		map.put("sizeArr", sizeArr);
		map.put("colorArr", colorArr);
		return mainDAO.mainSearchDetail(map);
	}
	
}