package muscle.member.login.controller;


import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import muscle.common.common.CommandMap;
import muscle.member.login.dao.UserVO;
import muscle.member.login.service.LoginService;
import muscle.member.login.service.MailService;


@Controller
public class LoginController {

	Logger log = Logger.getLogger(this.getClass());

	@Resource(name="mailService")
	private MailService mailService;
	
	@Inject
	private JavaMailSender mailSender;
	
	@Resource(name = "loginService")
	private LoginService loginService;
	/*
	 * @RequestMapping(value = "/member/openLoginForm.do") // loginForm.jsp public
	 * ModelAndView loginForm(HttpServletRequest request, Model model) throws
	 * Exception {
	 * 
	 * 
	 * 
	 * 
	 * //session HttpSession session = request.getSession();
	 * 
	 * System.out.println("session : " + session.getAttribute("session_MEMBER"));
	 * 
	 * if(session.getAttribute("session_MEMBER")!=null) { ModelAndView mv = new
	 * ModelAndView(); mv.setViewName("redirect:main/openMainList.do"); return mv; }
	 * else { ModelAndView mv = new ModelAndView("/member/loginForm");
	 * model.addAttribute("userVo", new UserVo()); return mv; } }
	 */
	
	@RequestMapping(value = "/member/openLoginForm.do") // loginForm.jsp
	public String loginForm(HttpServletRequest request, Model model) throws Exception {
		//session
		HttpSession session = request.getSession();
		System.out.println("session : " + session.getAttribute("session_MEMBER"));
		if(session.getAttribute("session_MEMBER")!=null) {
			return "redirect:main/openMainList.do";
		} else {
			model.addAttribute("userVO", new UserVO());
			return "member/loginForm";
		}
	}
	
	@RequestMapping(value = "/member/login.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login(@ModelAttribute @Valid UserVO vo, BindingResult result, CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("/member/login");
		String message = "";
		String url = "";
		
	if(result.hasErrors()) { // ????????? List??? ?????? 
		List<ObjectError> list = result.getAllErrors(); 
		for(ObjectError error : list ){
			System.out.println("login????????? ???????????? : " + error);
			}
		ModelAndView mv2 = new ModelAndView();
		mv2.setViewName("/member/loginForm"); 
	 
		
		return mv2; 
	 }
		
		HttpSession session = request.getSession();

		//????????? ?????????
		Map<String, Object> chk = loginService.login(commandMap.getMap());
		if (chk==null) { // ???????????? ????????? ???????????? ??????
			message = "?????? ???????????? ???????????? ????????????.";
		} else {
			if (chk.get("MEM_PW").equals(vo.getMEM_PW())) {
				session.setAttribute("session_MEM_ID", commandMap.get("MEM_ID"));
				session.setAttribute("session_MEMBER", chk);
				System.out.println("??????????????????" + session.getAttribute("session_MEM_ID"));
			} else { // if?????? ?????? sql??? ?????? ?????? ??????
				message = "??????????????? ?????? ????????????.";
			}
		}
		System.out.println(chk);
		mv.addObject("message",message);
		mv.addObject("url",url);
		 
		return mv;
	}
	

	
	@RequestMapping(value="/member/logout.do") // ????????????
    public ModelAndView logout(CommandMap commandMap, HttpServletRequest request) throws Exception {
       HttpSession session = request.getSession();
       if (session != null)
          session.invalidate();
       ModelAndView mv = new ModelAndView();
       mv.setViewName("redirect:/main/logoutSc.do");
       return mv;
    }
 
	 @RequestMapping(value="/main/logoutSc.do")
	 public ModelAndView openJoinForm(CommandMap commandMap)throws Exception{
	    ModelAndView mv = new ModelAndView("/member/logout");
	    
	    return mv;
	 }
	

	@RequestMapping(value = "/member/findId.do") // ????????? ?????? ?????? ???????????? ?????????
	public ModelAndView findId(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/member/findAccount");
		int ran = new Random().nextInt(900000) + 100000;
		
		mv.addObject("random",ran);
		
		return mv;
	}

	@RequestMapping(value = "/member/openFindIdResult.do", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/text; charset=utf8")

	public @ResponseBody String findIdResult(CommandMap commandMap) throws Exception {
		String id = String.valueOf(loginService.findId(commandMap.getMap()));
		
		if(id.equals("1")) {
			String findId = (String)(loginService.findIdWithEmail(commandMap.getMap()).get("MEM_ID"));
			System.out.println(findId);
			return findId;
			
		}else {
			return id;
		}
	}
	
	
	@RequestMapping(value = "/member/findPw.do") // ???????????? ?????? ?????? ???????????? ?????????
	public ModelAndView findPw(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/member/findAccount");
		int ran = new Random().nextInt(900000) + 100000;
		
		mv.addObject("random",ran);
		
		return mv;
	}
	
	@RequestMapping(value = "/member/openFindPwResult.do", method=RequestMethod.GET) //?????????????????? ?????????
	@ResponseBody
	public boolean findPwEmail(CommandMap commandMap,@RequestParam String MEM_ID, @RequestParam String MEM_EMAIL, @RequestParam int random, HttpServletRequest req) throws Exception {
		
		String emailCheck = String.valueOf(loginService.findPwWithEmail(commandMap.getMap()));
		System.out.println(emailCheck);
		if(emailCheck.equals("1")) {
			int ran = new Random().nextInt(900000) + 100000;
			   HttpSession session = req.getSession(true);
			   String authCode = String.valueOf(ran);
			   session.setAttribute("authCode", authCode);
			   session.setAttribute("random", random);
			   String subject = "muscle ???????????? ?????? ?????? ?????? ?????????.";
			   StringBuilder sb = new StringBuilder();
			   sb.append("????????? ?????? ??????????????? " + authCode + "?????????.");
			   
			   commandMap.put("MEM_ID", MEM_ID);
			   commandMap.put("MEM_EMAIL", MEM_EMAIL);
			   commandMap.put("authCode", authCode);
			   loginService.updateTempPw(commandMap.getMap());
			   return mailService.send(subject, sb.toString(),"chkch1991@gmail.com", MEM_EMAIL, null);
		}else {
			  return false;
		}
	}
}