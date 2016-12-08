package com.tcj.common.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.bytecode.Descriptor.Iterator;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.time.DateUtils;


public class DataConvert {
	/**
	 * 将一个 Map 对象转化为一个 JavaBean
	 * 
	 * @param type
	 *            要转化的类型
	 * @param map
	 *            包含属性值的 map
	 * @return 转化出来的 JavaBean 对象
	 * @throws IntrospectionException
	 *             如果分析类属性失败
	 * @throws IllegalAccessException
	 *             如果实例化 JavaBean 失败
	 * @throws InstantiationException
	 *             如果实例化 JavaBean 失败
	 * @throws InvocationTargetException
	 *             如果调用属性的 setter 方法失败
	 * @throws ParseException 
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("rawtypes")
	public static Object mapToBean(Class type, Map map)
			throws IntrospectionException, IllegalAccessException,
			InstantiationException, InvocationTargetException, ParseException {
		
		if(map==null || map.size()==0){
			return null;
		}
		BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
		Object obj = type.newInstance(); // 创建 JavaBean 对象

		// 给 JavaBean 对象的属性赋值
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName().toUpperCase();
			if (map.containsKey(propertyName)&&map.get(propertyName)!=null) {

				String propertyType = descriptor.getPropertyType().toString();
				Object value = map.get(propertyName);
				if(propertyType.equals("class java.lang.Long")){
					value=Long.parseLong(String.valueOf(value));
				}else if(propertyType.equals("long")){
					value=Long.parseLong(String.valueOf(value));
				}else if(propertyType.equals("class java.lang.Double")){
					value=Double.parseDouble(String.valueOf(value));
				}else if(propertyType.equals("double")){
					value=Double.parseDouble(String.valueOf(value));
				}else if(propertyType.equals("class java.lang.Float")){
					value=Float.parseFloat(String.valueOf(value));
				}else if(propertyType.equals("class java.lang.Boolean")){
					value=Boolean.parseBoolean(String.valueOf(value));
				}else if(propertyType.equals("float")){
					value=Float.parseFloat(String.valueOf(value));
				}else if(propertyType.equals("class java.lang.String")){
					value=String.valueOf(value);
				}else if(propertyType.equals("class java.lang.Integer")){
					value=Integer.parseInt(String.valueOf(value));
				}else if(propertyType.equals("int")){
					value=Integer.parseInt(String.valueOf(value));
				}else if(propertyType.equals("class java.util.Date")){					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					value = sdf.parse(String.valueOf(value));
				}
				Object[] args = new Object[1];
				args[0] = value;
				Method method = descriptor.getWriteMethod() ;
				if(method != null)
					method.invoke(obj, args);
					
			}
		}
		return obj;
	} 
	//static{ConvertUtils.register(new DateConverter(), Date.class) ;}
//	public static <T> T map2Inst(Map map, Class<T> cls) {
//		T obj = null;
//		try {
//			obj = (T) cls.newInstance();
//			java.util.Iterator it = map.keySet().iterator();
//			while(it.hasNext()){
//				System.out.println(it.next());
//			}
//			BeanUtils.copyProperties(obj, map);
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return obj;
//	}
//	private class DateConverter implements Converter {
//		
//		public Object convert(Class type, Object value) {
//			System.out.println(value);
//			if (value == null) {
//				return null;
//			}
//
//			if (value instanceof Date) {
//				return value;
//			}
//
//			if (value instanceof Long) {
//				Long longValue = (Long) value;
//				return new Date(longValue.longValue());
//			}
//			if (value instanceof String) {
//				Date endTime = null;
//				try {
//					endTime = DateUtils.parseDate(value.toString(), new String[] {
//							"yyyy-MM-dd HH:mm:ss.SSS", "yyyy-MM-dd HH:mm:ss",
//							"yyyy-MM-dd HH:mm" });
//				} catch (ParseException e) {
//					e.printStackTrace();
//				}
//				return endTime;
//			}
//
//			return null;
//		}
//		
//	}
	/**
	 * 将一个 JavaBean 对象转化为一个 Map
	 * 
	 * @param bean
	 *            要转化的JavaBean 对象
	 * @return 转化出来的 Map 对象
	 * @throws IntrospectionException
	 *             如果分析类属性失败
	 * @throws IllegalAccessException
	 *             如果实例化 JavaBean 失败
	 * @throws InvocationTargetException
	 *             如果调用属性的 setter 方法失败
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map beanToMap(Object bean) throws IntrospectionException,
			IllegalAccessException, InvocationTargetException {
		Class type = bean.getClass();
		Map returnMap = new HashMap();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);

		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(bean, new Object[0]);
				if (result != null) {
					returnMap.put(propertyName, result);
				} else {
					returnMap.put(propertyName, "");
				}
			}
		}
		return returnMap;
	}
	
	/**
	 * 
	 * @param <T>
	 * @param list 要转化的List
	 * @param type JavaBean 对象
	 * @return 转化出来的对象List
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IntrospectionException
	 * @throws InvocationTargetException
	 * @throws ParseException 
	 */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static  List toListObj(List list,Class type) throws InstantiationException, IllegalAccessException, IntrospectionException, InvocationTargetException, ParseException{
		List lst=new ArrayList();
		Object obj = type.newInstance();
		if(list==null || list.size()==0){
			return null;
		}
		for(int i=0;i<list.size();i++){
			Map map=(Map) list.get(i);
			obj = DataConvert.mapToBean(type,map);
			lst.add(obj);
		}
		return lst;
		
	}
}
