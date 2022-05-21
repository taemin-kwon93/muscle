package muscle.shop.basket.service;

import java.util.List;

import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import muscle.common.common.CommandMap;
import muscle.shop.basket.dao.BasketDao;
import muscle.shop.goods.dao.GoodsDao;




@Service("basketService")
public class BasketServiceImpl implements BasketService{
	
Logger log = Logger.getLogger(this.getClass()); // �α�
	
	@Resource(name="basketDao") 
	private BasketDao basketDao;

	@Override
	public List<Map<String, Object>> basketList(CommandMap commandMap) throws Exception {
		return (List<Map<String, Object>>) basketDao.basketList(commandMap);
	}

	@Override
	public void basketModify(CommandMap commandMap, HttpServletRequest request) throws Exception {
		basketDao.basketModify(commandMap);
		
	}

	@Override
	public void basketDelete(CommandMap commandMap, HttpServletRequest request) throws Exception {
		basketDao.basketDelete(commandMap);
		
	}

	@Override
	public void basketAllDelete(CommandMap commandMap, HttpServletRequest request) throws Exception {
		basketDao.basketAllDelete(commandMap);
	}
	

	@Override
	public List<Map<String, Object>> basketSelectList(CommandMap commandMap, HttpServletRequest request) throws Exception {
		return (List<Map<String, Object>>) basketDao.basketSelectList(commandMap);
	}
}
