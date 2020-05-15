package com.String.learn;

/**
 * 将一个字符串指定部分进行反转，比如"abcdefg" 反转为 "abfedcg"
 */
public class StringAlgorithm1 {
    public static void main(String[] args) {
        String str = "奥里给";
        String strReverse = reverse(str,0,2);
        System.out.println(strReverse);
    }

    //方式一：转化为char[]
//    public static String reverse(String str,int startIndex,int endIndex){
//
//        try{
//            if (startIndex > endIndex || startIndex < 0  || endIndex > str.length())
//                throw new Exception("error");
//        }catch(Exception e){
//            return e.getMessage();
//        }
//        char[] arr = null;
//        if (str != null){
//            arr = str.toCharArray();
//            for (int x = startIndex,y = endIndex; x < y; x++,y--){
//                char temp = arr[x];
//                arr[x] = arr[y];
//                arr[y] = temp;
//            }
//        }
//        return new String(arr);
//    }

    //方式二
//    public static String reverse(String str, int startIndex, int endIndex){
//        if (str != null){
//            String reverseStr = str.substring(0,startIndex);
//            for (int i = endIndex; i >= startIndex; i--){
//                reverseStr += str.charAt(i);
//            }
//            reverseStr += str.substring(endIndex+1);
//            return reverseStr;
//        }
//        return null;
//    }

    //第三种方式
    public static String reverse(String str,  int startIndex, int endIndex){
        if (str != null){
            StringBuilder sb = new StringBuilder(str.length());
            sb.append(str.substring(0,startIndex));
            for (int i = endIndex; i >= startIndex;i--){
                sb.append(str.charAt(i));
            }
            sb.append(str.substring(endIndex+1));

            return sb.toString();
        }
        return null;
    }
}
