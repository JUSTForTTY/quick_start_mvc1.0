package com.tcj.common.dao.model;

import java.util.ArrayList;
import java.util.List;



import org.directwebremoting.annotations.DataTransferObject;

/**
 * @author xuah
 * @date 2012-12-27-下午12:25:28
 * 后台分页javabean类
 */
@DataTransferObject
public class Page<T> {
	
	/**
	 * 前台分页页面参数map 常量 (当前查询页面数)
	 */
	public final static String  CONSTANTS_NOWPAGE = "page" ;
	
	/**
	 * 前台分页页面参数map 常量 (每页显示数据记录条数)
	 */
	public final static String CONSTANTS_PAGESIZE    = "rp" ;
	
	/**
	 *当前页
	 */
	protected int nowPage;
	
	
	/**
	 *总记录数 
	 */
	protected Long total;
	
	/**
	 *每分页条数
	 */
	protected int pageSize;
	/**
	 *分页总页数
	 */
	protected int totalPage;
	
	/**
	 * 异常信息
	 */
	
	protected String msg;
	
	/**
	 * 分页的参数
	 */
	protected List<T> rows;
	
	protected List<T> footer;
	
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	
	public List<T> getFooter() {
		if (footer==null) {
			return (new ArrayList());
		}
		return footer;
	}
	public void setFooter(List<T> footer) {
		this.footer = footer;
	}
	/**
	 * @param pageSize 每页数目
	 * @param totalRecords 总记录数
	 * @return
	 * @todo 获取页面总页数 静态方法
	 */
	public static int getTotalPage(int pageSize,int totalRecords){
		int totalPage   = totalRecords/pageSize;
		if(totalPage*pageSize<totalRecords){
			totalPage ++;
		}
		return totalPage ;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
}
