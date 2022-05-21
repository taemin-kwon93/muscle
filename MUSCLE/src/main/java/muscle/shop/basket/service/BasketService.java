package muscle.shop.basket.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import muscle.common.common.CommandMap;

public interface BasketService {
	
	
	//��ٱ��� ��ü����Ʈ �˻�
	List<Map<String, Object>> basketList(CommandMap commandMap) throws Exception;

	//��ٱ��� ��������
	void basketModify(CommandMap commandMap, HttpServletRequest request) throws Exception;

	//��ٱ��� ���̺��� ����
	void basketDelete(CommandMap commandMap, HttpServletRequest request) throws Exception;

	//��ٱ��� ��ü����
	void basketAllDelete(CommandMap commandMap, HttpServletRequest request) throws Exception;
	
	//��ٱ��Ͽ��� ���û�ǰ �ֹ��� �˻� 
	List<Map<String, Object>> basketSelectList(CommandMap commandMap, HttpServletRequest request) throws Exception;
	
}
