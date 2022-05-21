package muscle.admin.aorder.service;

import java.util.List;
import java.util.Map;

import muscle.common.common.CommandMap;

public interface AdminOrdersService {
	List<Map<String, Object>> selectAdminOrdersList(Map<String, Object> commandMap) throws Exception;
	
	List<Map<String, Object>> order_admin_a(CommandMap map) throws Exception; // �ֹ�/���-����Ʈ�ʱ�ȭ��
	
	List<Map<String, Object>> order_detail(CommandMap commandMap) throws Exception; //���� - �ֹ���

	List<Map<String, Object>> order_detail_sub(CommandMap commandMap) throws Exception;//���� - �ֹ� detail��
	
	void order_state(CommandMap map) throws Exception; // �ֹ�/���- �ֹ����º���
	
	void order_state_ex(CommandMap commandMap) throws Exception; // �ֹ�/��� - �����ȣ �Է�
	
	List<Map<String, Object>> as_admin_list(CommandMap commandMap) throws Exception; // ��ȯ.ȯ��.AS ��û��� 
	
	void as_cancle_a(CommandMap commandMap) throws Exception; // ��ȯ.ȯ��.AS ��û ���
	
	void as_ok_a(CommandMap commandMap) throws Exception; // as_list ��ȯ.ȯ��.AS (1)�űԿ�û ó��
	
}
