package io.f2xy.pel.utils;

/**
 * 06 Nov 2022
 *
 * @author Hakan GÜR
 * @version 1
 **/
@FunctionalInterface
public interface Callback<T> {
    void call(T obj) throws Throwable;
}
