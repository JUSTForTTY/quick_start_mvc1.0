package com.tcj.work.common.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface GetFieldFromTableService {
	public List<Map> PageLists(Map map);
	public void getTree(ArrayList<Map> list,String PID,List<Map> PageLists);
	
	/*List<Map> getCommonFunctions(Map map);*/ 
}
