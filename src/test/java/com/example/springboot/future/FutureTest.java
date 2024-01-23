package com.example.springboot.future;

import java.util.concurrent.*;

public class FutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        if (new Object() {
            @Override
            public boolean equals(Object obj) {
                System.out.print("a");
                return super.equals(obj);
            }
        }.equals(null)) {
            System.out.print("a");
        } else {
            System.out.print("b");
        }
//        timer(new Test1());
    }

    public static void timer(Runnable runnable) {
        long start = System.currentTimeMillis();

        runnable.run();

        long end = System.currentTimeMillis();

        System.out.println("耗时： " + (end - start) / 1000 + "s");

    }


}


class Test1 implements Runnable {
    @Override
    public void run() {
        FutureTask<String> f1 = new FutureTask<>(() -> {
            System.out.println("in f1");
            TimeUnit.SECONDS.sleep(10);
            System.out.println("f1: 123");
            return "123";
        });

        FutureTask<String> f2 = new FutureTask<>(() -> {
            System.out.println("in f2");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("f2: 456");
            return "456";
        });
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(f1);
        executorService.submit(f2);
        try {
            String s1 = f1.get();
            String s2 = f2.get();
            System.out.println("s1 = " + s1);
            System.out.println("s2 = " + s2);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}

class Test2 {
    public static void main(String[] args) {
        String  tel     = "+8613277988809";
        boolean matches = tel.matches(".*(132)\\d{4}(8809)$");
        System.out.println("matches = " + matches);
    }
}