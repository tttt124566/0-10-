/*
问题描述
给定一个字符串，求出其最长重复子串
例如：abcdabcd
最长重复子串是 abcd，最长重复子串可以重叠
例如：abcdabcda，这时最长重复子串是 abcda，中间的 a 是被重叠的。
后缀数组是一种数据结构，对一个字符串生成相应的后缀数组后，然后再排序，排完序依次检测相邻的两个字符串的开头公共部分。

abcbcab

0.abcbcab

1.bcbcab

2.cbcab

3.bcab

4.cab

5.ab

6.b

分成后缀数组

排序：（根据字典排序）

0.ab

1.abcbcab

2.b

3.bcab

4.bcbcab

5.cab

6.cbcab 

然后两两比较找出相同字符的长度

本例为： i(后缀数组下标)         temp(最大的相同数量，根据此数量截取)

            0(0-1数组比较以此类推)    2

            1                                  0

            2                                  1

            3                                  2

            4                                  0

            5(5-6数组比较)               1

*/
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class PostfixArray {
	public static void main(String[] args){
		String s = "abcbcab";
		System.out.println(maxLength(s));
	}
	
	public static List<String> maxLength(String s){
		Set<String> postfix = new TreeSet<String>();//存储后缀数组，并排序
		for(int i = 0; i < s.length(); i++){
			postfix.add(s.substring(i));
		}
		List<String> postfixList = new ArrayList<String>(postfix);//set转换成list方便调用
		int temp = 0;//最长的重复子序列截取位置
		
		for(int i = 0; i < postfixList.size() - 1; i++){//循环，找出最长的重复子序列下标，最长的重复子序列截取位置
			int len = maxlength(postfixList.get(i), postfixList.get(i + 1));//获取后缀数组两两比较的相同子序列长度
			if(len >= temp){
				temp = len;
				maxis = i;
			}
		}
		
		List<String> results = new ArrayList<String>();
		
		results.add(postfixList.get(maxis).substring(0, temp));//根据最长的重复子序列下标和截取位置，获得最长的重复子序列
		
		return results;
	}

	private static int maxlength(String next, String next2) {
		char[] c1 = next.toCharArray();
		char[] c2 = next2.toCharArray();
		int maxlen = 0;
		for(int i = 0; i < (c1.length > c2.length ? c2.length : c1.length); i++){
			if(c1[i] == c2[i]){
				maxlen++;
			}
		}
		return maxlen;
	}
}
