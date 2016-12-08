package com.tcj.common.dwr;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.directwebremoting.ConversionException;
import org.directwebremoting.convert.DateConverter;
import org.directwebremoting.extend.InboundVariable;
import org.directwebremoting.extend.NonNestedOutboundVariable;
import org.directwebremoting.extend.OutboundContext;
import org.directwebremoting.extend.OutboundVariable;

import com.tcj.common.util.DateUtil;


/**
 * @author xuah
 * 
 *         2012-12-26-下午3:42:08 自定义日期转换器
 */
public class DWRDateConverter extends DateConverter {
	public Object convertInbound(Class<?> paramType, InboundVariable data)
			throws ConversionException {
		
		String value = data.getValue();
		//如果前台传入为数字，则使用默认的日期转换器
		try{
	        if (value.length() > 0)
	        {
	            Long.parseLong(value);
	        }else{
	        	return null;
	        }
		}catch(Exception e){
			if("NaN".equals(value)) return null;
			if("null".equals(value)) return null;
			//如果抛出异常，则当字符串来处理
			Date date =  DateUtil.parse(value, "yyyy-MM-dd") ; 
			return new java.sql.Date(date.getTime()) ;
		}
		return super.convertInbound(paramType, data);
	}

	public OutboundVariable convertOutbound(Object data, OutboundContext outctx)
			throws ConversionException {
		Date date = null ;
		if (data instanceof Calendar) {
			Calendar cal = (Calendar) data; 
			date = DateUtil.cal2Date(cal) ;
		}
		else if(data instanceof Timestamp){
			if(data != null)
				return new NonNestedOutboundVariable("'"+DateUtil.format(((Date) data)) + "'");
			else {
				return new NonNestedOutboundVariable("''");
			}
		} 
		else if(data instanceof Time){
			System.out.println(data);
			if(data != null)
				return new NonNestedOutboundVariable("'"+DateUtil.format(((Date) data)) + "'");
			else {
				return new NonNestedOutboundVariable("''");
			}
		} 
		else if (data instanceof Date) {
			date = ((Date) data) ;
		} 
		else {
			throw new ConversionException(data.getClass());
		}

		return new NonNestedOutboundVariable("'"+DateUtil.format(date,"yyyy-MM-dd") + "'");
	}
}
