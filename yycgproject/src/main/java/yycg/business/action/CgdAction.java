package yycg.business.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import yycg.base.pojo.po.Dictinfo;
import yycg.base.pojo.vo.ActiveUser;
import yycg.base.process.context.Config;
import yycg.base.process.result.ResultInfo;
import yycg.base.process.result.ResultUtil;
import yycg.base.process.result.SubmitResultInfo;
import yycg.base.service.SystemConfigService;
import yycg.business.pojo.vo.YycgdCustom;
import yycg.business.pojo.vo.YycgdQueryVo;
import yycg.business.service.CgdService;
import yycg.business.service.impl.YycgdServiceImpl;
import yycg.util.MyUtil;

@Controller
@RequestMapping("/cgd")
public class CgdAction {
	@Autowired
	private CgdService cgdService;
	@Autowired
	private SystemConfigService systemConfigService;
	
  @RequestMapping("/addcgd")	
  public String addcgd(HttpSession httpSession,Model model) throws Exception{
	  ActiveUser activeUser = (ActiveUser)httpSession.getAttribute(Config.ACTIVEUSER_KEY);
	  String sysmc = activeUser.getSysmc();
	  String yycgdmc = sysmc + MyUtil.getDate()+"采购单";
	  model.addAttribute("yycgdmc", yycgdmc);
	  String year = MyUtil.get_YYYY(MyUtil.getDate());
	  model.addAttribute("year",year);
	  
	  return "/business/cgd/addcgd";	  
  }
   @RequestMapping("/addcgdsubmit")
   public  @ResponseBody SubmitResultInfo  addcgdsubmit(HttpSession session,String year,YycgdQueryVo yycgdQueryVo) throws Exception{
	   ActiveUser activeUser = (ActiveUser)session.getAttribute(Config.ACTIVEUSER_KEY);
	   String useryyid =activeUser.getSysid();
	   
	   String yycgdid = cgdService.insertYycgd(useryyid, year, yycgdQueryVo.getYycgdCustom());
	   ResultInfo resultInfo =ResultUtil.createSuccess(Config.MESSAGE,906, null);
	   resultInfo.getSysdata().put("yycgdid", yycgdid);
	   return ResultUtil.createSubmitResult(resultInfo);
   }
   @RequestMapping("/editcgd")
   public String editcgd(Model model,String id)throws Exception{
		List<Dictinfo> cgztlist = systemConfigService.findDictinfoByType("011");
		List<Dictinfo> jyztlist = systemConfigService.findDictinfoByType("003");
		model.addAttribute("cgztlist", cgztlist);
		model.addAttribute("jyztlist", jyztlist);
        YycgdCustom yycgdCustom = cgdService.findYycgdById(id);
        model.addAttribute("yycgd",yycgdCustom);
	   
	   return "/business/cgd/editcgd";
   }
}
