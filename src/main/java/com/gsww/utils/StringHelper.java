package com.gsww.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public final class StringHelper {

	/**
	 * 将字符串数组联合成一个字符串，数组元素中间用分隔符分开
	 * 
	 * @param seperator
	 *            分隔符
	 * @param strings
	 *            字符串数组
	 * 
	 * @return 返回字符串
	 */
	public static String join(String seperator, String[] strings) {
		int length = strings.length;
		if (length == 0)
			return "";
		StringBuffer buf = new StringBuffer(length * strings[0].length())
				.append(strings[0]);
		for (int i = 1; i < length; i++) {
			buf.append(seperator).append(strings[i]);
		}
		return buf.toString();
	}

	public static String replaceBlank(String str)

	{
		str = str.replace((char) 12288, ' ');
		Pattern p = Pattern.compile("\\s*|\t|\r|\n"); // 注意该表达式，不能去处全角空格，利用上面的：str
														// = str.replace((char)
														// 12288, ' '); 即可实现。
														// 呵呵！
		Matcher m = p.matcher(str);// 换成：str.replace((char) 12288, ' ')
		String after = m.replaceAll("").replace(" ", "");
		return after;
	}

	/**
	 * 将字符串重复Copy几次，形成新的字符串
	 * 
	 * @param string
	 *            字符串
	 * @param times
	 *            重复次数
	 * 
	 * 
	 */
	public static String repeat(String string, int times) {
		StringBuffer buf = new StringBuffer(string.length() * times);
		for (int i = 0; i < times; i++)
			buf.append(string);
		return buf.toString();
	}

	/**
	 * 将字符串中的某个字符串替换为另外一个字符串
	 * 
	 * @param template
	 *            操作的字符串
	 * @param placeholder
	 *            被替换的字符串
	 * @param replacement
	 *            替换的的字符串
	 * 
	 */
	public static String replace(String template, String placeholder,
			String replacement) {
		int loc = template.indexOf(placeholder);
		if (loc < 0) {
			return template;
		} else {
			return new StringBuffer(template.substring(0, loc))
					.append(replacement)
					.append(replace(
							template.substring(loc + placeholder.length()),
							placeholder, replacement)).toString();
		}
	}

	/**
	 * 将字符串中的某个字符串替换为另外一个字符串，仅被替换一次
	 * 
	 * @param template
	 *            操作的字符串
	 * @param placeholder
	 *            被替换的字符串
	 * @param replacement
	 *            替换的的字符串
	 * 
	 */

	public static String replaceOnce(String template, String placeholder,
			String replacement) {
		int loc = template.indexOf(placeholder);
		if (loc < 0) {
			return template;
		} else {
			return new StringBuffer(template.substring(0, loc))
					.append(replacement)
					.append(template.substring(loc + placeholder.length()))
					.toString();
		}
	}

	/**
	 * 将字符串分割，返回数组
	 * 
	 * @param seperators
	 *            分割字符
	 * @param list
	 *            字符串
	 * 
	 * 
	 * 
	 */
	public static String[] split(String seperators, String list) {
		StringTokenizer tokens = new StringTokenizer(list, seperators);
		// System.out.println(tokens.countTokens());
		String[] result = new String[tokens.countTokens()];
		int i = 0;
		while (tokens.hasMoreTokens()) {
			result[i++] = tokens.nextToken();
		}
		return result;
	}

	public static String unqualify(String qualifiedName) {
		return unqualify(qualifiedName, ".");
	}

	public static String unqualify(String qualifiedName, String seperator) {
		return qualifiedName
				.substring(qualifiedName.lastIndexOf(seperator) + 1);
	}

	public static String qualifier(String qualifiedName) {
		int loc = qualifiedName.lastIndexOf(".");
		if (loc < 0) {
			return "";
		} else {
			return qualifiedName.substring(0, loc);
		}
	}

	public static String getQualifier(String qualifiedName) {
		int loc = qualifiedName.lastIndexOf(".");
		if (loc < 0) {
			return "";
		} else {
			return qualifiedName.substring(loc);
		}
	}

	public static String root(String qualifiedName) {
		int loc = qualifiedName.indexOf(".");
		return (loc < 0) ? qualifiedName : qualifiedName.substring(0, loc);
	}

	public static boolean booleanValue(String tfString) {
		String trimmed = tfString.trim().toLowerCase();
		return "true".equals(trimmed) || "t".equals(trimmed);
	}

	public static String toString(Object[] array) {
		int len = array.length;
		if (len == 0)
			return "";
		StringBuffer buf = new StringBuffer(len * 12);
		for (int i = 0; i < len - 1; i++) {
			buf.append(array[i]).append(", ");
		}
		return buf.append(array[len - 1]).toString();
	}

	public static String[] multiply(String string,
			Iterator<Object> placeholders, Iterator<Object> replacements) {
		String[] result = new String[] { string };
		while (placeholders.hasNext()) {
			result = multiply(result, (String) placeholders.next(),
					(String[]) replacements.next());
		}
		return result;
	}

	private static String[] multiply(String[] strings, String placeholder,
			String[] replacements) {
		String[] results = new String[replacements.length * strings.length];
		int n = 0;
		for (int i = 0; i < replacements.length; i++) {
			for (int j = 0; j < strings.length; j++) {
				results[n++] = replaceOnce(strings[j], placeholder,
						replacements[i]);
			}
		}
		return results;
	}

	/**
	 * 取得某一个Char字符在字符串中的个数
	 * 
	 */
	public static int getValueCount(String str, char c) {
		int n = 0;
		for (int i = 0; i < str.length(); i++) {
			char a = str.charAt(i);
			if (a == c) {
				n++;
			}
		}
		return n;
	}

	public static String toString(Object obj) {
		if (obj == null || "".equals(obj.toString())
				|| "null".equals(obj.toString())
				|| "NULL".equals(obj.toString())) {
			return "";
		} else {
			String objValue = obj.toString().trim();
			return objValue;
		}
	}

	/** 截取汉字或汉字、字符混合串的前n位，如果第n位为双字节字符，则截取前n-1位 */
	public static String leftCut(String str, int n) {
		byte[] b_str = str.getBytes();
		byte[] new_str;
		int k;
		if (b_str.length < n) {
			return str;
		} else {
			if (b_str[n] < 0 && b_str[n - 1] > 0) {
				k = n - 1;
			} else {
				k = n;
			}
			new_str = new byte[k];
			for (int i = 0; i < k; i++) {
				new_str[i] = b_str[i];
			}
			return new String(new_str);
		}
	}

	/** 将 a,b,c,d, 转换成 'a','b','c','d' */
	public static String addSingleMark(String strContent) {
		String[] str = StringHelper.split(",", strContent);
		String newStr = "";
		for (int i = 0; i < str.length; i++) {
			newStr += "'" + str[i] + "',";
		}
		newStr = newStr.substring(0, newStr.length() - 1);
		return newStr;
	}

	/** 将回车换行符替换成HTML换行符* */
	public static String addBr(String Content) {
		String makeContent = new String();
		StringTokenizer strToken = new StringTokenizer(Content, "\n");
		while (strToken.hasMoreTokens()) {
			makeContent = makeContent + "<br>" + strToken.nextToken();
		}
		return makeContent;
	}

	/** 将HTML换行符替换成回车换行符* */
	public static String subtractBr(String Content) {
		String makeContent = new String();
		StringTokenizer strToken = new StringTokenizer(Content, "<br>");
		while (strToken.hasMoreTokens()) {
			makeContent = makeContent + "\n" + strToken.nextToken();
		}
		return makeContent;
	}

	/**
	 * 检查字符串是否为非零长度的有效字符串
	 * 
	 * @param var
	 *            需检查的字符串
	 * @return 不为空字符串返回true，否则返回false
	 */
	public static boolean checkString(String var) {
		boolean bRtn = true;
		if (var == null) {
			bRtn = false;
		} else {
			if (var.length() < 1)
				bRtn = false;
		}
		return bRtn;
	}

	/**
	 * 检查字符串是否是合法整数
	 * 
	 * @param var
	 *            传入需要检查的字符串
	 * @return 如果为合法整数返回true，否则返回false
	 */
	public static boolean checkInt(String var) {
		boolean bRtn = true;
		try {
			if (Integer.parseInt(var) > Integer.MAX_VALUE
					|| Integer.parseInt(var) < Integer.MIN_VALUE)
				bRtn = false;
		} catch (Exception e) {
			bRtn = false;
		}
		return bRtn;
	}

	public static int parseInt(String var) {
		int bRtn = 0;
		try {
			if (StringUtils.isNotBlank(var)) {
				if (Integer.parseInt(var) > Integer.MAX_VALUE
						|| Integer.parseInt(var) < Integer.MIN_VALUE)
					bRtn = Integer.parseInt(var);
			}
		} catch (Exception e) {
			bRtn = 0;
		}
		return bRtn;
	}

	public static boolean checkLong(String var) {
		boolean bRtn = true;
		try {
			Long.parseLong(var);
			bRtn = true;
		} catch (Exception e) {
			bRtn = false;
		}
		return bRtn;
	}

	public static boolean checkFloat(String var) {
		boolean bRtn = true;
		try {
			Float.parseFloat(var);
			bRtn = true;
		} catch (Exception e) {
			bRtn = false;
		}
		return bRtn;
	}

	public static boolean checkDouble(String var) {
		boolean bRtn = true;
		try {
			Double.parseDouble(var);
			bRtn = true;
		} catch (Exception e) {
			bRtn = false;
		}
		return bRtn;
	}

	public static Double parseDouble(String var) {
		Double bRtn = new Double(0);
		try {
			if (StringUtils.isNotBlank(var)) {
				bRtn = Double.parseDouble(var);
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			bRtn = new Double(0);
		}
		return bRtn;
	}

	public static BigDecimal parseBigDecimal(String var) {
		BigDecimal bRtn = new BigDecimal(0);
		try {
			if (StringUtils.isNotBlank(var)) {
				bRtn = new BigDecimal(var);
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			bRtn = new BigDecimal(0);
		}
		return bRtn;
	}

	public static boolean isNumeric(String str) {
		int begin = 0;
		boolean once = true;
		if (str == null || "".equals(str.trim())) {
			return false;
		}
		str = str.trim();
		if (str.startsWith("+") || str.startsWith("-")) {
			if (str.length() == 1) {
				// "+" "-"
				return false;
			}
			begin = 1;
		}
		for (int i = begin; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				if (str.charAt(i) == '.' && once) {
					// '.' can only once
					once = false;
				} else {
					return false;
				}
			}
		}
		if (str.length() == (begin + 1) && !once) {
			// "." "+." "-."
			return false;
		}
		return true;
	}

	/**
	 * 检查是否是有效的电子邮件格式
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkMail(String str) {
		Pattern p = Pattern
				.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*.\\w+([-.]\\w+)*");
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * 检查是否为有效的电话号码
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkPhone(String str) {
		Pattern p = Pattern
				.compile("^(\\d{3}-|\\d{4}-)?(\\d{8}|\\d{7})?(-\\d+)?$");
		Matcher m = p.matcher(str);
		return m.matches();
	}

	public static String formatMobile(String str) {
		str = str.replaceAll("-", "");
		str = str.replaceAll(" ", "");
		return str;
	}

	/**
	 * 检查电信手机号码是否符合检验公式
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkMobile(String str) {
		Pattern p = Pattern.compile("^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$");// "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$";
																				// "^(((1[35]3)|(18[019])|(17[17]))+\\d{8})$"
		Matcher m = p.matcher(str);
		return m.matches();
	}

	public static boolean checkCdmaMobile(String str) {
		Pattern p = Pattern.compile("^(((1[35]3)|(18[019])|(17[17]))+\\d{8})$");// "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$";
																				// "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$"
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * Object转int
	 * 
	 * @param obj
	 * @return int
	 */
	public static int convertToInt(Object obj) {
		if (obj == null)
			return -999;
		try {
			int result = Integer.parseInt(obj.toString().trim());
			return result;
		} catch (NumberFormatException e) {
			return -999;
		}
	}

	/**
	 * 检查是否为有效的网页地址
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkURL(String str) {
		Pattern p = Pattern.compile("^[\\w\\.]+$");
		Matcher m = p.matcher(str);
		return m.matches();
	}

	public static boolean checkURL2(String str) {
		Pattern p = Pattern
				.compile("^[a-zA-Z]+://([\\w\\-\\+%]+\\.)+[\\w\\-\\+%]+(:\\d+)?(/[\\w\\-\\+%])*(/.*)?$");
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * 检查是否为有效的邮政编码
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkZIP(String str) {
		Pattern p = Pattern.compile("^\\d{6}$");
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * 检查是否为有效的客户帐号
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkAccount(String str) {
		Pattern p = Pattern.compile("^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){0,30}$");
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * 检查是否为有效的客户帐号
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkID(String str) {
		Pattern p = Pattern.compile("^[0-9]{4,32}$");
		Matcher m = p.matcher(str);
		return m.matches();
	}

	public static List<String> getDiff(List<String> list) {
		List<String> result = new ArrayList<String>();
		String temp;
		for (int i = 0; i < list.size(); i++) {
			temp = list.get(i);
			if (isExist(result, temp))
				continue;
			else
				result.add(temp);

		}
		return result;
	}

	private static boolean isExist(List<String> list, String str) {
		for (String temp : list)
			if (str.equals(temp))
				return true;
		return false;
	}

	/** 校验长度小于len的字符串;0,可以为空,1,不能为空* */
	public static boolean checkStringLen(String Content, int len,
			String emptyState) {
		try {
			if (emptyState == null || "0".equals(emptyState)) {
				if (Content == null)
					return true;
				else if (Content.length() >= len)
					return false;
				else
					return true;
			} else if ("1".equals(emptyState)) {
				if (Content == null || "".equals(Content.trim())
						|| Content.length() >= len)
					return false;
				else
					return true;
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 验证字符串是否为中文字符
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isChinese(String str) {
		String strTemp = null;
		try {
			strTemp = new String(str.getBytes("ISO-8859-1"), "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 如果值为空，通过校验
		if ("".equals(str))
			return true;
		Pattern p = Pattern.compile("/[^\u4E00-\u9FA5]/g,''");
		Matcher m = p.matcher(strTemp);
		return m.matches();
	}

	/**
	 * 检查是否为有效的客户帐号
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkEntAccount(String str) {
		Pattern p = Pattern.compile("^[a-zA-Z0-9][\\w]{1,32}$");
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * 检查是否全部为英文字母
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkEn(String str) {
		Pattern p = Pattern.compile("^[A-Za-z]+$");
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * 检查是否为合法的IP
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkIp(String str) {
		String regex = "(((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/** 检验输入的值整数位最多6位，小数位最多2位 */
	public static boolean checkMoney(String str) {
		String money = "^(\\d?\\d?\\d?\\d?\\d?\\d?[.]\\d?\\d?)|(\\d?\\d?\\d?\\d?\\d?\\d?)$";
		Pattern p = Pattern.compile(money);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	public static boolean chenkbanj(String str) {
		String ints = "^(\\d{1,32})$";
		Pattern p = Pattern.compile(ints);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	public static boolean checkNumBan(String str) {
		Pattern p = Pattern.compile("^(\\d+,)*\\d*$");
		Matcher m = p.matcher(str);
		return m.matches();
	}

	public static String showInt(int i) {
		String intStr = String.valueOf(i);
		String[] strArr = intStr.split(",");
		StringBuffer sb = new StringBuffer();
		for (int j = 0; j <= strArr.length; j++) {
			sb.append(strArr[j]);
		}
		return sb.toString();
	}

	public static String splitListToString(String seperators, List list) {
		String temp = list.toString();
		StringBuffer strSb = new StringBuffer();
		String[] arr = split(",", temp);
		for (int i = 0; i < arr.length; i++) {
			strSb.append(arr[i].trim());
		}

		return strSb.toString().substring(1, strSb.length() - 1);
	}

	public static String splitListToStringIncDou(String seperators, List list) {
		String temp = list.toString();
		StringBuffer strSb = new StringBuffer();
		String[] arr = split(",", temp);
		for (int i = 0; i < arr.length; i++) {
			strSb.append(arr[i].trim()).append("\n");
		}

		return strSb.toString().substring(1, strSb.length() - 2);
	}

	/** 生成4位随机码 */
	public static String getRandom() {
		Random random = new Random();
		String str = "";
		for (int i = 0; i < 4; i++) {
			str += String.valueOf(random.nextInt(10));
		}
		return str;
	}

	public static String getRandom(int max) {
		Random random = new Random();
		String str = "";
		for (int i = 0; i < max; i++) {
			str += String.valueOf(random.nextInt(10));
		}
		return str;
	}

	public static StringBuffer appendString(Map<String, Object> map) {
		StringBuffer params = new StringBuffer();
		Iterator<Map.Entry<String, Object>> iterator = map.entrySet()
				.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			Map.Entry<String, Object> entry = iterator.next();
			if (i == 0)
				params.append(entry.getKey()).append("=")
						.append(entry.getValue());
			else
				params.append("&").append(entry.getKey()).append("=")
						.append(entry.getValue());
			i++;
		}
		return params;
	}

	public static String guoHtml(String s) {

		if (!s.equals("") || s != null) {

			String str = s.replaceAll("<[.[^<]]*>", "");

			return str;

		} else {

			return s;

		}
	}
	public static String Html2Text(String inputString) {
		   //过滤html标签
		   String htmlStr = inputString; // 含html标签的字符串
		   String textStr = "";
		   java.util.regex.Pattern p_script;
		   java.util.regex.Matcher m_script;

		   try {
		    String regEx = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
		    // }
		    p_script = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		    m_script = p_script.matcher(htmlStr);
		    htmlStr = m_script.replaceAll(""); 
		    regEx = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
		    p_script = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		    m_script = p_script.matcher(htmlStr);
		    htmlStr = m_script.replaceAll(""); 
		    regEx = "[\\s]?style=[\"'][^\">']*[\"'>]";
		    p_script = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		    m_script = p_script.matcher(htmlStr);
		    htmlStr = m_script.replaceAll(""); 
		    regEx = "<!--.*[^<>!-]-->"; 
		    p_script = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		    m_script = p_script.matcher(htmlStr);
		    htmlStr = m_script.replaceAll(""); 
		    regEx = "[\\s]?width=[\"'][^\">']*[\"'>]";
		    p_script = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		    m_script = p_script.matcher(htmlStr);
		    htmlStr = m_script.replaceAll(""); 
		    regEx = "[\\s]?height=[\"'][^\">']*[\"'>]"; 
		    p_script = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		    m_script = p_script.matcher(htmlStr);
		    htmlStr = m_script.replaceAll(""); 
		    regEx = "[\\s]?class=[\"'][^\">']*[\"'>]"; 
		    p_script = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		    m_script = p_script.matcher(htmlStr);
		    htmlStr = m_script.replaceAll(""); 
		    regEx = "[\\s]?align=[\"'][^\">']*[\"'>]"; 
		    p_script = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		    m_script = p_script.matcher(htmlStr);
		    htmlStr = m_script.replaceAll(""); 
		    regEx = "[\\s]?valign=[\"'][^\">']*[\"'>]"; 
		    p_script = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		    m_script = p_script.matcher(htmlStr);
		    htmlStr = m_script.replaceAll(""); 
		    regEx = "[\\s]?id=[\"'][^\">']*[\"'>]"; 
		    p_script = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		    m_script = p_script.matcher(htmlStr);
		    htmlStr = m_script.replaceAll(""); 
		    regEx = "<tr[\\s]?>"; 
		    p_script = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		    m_script = p_script.matcher(htmlStr);
		    htmlStr = m_script.replaceAll(""); 
		    regEx = "</tr[\\s]?>"; 
		    p_script = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		    m_script = p_script.matcher(htmlStr);
		    htmlStr = m_script.replaceAll(""); 
		    regEx = "<td[\\s]?>"; 
		    p_script = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		    m_script = p_script.matcher(htmlStr);
		    htmlStr = m_script.replaceAll(""); 
		    regEx = "</td[\\s]?>"; 
		    p_script = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		    m_script = p_script.matcher(htmlStr);
		    htmlStr = m_script.replaceAll(""); 
		    regEx = "<a(.[^>]*)>"; 
		    p_script = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		    m_script = p_script.matcher(htmlStr);
		    htmlStr = m_script.replaceAll(""); 
		    regEx = "</a[\\s]?>"; 
		    p_script = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		    m_script = p_script.matcher(htmlStr);
		    htmlStr = m_script.replaceAll(""); 
		    regEx = "<center>"; 
		    p_script = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		    m_script = p_script.matcher(htmlStr);
		    htmlStr = m_script.replaceAll("<p>"); 
		    regEx = "</center>"; 
		    p_script = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		    m_script = p_script.matcher(htmlStr);
		    htmlStr = m_script.replaceAll(""); 
		    regEx = "</p>"; 
		    p_script = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		    m_script = p_script.matcher(htmlStr);
		    htmlStr = m_script.replaceAll(""); 
		    regEx = "<font[^>]*?>"; 
		    p_script = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		    m_script = p_script.matcher(htmlStr);
		    htmlStr = m_script.replaceAll(""); 
		    regEx = "</font>"; 
		    p_script = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		    m_script = p_script.matcher(htmlStr);
		    htmlStr = m_script.replaceAll(""); 
		    htmlStr = m_script.replaceAll(""); 
		    
		    textStr = htmlStr;

		   } catch (Exception e) {
		    System.err.println("Html2Text: " + e.getMessage());
		   }
		   return textStr;// 返回文本字符串
		}

	public static void main(String[] args) {
		String account = "0931-1233 45 655 -2135";
		System.out.println(replaceBlank("李 　三"));
		System.out.println(checkMobile("13309463380"));
		String s = "因为他觉得这样的女人真是美丽极了。没有人会对相同的外表或事物着迷，如果有，巧合的成份应该会多于实质的意义。就许多男人而言，女人长得什么样，并不是那么的具有决定性，“ 完美的大脑以及过人的智慧，才是我<a class='cmsLink' style='color: rgb(0, 0, 0); line-height: 18px; text-decoration: none; border-bottom-color: rgb(204, 0, 102); border-bottom-width: 1px; border-bottom-style: dashed;' href='http://emotion.pclady.com.cn/zxzq/zhuiqiu/ "+

"' target='_blank'>追求</a>的。”说这个话的人是大二的光仁。</p><p style='font: 14px/28px 宋体; margin: 8px 0px 0px; padding: 0px; color: rgb(0, 0, 0); text-transform: none; text-indent: 0px; letter-spacing: normal; word-spacing: 0px; white-space: normal; font-size-adjust: none; font-stretch: normal; background-color: rgb(255, 255, 255); -webkit-text-stroke-width: 0px;'>　　四、男人总是向女人施压，要求发生关系!</p><p style='font: 14px/28px 宋体; margin: 8px 0px 0px; padding: 0px; color: rgb(0, 0, 0); text-transform: none; text-indent: 0px; letter-spacing: normal; word-spacing: 0px; white-space: normal; font-size-adjust: none; font-stretch: normal; background-color: rgb(255, 255, 255); -webkit-text-stroke-width: 0px;'>　　事实的情况——完完全全不是这么回事，很多男生这样回答，他们觉得除非你们两人都认为应该做这件事，否则对彼此都没有什么好处;他们更强调，在男女关系更进一步的时候，男方往往会误解女人的行为讯号，这点是很重要而且该表明的。</p><p align='center' style='font: 14px/28px 宋体; margin: 8px 0px 0px; padding: 0px; color: rgb(0, 0, 0); text-transform: none; text-indent: 0px; letter-spacing: normal; word-spacing: 0px; white-space: normal; font-size-adjust: none; font-stretch: normal; background-color: rgb(255, 255, 255); -webkit-text-stroke-width: 0px;'><img src='http://localhost:8080/iportal/upload/20150121/89301421809554782.jpg' _src='http://localhost:8080/iportal/upload/20150121/89301421809554782.jpg'/><div align='left'></div></p><p style='font: 14px/28px 宋体; margin: 8px 0px 0px; padding: 0px; color: rgb(0, 0, 0); text-transform: none; text-indent: 0px; letter-spacing: normal; word-spacing: 0px; white-space: normal; font-size-adjust: none; font-stretch: normal; background-color: rgb(255, 255, 255); -webkit-text-stroke-width: 0px;'>五、男人认为，女人应该为<a class='cmsLink' style='color: rgb(0, 0, 0); line-height: 18px; text-decoration: none; border-bottom-color: rgb(204, 0, 102); border-bottom-width: 1px; border-bottom-style: dashed;' href='http://huaiyun.pcbaby.com.cn/' target='_blank'>怀孕</a>负责!</p><p style='font: 14px/28px 宋体; margin: 8px 0px 0px; padding: 0px; color: rgb(0, 0, 0); text-transform: none; text-indent: 0px; letter-spacing: normal; word-spacing: 0px; white-space: normal; font-size-adjust: none; font-stretch: normal; background-color: rgb(255, 255, 255); -webkit-text-stroke-width: 0px;'>　　事实的情况——没有这种事!二十岁的晓明说：“现在的社会，你只有靠自己照顾自己，这种事我是不可能靠别人来替我解决的。”除了<a class='cmsLink' style='color: rgb(0, 0, 0); line-height: 18px; text-decoration: none; border-bottom-color: rgb(204, 0, 102); border-bottom-width: 1px; border-bottom-style: dashed;' href='http://health.pclady.com.cn/ "+

"' target='_blank'>健康</a>与责任的问题之外，多数男人都明白，没有爱与经济基础的约会，不值得冒受孕的危险。</p><p style='font: 14px/28px 宋体; margin: 8px 0px 0px; padding: 0px; color: rgb(0, 0, 0); text-transform: none; text-indent: 0px; letter-spacing: normal; word-spacing: 0px; white-space: normal; font-size-adjust: none; font-stretch: normal; background-color: rgb(255, 255, 255); -webkit-text-stroke-width: 0px;'>　　六、男人不愿意谈论他们的<a class='cmsLink' style='color: rgb(0, 0, 0); line-height: 18px; text-decoration: none; border-bottom-color: rgb(204, 0, 102); border-bottom-width: 1px; border-bottom-style: dashed;' href='http://emotion.pclady.com.cn/ "+

"' target='_blank'>感情</a>!</p><p style='font: 14px/28px 宋体; margin: 8px 0px 0px; padding: 0px; color: rgb(0, 0, 0); text-transform: none; text-indent: 0px; letter-spacing: normal; word-spacing: 0px; white-space: normal; font-size-adjust: none; font-stretch: normal; background-color: rgb(255, 255, 255); -webkit-text-stroke-width: 0px;'>　　事实的情况——在男女交往的过程中，没有一方愿意先开口说“我爱你”，因为你不知道说出这句话的结果是好是坏!但我们可以肯定一件事，双方感情的巨轮启动后，几乎任何一个男人都会和女人一样，心里充满了柔情;既然会在一起，象征的意义就很深远了，是不是?</p><p></p>";
		System.out.println(Html2Text(s));
	}

}
