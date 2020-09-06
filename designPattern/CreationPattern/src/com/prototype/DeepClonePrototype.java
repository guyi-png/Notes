package com.prototype;

import java.io.Serializable;
import java.util.Objects;

public class DeepClonePrototype implements Cloneable, Serializable {
    private String prototypeName;
    private String prototypeDesc;

    public DeepClonePrototype(String prototypeName, String prototypeDesc) {
        this.prototypeName = prototypeName;
        this.prototypeDesc = prototypeDesc;
    }

    public String getPrototypeName() {
        return prototypeName;
    }

    public String getPrototypeDesc() {
        return prototypeDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeepClonePrototype)) return false;
        DeepClonePrototype that = (DeepClonePrototype) o;
        return Objects.equals(prototypeName, that.prototypeName) &&
                Objects.equals(prototypeDesc, that.prototypeDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prototypeName, prototypeDesc);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
