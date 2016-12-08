package com.tcj.common.dwr;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.directwebremoting.AjaxFilter;
import org.directwebremoting.AjaxFilterChain;
import org.springframework.stereotype.Component;

@Component
public class DWRFilter implements AjaxFilter {

	private static final Log log=LogFactory.getLog("ACCESS_LOG"); 
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public Object doFilter(Object object, Method method, Object[] args,
			AjaxFilterChain chain) throws Exception {
		
		if(log.isDebugEnabled()){
			String argsStr = "";
		    try {
		    	argsStr = objectMapper.writeValueAsString(args);
		    } catch (Exception e) {
		       log.error(e.getLocalizedMessage(),e) ;
		    }
			log.debug("类:" + object.getClass().getName() + ",方法:" + method.getName() + ",参数:" + argsStr) ;
		}
		//如果调用方法异常，则将异常方法写入到日志文件，并再次抛出该异常
		Object obj = null;
		try{
			obj=chain.doFilter(object, method, args);   
		}catch(Exception e){
			log.error("dwr返回值异常:"+e.getCause().getMessage(), e.getCause()); //注意此处的写法
			throw new Exception(e.getCause());
		}
		
		return obj;  
	}  
}
