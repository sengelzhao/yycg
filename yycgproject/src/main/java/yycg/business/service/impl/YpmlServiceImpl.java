package yycg.business.service.impl;

import java.util.List;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import yycg.base.process.context.Config;
import yycg.base.process.result.ResultUtil;
import yycg.base.service.SystemConfigService;
import yycg.business.dao.mapper.GysypmlControlMapper;
import yycg.business.dao.mapper.GysypmlMapper;
import yycg.business.dao.mapper.GysypmlMapperCustom;
import yycg.business.dao.mapper.YpxxMapper;
import yycg.business.pojo.po.Gysypml;
import yycg.business.pojo.po.GysypmlControl;
import yycg.business.pojo.po.GysypmlControlExample;
import yycg.business.pojo.po.GysypmlExample;
import yycg.business.pojo.po.Ypxx;
import yycg.business.pojo.vo.GysypmlCustom;
import yycg.business.pojo.vo.GysypmlQueryVo;
import yycg.business.service.YpmlService;
import yycg.util.UUIDBuild;

public class YpmlServiceImpl implements YpmlService {
	@Autowired
	GysypmlMapperCustom gysypmlMapperCustom;
	@Autowired
	GysypmlMapper gysypmlMapper;
	@Autowired
	GysypmlControlMapper gysypmlControlMapper;
	@Autowired
	YpxxMapper ypxxMapper;

	@Autowired
	private SystemConfigService systemConfigService;

	public List<GysypmlCustom> findAddGysypmlList(String usergysId, GysypmlQueryVo gysypmlQueryVo) throws Exception {
		gysypmlQueryVo = gysypmlQueryVo != null ? gysypmlQueryVo : new GysypmlQueryVo();
		GysypmlCustom gysypmlCustom = gysypmlQueryVo.getGysypmlCustom();
		if (gysypmlCustom == null) {
			gysypmlCustom = new GysypmlCustom();
		}

		gysypmlCustom.setUsergysid(usergysId);
		gysypmlQueryVo.setGysypmlCustom(gysypmlCustom);

		return gysypmlMapperCustom.findAddGysypmlList(gysypmlQueryVo);
	}

	@Override
	public int findAddGysypmlCount(String usergysId, GysypmlQueryVo gysypmlQueryVo) throws Exception {
		gysypmlQueryVo = gysypmlQueryVo != null ? gysypmlQueryVo : new GysypmlQueryVo();

		GysypmlCustom gysypmlCustom = gysypmlQueryVo.getGysypmlCustom();
		if (gysypmlCustom == null) {
			gysypmlCustom = new GysypmlCustom();
		}

		// 设置供货商id
		gysypmlCustom.setUsergysid(usergysId);

		gysypmlQueryVo.setGysypmlCustom(gysypmlCustom);
		return gysypmlMapperCustom.findAddGysypmlCount(gysypmlQueryVo);
	}

	public Gysypml findGysypmlByUsergysidAndYpxxid(String usergysid, String ypxxid) throws Exception {
		GysypmlExample gysypmlExample = new GysypmlExample();
		GysypmlExample.Criteria criteria = gysypmlExample.createCriteria();
		criteria.andUsergysidEqualTo(usergysid);
		criteria.andYpxxidEqualTo(ypxxid);
		List<Gysypml> list = gysypmlMapper.selectByExample(gysypmlExample);
		if (list != null && list.size() == 1) {
			return list.get(0);
		}

		return null;
	}

	public GysypmlControl findGysypmlControlByUsergysidAndYpxxid(String usergysid, String ypxxid) throws Exception {
		GysypmlControlExample gysypmlControlExample = new GysypmlControlExample();
		GysypmlControlExample.Criteria criteria = gysypmlControlExample.createCriteria();
		criteria.andUsergysidEqualTo(usergysid);
		criteria.andYpxxidEqualTo(ypxxid);
		List<GysypmlControl> list = gysypmlControlMapper.selectByExample(gysypmlControlExample);
		if (list != null && list.size() == 1) {
			return list.get(0);
		}

		return null;
	}

	@Override
	public void insertGysypml(String usergysid, String ypxxid) throws Exception {

		Gysypml gysypml = this.findGysypmlByUsergysidAndYpxxid(usergysid, ypxxid);

		if (gysypml != null) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 401, null));
		} else {
			gysypml = new Gysypml();
		}
		Ypxx ypxx = ypxxMapper.selectByPrimaryKey(ypxxid);
		if (ypxx == null) {
		}
		String jyzt = ypxx.getJyzt();
		if (jyzt.equals("2")) {
			ResultUtil.throwExcepion(
					ResultUtil.createFail(Config.MESSAGE, 403, new Object[] { ypxx.getBm(), ypxx.getMc() }));
		}
 
		gysypml.setId(UUIDBuild.getUUID());
		gysypml.setUsergysid(usergysid);
		gysypml.setYpxxid(ypxxid);
		gysypmlMapper.insert(gysypml);

		GysypmlControl gysypmlControl = this.findGysypmlControlByUsergysidAndYpxxid(usergysid, ypxxid);
		if (gysypmlControl == null) {
			String control = systemConfigService.findBasicinfoById("00101").getValue();
			gysypmlControl = new GysypmlControl();
            gysypmlControl.setId(UUIDBuild.getUUID());
            gysypmlControl.setUsergysid(usergysid);
            gysypmlControl.setYpxxid(ypxxid);
            gysypmlControl.setControl(control);
            gysypmlControlMapper.insert(gysypmlControl);
		}

	}

	@Override
	public List<GysypmlCustom> findGysypmlList(String usergysId, GysypmlQueryVo gysypmlQueryVo) throws Exception {
		// TODO Auto-generated method stub
		gysypmlQueryVo = gysypmlQueryVo!=null ? gysypmlQueryVo: new GysypmlQueryVo();
		GysypmlCustom gysypmlCustom= gysypmlQueryVo.getGysypmlCustom();
		if(gysypmlCustom==null){
			gysypmlCustom = new GysypmlCustom();
		}
		 gysypmlCustom.setUsergysid(usergysId);
		 gysypmlQueryVo.setGysypmlCustom(gysypmlCustom);
		 
		return gysypmlMapperCustom.findGysypmlList(gysypmlQueryVo);
	}

	@Override
	public int findGysypmlCount(String usergysId, GysypmlQueryVo gysypmlQueryVo) throws Exception {
		gysypmlQueryVo = gysypmlQueryVo!=null ? gysypmlQueryVo: new GysypmlQueryVo();
		GysypmlCustom gysypmlCustom= gysypmlQueryVo.getGysypmlCustom();
		if(gysypmlCustom==null){
			gysypmlCustom = new GysypmlCustom();
		}
		 gysypmlCustom.setUsergysid(usergysId);
		 gysypmlQueryVo.setGysypmlCustom(gysypmlCustom);
		 
		return gysypmlMapperCustom.findGysypmlCount(gysypmlQueryVo);
	}
	
	

}
