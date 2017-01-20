package yycg.business.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import yycg.base.pojo.vo.ActiveUser;
import yycg.base.pojo.vo.PageQuery;
import yycg.base.process.context.Config;
import yycg.base.process.result.DataGridResultInfo;
import yycg.base.process.result.ExceptionResultInfo;
import yycg.base.process.result.ResultInfo;
import yycg.base.process.result.ResultUtil;
import yycg.base.process.result.SubmitResultInfo;
import yycg.business.pojo.vo.GysypmlCustom;
import yycg.business.pojo.vo.GysypmlQueryVo;
import yycg.business.pojo.vo.YpxxCustom;
import yycg.business.service.YpmlService;

/**
 * 
 * <p>
 * Title: YpmlAction
 * </p>
 * <p>
 * Description:供货商药品目录
 * </p>
 * <p>
 * Company: www.itcast.com
 * </p>
 * 
 * @author 苗润土
 * @date 2014年11月30日下午4:20:42
 * @version 1.0
 */
@Controller
@RequestMapping("/ypml")
public class YpmlAction {

   @Autowired
   YpmlService ypmlService;
	// 查询页面
	@RequestMapping("/querygysypml")
	public String querygysypml(Model model) throws Exception {
		// 药品类别

		return "/business/ypml/querygysypml";
	}

	  @RequestMapping("/querygysypmladd")
	  public String queryaddgysypml(Model model) throws Exception{
		  
		  return "/business/ypml/querygysypmladd";
	  }	
	     
	/**
	 * 药品信息添加查询
	 */
	@RequestMapping("/querygysypmladd_result")
	public @ResponseBody 
	DataGridResultInfo querygysypmladd_result(
			HttpSession session,GysypmlQueryVo gysypmlQueryVo
			,int page, int rows) throws Exception{
		
		ActiveUser activeUser = (ActiveUser)session.getAttribute(Config.ACTIVEUSER_KEY);
		String usergysId = activeUser.getSysid();
	     int total = ypmlService.findAddGysypmlCount(usergysId, gysypmlQueryVo);
	     PageQuery pageQuery = new PageQuery();
		pageQuery.setPageParams(total, rows, page);
			gysypmlQueryVo.setPageQuery(pageQuery);// 设置分页参数
		List<GysypmlCustom> list = ypmlService.findAddGysypmlList(usergysId,gysypmlQueryVo);
	   
		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
	
		dataGridResultInfo.setRows(list);
	    dataGridResultInfo.setTotal(total);
		
	     return dataGridResultInfo;
	}
		
	@RequestMapping("/addgysypmlsubmit")	               
	public @ResponseBody SubmitResultInfo addgysypmlsubmit(HttpSession session,int[] indexs,GysypmlQueryVo gysypmlQueryVo) throws Exception{
		
	    ActiveUser activeUser = (ActiveUser)session.getAttribute(Config.ACTIVEUSER_KEY);
	
		String usergysid = activeUser.getSysid();
		List<YpxxCustom> list = gysypmlQueryVo.getYpxxCustoms();
		int count = indexs.length;
		int count_success=0;
	    int count_error=0;
	    List<ResultInfo> msgs_error = new ArrayList<ResultInfo>();
	    
		for(int i =0;i<count;i++){
			ResultInfo resultInfo = null;
			YpxxCustom ypxxCustom = list.get(i);
			String ypxxid = ypxxCustom.getId();
			try {
				ypmlService.insertGysypml(usergysid, ypxxid);
			} catch (Exception e) {
				
				e.printStackTrace();
				
				if(e instanceof ExceptionResultInfo){
					resultInfo = ((ExceptionResultInfo)e).getResultInfo();
				}else{
					resultInfo = ResultUtil.createFail(Config.MESSAGE,900,null);
				}
				
				}
			if(resultInfo==null){
					count_success++;
			}else{
					count_error++;
					msgs_error.add(resultInfo);
			}
			
		}
	    return ResultUtil.createSubmitResult(ResultUtil.createSuccess(Config.MESSAGE, 907, new Object[]{
				count_success,
				count_error
		}), msgs_error);
	}
	@RequestMapping("/querygysypml_result")
	public @ResponseBody DataGridResultInfo querygysypml_result(HttpSession session, 
			GysypmlQueryVo gysypmlQueryVo,int page,int rows) throws Exception{
		ActiveUser activeuser = (ActiveUser)session.getAttribute(Config.ACTIVEUSER_KEY);
		String usergysId = activeuser.getSysid();
		int total = ypmlService.findGysypmlCount(usergysId, gysypmlQueryVo);
		PageQuery pageQuery = new PageQuery();
		pageQuery.setPageParams(total, rows, page);
		gysypmlQueryVo.setPageQuery(pageQuery);
		
		List<GysypmlCustom> list = ypmlService.findAddGysypmlList(usergysId, gysypmlQueryVo);
		
		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		dataGridResultInfo.setRows(list);
		dataGridResultInfo.setTotal(total);
		
		return dataGridResultInfo;
	}
	
}
