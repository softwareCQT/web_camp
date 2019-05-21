/**
 * 
 */
package model;

import java.util.List;

/**
 * 分页辅助类
 * @author 陈起廷
 * @version 2019年5月2日
 */
public class Page {
	/**
	 * 分别为需求的当前页、页数的容量、总页数、总数据数、当前页的对象集合
	 * servletName主要是商品展示在页面的的时候，可以按照这样的格式置换商品的下一页，
	 * 因为有多中不同的servlet可以传page数据给首页
	 */
    private int currentPage;
    private int pageSize;
    private int totalPage;
    private int totalData;
    private List<?> list;
    private String servletName;
    /**
     * 总页数由公式自动计算 ，所以不需要set方法
     * @param currentPage
     * @param pageSize
     * 总数据数从dao层获取
     */
	public Page(int currentPage, int pageSize) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public int getTotalData() {
		return totalData;
	}
	/**
	 * 总页数在此进行计算
	 * @param totalData
	 */
	public void setTotalData(int totalData) {
		this.totalData = totalData;
		totalPage = (this.totalData%pageSize == 0)?(this.totalData/pageSize):(this.totalData/pageSize+1);
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	public String getServletName() {
		return servletName;
	}
	public void setServletName(String servletName) {
		this.servletName = servletName;
	}
    
}
