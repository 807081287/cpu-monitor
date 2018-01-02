package com.gsww.utils;

import javax.persistence.Entity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ToString {
	public static String str(Object o)
	{
		return ToStringBuilder.reflectionToString(o, ToStringStyle.MULTI_LINE_STYLE, false);
	}
	
	public static String toHexString(byte[] buffer, int len)
	{
		StringBuffer sb = new StringBuffer(buffer.length*2);
		for(int i=0; i < len; i++)
		{
			sb.append(Integer.toHexString((0xf0&buffer[i])>>4)).append(Integer.toHexString(0xf&buffer[i])).append(' ');
		}
		return sb.toString();
	}
	
}
