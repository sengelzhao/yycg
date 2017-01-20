package yycg.base.action;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import yycg.base.pojo.vo.ActiveUser;
import yycg.base.process.context.Config;
import yycg.base.process.result.ResultUtil;
import yycg.base.process.result.SubmitResultInfo;
import yycg.base.service.UserService;

@Controller
public class LoginAction {
    @Autowired 
	UserService userservice;
	@RequestMapping("/login")
	public String login(Model model){
        return "/base/login";		
	}
	
	@RequestMapping("/loginsubmit")
	public @ResponseBody SubmitResultInfo loginsubmit(HttpSession session,String userid,String pwd,String validateCode) throws Exception{
		String validateCode_session = (String)session.getAttribute(validateCode);
		if(validateCode_session!=null&&!validateCode_session.equals(validateCode)){
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,113, null));
		}
		
		ActiveUser activeUser = userservice.checkUserInfo(userid, pwd);
		session.setAttribute(Config.ACTIVEUSER_KEY, activeUser);

		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(
				Config.MESSAGE, 107, new Object[] { "" }));
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:login.action";
	}
}
