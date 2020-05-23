package newFeature;

import java.util.Optional;

/**
 * Optional类： 为了在程序中避免出现空指针异常而创建的
 *
 * Optional.of(T t): 创建一个Optional实例，t必须非空
 * Optional.empty(): 创建一个空的Optional实例
 * Optional.ofNullable(T t): t可以为空
 */
public class OptionalTest {
    public static void main(String[] args) {
        Dog dog = new Dog(40,true);
        Optional<Dog> optionalDog = Optional.of(dog);//dog必须非空
        Dog dog1 = optionalDog.get(); //获得 dog必须非空
        System.out.println(optionalDog);
        System.out.println(dog1);

        dog =null;
        Optional<Dog> optionalDog1 = Optional.ofNullable(dog);
        System.out.println(optionalDog1);
        //orElse(T  t): 如果当前的Optional内部封装的t是非空，则返回内部的t，
        //如果当前的Optional内部封装的t是null，则返回orElse中的参数
        Dog dog2 = optionalDog1.orElse(new Dog(50, true));
        System.out.println(dog2);

        Optional<Object> empty = Optional.empty();
        if (empty.isPresent()){    // Optional是否包含对象
        }else{
            System.out.println("不包含对象");
        }
        if (empty.isEmpty()){      //Optional是否包含对象
            System.out.println("包含对象");
        }


    }
}

class Dog{
    private int age;
    private boolean isLoneliness;

    public Dog() {

    }

    public Dog(int age, boolean isLoneliness) {
        this.age = age;
        this.isLoneliness = isLoneliness;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isLoneliness() {
        return isLoneliness;
    }

    public void setLoneliness(boolean loneliness) {
        isLoneliness = loneliness;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "age=" + age +
                ", isLoneliness=" + isLoneliness +
                '}';
    }
}
