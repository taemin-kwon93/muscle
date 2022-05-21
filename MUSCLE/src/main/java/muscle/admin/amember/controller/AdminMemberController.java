package muscle.admin.amember.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import muscle.admin.amember.service.AdminMemberService;
import muscle.common.common.CommandMap;

@Controller
public class AdminMemberController {

	@Resource(name = "adminMemberService")
	private AdminMemberService adminMemberService;

	@RequestMapping(value = "/admin/openAdminMember.do")
	public ModelAndView openAdminMemberList(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/adminMemberList");
		System.out.println("openAdminMemberList 회원관리 실행");
		List<Map<String, Object>> list = adminMemberService.selectMemberList(commandMap.getMap());
		System.out.println("openAdminMemberList commandMap값 확인: " + commandMap.getMap());
		System.out.println("openAdminMemberList list값 확인: " + list);
		System.out.println("openAdminMemberList TOTAL_COUNT값 확인: " + list.get(0).get("TOTAL_COUNT"));
		mv.addObject("list", list);
		mv.addObject("TOTAL", list.get(0).get("TOTAL_COUNT"));
		return mv;
	}

	@RequestMapping(value = "/admin/selectMemberList.do")
	public ModelAndView selectMemberList(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
		System.out.println("selectMemberList jsonView 실행");
		System.out.println("selectMemberList commandMap값 확인: " + commandMap.getMap());
		List<Map<String, Object>> list = adminMemberService.selectMemberList(commandMap.getMap());
		System.out.println("selectMemberList list값 확인: " + list);
		System.out.println("selectMemberList TOTAL_COUNT값 확인: " + list.get(0).get("TOTAL_COUNT"));
		mv.addObject("list", list);
		mv.addObject("TOTAL", list.get(0).get("TOTAL_COUNT"));
		
		return mv;
	}



	@RequestMapping(value = "/admin/openselectMemberSearchList.do")
	public ModelAndView openselectMemberSearchList(CommandMap commandMap, @RequestParam(value = "key2", defaultValue = "") 
			String key2, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView("/admin/adminMemberSearch");
		request.setAttribute("key2", key2);
		List<Map<String, Object>> list = adminMemberService.selectMemberSearchList(commandMap.getMap(), key2);
		System.out.println("openselectMemberSearchList 검색어 확인 : " + key2);
		System.out.println("openselectMemberSearchList 메인->관리자페이지->'회원관리 검색실행'");
		System.out.println("openselectMemberSearchList commandMap값 확인: " + commandMap.getMap());
		System.out.println("openselectMemberSearchList list값 확인: "+list);
		mv.addObject("list", list);
		mv.addObject("key2", key2);

		return mv;
	}

	@RequestMapping(value="/admin/selectMemberSearchList.do") 
	public ModelAndView selectMemberSearchList(CommandMap commandMap, @RequestParam(value = "key2", defaultValue = "")
			String key2, HttpServletRequest request) throws Exception {

		ModelAndView mv = new ModelAndView("jsonView");
		List<Map<String, Object>> list = null;
		System.out.println("selectMemberSearchList 검색어 : " + key2);
		System.out.println("selectMemberSearchList commandMap값 확인: "+commandMap.getMap());
		list = adminMemberService.selectMemberSearchList(commandMap.getMap(), key2);
		System.out.println("selectMemberSearchList 가지고 온 개수 list.get(0).get('TOTAL_COUNT') 값 확인 : " + list.get(0).get("TOTAL_COUNT"));
		mv.addObject("list", list);
		mv.addObject("key2");
		
		if (list.size() > 0) {
			mv.addObject("TOTAL", list.get(0).get("TOTAL_COUNT"));
		} else {
			mv.addObject("TOTAL", 0);
		}
 
		return mv;
	}
	/*
	 * 21.10.31 원래의 selectMemberSearchList 코드
	 * 
	 * @RequestMapping(value="/admin/selectMemberSearchList.do") public ModelAndView
	 * selectMemberSearchList(CommandMap commandMap)throws Exception{ 
	 * ModelAndView mv = new ModelAndView("jsonView"); 
	 * System.out.println("AdminMemberController.selectMemberSearchList commandMap값 확인: "+commandMap.getMap()); 
	 * List<Map<String, Object>> list = adminMemberService.selectMemberSearchList(commandMap.getMap());
	 * mv.addObject("list", list); if(list.size()>0) { 
	 * mv.addObject("TOTAL", list.get(0).get("TOTAL_COUNT")); 
	 * } else { 
	 * mv.addObject("TOTAL", 0); 
	 * } return mv; 
	 * }
	 * 
	 * 기존 도전했던 방식
	 * 
	 * @RequestMapping(value="/admin/selectMemberSearchList.do") public ModelAndView
	 * selectMemberSearchList(CommandMap commandMap,
	 * 
	 * @RequestParam(value = "key", defaultValue = "") String key,
	 * HttpServletRequest request)throws Exception{ ModelAndView mv = new
	 * ModelAndView("/admin/adminMemberSearch"); System.out.
	 * println("AdminMemberController.selectMemberSearchList commandMap 값 확인 : " +
	 * commandMap.getMap()); List<Map<String, Object>> list = null;
	 * System.out.println("AdminMemberController.selectMemberSearchList key값 확인: "
	 * +key); list = adminMemberService.selectMemberSearchList(commandMap.getMap(),
	 * key);
	 * System.out.println("AdminMemberController.selectMemberSearchList list값 확인: "
	 * + list); mv.addObject("list", list); if(list.size()>0) {
	 * mv.addObject("TOTAL", list.get(0).get("TOTAL_COUNT")); }else {
	 * mv.addObject("TOTAL", 0); } return mv; }
	 * 
	 * 21.10.31 MainController에서 가지고온 코드
	 * @RequestMapping(value = "/main/openMainSearch.do")//header 검색 요청 public
	 * ModelAndView openMainSearch(CommandMap commandMap,
	 * 
	 * @RequestParam(value = "keyword", defaultValue = "") String keyword,
	 * HttpServletRequest request) // ���ο��� �˻��� ����Ʈ throws Exception {
	 * ModelAndView mv = new ModelAndView("/main/searchResult");
	 * 
	 * request.setAttribute("keyword", keyword);
	 * System.out.println("openMainSearch commandMap 값 확인 : " +
	 * commandMap.getMap()); System.out.println("검색어 확인 : " + keyword);
	 * mv.addObject("keyword"); mv.addObject("IDX", commandMap.getMap().get("IDX"));
	 * 
	 * String filePath_temp = request.getContextPath() + "/file/";
	 * mv.addObject("path", filePath_temp); request.setAttribute("path",
	 * filePath_temp); return mv; }
	 * 
	 */
	@RequestMapping(value = "/admin/openAdminMemberDetail.do")
	public ModelAndView openAdminMemberDetail(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/adminMemberDetail");
		Map<String, Object> map = adminMemberService.openAdminMemberDetail(commandMap.getMap());
		mv.addObject("map", map);
		return mv;
	}

	@RequestMapping(value = "/admin/ignoreAdminMember.do")
	public ModelAndView ignoreAdminMember(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin/openAdminMember.do");
		adminMemberService.ignoreAdminMember(commandMap.getMap());
		return mv;
	}

}