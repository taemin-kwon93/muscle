package muscle.member.myinfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import muscle.common.dao.AbstractDAO;

@Repository("myInfoDAO")
public class MyInfoDAO extends AbstractDAO{
   
   public Map<String, Object> selectMyInfo(Map<String, Object> map)throws Exception{
      return (Map<String, Object>) selectOne ("myInfo.selectMyInfo", map); //���������� �г��� �����ֱ�
   }
   
   public Map<String, Object> updateMyInfoForm(Map<String, Object> map)throws Exception{
      return (Map<String, Object>) selectOne ("myInfo.selectMyInfoModifyForm", map); //���������� ���� �� �����ֱ�
   }
   
   public void updateMyInfo(Map<String, Object> map)throws Exception{
      update("myInfo.updateMyInfo", map);//ȸ������ ����
   }
   
   public void deleteMyInfo(Map<String, Object> map)throws Exception{
      update("myInfo.deleteMyInfo", map);//ȸ��Ż�� �츮�� ������ �ƴ� �÷� N -> Y�� ���, update
   }
   
	
   public List<Map<String, Object>> openMyBoardList(Map<String, Object> map) throws Exception{ 
	   return (List<Map<String, Object>>)selectList("myInfo.selectMyBoardList", map); 
   }
}