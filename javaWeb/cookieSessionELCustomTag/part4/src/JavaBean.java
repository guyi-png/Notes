/*
 javaBean：
   用作JavaBean的类必须只有一个公共的，无参数的构造方法
   下面形式的java类就是JavaBean
*/

public class JavaBean {
    private String Name;
    private byte gender;
    private int age;
    private String address;

    public JavaBean(){
        System.out.println("hello world");
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
