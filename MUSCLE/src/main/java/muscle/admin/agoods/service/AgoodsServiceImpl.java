package muscle.admin.agoods.service;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import muscle.admin.agoods.dao.AgoodsDAO;
import muscle.common.common.SearchDTO;
import muscle.shop.goods.dao.GoodsDao;
@Service("agoodsService")
public class AgoodsServiceImpl implements AgoodsService {
	
	@Resource(name="agoodsDAO")
	private AgoodsDAO agoodsDAO;
	
	@Resource(name="goodsDao")
	private GoodsDao goodsDao;
	
	public void deleteAdminGoods(Map<String, Object> map)throws Exception{
	      agoodsDAO.deleteAdminGoodsATT(map);
	      agoodsDAO.deleteAdminGoods(map);
	   }
	
	public List<Map<String, Object>> openAgoodsList(Map<String, Object> map)throws Exception{
		
		return agoodsDAO.openAgoodsList(map);
	}
	
	@Override
	   public void updateGoods(Map<String, Object> map, HttpServletRequest request) throws Exception { // ��ǰ ����
	      agoodsDAO.updateGoods(map); // ��ǰ���̺� ������Ʈ
	      agoodsDAO.attributeDelete(map); // ���� ��ǰ�� �ִ� �ɼǵ� ����
	      map.get("GOODS_SIZE");
	      map.get("GOODS_COLOR");
	      System.out.println("������ �÷�="+map.get("GOODS_SIZE"));
	      System.out.println("������ ������="+map.get("GOODS_COLOR"));
	     
	      String Size = map.get("GOODS_SIZE").toString();
	      String Color = map.get("GOODS_COLOR").toString();
	      String ColorList[] = Color.split(",");
	      String SizeList[] = Size.split(",");
	      System.out.println(ColorList.length);
	     
	      for(int i=0; i <=ColorList.length-1; i++) {
	         for(int j=0; j<=SizeList.length-1; j++) {
	            System.out.println("�迭�Դϴ�="+ColorList[i]+","+SizeList[j]);
	            map.put("GOODS_SIZE", SizeList[j]);
	            map.put("GOODS_COLOR", ColorList[i]);
	            goodsDao.goodsAttribute(map); // ��ǰ ������ ������ �÷� ������ �ٽ� ���
	         }
	      }
	}
	
	@Override
	public List<Map<String, Object>> searchTypeList(SearchDTO searchDTO) throws Exception {
		return agoodsDAO.searchTypeList(searchDTO);
	}
}