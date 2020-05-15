package com.String.learn;

/**
 * 获取一个字符串在另一个字符串中出现的次数
 *  比如： 获取 "ab" 在 "abkkabsdabsdfabffab"中出现的次数
 */
public class StringAlgorithm2 {
    public static void main(String[] args) {
        String mainStr = "abkkabsdabsdfabffab";
        String subStr = "ab";
        int count = getCount(mainStr,subStr);
        System.out.println(count);
    }

    public static int getCount(String mainStr, String subStr){
        int mainLength = mainStr.length();
        int subLength = subStr.length();
        int count = 0;
        int index = 0;
        if (subLength <= mainLength){
            while((index = mainStr.indexOf(subStr,index)) != -1){
                count++;
//                mainStr = mainStr.substring(index + subStr.length());
                index = subLength;
            }
            return count;
        }else{
            return 0;
        }
    }
}

