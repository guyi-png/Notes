package newFeature;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Java10Test {

    public static void main(String[] args) {
        /*
        局部类型推断
        */
        // var是一个标识符
        var num = 13;
        var list = new ArrayList<Integer>();
        list.add(35);
        list.add(56);
        for (var i : list){
            System.out.println(i);
        }

        /*
        集合中新增的copyOf(),用于创建一个只读的集合
        */
        var list1 = List.of("java","javascript","json");//创建只读集合
        var copy1 = List.copyOf(list1);
        System.out.println(copy1 == list1); //true

        var list2 = new ArrayList<String>();
        var copy2 = List.copyOf(list2);
        System.out.println(copy2 == list2);  //false
    }
}
