package fr.kissy.mockloadtesting;

import fr.kissy.mockloadtesting.sql.DataSourceWork;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class DataSourceController {
    private final AsyncTaskExecutor taskExecutor;

    public DataSourceController(AsyncTaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    @GetMapping("/datasource")
    public String datasource(@RequestParam(defaultValue = "1") Integer depth,
                             @RequestParam(defaultValue = "100") Integer milliseconds) throws ExecutionException, InterruptedException {
        Future<Integer> work = taskExecutor.submit(new DataSourceWork(taskExecutor, depth, milliseconds));
        return String.format("called datasource for a total duration of %d ms — %tQ", work.get(), new Date());
    }
}
