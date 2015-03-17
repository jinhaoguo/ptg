package org.gdut.ptg.util;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringEscapeUtils;



/**
 * 字符串操作类
 * 
 */
public class StringUtil {
	/**
	 * 对字符串 escape 编码
	 * 
	 * @param src
	 * @return
	 */
	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);

		for (i = 0; i < src.length(); i++) {

			j = src.charAt(i);

			if (Character.isDigit(j) || Character.isLowerCase(j)
					|| Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	
	/**
	 * 根据变量替换模板。 
	 * @param template	模板。格式:/temp/{a}/{b}
	 * @param map		需要替换的变量。
	 * @return
	 * @throws Exception 
	 * String
	 */
	public static String replaceVariable(String template,
			Map<String, String> map) throws Exception {
		Pattern regex = Pattern.compile("\\{(.*?)\\}");
		Matcher regexMatcher = regex.matcher(template);
		while (regexMatcher.find()) {
			String key = regexMatcher.group(1);
			String toReplace = regexMatcher.group(0);
			String value = (String) map.get(key);
			if (value != null)
				template = template.replace(toReplace, value);
			else
				throw new Exception("没有找到[" + key + "]对应的变量值，请检查表变量配置!");
		}

		return template;
	}

	/**
	 * 对编码的字符串解码
	 * 
	 * @param src
	 * @return
	 */
	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(
							src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(
							src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

	/**
	 * 判断指定的内容是否存在
	 * 
	 * @param content
	 *            内容
	 * @param begin
	 *            开始标签
	 * @param end
	 *            结束标签
	 * @return
	 */
	public static boolean isExist(String content, String begin, String end) {
		String tmp = content.toLowerCase();
		begin = begin.toLowerCase();
		end = end.toLowerCase();
		int beginIndex = tmp.indexOf(begin);
		int endIndex = tmp.indexOf(end);
		return (beginIndex != -1 && endIndex != -1 && beginIndex < endIndex);
	}

	/**
	 * 去掉前面的指定字符
	 * 
	 * @param toTrim
	 * @param trimStr
	 * @return
	 */
	public static String trimPrefix(String toTrim, String trimStr) {
		while (toTrim.startsWith(trimStr)) {
			toTrim = toTrim.substring(trimStr.length());
		}
		return toTrim;
	}

	/**
	 * 删除后面指定的字符
	 * 
	 * @param toTrim
	 * @param trimStr
	 * @return
	 */
	public static String trimSufffix(String toTrim, String trimStr) {
		while (toTrim.endsWith(trimStr)) {
			toTrim = toTrim.substring(0, toTrim.length() - trimStr.length());
		}
		return toTrim;
	}

	/**
	 * 删除指定的字符
	 * 
	 * @param toTrim
	 * @param trimStr
	 * @return
	 */
	public static String trim(String toTrim, String trimStr) {
		return trimSufffix(trimPrefix(toTrim, trimStr), trimStr);
	}

	/**
	 * 编码html
	 * 
	 * @param content
	 * @return
	 */
	public static String escapeHtml(String content) {
		return StringEscapeUtils.escapeHtml(content);
	}

	/**
	 * 反编码html
	 * 
	 * @param content
	 * @return
	 */
	public static String unescapeHtml(String content) {
		return StringEscapeUtils.unescapeHtml(content);
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().equals("");
	}

	/**
	 * 判断字符串非空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static String replaceVariable(String template, String repaceStr) {
		Pattern regex = Pattern.compile("\\{(.*?)\\}");
		Matcher regexMatcher = regex.matcher(template);
		if (regexMatcher.find()) {
			String toReplace = regexMatcher.group(0);
			template = template.replace(toReplace, repaceStr);
		}
		return template;
	}

	/**
	 * 截取字符串 中文为两个字节，英文为一个字节。 两个英文为一个中文。
	 * 
	 * @param str
	 * @param len
	 * @return
	 */
	public static String subString(String str, int len, String chopped) {
		if (str == null || "".equals(str))
			return "";
		char[] chars = str.toCharArray();
		int cnLen = len * 2;
		String tmp = "";
		boolean isOver = false;
		int iLen = 0;
		for (int i = 0; i < chars.length; i++) {
			int iChar = (int) chars[i];
			if (iChar <= 128)
				iLen = iLen + 1;
			else
				iLen = iLen + 2;
			if (iLen >= cnLen) {
				isOver = true;
				break;
			}

			tmp += String.valueOf(chars[i]);
		}
		if (isOver) {
			tmp += chopped;
		}
		return tmp;
	}

	/**
	 * 截取字符串 中文为两个字节，英文为一个字节。 两个英文为一个中文。
	 * 
	 * @param str
	 * @return
	 */
	public static String subString(String str) {
		int len = 25;
		String tmp = AppConfigUtil.get("titleLen");
		String regex="<(?:(?:/([^>]+)>)|(?:!--([\\S|\\s]*?)-->)|(?:([^\\s/>]+)\\s*((?:(?:\"[^\"]*\")|(?:'[^']*')|[^\"'<>])*)/?>))";
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher=pattern.matcher(str);
		if (matcher.find()) {
			return str;
		}else {
			if (StringUtil.isNotEmpty(tmp)) {
				len = Integer.parseInt(tmp);
			}
			return subString(str, len, "...");
		}
	}
	

	/**
	 * 判读输入字符是否是数字
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNumberic(String s) {
		return isNotEmpty(s) 
				&& (validByRegex("^[-+]{0,1}\\d*\\.{0,1}\\d+$", s) 
						|| validByRegex("^0[x|X][\\da-eA-E]+$", s));
	}

	/**
	 * 是否是整数。
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isInteger(String s) {
		return validByRegex("^[-+]{0,1}\\d*$", s);
	}

	/**
	 * 是否是电子邮箱
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmail(String s) {
		return validByRegex("(\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*)*", s);
	}

	/**
	 * 手机号码
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isMobile(String s) {
		return validByRegex("^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\\d{8})$", s);
	}

	/**
	 * 电话号码
	 * 
	 * @param
	 * @return
	 */
	public static boolean isPhone(String s) {
		return validByRegex("(0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?", s);
	}

	/**
	 * 邮编
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isZip(String s) {
		return validByRegex("^[0-9]{6}$", s);
	}

	/**
	 * qq号码
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isQq(String s) {
		return validByRegex("^[1-9]\\d{4,9}$", s);
	}

	/**
	 * ip地址
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isIp(String s) {
		return validByRegex(
				"^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$",
				s);
	}

	/**
	 * 判断是否中文
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isChinese(String s) {
		return validByRegex("^[\u4e00-\u9fa5]+$", s);
	}

	/**
	 * 字符和数字
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isChrNum(String s) {
		return validByRegex("^([a-zA-Z0-9]+)$", s);
	}

	/**
	 * 判断是否是URL
	 * 
	 * @param url
	 * @return
	 */
	public static boolean isUrl(String url) {
		return validByRegex(
				"(http://|https://)?([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?",
				url);
	}

	/**
	 * 判断是否json格式
	 * 
	 * @param json
	 * @return
	 */
	public static Boolean isJson(String json) {
		if (isEmpty(json))
			return false;
		try {
			JSONObject.fromObject(json);
			return true;
		} catch (JSONException e) {
			try {
				JSONArray.fromObject(json);
				return true;
			} catch (JSONException ex) {
				return false;
			}
		}
	}

	/**
	 * 使用正则表达式验证。
	 * 
	 * @param regex
	 * @param input
	 * @return
	 */
	public static boolean validByRegex(String regex, String input) {
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher regexMatcher = p.matcher(input);
		return regexMatcher.find();
	}

	/**
	 * 判断某个字符串是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 把字符串的第一个字母转为大写或小写
	 * 
	 * @param targetString	要转换的字符串
	 * @param isToUpperCase	是否转成大写
	 * @return
	 */
	public static String changeFirstLetterCase(String targetString, boolean isToUpperCase) {
		if (isEmpty(targetString)) return targetString;
		char[] oneChar = new char[1];
		oneChar[0] = targetString.charAt(0);
		String firstChar = new String(oneChar);
		firstChar = isToUpperCase ? firstChar.toUpperCase() : firstChar.toLowerCase();
		return firstChar + targetString.substring(1);
	}

	/**
	 * 把字符串的第一个字母转为大写
	 * 
	 * @param newStr
	 * @return
	 */
	public static String makeFirstLetterUpperCase(String newStr) {
		return changeFirstLetterCase(newStr, true);
	}

	/**
	 * 把字符串的第一字每转为小写
	 * 
	 * @param newStr
	 * @return
	 */
	public static String makeFirstLetterLowerCase(String newStr) {
		return changeFirstLetterCase(newStr, false);
	}

	/**
	 * 替换json特殊字符转码
	 * 
	 * @param str
	 * @return
	 */
	public static String jsonUnescape(String str) {
		return str.replace("&quot;", "\"").replace("&nuot;", "\n");
	}

	/**
	 * 字符串 编码转换
	 * 
	 * @param str
	 *            字符串
	 * @param from
	 *            原來的編碼
	 * @param to
	 *            轉換后的編碼
	 * @return
	 */
	public static String encodingString(String str, String from, String to) {
		try {
			return new String(str.getBytes(from), to);
		} catch (Exception e) {
		}
		return str;
	}

}
