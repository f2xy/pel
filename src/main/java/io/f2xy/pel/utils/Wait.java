package io.f2xy.pel.utils;

import java.util.function.Predicate;

/**
 * 19 Jul 2023
 *
 * @author Hakan GÜR
 * @version 1
 **/
public class Wait {

    public static <T> void check(Predicate<T> predicate, T t, long wait, int checkCount){
        int currentCheck = checkCount;
        while (!predicate.test(t) && currentCheck-- > 0){
            delay(wait);
        }
    }

    public static void delay(long time, ThrowableRunnable runnable){
        delay(time);
        Try.execute(runnable, null, null);
    }

    public static void delay(long time){
        try{
            Thread.sleep(time);
        } catch (InterruptedException ignore) {
        }
    }

}
