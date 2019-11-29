package com.xiaoma.music.utils;

import java.io.PrintWriter;
/**
 * 打印流输出内容到网页工具类
 */
public final class PrintUtil {

	private PrintUtil() {}
	/**
	 * 打印流输出内容到网页
	 * @param pw 打印流
	 * @param str 要打印的字符串
	 */
	public static void Print(PrintWriter pw,String str){
		
		pw.print(str);
		
		pw.flush();
		
		pw.close();
	}
}
