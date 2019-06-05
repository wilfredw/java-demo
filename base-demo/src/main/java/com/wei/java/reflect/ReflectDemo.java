package com.wei.java.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by viruser on 2019/6/1.
 */
public class ReflectDemo extends ReflectSuperDemo implements ReflectInterfaceDemo {
    public static void main(String[] args) {

        Class<?> classDemo1 = null;
        Class<?> demo2 = null;
        Class<?> demo3 = null;
        try {
            classDemo1 = Class.forName("com.wei.java.reflect.ReflectDemo");
        } catch (Exception e) {
            e.printStackTrace();
        }
        demo2 = new ReflectDemo().getClass();
        demo3 = ReflectDemo.class;


        System.out.println("类名称   " + classDemo1.getName());
        System.out.println("类名称   " + demo2.getName());
        System.out.println("类名称   " + demo3.getName());

        System.out.println("获取构造函数，生成实例");
        ReflectDemo reflectDemo = null;
        ReflectDemo reflectDemo1 = null;
        ReflectDemo reflectDemo2 = null;
        ReflectDemo reflectDemo3 = null;
        Constructor<?> constructors[] = classDemo1.getConstructors();
        //取得全部的构造函数
        try {
            reflectDemo = (ReflectDemo) classDemo1.newInstance();

            for (int i = 0; i < constructors.length; i++) {
                Constructor<?> constructor = constructors[i];
                Class<?> params[] = constructor.getParameterTypes();
                int modif = constructor.getModifiers();
                System.out.print("Construct" + i
                        + " " + constructor.getName()
                        + " " + Modifier.toString(modif));
                for (int j = 0; j < params.length; j++) {
                    Class<?> p = params[j];
                    System.out.print(" -- " + j + " " + p.getName());
                }
                if (params.length == 0) {
                    reflectDemo1 = (ReflectDemo) constructors[i].newInstance();
                } else if (params.length == 1) {
                    reflectDemo2 = (ReflectDemo) constructors[i].newInstance(100);
                } else if (params.length == 2) {
                    reflectDemo3 = (ReflectDemo) constructors[i].newInstance(1, 2);
                }
                System.out.println();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        reflectDemo.setX(10);
        reflectDemo.setY(20);

        reflectDemo.print();
        reflectDemo1.print();
        reflectDemo2.print();
        reflectDemo3.print();

        // 获取接口
        Class<?> interfaces[] = classDemo1.getInterfaces();

        Class<?> superClass = classDemo1.getSuperclass();


        // 获取方法
        Method method[] = classDemo1.getMethods();
        for(int i=0;i<method.length;++i){
            Class<?> returnType=method[i].getReturnType();
            Class<?> para[]=method[i].getParameterTypes();
            int temp=method[i].getModifiers();
            System.out.print(Modifier.toString(temp)+" ");
            System.out.print(returnType.getName()+"  ");
            System.out.print(method[i].getName()+" ");
            System.out.print("(");
            for(int j=0;j<para.length;++j){
                System.out.print(para[j].getName()+" "+"arg"+j);
                if(j<para.length-1){
                    System.out.print(",");
                }
            }
            Class<?> exce[]=method[i].getExceptionTypes();
            if(exce.length>0){
                System.out.print(") throws ");
                for(int k=0;k<exce.length;++k){
                    System.out.print(exce[k].getName()+" ");
                    if(k<exce.length-1){
                        System.out.print(",");
                    }
                }
            }else{
                System.out.print(")");
            }
            System.out.println();
        }

        System.out.println("===============本类属性========================");
        // 取得本类的全部属性
        Field[] field = classDemo1.getDeclaredFields();
        for (int i = 0; i < field.length; i++) {
            // 权限修饰符
            int mo = field[i].getModifiers();
            String priv = Modifier.toString(mo);
            // 属性类型
            Class<?> type = field[i].getType();
            System.out.println(priv + " " + type.getName() + " "
                    + field[i].getName() + ";");
        }
        System.out.println("===============实现的接口或者父类的属性========================");
        // 取得实现的接口或者父类的属性
        Field[] filed1 = classDemo1.getFields();
        for (int j = 0; j < filed1.length; j++) {
            // 权限修饰符
            int mo = filed1[j].getModifiers();
            String priv = Modifier.toString(mo);
            // 属性类型
            Class<?> type = filed1[j].getType();
            System.out.println(priv + " " + type.getName() + " "
                    + filed1[j].getName() + ";");
        }

    }

    public ReflectDemo(int x) {
        this.x = x;
    }


    public ReflectDemo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public ReflectDemo() {

    }


    private int x;
    private int y;
    private String comment;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public void print() {
        System.out.println("Hello World!" + x + " - " + y + " - "
                + comment + " " + this);
    }
}
