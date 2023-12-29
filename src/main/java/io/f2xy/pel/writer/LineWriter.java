package io.f2xy.pel.writer;

import io.f2xy.pel.utils.BiCallback;
import io.f2xy.pel.utils.Try;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * 28 Dec 2023
 *
 * @author Hakan GÜR
 * @version 1
 **/
public class LineWriter implements StreamWriter<String>{

    private BufferedWriter pw;

    private BiCallback<String, Throwable> errorCallback;

    @Override
    public void setStream(OutputStream outputStream) {
        pw = new BufferedWriter(new OutputStreamWriter(outputStream));
    }

    @Override
    public void send(String line) {
        Try.execute(this::sendLine, line, errorCallback);
    }

    @Override
    public void setErrorCallback(BiCallback<String, Throwable> callback) {
        this.errorCallback = callback;
    }

    private void sendLine(String line) throws IOException {
        pw.write(line);
        //pw.newLine();
        pw.write('\n');
        pw.flush();
    }

}
