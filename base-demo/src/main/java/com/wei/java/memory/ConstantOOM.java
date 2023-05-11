package com.wei.java.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * JDK7及以前（了解）：-XX:PermSize设置永久代初始大小。-XX:MaxPermSize设置永久代最大可分配空间。（JDK7目前已经很少用了，这两个参数在JDK8及以后已经没有了，所以不必掌握，了解一下）
 * JDK8及以后：可以使用-XX:MetaspaceSize和-XX:MaxMetaspaceSize设置元空间初始大小以及最大可分配大小。
 * 例子：设置初始大小是100M，最大可分配空间也是100M。-XX:MetaspaceSize=100m -XX:MaxMetaspaceSize=100m。
 * 1.如果不指定元空间的大小，默认情况下，元空间最大的大小是系统内存的大小，元空间一直扩大，虚拟机可能会消耗完所有的可用系统内存。
 * 2.如果元空间内存不够用，就会报OOM。
 * 3.默认情况下，对应一个64位的服务端JVM来说，其默认的-XX:MetaspaceSize值为21MB，这就是初始的高水位线，一旦元空间的大小触及这个高水位线，就会触发Full GC并会卸载没有用的类，然后高水位线的值将会被重置。
 * 4.从第3点可以知道，如果初始化的高水位线设置过低，会频繁的触发Full GC，高水位线会被多次调整。所以为了避免频繁GC以及调整高水位线，建议将-XX:MetaspaceSize设置为较高的值，而-XX:MaxMetaspaceSize不进行设置。
 * -XX:MaxMetaspaceSize不设置就是无限制
 *
 * VM Args: -Xms20m -Xmx20m -XX:MetaspaceSize=20m -XX:MaxMetaspaceSize=20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class ConstantOOM {
    public static void main(String[] args) {
        int i = 10000;
        List<String> list = new ArrayList<>();
        while (true) {
            list.add(String.valueOf(++i).intern());
        }
    }
}
