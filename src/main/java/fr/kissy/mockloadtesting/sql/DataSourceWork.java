package fr.kissy.mockloadtesting.sql;

import org.springframework.core.task.AsyncTaskExecutor;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class DataSourceWork implements Callable<Integer> {
    private final AsyncTaskExecutor taskExecutor;
    private final int depth;
    private final int milliseconds;

    public DataSourceWork(AsyncTaskExecutor taskExecutor, int depth, int milliseconds) {
        this.taskExecutor = taskExecutor;
        this.depth = depth;
        this.milliseconds = milliseconds;
    }

    @Override
    public Integer call() throws Exception {
        int i = new Random().nextInt();
        int result = 0;
        if (depth > 0) {
            Future<Integer> work = taskExecutor.submit(new DataSourceWork(taskExecutor, depth - 1, milliseconds));
            result += work.get();
        }
        Thread.sleep(milliseconds);
        return result + milliseconds;
    }
}