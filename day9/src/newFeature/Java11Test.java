package newFeature;

/**
 *
 */
public class Java11Test {
    public static void main(String[] args) {
        newString();
    }

    public static void newString(){
        System.out.println("  \t  \n    ".isBlank());//判断是不是空白

        System.out.println("  sa dj fg   \n".strip());//去除两边的空白，与trim()差不多
        System.out.println("  sa dj fg   \n".stripTrailing());//去尾（右）空白
        System.out.println("  sa dj fg   \n".stripLeading()+"al"); //去头（左）空白

        String str = "奥利给";
        String str1 = str.repeat(10);  //将现有的字符串重复多少次返回
        System.out.println(str1);

        String str2 = "sad\nrfh\nhgf\nasd\nfuh\n";
        System.out.println(str2.lines().count()); //看它换了多少行
    }


}
