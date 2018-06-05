package com.weison.model.dataStruct.hashSet;

public class F {

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private int count;

    public F(int count) {
        this.count = count;
    }

    //根据count的值来判断两个对象是否相等。
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj != null && obj.getClass() == F.class) {
            F a = (F) obj;
            return this.count == a.count;
        }
        return false;
    }

    //根据count来计算hashCode值。
    public int hashCode() {
        return this.count;
    }
}
