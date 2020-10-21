package fr.kissy.mockloadtesting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class MemoryController {

    private ThreadLocal<MemoryController> localLeak = new ThreadLocal<>();
    private byte[] allocation;

    @GetMapping("/memory")
    public String memory(@RequestParam(defaultValue = "1000") Integer bytes,
                           @RequestParam(defaultValue = "100") Integer milliseconds,
                           @RequestParam(defaultValue = "false") Boolean leak) throws InterruptedException {
        allocation = new byte[bytes];
        if (leak) {
            localLeak.set(this);
        }
        Thread.sleep(milliseconds);
        return String.format("memory allocated an array of %d bytes for %d milliseconds — %tQ", allocation.length, milliseconds, new Date());
    }

    public byte[] getAllocation() {
        return allocation;
    }
}
