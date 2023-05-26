package com.wei.java.steam;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by viruser on 2019/5/29.
 */
public class Widget {
    private final Color color;
    private final int weight;
    public Widget(Color color, int weight) {
        this.color = color;
        this.weight = weight;
    }
    public Color getColor() { return this.color; }
    public int getWeight() { return this.weight; }

    public static void main(String[] args) {
        List<Widget> widgets = new ArrayList<>();
        widgets.add(new Widget(Color.RED, 1));
        widgets.add(new Widget(Color.BLUE, 2));
        widgets.add(new Widget(Color.ORANGE, 3));
        widgets.add(new Widget(Color.YELLOW, 4));
        // stream() 获取当前的source, filter 和 mapToInt为intermediate操作, 进行数据筛选和转换,
        // 最后一个sum为terminal操作，对符合条件的全部widget做重量求和
        int sum = widgets.stream()
                .filter(w -> w.getColor() == Color.RED)
                .mapToInt(w -> w.getWeight())
                .sum();
        System.out.println(sum);
    }
}
