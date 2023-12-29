package io.f2xy.pel;

import io.f2xy.pel.reader.StreamReader;
import io.f2xy.pel.utils.Callback;
import io.f2xy.pel.utils.ThrowableRunnable;
import io.f2xy.pel.utils.Wait;
import io.f2xy.pel.writer.StreamWriter;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 20 Dec 2023
 *
 * @author Hakan GÜR
 * @version 1
 **/
public class ProcContext {

    private Process process;

    private String command;
    private String[] args;

    private File workingDir;
    private Map<String, String> environments = new HashMap<>();

    private Callback<Throwable> errorCallback;
    private ThrowableRunnable finishCallback;

    private StreamReader<?> reader;
    private StreamReader<?> errorReader;

    private StreamWriter<?> writer;

    public ProcContext run(String command) {
        this.command = command;
        return this;
    }

    public ProcContext args(String... args) {
        this.args = args;
        return this;
    }

    public ProcContext addEnvironment(String key, String value) {
        environments.put(key, value);
        return this;
    }

    public ProcContext reader(StreamReader<?> reader) {
        this.reader = reader;
        return this;
    }

    public ProcContext errorReader(StreamReader<?> errorReader) {
        this.errorReader = errorReader;
        return this;
    }

    public ProcContext writer(StreamWriter<?> writer) {
        this.writer = writer;
        return this;
    }

    public ProcContext onError(Callback<Throwable> error){
        errorCallback = error;
        return this;
    }

    public ProcContext onFinish(ThrowableRunnable finishFunc){
        finishCallback = finishFunc;
        return this;
    }

    public int waitFor() throws InterruptedException {
        return process.waitFor();
    }

    public int exitProcess(){

        Wait.check(Process::isAlive, process, 1000, 5);

        if(process.isAlive()){
            process.destroy();
        }

        return process.exitValue();
    }

    protected void setProcess(Process process) {
        this.process = process;
    }

    public Process getProcess() {
        return process;
    }

    public String getCommand() {
        return command;
    }

    public String[] getArgs() {
        return args;
    }

    public File getWorkingDir() {
        return workingDir;
    }

    public Map<String, String> getEnvironments() {
        return environments;
    }

    public Callback<Throwable> getErrorCallback() {
        return errorCallback;
    }

    public ThrowableRunnable getFinishCallback() {
        return finishCallback;
    }

    public StreamReader<?> getReader() {
        return reader;
    }

    public StreamReader<?> getErrorReader() {
        return errorReader;
    }

    public StreamWriter<?> getWriter() {
        return writer;
    }
}
