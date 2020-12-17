package com.xgcd.lab.multithread;

import java.util.concurrent.CountDownLatch;

public class StringBuilderAndStringBuffer {
    public static void main(String[] args) {
        /**
         * 证明StringBuffer线程安全，StringBuilder线程不安全
         * 分别用1000个线程写StringBuffer和StringBuilder，
         * 使用CountDownLatch保证在各自1000个线程执行完之后才打印StringBuffer和StringBuilder长度，
         * 观察结果。
         */
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer();
        CountDownLatch stringBuilderLatch = new CountDownLatch(1000);
        CountDownLatch stringBufferLatch = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        stringBuilder.append(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        stringBuilderLatch.countDown();
                    }
                }
            }).start();
        }
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        stringBuffer.append(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        stringBufferLatch.countDown();
                    }

                }
            }).start();
        }
        try {
            stringBuilderLatch.await();
            System.out.println("stringBuilder长度:" + stringBuilder.length());// stringBuilder长度:993(<1000)
            stringBufferLatch.await();
            System.out.println("stringBuffer长度:" + stringBuffer.length());// stringBuffer长度:1000
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
