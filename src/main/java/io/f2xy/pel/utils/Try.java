package io.f2xy.pel.utils;

import java.util.Iterator;

/**
 * 07 Jul 2023
 *
 * @author Hakan GÜR
 * @version 1
 **/
public class Try {

    public static <T> void execute(Iterator<T> list, Callback<T> callback, BiCallback<T, Throwable> onError){
        while (list.hasNext()) {
            execute(callback,list.next(),onError);
        }
    }

    public static void execute(ThrowableRunnable runnable, Callback<Throwable> onError, ThrowableRunnable onFinish){
        try {
            runnable.run();
        }catch (Throwable t){
            if(onError != null) {
                execute(onError, t, null);
            }
        }finally {
            if(onFinish != null)
                execute(onFinish, null, null);
        }
    }

    public static <T> void execute(Callback<T> callback, T parameter, BiCallback<T, Throwable> onError){
        try{
            callback.call(parameter);
        }catch (Throwable t){
            if(onError != null)
                onError.call(parameter, t);
        }
    }
}
