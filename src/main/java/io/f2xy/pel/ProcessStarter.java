package io.f2xy.pel;

import io.f2xy.pel.utils.Async;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 20 Dec 2023
 *
 * @author Hakan GÜR
 * @version 1
 **/
public class ProcessStarter {

    public static ProcContext start(ProcContext context) throws IOException, InterruptedException {
        List<String> args = new ArrayList<>();
        args.add(context.getCommand());

        if(context.getArgs() != null)
            args.addAll(Arrays.asList(context.getArgs()));

        ProcessBuilder pb = new ProcessBuilder(args);

        pb.directory(context.getWorkingDir());
        pb.environment().putAll(context.getEnvironments());

        context.setProcess(pb.start());

        if(context.getReader() != null) {
            context.getReader().setStream(context.getProcess().getInputStream());
            Async.call(context.getReader()::startReadStream, context.getErrorCallback());}

        if(context.getErrorReader() != null) {
            context.getErrorReader().setStream(context.getProcess().getErrorStream());
            Async.call(context.getErrorReader()::startReadStream, context.getErrorCallback());
        }

        if(context.getWriter() != null) {
            context.getWriter().setStream(context.getProcess().getOutputStream());
        }

        return context;
    }

}
