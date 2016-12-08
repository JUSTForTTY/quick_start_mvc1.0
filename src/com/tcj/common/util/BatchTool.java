package com.tcj.common.util;

public class BatchTool {

	/*
	 * @auther: tty
	 * 
	 * @param:objs 删除数据数组
	 * 
	 * @return
	 * 
	 */
	public String batchDeleteSql(String sql, Object[] objs) {

		sql += "(";
		for (int i = 1; i <= objs.length-3; i++) {

			

			
		
			if (i == objs.length - 3) {

				sql += "?" + ")";
				
			}
			else{	sql += "?" + ",";}
			

		}

		return sql;
	}
	
	public String batchDeleteSql(String sql, Object[] objs, int paraNo) {

		sql += "(";
		for (int i = 1; i <= objs.length-paraNo; i++) {
			if (i == objs.length - paraNo) {
				sql += "?" + ")";
			}
			else{	sql += "?" + ",";}
		}
		return sql;
	}
	
}
