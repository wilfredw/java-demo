package com.wei.java.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class LambdaTest {
    public static void main(String[] args) {
        Person p1 = new Person("John",23);
        Person p2 = new Person("Tom", 24);
        Person p3 = new Person("Abed", 24);

        // Lambda表达式
        Comparator<Person> c = (Person a1, Person a2) -> a1.getAge().compareTo(a2.getAge());
        System.out.println(c.compare(p1, p2));
        // ::方法引用
        Comparator<Person> c2 = Comparator.comparing(Person::getAge).thenComparing(Person::getName);
        System.out.println(c2.compare(p1, p2));
        System.out.println(c2.compare(p2, p3));

        // 获取getAge的方法引用的Function对象
        Function<Person, Integer> getAge = Person::getAge;
        Integer p1Age = getAge.apply(p1);

        PersonFunction<Person, String, String> hello = Person::hello;
        System.out.println(hello.apply(p1, "wang"));

        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        list.stream().map((x) -> x*x).forEach(System.out::print);
        System.out.println("");

    }
}
