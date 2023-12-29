package io.f2xy.pel.utils;

/**
 * 06 Nov 2022
 *
 * @author Hakan GÜR
 * @version 1
 **/
@FunctionalInterface
public interface BiCallback<T1, T2> {
    void call(T1 obj1, T2 obj2);
}
