package muscle.shop.order.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import muscle.common.common.CommandMap;
import muscle.shop.order.dao.OrderDao;

@Service("orderService")
public class OrderServiceImpl implements OrderService{
Logger log = Logger.getLogger(this.getClass()); // �α�
	
	@Resource(name="orderDao") 
	private OrderDao orderDao;

	//�ֹ����������� �ʿ��� ���� �˻�
	@Override
	public Map<String, Object> orderMemberInfo(CommandMap commandMap, HttpServletRequest request) throws Exception {
		return (Map<String, Object>) orderDao.orderMemberInfo(commandMap);
	}
	
	//�ֹ����̺� �Է�
		@Override
		public void insertOrder(CommandMap commandMap, HttpServletRequest request) throws Exception {
			orderDao.insertOrder(commandMap);
		}
		
		//�Է��� �ֹ� ��ȣ�˻�(�ֹ��Ϸ� ���������� �ֹ���ȣ �����ֱ�)
		@Override public Map<String, Object> selectOrder(CommandMap commandMap, HttpServletRequest request) throws Exception { 
			return (Map<String, Object>) orderDao.selectOrder(commandMap); 
		}


}
