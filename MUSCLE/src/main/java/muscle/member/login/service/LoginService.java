package muscle.member.login.service;


import java.util.Map;

public interface LoginService {
	public Map<String, Object> login(Map<String, Object> map) throws Exception;
	
	public Map<String, Object> findIdWithEmail(Map<String, Object> map) throws Exception;

	public int findId(Map<String, Object> map) throws Exception;
	
	public int findPwWithEmail(Map<String, Object> map) throws Exception;

	public void updateTempPw(Map<String,Object> map) throws Exception;
}

