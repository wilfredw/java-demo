package com.wei.java.thread.stop;

/**
 * 使用stop()释放锁将会给数据造成不一致性的结果。如果出现这样的情况，程序处理的数据就有可能遭到破坏，最终导致程序执行的流程错误，一定要特别注意：
 * 由于stop()方法以及在JDK中被标明为“过期/作废”的方法，显然它在功能上具有缺陷，所以不建议在程序张使用stop()方法。
 *
 * @author buhuan.wang
 * @since 2023/2/8
 */
public class ThreadStopDemo2 {
    public static void main(String args[]) throws InterruptedException {
        SynchronizedObject synchronizedObject = new SynchronizedObject();
        Thread thread = new MyThread(synchronizedObject);
        thread.start();
        Thread.sleep(500);
        thread.stop();
        // stop后，synchronizedObject中数据不一致
        System.out.println(synchronizedObject.getName() + "  " + synchronizedObject.getPassword());
    }


    public static class SynchronizedObject {
        private String name = "a";
        private String password = "aa";

        public synchronized void setString(String name, String password) {
            try {
                this.name = name;
                Thread.sleep(100000);
                this.password = password;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class MyThread extends Thread {
        private SynchronizedObject synchronizedObject;

        public MyThread(SynchronizedObject synchronizedObject) {
            this.synchronizedObject = synchronizedObject;
        }

        public void run() {
            synchronizedObject.setString("b", "bb");
        }
    }

}
