package muscle.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("mainDAO")
public class MainDAO extends AbstractDAO {
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectMainList(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>)selectList("main.selectMainList", map);
		}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectNoticeList(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>)selectList("main.selectNoticeList", map);
		}	

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> mainSearch(Map<String, Object> map) throws Exception { // ���ο��� �˻�
		System.out.println("mainSearch" + map);
		return (List<Map<String, Object>>) selectPagingList("main.mainSearch", map);
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> mainSearchDetail(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectPagingList("main.mainSearchDetail", map);
	}
}
