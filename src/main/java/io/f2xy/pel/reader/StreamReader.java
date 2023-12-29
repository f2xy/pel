package io.f2xy.pel.reader;

import io.f2xy.pel.utils.BiCallback;
import io.f2xy.pel.utils.Callback;

import java.io.InputStream;

/**
 * 15 Jun 2022
 *
 * @author Hakan GÜR
 * @version 1
 **/
public interface StreamReader<T>{

    void setStream(InputStream inputStream);

    void startReadStream() throws Exception;

    void setCallback(Callback<T> callback);

    void setErrorCallback(BiCallback<T, Throwable> callback);
}
