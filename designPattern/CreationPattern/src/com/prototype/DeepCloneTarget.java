package com.prototype;

import java.io.*;
import java.util.Objects;

public class DeepCloneTarget implements Cloneable, Serializable {
    private String name;
    private String desc;
    private DeepClonePrototype deepClonePrototype;

    public DeepCloneTarget(String name, String desc, DeepClonePrototype deepClonePrototype) {
        this.name = name;
        this.desc = desc;
        this.deepClonePrototype = deepClonePrototype;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public DeepClonePrototype getDeepClonePrototype() {
        return deepClonePrototype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeepCloneTarget)) return false;
        DeepCloneTarget that = (DeepCloneTarget) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(desc, that.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, desc);
    }

    //重写clone方法实现深拷贝
    @Override
    protected DeepCloneTarget clone() {
        DeepCloneTarget deep = null;
        try {
            // 先让自己的基本类型复制
            deep = (DeepCloneTarget)super.clone();
            // 让引用类型它自己也克隆克隆
            deep.deepClonePrototype =(DeepClonePrototype) deepClonePrototype.clone();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        // 返回克隆体
        return deep;
    }

    // 使用对象的序列化实现深拷贝
    public DeepCloneTarget deepClone(){
        // 创建流对象
        ByteArrayOutputStream abos = null;
        ByteArrayInputStream abis = null;
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            // 序列化
            abos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(abos);
            // 把当前的对象以流的方式输出
            oos.writeObject(this);

            // 反序列化
            abis = new ByteArrayInputStream(abos.toByteArray());
            ois = new ObjectInputStream(abis);
            // 把对象读出来
            return (DeepCloneTarget) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                assert oos != null;
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                assert ois != null;
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                abos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                abis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
