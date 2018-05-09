package ru.geekbrains.javacore.homework;

public class Main {

    private static final int size = 10_000_000;
    private static final int h = size / 2;

    public static void main(String[] args) {
        Main e1 = new Main();

        System.out.println("Start");

        new Thread(() -> e1.method1()).start();
        e1.method2();
    }

    public void method1() {

        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }

        long time = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            //System.out.println("arr[" + i+ "]" + arr[i]);
        }
        time = System.currentTimeMillis() - time;
        System.out.println("Method 1 - runtime: " + time + " ms.");

        float summ = 0;
        for (int i = 0; i < size; i++) {
            summ += arr[i];
        }

        System.out.println("Method 1 - summ: " + summ);
    }

    public void method2() {
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }

        float[] a1 = new float[h];
        float[] a2 = new float[h];

        long time = System.currentTimeMillis();

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);


        Thread t1 = new Thread(() -> {
            for (int i = 0; i < h; i++) {
                //a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                 a1[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

            }
        } );
        t1.start();

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < h; i++) {
                //a2[i] = (float) (a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                a2[i] = (float) (arr[i+h] * Math.sin(0.2f + (i+h) / 5) * Math.cos(0.2f + (i+h) / 5) * Math.cos(0.4f + (i+h) / 2));
            }
        } );
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        time = System.currentTimeMillis() - time;
        System.out.println("Method 2 - runtime: " + time + " ms.");

        float summ = 0;
        for (int i = 0; i < size; i++) {
            summ += arr[i];

        }

        System.out.println("Method 2 - summ: " + summ);
    }
}
