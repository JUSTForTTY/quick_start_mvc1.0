package com.tcj.common;

import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tcj.common.util.MD5utils;
import com.tcj.domains.User;

public class Encryption
{
	/*
	 * 计算预付款加密算法
	 */
  public static String getComfirm(User bean)
  {
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dt = sf.format(bean.getTakingtime());
    DecimalFormat df = new DecimalFormat("#.00");
    String mount = df.format(bean.getPrepay());
    System.out.println(dt + mount + bean.getUid());
    return MD5utils.MD5Encode(dt + mount + bean.getUid());
  }
  /*
   * 判断金额是否正确，true为正确，false资金异常
   */
  public static boolean isEqual(User bean) {
    return getComfirm(bean).equals(bean.getPrepayC());
  }
  public static void main(String[] args) {
    User bean = new User();
    bean.setUid(String.valueOf(1));
    bean.setTakingtime(new Date());
    bean.setPrepay((Double.valueOf(100.0d)));

    System.out.println(getComfirm(bean));
  }
}