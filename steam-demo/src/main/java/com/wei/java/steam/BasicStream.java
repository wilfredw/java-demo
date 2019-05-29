package com.wei.java.steam;

import java.util.stream.IntStream;

/**
 * Created by viruser on 2019/5/29.
 */
// JDK 8
public class BasicStream {
    // IntStream, LongStream, DoubleStream. 当然我们也可以用Stream<Integer>, Stream<Long>, Stream<Double>,
    // 但是boxing 和 unboxing会很耗时, 所以特别为这三个基本数值型提供了对应的Stream
    public static void main(String[] args) {
        IntStream.of(new int[] {1, 2, 3}).forEach(System.out::print);// 123
        IntStream.range(1, 3).forEach(System.out::print);// [1,3) 12
        IntStream.rangeClosed(1, 3).forEach(System.out::print);// [1,3] 123
    }
}