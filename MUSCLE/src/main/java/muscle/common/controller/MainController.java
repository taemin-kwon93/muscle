package muscle.common.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import muscle.common.common.CommandMap;
import muscle.common.dao.SearchDetailVO;
import muscle.common.service.MainService;

@Controller
public class MainController{
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="mainService")
	private MainService mainService;
	
	@RequestMapping(value="/main/openMainList.do")
	public ModelAndView openMainList(Map<String,Object> commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/main/main");
		List<Map<String,Object>> Nlist = mainService.selectNoticeList(commandMap);
		mv.addObject("Nlist", Nlist);
		
		List<Map<String,Object>> list = mainService.selectMainList(commandMap);
		mv.addObject("list", list);
		return mv;
		}
	
	@RequestMapping(value = "/main/openMainSearch.do")
	public ModelAndView openMainSearch(CommandMap commandMap,
			@ModelAttribute SearchDetailVO searchDetailVO,
			@RequestParam(value = "keyword", defaultValue = "") String keyword, HttpServletRequest request) // 메인에서 검색시 리스트
			throws Exception {
		
		
		ModelAndView mv = new ModelAndView("/main/searchResult");
		ModelAndView mv2 = new ModelAndView("/main/searchResult2");
		String[] category1Arr = request.getParameterValues("category1");
		String[] category2Arr = request.getParameterValues("category2");
		String[] sizeArr = request.getParameterValues("size");
		String[] colorArr = request.getParameterValues("color");
		
		request.setAttribute("keyword", keyword);
		System.out.println("카테고리 검색확인=" + commandMap.getMap());
		System.out.println("검색키워드=" + keyword);
		mv.addObject("keyword");
		mv.addObject("IDX", commandMap.getMap().get("IDX"));

		String filePath_temp = request.getContextPath() + "/file/";
		mv.addObject("path", filePath_temp);
		request.setAttribute("path", filePath_temp);
		
		
		if(category1Arr != null || category2Arr != null || sizeArr != null || colorArr != null) {
			
			List<Map<String, Object>> detailList = mainService.mainSearchDeatail(commandMap.getMap(), keyword, category1Arr, category2Arr, sizeArr, colorArr);
			
			if(category1Arr != null) {
				for(String checkbox : category1Arr) {
					System.out.println("category1Arr 값 체크 : " + checkbox);
				}
			}
			mv2.addObject("detailList", detailList);
			mv2.addObject("searchDetailVO", searchDetailVO);
			
			return mv2;
			}
		
		return mv;

	}
	
	
	@RequestMapping(value="/main/mainSearch.do")
	public ModelAndView mainSearch(CommandMap commandMap,
			@RequestParam(value = "keyword", defaultValue = "") String keyword, HttpServletRequest request) // 占쏙옙占싸울옙占쏙옙 占싯삼옙占쏙옙 占쏙옙占쏙옙트 占쏙옙占싱쏙옙
			throws Exception {

		ModelAndView mv = new ModelAndView("jsonView");
		List<Map<String, Object>> list = null;

		System.out.println("mainSearch : " + keyword);

		list = mainService.mainSearch(commandMap.getMap(), keyword);

		System.out.println("mainSearch list.get(0).get('TOTAL_COUNT') 값 확인: " + list.get(0).get("TOTAL_COUNT"));

		list.get(0);
		if (list.size() != 0) {
			list.get(0).get("");
		}

		mv.addObject("list", list);
		if (list.size() > 0) {
			mv.addObject("TOTAL", list.get(0).get("TOTAL_COUNT"));
		} else {
			mv.addObject("TOTAL", 0);
		}

		return mv;
	}
	
}
