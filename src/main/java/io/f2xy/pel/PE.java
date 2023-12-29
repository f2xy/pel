package io.f2xy.pel;

import io.f2xy.pel.reader.LineReader;
import io.f2xy.pel.utils.Async;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Process Executor
 * 19 Dec 2023
 *
 * @author Hakan GÜR
 * @version 1
 **/
public class PE {

    public static int run(String cmd) throws IOException, InterruptedException {

        ProcContext procContext = new ProcContext()
                .run(cmd);

        return ProcessStarter.start(procContext).waitFor();
    }

    public static List<String> run(String cmd, String... args) throws IOException, InterruptedException {
        List<String> lines = new ArrayList<>();
        LineReader lineReader = new LineReader(lines::add, null);
        ProcessStarter.start(
                new ProcContext()
                        .run(cmd)
                        .args(args)
                        .reader(lineReader)
        ).waitFor();

        return lines;
    }

    public static void run(ProcContext procContext) {
        Async.call(
                ()-> ProcessStarter.start(procContext).waitFor(),
                procContext.getErrorCallback(),
                procContext.getFinishCallback(),
                0L
        );
    }

}
