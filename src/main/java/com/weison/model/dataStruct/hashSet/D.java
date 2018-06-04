package com.weison.model.dataStruct.hashSet;

public class D implements Comparable<D>
{

    public Integer age;

    public String name;

    public Integer getAge()
    {
        return age;
    }

    public String getName()
    {
        return name;
    }

    public D(Integer age, String name)
    {
        this.age = age;
        this.name = name;
    }

    public boolean equals(D another)
    {
        return another.age == this.age && another.name.equals(this.name);
    }

    public int hashCode()
    {
        return 2;
    }

    @Override
    public int compareTo(D another) throws NullPointerException, ClassCastException
    {
        return (another.age == this.age && another.name.equals(this.name)) ? 0 :
                (another.age > this.age ? -1 : 1);

    }
}
