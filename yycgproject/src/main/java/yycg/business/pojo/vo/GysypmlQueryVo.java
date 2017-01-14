package yycg.business.pojo.vo;

import java.util.List;

import yycg.base.pojo.vo.PageQuery;

public class GysypmlQueryVo {
  private GysypmlCustom gysypmlCustom;
  private YpxxCustom ypxxCustom;
  private PageQuery pageQuery;
  private List<YpxxCustom> ypxxCustoms;
public List<YpxxCustom> getYpxxCustoms() {
	return ypxxCustoms;
}

public void setYpxxCustoms(List<YpxxCustom> ypxxCustoms) {
	this.ypxxCustoms = ypxxCustoms;
}

public PageQuery getPageQuery() {
	return pageQuery;
}

public void setPageQuery(PageQuery pageQuery) {
	this.pageQuery = pageQuery;
}

public YpxxCustom getYpxxCustom() {
	return ypxxCustom;
}

public void setYpxxCustom(YpxxCustom ypxxCustom) {
	this.ypxxCustom = ypxxCustom;
}

public GysypmlCustom getGysypmlCustom() {
	return gysypmlCustom;
}

public void setGysypmlCustom(GysypmlCustom gysypmlCustom) {
	this.gysypmlCustom = gysypmlCustom;
}

}
