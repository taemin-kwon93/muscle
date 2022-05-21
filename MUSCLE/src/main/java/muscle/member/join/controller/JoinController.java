package muscle.member.join.controller;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import muscle.common.common.CommandMap;
import muscle.member.join.dao.JoinVO;
import muscle.member.join.service.JoinService;
import muscle.member.join.service.JoinValidator;
@Controller
public class JoinController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="joinService")
	private JoinService joinService;
	
	@Resource(name="joinVO")
	private JoinVO joinVO;
	
	@RequestMapping(value="/member/openJoinForm.do")
	public ModelAndView openJoinForm(CommandMap commandMap)throws Exception{
		ModelAndView mv = new ModelAndView("/member/joinForm");
		mv.addObject("joinVO", new JoinVO());
		return mv;
	}
	
	@RequestMapping(value="/member/insertJoin.do")
	public ModelAndView insertJoin(CommandMap commandMap,@ModelAttribute JoinVO joinVO, Errors errors, BindingResult result) throws Exception {
		ModelAndView mv = new ModelAndView();
		JoinValidator joinValidator = new JoinValidator();
		
		joinValidator.validate(joinVO, errors);
		if(errors.hasErrors()) {
			
			List<Errors> list1 = new ArrayList<Errors>();
			list1.add(errors);
			Iterator iter1 = list1.iterator();
			
			while(iter1.hasNext()) {
				System.out.println(iter1.next());
			}
			
			
			mv.addObject("joinVO", joinVO);
			mv.setViewName("/member/joinForm");
			return mv;
		}
	
		joinService.insertJoin(commandMap.getMap());
		mv.setViewName("redirect:/member/openLoginForm.do");
		return mv;
	}
	
	@RequestMapping(value = "/member/idCheck.do") //아이디 중복확인
	   public @ResponseBody String idCheck(CommandMap commandMap) throws Exception {
	     
	      System.out.println(commandMap.getMap());
	     
	      String idCheck = String.valueOf(joinService.selectIdCheck(commandMap.getMap()));
	     
	      System.out.println(idCheck);
	      return idCheck;
	   }
	
	@RequestMapping(value = "/member/nickCheck.do") //닉네임 중복확인
	   public @ResponseBody String nickCheck(CommandMap commandMap) throws Exception {
	     
	      System.out.println(commandMap.getMap());
	     
	      String nickCheck = String.valueOf(joinService.selectNickCheck(commandMap.getMap()));
	     
	      System.out.println(nickCheck);
	      return nickCheck;
	   }
	
	@RequestMapping(value="/member/openAgree.do") //이용약관 보여주기
	   public ModelAndView openAgree(CommandMap commandMap)throws Exception{
	      ModelAndView mv = new ModelAndView("/member/agree");
	     
	      return mv;
	   }
}