package io.f2xy.pel.reader;

import io.f2xy.pel.utils.Try;
import io.f2xy.pel.utils.BiCallback;
import io.f2xy.pel.utils.Callback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 20 Dec 2023
 *
 * @author Hakan GÜR
 * @version 1
 **/
public class LineReader implements StreamReader<String> {

    BufferedReader reader;
    Callback<String> callback;
    BiCallback<String, Throwable> errorCallback;

    public LineReader(Callback<String> callback, BiCallback<String, Throwable> errorCallback) {
        this.callback = callback;
        this.errorCallback = errorCallback;
    }

    @Override
    public void setStream(InputStream inputStream) {
        reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    public void startReadStream() throws IOException {
        String value;
        while ((value = reader.readLine()) != null) {
            Try.execute(callback, value, errorCallback);
        }
    }

    @Override
    public void setCallback(Callback<String> callback) {
        this.callback = callback;
    }

    @Override
    public void setErrorCallback(BiCallback<String, Throwable> callback) {
        this.errorCallback = callback;
    }
}
