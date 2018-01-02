package com.gsww.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class HttpUtils {
	// 连接对象
	private static HttpURLConnection conn;
	private final static String connStr = "http://www.baidu.com";
	private final static String basName="wlanacname";
	private static String getConnectionUrl(){
		try {
			URL url = new URL(connStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.getResponseCode();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn.getURL().toString();
	}
	public static String getBasName(){
		return getRequestParamter(basName);
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	public static String getRequestParamter(String key) {
		Map<String, String> mapRequest = new HashMap<String, String>();
		String[] arrSplit = null;
		
		String strUrlParam = TruncateUrlPage(getConnectionUrl());
		
		if (strUrlParam != null) {
			
			// 每个键值为一组
			arrSplit = strUrlParam.split("[&]");
			for (String strSplit : arrSplit) {
				
				String[] arrSplitEqual = null;
				arrSplitEqual = strSplit.split("[=]");
				
				// 解析出键值
				if (arrSplitEqual.length > 1) {
					// 正确解析
					
					mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
				} else {
					if (StringUtils.isNotBlank(arrSplitEqual[0])) {
						// 只有参数没有值，不加入
						mapRequest.put(arrSplitEqual[0], "");
					}
				}
			}
		}

		return mapRequest.get(key);
	}
	/**
	 * 去掉url中的路径，留下请求参数部分
	 * 
	 * @param strURL
	 *            url地址
	 * @return url请求参数部分
	 */
	private static String TruncateUrlPage(String strURL) {
		String strAllParam = null;
		String[] arrSplit = null;
		strURL = strURL.trim();
		arrSplit = strURL.split("[?]");
		if (strURL.length() > 1) {
			if (arrSplit.length > 1) {
				if (arrSplit[1] != null) {
					strAllParam = arrSplit[1];
				}
			}
		}
		return strAllParam;
	}
	
	public static void main(String []args){
		System.out.println(getBasName());
	}
}
