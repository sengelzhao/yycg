package yycg.business.service;

import yycg.business.pojo.vo.YycgdCustom;

public interface CgdService {
	public String insertYycgd(String userid, String year,YycgdCustom yycgdcustom) throws Exception;
    public YycgdCustom findYycgdById(String id) throws Exception;
    
}
