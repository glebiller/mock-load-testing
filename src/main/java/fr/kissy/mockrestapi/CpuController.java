package fr.kissy.mockrestapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class CpuController {
    private static final int SCALE = 10000;

    @GetMapping("/cpu")
    public String greeting(@RequestParam(defaultValue = "1000") Integer load) {
        StringBuilder pi = new StringBuilder();
        int[] arr = new int[load + 1];
        int carry = 0;

        for (int i = 0; i <= load; ++i)
            arr[i] = 2000;

        for (int i = load; i > 0; i-= 14) {
            int sum = 0;
            for (int j = i; j > 0; --j) {
                sum = sum * j + SCALE * arr[j];
                arr[j] = sum % (j * 2 - 1);
                sum /= j * 2 - 1;
            }

            pi.append(String.format("%04d", carry + sum / SCALE));
            carry = sum % SCALE;
        }
        return String.format("cpu used to calculate %d pi digits — %tQ", pi.length() - 1, new Date());
    }
}
