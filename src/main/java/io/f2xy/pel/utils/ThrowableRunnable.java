package io.f2xy.pel.utils;

/**
 * 07 Jul 2023
 *
 * @author Hakan GÜR
 * @version 1
 **/
@FunctionalInterface
public interface ThrowableRunnable {

    void run() throws Throwable;

}
