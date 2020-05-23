package newFeature;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 */
public class TryTest {
    public static void main(String[] args) {
        //java8
//        try(InputStreamReader reader = new InputStreamReader(System.in)){
//            char[] buffer = new char[1024];
//            int len;
//            while (true){
//                len = reader.read(buffer);
//                String str = new String(buffer,0,len);
//                if (str.startsWith("e"))
//                    break;
//                System.out.println(str);
//            }
//        }catch(IOException e){
//            e.getStackTrace();
//        }
        //Java9
        InputStreamReader reader = new InputStreamReader(System.in);
        try(reader){  //reader在这是常量
            char[] buffer = new char[1024];
            int len;
            while (true){
                len = reader.read(buffer);
                String str = new String(buffer,0,len);
                if (str.startsWith("exit"))
                    break;
                System.out.println(str);
            }
        }catch(IOException e){
            e.getStackTrace();
        }
    }
}
