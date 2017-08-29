package com.yincongyang.base.io.tokenizer;

import java.util.StringTokenizer;

/**
 * 字符串分割器
 * 
 * StringTokenizer类：根据自定义字符为分界符进行拆分，并将结果进行封装提供对应方法进行遍历取值，
 * StringTokenizer 方法不区分标识符、数和带引号的字符串，它们也不识别并跳过注释；该方法用途类似于split方法，只是对结果进行了封装；
 * 
 * StringTokenizer 是出于兼容性的原因而被保留的遗留类（虽然在新代码中并不鼓励使用它）。
 * 建议所有寻求此功能的人使用 String 的 split 方法或 Java.util.regex 包。
 * @author yincongyang
 *
 */
public class StringTokenizerTest {
	/**
	 * 不建议使用
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "hello,java,delphi,asp,php";
		StringTokenizer st = new StringTokenizer(str, ",");
		while (st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
	}
}
