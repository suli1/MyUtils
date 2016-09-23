package common.utils;

/**
 * Created by suli on 16-9-9.
 */
public class TestMultiThread {

    public static class Counter {
        private static final Object lock = new Object();

        private static final int COUNT = 100000;

        public Counter() {
        }


        /****************************
         * print0 和 print1 一样，作用与类级别
         *
         **************************/

        /***
         * @param var
         */
        public synchronized static void print0(int var) {
            for (int i = 0; i < COUNT; i++) {
                System.out.print(var);
            }
        }

        /**
         * instance
         *
         * @param var
         */
        public synchronized void print1(int var) {
            for (int i = 0; i < COUNT; i++) {
                System.out.print(var);
            }
        }

        /**
         * class literals
         *
         * @param var
         */
        public void print2(int var) {
            synchronized (Counter.class) {
                for (int i = 0; i < COUNT; i++) {
                    System.out.print(var);
                }
            }
        }


        /**********
         * print1, print3 效果一样, 作用于当前实例的一个成员变量,对不用的类对象间互相不影响
         *
         *********/

        /***
         * object reference
         *
         * @param var
         */
        public void print3(int var) {
            synchronized (this) {
                for (int i = 0; i < COUNT; i++) {
                    System.out.print(var);
                }
            }
        }

        /**
         * object reference
         *
         * @param var
         */
        public void print4(int var) {
            synchronized (lock) {
                for (int i = 0; i < COUNT; i++) {
                    System.out.print(var);
                }
            }
        }
    }


    public static void main(String[] args) {
        final Counter counter1 = new Counter();
        final Counter counter2 = new Counter();

        new Thread(new Runnable() {
            @Override
            public void run() {
                counter1.print2(1);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                counter1.print2(2);
            }
        }).start();


    }


}
