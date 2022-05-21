package muscle.admin.acs.service;

import java.util.List;
import java.util.Map;

public interface AcsService {
	List<Map<String, Object>> acsBoardList(Map<String, Object> map)throws Exception; //����Ʈ
	Map<String, Object> acsDetail(Map<String, Object> map)throws Exception;	 //������
	void insertAcs(Map<String, Object> map)throws Exception; //�� �ۼ�
	Map<String, Object> updateAcsForm(Map<String, Object> map)throws Exception; //���� ��
	void updateAcs(Map<String, Object> map)throws Exception; //�����Ϸ�
	void deleteAcs(Map<String, Object> map)throws Exception; //����
	List<Map<String, Object>> allList(Map<String, Object> map)throws Exception; //��ü ����Ʈ
	List<Map<String, Object>> notice(Map<String, Object> map)throws Exception; //���� ����Ʈ
	List<Map<String, Object>> event(Map<String, Object> map)throws Exception; //�̺�Ʈ ����Ʈ
	List<Map<String, Object>> SearchAcs(Map<String, Object> map)throws Exception; //�˻�
	
	//faq	
	List<Map<String, Object>> faqBoardList(Map<String, Object> map)throws Exception; //����Ʈ
	void insertFaq(Map<String, Object> map)throws Exception; //�� �ۼ�
	Map<String, Object> updateFaqForm(Map<String, Object> map)throws Exception; //���� ��
	void updateFaq(Map<String, Object> map)throws Exception; //�����Ϸ�
}
