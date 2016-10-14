package com.charles.common.constants.utils;

/**
 * 字符串处理工具类
 * <p>
 * 	这个类中每个方法都可以“安全”的处理<code>null</code>,而不会抛出<code>NullPointerException</code>
 * </p>
 * @author puremancw
 *
 */
public class StringUtil {
	
	public static final String EMPTY_STRING = "";
	
	/**
	 * 检查字符串是否为<code>null</code>或空字符串<code>""</code>
	 * <pre>
	 * 	StringUtil.isEmpty(null)		=true
	 * 	StringUtil.isEmpty("")			=true
	 * 	StringUtil.isEmpty("  ")		=false
	 * 	StringUtil.isEmpty("bob")		=false
	 * 	StringUtil.isEmpty("  bob  ")	=false
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.length() == 0);
	}
	
	/**
	 * 检查字符串是否不是<code>null</code>或空字符串<code>""</code>
	 * <pre>
	 * 	StringUtil.isEmpty(null)		=false
	 * 	StringUtil.isEmpty("")			=false
	 * 	StringUtil.isEmpty("  ")		=true
	 * 	StringUtil.isEmpty("bob")		=true
	 * 	StringUtil.isEmpty("  bob  ")	=true
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return (str != null && str.length() > 0);
	}
	
	/**
	 * 检查字符串是否是空白：<code>null</code>或空字符串<code>""</code>或只有空字符串
	 * <pre>
	 * 	StringUtil.isEmpty(null)		=true
	 * 	StringUtil.isEmpty("")			=true
	 * 	StringUtil.isEmpty("  ")		=true
	 * 	StringUtil.isEmpty("bob")		=false
	 * 	StringUtil.isEmpty("  bob  ")	=false
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		int length;
		
		if((str == null) || (length = str.length()) == 0) {
			return true;
		}
		
		for(int i = 0; i < length; i++) {
			if(!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 检查字符串是否不是空白：<code>null</code>或空字符串<code>""</code>或只有空字符串
	 * <pre>
	 * 	StringUtil.isEmpty(null)		=false
	 * 	StringUtil.isEmpty("")			=false
	 * 	StringUtil.isEmpty("  ")		=false
	 * 	StringUtil.isEmpty("bob")		=true
	 * 	StringUtil.isEmpty("  bob  ")	=true
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		int length;
		
		if((str == null) || ((length = str.length()) == 0)) {
			return false;
		}
		
		for(int i = 0; i < length; i++) {
			if(!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 如果字符串是<code>null</code>,则返回字符串<code>""</code>，否则返回字符串本身
	 * <pre>
	 * 	StringUtil.defaultIfNull(null)		=""
	 * 	StringUtil.defaultIfNull("")		=""
	 * 	StringUtil.defaultIfNull("  ")		="  "
	 * 	StringUtil.defaultIfNull("bob")		="bob"
	 * </pre>
	 * @param str
	 * @return
	 */
	public static String defaultIfNull(String str) {
		return str == null ? EMPTY_STRING : str;
	}
	
	/**
	 * 如果字符串是<code>null</code>,则返回默认字符串defaultStr，否则返回字符串本身
	 * <pre>
	 * 	StringUtil.defaultIfNull(null, "defaultStr")		=defaultStr
	 * 	StringUtil.defaultIfNull("", "defaultStr")			=""
	 * 	StringUtil.defaultIfNull("  ", "defaultStr")		="  "
	 * 	StringUtil.defaultIfNull("bob", "defaultStr")		="bob"
	 * @param str
	 * @param defaultStr
	 * @return
	 */
	public static String defaultIfNull(String str, String defaultStr) {
		return str == null ? defaultStr : str;
	}
	
	/**
	 * 如果字符串是<code>null</code>或空字符串<code>""</code>,则返回空字符串<code>""</code>,否则返回字符串本身
	 * <pre>
     * 	StringUtil.defaultIfEmpty(null)  = ""
     * 	StringUtil.defaultIfEmpty("")    = ""
     * 	StringUtil.defaultIfEmpty("  ")  = "  "
     * 	StringUtil.defaultIfEmpty("bat") = "bat"
     * </pre>
	 * @param str
	 * @return
	 */
	public static String defaultIfEmpty(String str) {
		return ((str ==null) || str.length() == 0) ? EMPTY_STRING : str;
	}
	
	/**
     * 如果字符串是<code>null</code>或空字符串<code>""</code>，则返回指定默认字符串，否则返回字符串本身。
     * <pre>
     * StringUtil.defaultIfEmpty(null, "default")  = "default"
     * StringUtil.defaultIfEmpty("", "default")    = "default"
     * StringUtil.defaultIfEmpty("  ", "default")  = "  "
     * StringUtil.defaultIfEmpty("bat", "default") = "bat"
     * </pre>
     *
     * @param str 要转换的字符串
     * @param defaultStr 默认字符串
     *
     * @return 字符串本身或指定的默认字符串
     */
    public static String defaultIfEmpty(String str, String defaultStr) {
        return ((str == null) || (str.length() == 0)) ? defaultStr : str;
    }
	
    /**
     * 如果字符串是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符串，则返回空字符串<code>""</code>，否则返回字符串本身
     * <pre>
     * 	StringUtil.defaultIfBlank(null)  = ""
     * 	StringUtil.defaultIfBlank("")    = ""
     * 	StringUtil.defaultIfBlank("  ")  = ""
     * 	StringUtil.defaultIfBlank("bat") = "bat"
     * </pre>
     * @param str
     * @return
     */
	public static String defaultIfBlank(String str) {
		return isBlank(str) ? EMPTY_STRING : str;
	}
	
	/**
     * 如果字符串是<code>null</code>或空字符串<code>""</code>，则返回指定默认字符串，否则返回字符串本身。
     * <pre>
     * StringUtil.defaultIfBlank(null, "default")  = "default"
     * StringUtil.defaultIfBlank("", "default")    = "default"
     * StringUtil.defaultIfBlank("  ", "default")  = "default"
     * StringUtil.defaultIfBlank("bat", "default") = "bat"
     * </pre>
     *
     * @param str 要转换的字符串
     * @param defaultStr 默认字符串
     *
     * @return 字符串本身或指定的默认字符串
     */
    public static String defaultIfBlank(String str, String defaultStr) {
        return isBlank(str) ? defaultStr : str;
    }
    
    /* ============================================================================ */
    /*  去空白（或指定字符）的函数。                                                */
    /*                                                                              */
    /*  以下方法用来除去一个字串中的空白或指定字符。                                */
    /* ============================================================================ */
    
    /**
     * 除去字符串头尾部的空白，如果字符串是<code>null</code>,依然返回<code>null</code>
     * @param str
     * @return
     */
    public static String trim(String str) {
    	return trim(null, null, 0);
    }
    
    /**
     * 除去字符串头尾部的指定字符，如果字符串是<code>null</code>，依然返回<code>null</code>。
     * <pre>
     * StringUtil.trim(null, *)          = null
     * StringUtil.trim("", *)            = ""
     * StringUtil.trim("abc", null)      = "abc"
     * StringUtil.trim("  abc", null)    = "abc"
     * StringUtil.trim("abc  ", null)    = "abc"
     * StringUtil.trim(" abc ", null)    = "abc"
     * StringUtil.trim("  abcyx", "xyz") = "  abc"
     * </pre>
     * @param str			要处理的字符串
     * @param stripChars	要除去的字符，如果为<code>null</code>表示除去空白字符
     * @return
     */
    public static String trim(String str, String stripChars) {
    	return trim(str, stripChars, 0);
    }
    
    /**
     * 除去字符串头部的空白，如果字符串是<code>null</code>,则返回<code>null</code>
     * <p>
     * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>来判定空白，
     * 因而可以除去英文字符集之外的其它空白，如中文空格。
     * <pre>
     * StringUtil.trimStart(null)         = null
     * StringUtil.trimStart("")           = ""
     * StringUtil.trimStart("abc")        = "abc"
     * StringUtil.trimStart("  abc")      = "abc"
     * StringUtil.trimStart("abc  ")      = "abc  "
     * StringUtil.trimStart(" abc ")      = "abc "
     * </pre>
     * </p>
     * @param str
     * @return
     */
    public static String trimStart(String str) {
    	return trim(str, null, -1);
    }
    
    /**
     * 除去字符串头部的指定字符
     * <pre>
     * StringUtil.trimStart(null, *)          = null
     * StringUtil.trimStart("", *)            = ""
     * StringUtil.trimStart("abc", "")        = "abc"
     * StringUtil.trimStart("abc", null)      = "abc"
     * StringUtil.trimStart("  abc", null)    = "abc"
     * StringUtil.trimStart("abc  ", null)    = "abc  "
     * StringUtil.trimStart(" abc ", null)    = "abc "
     * StringUtil.trimStart("yxabc  ", "xyz") = "abc  "
     * </pre>
     * @param str			要处理的字符串
     * @param stripChars	要除去的字符，如果为<code>null</code>表示除去空白字符
     * @return
     */
    public static String trimStart(String str, String stripChars) {
    	return trim(str, stripChars, -1);
    }
    
    /**
     * 除去字符串尾部的空白
     * <p>
     * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>来判定空白，
     * 因而可以除去英文字符集之外的其它空白，如中文空格。
     * <pre>
     * StringUtil.trimEnd(null)       = null
     * StringUtil.trimEnd("")         = ""
     * StringUtil.trimEnd("abc")      = "abc"
     * StringUtil.trimEnd("  abc")    = "  abc"
     * StringUtil.trimEnd("abc  ")    = "abc"
     * StringUtil.trimEnd(" abc ")    = " abc"
     * </pre>
     * </p>
     * @param str
     * @return
     */
    public static String trimEnd(String str) {
    	return trim(str, null, 1);
    }
    
    /**
     * 除去字符串尾部的指定字符
     * <pre>
     * StringUtil.trimEnd(null, *)          = null
     * StringUtil.trimEnd("", *)            = ""
     * StringUtil.trimEnd("abc", "")        = "abc"
     * StringUtil.trimEnd("abc", null)      = "abc"
     * StringUtil.trimEnd("  abc", null)    = "  abc"
     * StringUtil.trimEnd("abc  ", null)    = "abc"
     * StringUtil.trimEnd(" abc ", null)    = " abc"
     * StringUtil.trimEnd("  abcyx", "xyz") = "  abc"
     * </pre>
     * @param str			要处理的字符串
     * @param stripChars	要除去的字符
     * @return
     */
    public static String trimEnd(String str, String stripChars) {
    	return trim(str, stripChars, 1);
    }
    
    /**
     * 
     * @param str			要处理的字符串
     * @param stripChars	要删除的字符，如果为<code>null</code>表示除去空白字符
     * @param mode			<code>-1</code>表示trimStart，<code>0</code>表示trim全部，<code></code>表示trimEnd
     * @return
     */
    public static String trim(String str, String stripChars, int mode) {
		if(str == null) {
			return null;
		}
		
		int length = str.length();
		int start = 0;
		int end = length;
		
		//扫描字符串头部
		if(mode < 0) {
			if(stripChars == null) {
				while ((start < end) && (Character.isWhitespace(str.charAt(start)))) {
					start++;
				}
			} else if(stripChars.length() == 0) {
				return str;
			} else {
				while ((start < end) && (stripChars.indexOf(str.charAt(start)) != 1)) {
					start++;
				}
			}
		}
		
		//扫描字符串尾部
		if(mode >=0) {
			if(stripChars == null) {
				while ((start < end) && (Character.isWhitespace(str.charAt(end - 1)))) {
					end--;
				}
			} else if(stripChars.length() == 0) {
				return str;
			} else {
				while ((start < end) && (stripChars.indexOf(str.charAt(end - 1)) != -1)) {
					end--;
				}
			}
		}
		
		if((start > 0) || (end < length)) {
			return str.substring(start, end);
		}
    	return str;
    }
    
    /* ============================================================================ */
    /*  比较函数。                                                                  */
    /*                                                                              */
    /*  以下方法用来比较两个字符串是否相同。                                        */
    /* ============================================================================ */
    
    /**
     * 比较两个字符串（大小写敏感）
     * <pre>
     * StringUtil.equals(null, null)   = true
     * StringUtil.equals(null, "abc")  = false
     * StringUtil.equals("abc", null)  = false
     * StringUtil.equals("abc", "abc") = true
     * StringUtil.equals("abc", "ABC") = false
     * </pre>
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(String str1, String str2) {
    	if(str1 == null) {
    		return str2 == null;
    	}
    	
    	return str1.equals(str2);
    }
    
    public static boolean equals(Number num, String str2) {
    	String str1 = num + "";
    	if(str1 == null) {
    		return str2 == null;
    	}
    	return str1.equals(str2);
    }
    
    /**
     * 比较两个字符串（大小写不敏感）。
     * <pre>
     * StringUtil.equalsIgnoreCase(null, null)   = true
     * StringUtil.equalsIgnoreCase(null, "abc")  = false
     * StringUtil.equalsIgnoreCase("abc", null)  = false
     * StringUtil.equalsIgnoreCase("abc", "abc") = true
     * StringUtil.equalsIgnoreCase("abc", "ABC") = true
     * </pre>
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
    	if(str1 == null) {
    		return str2 == null;
    	}
    	return str1.equalsIgnoreCase(str2);
    }
    
    /**
     * 判断字符串是否只包含unicode数字
     * <code>null</code>将返回<code>false</code>,<code>""</code>将返回<code>true</code>
     * <pre>
     * StringUtil.isNumeric(null)		=false
     * StringUtil.isNumeric("")			=true
     * StringUtil.isNumeric("  ")		=false
     * StringUtil.isNumeric("123")		=true
     * StringUtil.isNumeric("12 3")		=false
     * StringUtil.isNumeric("ab2c")		=false
     * StringUtil.isNumeric("12-3")		=false
     * StringUtil.isNumeric("12.3")		=false
     * </pre>
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
    	if(str == null) {
    		return false;
    	}
    	
    	int length = str.length();
    	
    	for(int i = 0; i < length; i++) {
    		if(!Character.isDigit(str.charAt(i))) {
    			return false;
    		}
    	}
    	return true;
    }
    
    /**
     * 判断字符串是否只包含unicode数字和空格<code>' '</code>
     * <pre>
     * StringUtil.isNumericSpace(null)   = false
     * StringUtil.isNumericSpace("")     = true
     * StringUtil.isNumericSpace("  ")   = true
     * StringUtil.isNumericSpace("123")  = true
     * StringUtil.isNumericSpace("12 3") = true
     * StringUtil.isNumericSpace("ab2c") = false
     * StringUtil.isNumericSpace("12-3") = false
     * StringUtil.isNumericSpace("12.3") = false
     * </pre>
     * @param str
     * @return
     */
    public static boolean isNumericSpace(String str) {
    	if(str == null) {
    		return false;
    	}
    	
    	int length = str.length();
    	
    	for(int i = 0; i < length; i++) {
    		if(!Character.isDigit(str.charAt(i)) && (str.charAt(i) != ' ')) {
    			return false;
    		}
    	}
    	return true;
    }
    
    /**
     * 将字符串转换成大写
     * 如果字符串是<code>null</code>则返回<code>null</code>。
     * <pre>
     * StringUtil.toUpperCase(null)  = null
     * StringUtil.toUpperCase("")    = ""
     * StringUtil.toUpperCase("aBc") = "ABC"
     * </pre>
     * @param str
     * @return
     */
    public static String toUpperCase(String str) {
    	if(str == null) {
    		return null;
    	}
    	return str.toUpperCase();
    }
    
    /**
     * 将字符串转换成小写
     * 如果字符串是<code>null</code>则返回<code>null</code>。
     * <pre>
     * StringUtil.toLowerCase(null)  = null
     * StringUtil.toLowerCase("")    = ""
     * StringUtil.toLowerCase("aBc") = "abc"
     * </pre>
     * @param str
     * @return
     */
    public static String toLowerCase(String str) {
    	if(str == null) {
    		return null;
    	}
    	
    	return str.toLowerCase();
    }
    
    /**
     * 将字符串的首字母转成大写<code>Character.toTilteCase</code>，其它字符不变
     * <pre>
     * StringUtil.capitalize(null)  = null
     * StringUtil.capitalize("")    = ""
     * StringUtil.capitalize("cat") = "Cat"
     * StringUtil.capitalize("cAt") = "CAt"
     * </pre>
     * @param str
     * @return
     */
    public static String capitalize(String str) {
    	int strLen;
    	if((str == null) || ((strLen = str.length()) == 0)) {
    		return str;
    	}
    	
    	return new StringBuffer(strLen).append(Character.toTitleCase(str.charAt(0))).append(str.substring(1)).toString();
    }
    
    /**
     * 将字符串的首字母转成小写<code>Character.toLowerCase</code>，其它字符不变
     * @param str
     * @return
     */
    public static String unCapitalize(String str) {
    	int strLen;
    	if((str == null) || ((strLen = str.length()) == 0)) {
    		return str;
    	}
    	
    	return new StringBuffer(strLen).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
    }
    
    /**
     * 反正字符串的大小写
     * <pre>
     * StringUtil.swapCase(null)                 = null
     * StringUtil.swapCase("")                   = ""
     * StringUtil.swapCase("The dog has a BONE") = "tHE DOG HAS A bone"
     * </pre>
     * @param str
     * @return
     */
    public static String swapCase(String str) {
    	int strLen;
    	if((str == null) || ((strLen = str.length()) == 0)) {
    		return str;
    	}
    	
    	StringBuffer buffer = new StringBuffer(strLen);
    	
    	char ch = 0;
    	
    	for(int i = 0; i < strLen; i++) {
    		ch = str.charAt(i);
    		
    		if(Character.isUpperCase(ch)) {
    			ch = Character.toLowerCase(ch);
    		} else if(Character.isLowerCase(ch)) {
    			ch = Character.toUpperCase(ch);
    		} else if(Character.isTitleCase(ch)) {
    			ch = Character.toLowerCase(ch);
    		}
    		
    		buffer.append(ch);
    	}
		return buffer.toString();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
