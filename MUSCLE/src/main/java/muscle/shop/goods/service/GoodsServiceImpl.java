package muscle.shop.goods.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import muscle.common.common.CommandMap;
import muscle.common.util.FileUtils;
import muscle.shop.goods.dao.GoodsDao;


@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
	
	Logger log = Logger.getLogger(this.getClass()); // �α�
	
	@Resource(name="goodsDao") 
	private GoodsDao goodsDao;
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;

	@Override
	public List<Map<String, Object>> newGoodsList(Map<String, Object> map) throws Exception { // �ֽ� ��ǰ ����Ʈ 
		
		return goodsDao.newGoodsList(map);
	}

	@Override
	public List<Map<String, Object>> bestGoodsList(Map<String, Object> map) throws Exception { // ����Ʈ ��ǰ ����Ʈ
		
		return goodsDao.bestGoodsList(map);
	}

	@Override
	public List<Map<String, Object>> cateGoodsList(Map<String, Object> map, String GOODS_CATEGORY2) throws Exception { // ī�װ��� ��ǰ ����Ʈ
		map.put("GOODS_CATEGORY2", GOODS_CATEGORY2);
		System.out.println(GOODS_CATEGORY2);
		return goodsDao.cateGoodsList(map);
	}
	
	@Override
	public void insertGoods(Map<String, Object> map, HttpServletRequest request) throws Exception { // ��ǰ���
		
		
		// ��ǰ���� ��� ���� ����
		goodsDao.insertGoods(map);
		System.out.println("****12132* " + map);
		
		
		map.get("GOODS_SIZE");
		map.get("GOODS_COLOR");
		System.out.println("������ �÷�="+map.get("GOODS_COLOR"));
		System.out.println("������ ������="+map.get("GOODS_SIZE"));
		
		String Size = map.get("GOODS_SIZE").toString();
		String Color = map.get("GOODS_COLOR").toString();
		String ColorList[] = Color.split(",");
		String SizeList[] = Size.split(",");
		System.out.println(ColorList.length);
		
		for(int i=0; i <=ColorList.length-1; i++) { // ��ǰ��Ͻ� ������ ������ �÷� �ϳ��ϳ��� ��ǰ�ɼ����̺� �������
			for(int j=0; j<=SizeList.length-1; j++) {
				System.out.println("�迭�Դϴ�="+ColorList[i]+","+SizeList[j]);
				map.put("GOODS_SIZE", SizeList[j]);
				map.put("GOODS_COLOR", ColorList[i]);
				goodsDao.goodsAttribute(map);
				
			}
		}
		
		System.out.println("�ɼǰ�="+map);
		
	}
	
	@Override
	public Map<String, Object> selectGoodsDetail(Map<String, Object> map, HttpServletRequest request) throws Exception { // ��ǰ ������
		System.out.println("map1111111="+map.get("IDX"));
		
		
		if(map.get("IDX").getClass().getName().equals("java.lang.String")){  // PK���� �Ϲ� ��Ʈ������ ���� ��
			Map<String,Object> map1 = new HashMap<String,Object>();
			map1.put("IDX", map.get("IDX"));
			goodsDao.goodsHitCnt(map1); //��ȸ��
		}else { // PK���� �迭�� �Ѿ� ���� �� 
			String[] Goods_No = (String[])map.get("IDX");
			map.put("IDX", Goods_No[0]);
			goodsDao.goodsHitCnt(map); //��ȸ��
		}
		
		System.out.println("map="+map);
		Map<String, Object> resultMap = goodsDao.selectGoodsDetail(map);

		return resultMap;
	}
	
	@Override
	public Map<String, Object> selectGoodsAtt(Map<String, Object> map) throws Exception { // ��ǰ �󼼺��� �÷��� ������ ��������(�迭�� ������)
		
		Map<String, Object> resultMap = goodsDao.selectGoodsAtt(map);
		
		return resultMap;
	}
	
	@Override
	public void insertBasket(Map<String, Object> map, HttpServletRequest request) throws Exception { // ��ǰ�����Ͽ��� ��ٱ��� �߰�
		map.remove("resultList");
		System.out.println("���񽺸�"+map);
		goodsDao.insertBasket(map);

	}
	
	@Override
	public void gumeListDelete(Map<String, Object> map) throws Exception {
		goodsDao.gumeListDelete(map);
		
	}
	
	@Override
	public List<Map<String, Object>> selectBasketNo(Map<String, Object> map) throws Exception { // ��ٱ��� PK�� ��������
		
		return goodsDao.selectBasketNo(map);
	}
	
}
