package muscle.admin.agoods.controller;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import muscle.admin.agoods.service.AgoodsService;
import muscle.common.common.CommandMap;
import muscle.common.common.SearchDTO;
import muscle.shop.goods.service.GoodsService;
@Controller
public class AgoodsController {
	
	@Resource(name="agoodsService")
	private AgoodsService agoodsService;
	
	 @Resource(name = "goodsService")
	   private GoodsService goodsService;
	
	/* @Resource(name="searchDTO")
	 private SearchDTO searchDTO; */
	
	@RequestMapping(value="/admin/openAdminGoods.do") //������ ��ǰ����Ʈ
	public ModelAndView openAdminGoodsList(CommandMap commandMap)throws Exception{
		ModelAndView mv = new ModelAndView("/admin/adminGoodsList");
		List<Map<String, Object>> list = agoodsService.openAgoodsList(commandMap.getMap());
		mv.addObject("list", list);
		
		return mv;
	}
	
	@RequestMapping(value="/admin/deleteAdminGoods.do") //��ǰ ����
	public ModelAndView deleteAdminGoods(CommandMap commandMap)throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/admin/openAdminGoods.do");
		agoodsService.deleteAdminGoods(commandMap.getMap());
	
		return mv;
	}
	
	@RequestMapping(value = "/admin/goodsModifyForm.do")
	public ModelAndView goodsModifyForm(CommandMap commandMap, HttpServletRequest request) throws Exception { // ��ǰ ������(������)
		ModelAndView mv = new ModelAndView("shop/goodsWrite");
		Map<String, Object> map = goodsService.selectGoodsDetail(commandMap.getMap(), request);
		System.out.println("������1=" + commandMap.getMap());
		System.out.println("������2=" + map);
		mv.addObject("map", map);
		mv.addObject("list", map.get("list"));
		mv.addObject("type", "modify");
		mv.addObject("title", "��ǰ ����");
		System.out.println("������3=" + map);
		return mv;
	}
	@RequestMapping(value = "/admin/goodsModify.do")
	public ModelAndView goodsModify(CommandMap commandMap, HttpServletRequest request) throws Exception { // ��ǰ �����Ϸ�(������)
		ModelAndView mv = new ModelAndView("redirect:/admin/openAdminGoods.do");
		System.out.println(commandMap.getMap());
		String GOODS_HASH = request.getParameter("HASH");
		commandMap.put("GOODS_IMG_THUM", request.getSession().getAttribute("GOODS_IMG_THUM"));
		commandMap.put("GOODS_HASH", GOODS_HASH);
		agoodsService.updateGoods(commandMap.getMap(), request);
		System.out.println("������ƮMap=" + commandMap);
		System.out.println("������ƮMap=" + commandMap.getMap());
		mv.addObject("IDX", commandMap.getMap().get("GOODS_NUM"));
		return mv;
	}
	
	@RequestMapping(value="/admin/SearchGoods.do")
	public ModelAndView searchTypeList(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/adminGoodsList");
		
		String searchType = request.getParameter("searchType").trim();
		String keyword = request.getParameter("keyword").trim();
		SearchDTO searchDTO = new SearchDTO();
		
			searchDTO.setSearchType(searchType);
			searchDTO.setKeyword("%" + keyword + "%");
			searchDTO.setReplaceKeyword(keyword);
	
		
		List<Map<String, Object>> list = agoodsService.searchTypeList(searchDTO);
		mv.addObject("list", list);
		mv.addObject("searchDTO", searchDTO);
		
		System.out.println(searchDTO.getSearchType());
		System.out.println(searchDTO.getKeyword());
		System.out.println(searchDTO.getReplaceKeyword());
		
		return mv;
	}
}