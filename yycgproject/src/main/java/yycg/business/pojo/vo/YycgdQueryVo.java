package yycg.business.pojo.vo;

import java.util.List;

import yycg.base.pojo.po.Useryy;
import yycg.base.pojo.vo.PageQuery;

public class YycgdQueryVo {

	 private List<YycgdmxCustom> yycgdmxCustoms;
	 private String businessyear;
	 private PageQuery pageQuery;
	 private Useryy useryy;
	 private YycgdmxCustom yycgdmxCustom;
	 private YycgdCustom yycgdCustom;
	public List<YycgdmxCustom> getYycgdmxCustoms() {
		return yycgdmxCustoms;
	}
	public void setYycgdmxCustoms(List<YycgdmxCustom> yycgdmxCustoms) {
		this.yycgdmxCustoms = yycgdmxCustoms;
	}
	public String getBusinessyear() {
		return businessyear;
	}
	public void setBusinessyear(String businessyear) {
		this.businessyear = businessyear;
	}
	public PageQuery getPageQuery() {
		return pageQuery;
	}
	public void setPageQuery(PageQuery pageQuery) {
		this.pageQuery = pageQuery;
	}
	public Useryy getUseryy() {
		return useryy;
	}
	public void setUseryy(Useryy useryy) {
		this.useryy = useryy;
	}
	public YycgdmxCustom getYycgdmxCustom() {
		return yycgdmxCustom;
	}
	public void setYycgdmxCustom(YycgdmxCustom yycgdmxCustom) {
		this.yycgdmxCustom = yycgdmxCustom;
	}
	public YycgdCustom getYycgdCustom() {
		return yycgdCustom;
	}
	public void setYycgdCustom(YycgdCustom yycgdCustom) {
		this.yycgdCustom = yycgdCustom;
	}
	 
}
