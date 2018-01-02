package com.gsww.utils;

import org.apache.log4j.Logger;

public class IPHelper {
	static Logger logger = Logger.getLogger(IPHelper.class);
	public static String formatIp(String user_ip)
	{
		String[] userIpArr=StringHelper.split(".", user_ip);
		for (int i=0;i<userIpArr.length;i++){
			String temp="";
			for (int t = 0; t < (3 - String.valueOf(userIpArr[i]).length()); t++) {
				temp += "0";
			}
			userIpArr[i]=temp+userIpArr[i];
			//logger.info("userIpArr[i]"+userIpArr[i]);
		}
		String newUserIp=StringHelper.join(".",userIpArr);
		logger.info("ip convert(" + user_ip +"," + newUserIp + ")");
		return newUserIp;
	}
	
	/**将给定的字节数组转换成IPV4的十进制分段表示格式的ip地址字符串*/
	 public static String binaryArray2Ipv4Address(byte[]addr){
	  String ip="";
	  for(int i=0;i<addr.length;i++){
	   ip+=(addr[i]&0xFF)+".";
	  }
	  return ip.substring(0, ip.length()-1);
	 }
	 
	 /**将给定的用十进制分段格式表示的ipv4地址字符串转换成字节数组*/
	 public static byte[] ipv4Address2BinaryArray(String ipAdd){
	  byte[] binIP = new byte[4];
	  String[] strs = ipAdd.split("\\.");
	  for(int i=0;i<strs.length;i++){
	   binIP[i] = (byte) Integer.parseInt(strs[i]);
	  }
	  return binIP;
	 }
}
