package muscle.shop.goods.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import muscle.common.common.CommandMap;
import muscle.common.dao.AbstractDAO;

@Repository("goodsDao")
public class GoodsDao extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> newGoodsList(Map<String, Object> map) throws Exception { // �ֽ� ��ǰ ����Ʈ

		return (List<Map<String, Object>>) newGoodsList("goods.newGoodsList", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> bestGoodsList(Map<String, Object> map) throws Exception { // ����Ʈ ��ǰ ����Ʈ

		return (List<Map<String, Object>>) bestGoodsList("goods.bestGoodsList", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> cateGoodsList(Map<String, Object> map) throws Exception { // ī�װ� �� ��ǰ ����Ʈ
		System.out.println("ī�װ�����Ʈ" + map);
		return (List<Map<String, Object>>) selectPagingList("goods.cateGoodsList", map);
	}
	
	public void insertGoods(Map<String, Object> map) throws Exception { // ��ǰ ���
		insert("goods.goodsInsert", map);
	}
	
	public void goodsAttribute(Map<String, Object> map) throws Exception { // ��ǰ�ɼ� ���
		insert("goods.attributeInsert", map);
	}
	
	public void insertGoodsThumbnail(Map<String, Object> map) throws Exception { // ��ǰ ����� �̹��� ���
		update("goods.updateGoodsThumbnail", map);
	}
	
	public void insertFile(Map<String, Object> map) throws Exception { // ��ǰ �̹������� ���
		insert("goods.insertFile", map);
	}
	
	public void insertGoodsLike(Map<String, Object> map) throws Exception { // ��ǰ �����Ͽ��� ���ƿ� ���
		System.out.println("���ƿ�" + map);
		insert("goods.insertGoodsLike", map);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectGoodsDetail(Map<String, Object> map) throws Exception { // ��ǰ ������ ������ ��������
		return (Map<String, Object>) selectOne("goods.selectGoodsDetail", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectGoodsAtt(Map<String, Object> map) throws Exception { // ��ǰ�ɼ� �÷� ������ �迭 ��������
		return (Map<String, Object>) selectOne("goods.selectGoodsAtt", map);
	}
	
	public void goodsHitCnt(Map<String, Object> map) throws Exception { // ��ȸ�� ����
		System.out.println("goodsHitCntDao=" + map);
		update("goods.goodsReadCntUp", map);
	}
	
	public void insertBasket(Map<String, Object> map) throws Exception { // ��ǰ �����Ͽ��� ��ٱ��� ���
		System.out.println("��ٱ����߰�=" + map);
		insert("goods.insertBasket", map);
	}
	
	public void gumeListDelete(Map<String, Object> map) throws Exception { // ���� ����Ʈ �ʱ�ȭ
		System.out.println("���Ÿ���Ʈ ����:"+map);
		delete("goods.gumeListDelete", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectBasketNo(Map<String, Object> map) throws Exception { // ��ٱ��� ��������ȣ ��������
		System.out.println("selectBasketNo" + map);
		return (List<Map<String, Object>>) selectList("goods.selectBasketNo", map);
	}
	
}
