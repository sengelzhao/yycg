package yycg.business.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import yycg.base.process.context.Config;
import yycg.base.process.result.ResultUtil;
import yycg.base.service.SystemConfigService;
import yycg.business.dao.mapper.YycgdMapper;
import yycg.business.dao.mapper.YycgdMapperCustom;
import yycg.business.pojo.po.GysypmlExample.Criteria;
import yycg.business.pojo.po.Yycgd;
import yycg.business.pojo.po.YycgdExample;
import yycg.business.pojo.vo.YycgdCustom;
import yycg.business.service.CgdService;

public class YycgdServiceImpl implements CgdService {
     
	@Autowired
	private YycgdMapperCustom yycgdMapperCustom;
	@Autowired
	private YycgdMapper yycgdMapper;
	@Autowired
	private SystemConfigService systemConfigService;
	
	@Override
	public String insertYycgd(String userid, String year, YycgdCustom yycgdCustom) throws Exception {
	
		String bm = yycgdMapperCustom.getYycgdBm(year);
	   
	
		yycgdCustom.setId(bm);
		yycgdCustom.setBm(bm);
		yycgdCustom.setUseryyid(userid);
		yycgdCustom.setCjtime(new Date());
		yycgdCustom.setZt("1");
		yycgdCustom.setBusinessyear(year);
		yycgdMapper.insert(yycgdCustom);
		return bm;
	}
	@Override
	public YycgdCustom findYycgdById(String id) throws Exception {
		// TODO Auto-generated method stub
		String year = id.substring(0,4);
		YycgdExample yycgdExample = new YycgdExample();
		YycgdExample.Criteria criteria = yycgdExample.createCriteria();
		criteria.andIdEqualTo(id);
		yycgdExample.setBusinessyear(year);
		List<Yycgd> list = yycgdMapper.selectByExample(yycgdExample);
		Yycgd yycgd = null;
		YycgdCustom yycgdCustom = new YycgdCustom();
		if(list!=null && list.size()==1){
			yycgd = list.get(0);
			BeanUtils.copyProperties(yycgd, yycgdCustom);			
		}else{
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 501, null));
		}
		
		String yycgdztmc = systemConfigService.findDictinfoByDictcode("010", yycgd.getZt()).getInfo();
		yycgdCustom.setYycgdztmc(yycgdztmc);
		return yycgdCustom;
	}
	
	

}
