package com.tcj.common;

import java.util.Random;
/**
 * 生成方法：
 * 1.获取手机号,按一定规则生成11-13位的新号码
 * 2.将新号码用35进制转换成8位邀请码
 * @author gury
 * */
public class RandomCode {
	/**
     * 进制数组
     * 去掉大写字母O,共35进制,特定11-13位数必定生成8位邀请码
     */
    private static final char[] jz=new char[]{
    		'0','1','2','3','4','5','6','7','8','9',
    		'A','B','C','D','E','F','G','H','I','J','K','L','M',
    		'N','P','Q','R','S','T','U','V','W','X','Y','Z',
    };
    /** 进制长度 */
    private static final int binLen=jz.length;
    /** 测试号码    */
    private static String phone[]={
    		"1a","18251172398","18251127397",
    		null,"18251152399","18251142399",
    		"18761383523123123122","18761383522","18761383522"};
    /**
     * 最大长度
     */
    private static final int maxLen=13;
     
    public static void main(String[] args) {
    	for (int i = 0; i < phone.length; i++) {
			String code=toSerialCode(phone[i]);
			System.out.println(code);
		}
    }
    /**
     * 新字串生成规则：
     * 1.去掉号码的首位"1";
     * 2.将剩余号码从中间断开重新拼接;
     * 3.在头部随机生成数字凑够11-13位
     * 4.新号码范围70,000,000,000(11位)-1,999,999,999,999(13位),总计1,900,000,000,000+种可能
     * @param phone 原号码
     * @return
     */
    private static String getNewString(String phone){
    	//先进行一定的容错处理,可去掉
    	String str=(phone==null||phone.length()<4||phone.length()>maxLen)?"12345":phone;
    	//1.去掉第一位
    	str=str.substring(1);
    	//2.中间断开重新拼接	
    	str=str.substring(str.length()/2)+str.substring(0, str.length()/2);
    	//3.凑13或14位数
    	str=getHeadString(maxLen-str.length())+str;
    	System.out.println("原号码:"+phone+",新号码:"+str);
    	return str;
    }
    /**
     * 给电话号码加个头,组成13位字符串
     * @param length 需要加的长度
     * @return
     */
    public static String getHeadString(int length){
    	int s=0;
    	Random random=new Random();
    	for (int i = 1; i <=length; i++) {
    		if(i==1){//第一位0或1
    			s=s*10+random.nextInt(2);
    		}
    		else if(i==3&&s==0)//前两位全是0,第三位要大于等于7
    			s=s*10+random.nextInt(3)+7;
    		else
    			s=s*10+random.nextInt(10);
		}
    	return s+"";
    }
    /**
     * 根据号码生成八位随机码
     * @param id 号码
     * @return 邀请码
     */
    public static String toSerialCode(String number) {
    	long id=Long.parseLong(getNewString(number));
        char[] buf=new char[binLen];
        int charPos=binLen;
        while((id / binLen) > 0) {
            int ind=(int)(id % binLen);
            buf[--charPos]=jz[ind];
            id /= binLen;
        }
        buf[--charPos]=jz[(int)(id % binLen)];
        String str=new String(buf, charPos, (binLen - charPos));
        return str;
    }

    
}