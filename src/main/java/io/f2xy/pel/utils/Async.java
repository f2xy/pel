package io.f2xy.pel.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 07 Jul 2023
 *
 * @author Hakan GÜR
 * @version 1
 **/
public class Async {

    public static <T> void call(Iterable<T> list, Callback<T> call, BiCallback<T, Throwable> onError, ThrowableRunnable finish){
        ExecutorService executorService = Executors.newWorkStealingPool();

        for (T t : list) {
            executorService.submit(() -> {
                Try.execute(call, t, onError);
            });
        }

        call(() -> {
            Try.execute(()->{
                executorService.shutdown();
                executorService.awaitTermination(1,TimeUnit.DAYS);
            },null, finish);
        });

    }

    public static void call(ThrowableRunnable runnable) {
        call(runnable, null, null, 0);
    }

    public static void call(ThrowableRunnable runnable, Callback<Throwable> onError){
        call(runnable, onError, null, 0);
    }

    public static void call(ThrowableRunnable runnable, Callback<Throwable> onError, ThrowableRunnable onFinish, long delay){
        new Thread(() -> {
            if(delay > 0)
                Wait.delay(delay);
            Try.execute(runnable, onError, onFinish);
        }).start();
    }

}
