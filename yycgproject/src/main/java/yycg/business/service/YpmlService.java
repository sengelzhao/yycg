package yycg.business.service;

import java.util.List;

import yycg.business.pojo.po.Gysypml;
import yycg.business.pojo.po.GysypmlControl;
import yycg.business.pojo.vo.GysypmlCustom;
import yycg.business.pojo.vo.GysypmlQueryVo;

public interface YpmlService {
	public List<GysypmlCustom> findAddGysypmlList(String usergysId,
			GysypmlQueryVo gysypmlQueryVo) throws Exception;
	public int findAddGysypmlCount(String usergysId,
			GysypmlQueryVo gysypmlQueryVo) throws Exception;

	public void insertGysypml(String usergysid,String ypxxid)throws Exception;

	public GysypmlControl findGysypmlControlByUsergysidAndYpxxid(String usergysid, String ypxxid) throws Exception;
	public Gysypml findGysypmlByUsergysidAndYpxxid(String usergysid, String ypxxid) throws Exception;

	public List<GysypmlCustom> findGysypmlList(String usergysId,
			GysypmlQueryVo gysypmlQueryVo) throws Exception;
	public int findGysypmlCount(String usergysId,
			GysypmlQueryVo gysypmlQueryVo) throws Exception;

}
