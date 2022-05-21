package muscle.member.login.dao;

import java.util.Map;
import org.springframework.stereotype.Repository;
import muscle.common.dao.AbstractDAO;

@Repository("loginDAO")
public class LoginDAO extends AbstractDAO{
	@SuppressWarnings("unchecked")
	public Map<String, Object>selectId(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("login.selectId", map);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> findIdWithEmail(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("login.findIdWithEmail", map);
	}
	
	public int findId(Map<String, Object> map) throws Exception {
		return (Integer)selectOne("login.findId", map);
	}

	
	public int findPwWithEmail(Map<String, Object> map) throws Exception {
		return (Integer)selectOne("login.findPwWithEmail", map);
	}

	public void updateTempPw(Map<String,Object> map) throws Exception {
		update("login.updateTempPw",map);
	}
}
