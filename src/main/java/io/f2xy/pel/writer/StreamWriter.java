package io.f2xy.pel.writer;

import io.f2xy.pel.utils.BiCallback;

import java.io.OutputStream;

/**
 * 28 Dec 2023
 *
 * @author Hakan GÜR
 * @version 1
 **/
public interface StreamWriter<T> {

    void setStream(OutputStream outputStream);

    void send(T data);

    void setErrorCallback(BiCallback<T, Throwable> callback);

}
