package com.annotationAndEnum;

/**
 *一: 枚举类的使用:
 * 1.枚举类的理解: 类的对象只有有限个,确定的,可称此类为枚举类
 * 2.当需要定义一组常量时,建议使用枚举类
 * 3. 如果枚举类中只有一个对象,则可以作为单例模式的实现方式
 * 二: 如何定义枚举类:
 *      方式一: jdk5.0之前, 自定义枚举类
 *      方式二:jdk5.0之后,使用enum关键字定义枚举类
 */
public class EnumTest {
    public static void main(String[] args){
        Season winter = Season.WINTER;
        System.out.println(winter.toString());

        Season1 summer = Season1.SUMMER;
        System.out.println(summer);

        Season1[] values = Season1.values();  ///返回Season1枚举类中的所有对象
        for (int i =0; i < values.length; i++){
            System.out.println(values[i]);
        }
        Season1 winter1 = Season1.valueOf("WINTER"); //   返回Season1枚举类中指定的对象
        System.out.println(winter1);
        summer.show();
        winter1.show();
    }
}

//自定义枚举类
class Season extends Object{
    //声明Season对象的属性
    private final String seasonName;
    private final String seasonDesc;

    //私有化类的构造器, 并给对象属性赋值
    private Season(String seasonName,String seasonDesc){
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    //提供多个枚举对象
    public static final Season SPRING = new Season("春天","春天是美好的季节,适合去外面");
    public static final Season SUMMER = new Season("夏天","夏天适合去冒险,那份故事总是触及人心");
    public static final Season AUTUMN = new Season("秋天","秋天,一个个故事如同落叶一样掉落,最后逝去");
    public static final Season WINTER = new Season("冬天","冬天,雪是那么的洁白,似你我曾经的童年,可是如今,时间总是会变");

    //其他的方法

    public String getSeasonDesc() {
        return seasonDesc;
    }

    public String getSeasonName() {
        return seasonName;
    }

    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}



interface Info{
    void show();
}
//使用关键字enum,此类默认继承与java.lang.Enum
enum Season1 implements Info{
    //提供多个枚举对象
    SPRING("春天","春天是美好的季节,适合去外面"){
        @Override
        public void show() {
            System.out.println("Hello,春天");  //各自实现自己的接口方法
        }
    },
    SUMMER("夏天","夏天适合去冒险,那份故事总是触及人心"){
        @Override
        public void show() {
            System.out.println("Hello,夏天");
        }
    },
    AUTUMN("秋天","秋天,一个个故事如同落叶一样掉落,最后逝去"){
        @Override
        public void show() {
            System.out.println("Hello,秋天");
        }
    },
    WINTER("冬天","冬天,雪是那么的洁白,似你我曾经的童年,可是如今,时间总是会变"){
        @Override
        public void show() {
            System.out.println("Hello,冬天");
        }
    };
    //声明Season对象的属性
    private final String seasonName;
    private final String seasonDesc;

    //私有化类的构造器, 并给对象属性赋值
    Season1(String seasonName, String seasonDesc){
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    //其他的方法
    public String getSeasonDesc() {
        return seasonDesc;
    }

    public String getSeasonName() {
        return seasonName;
    }
}