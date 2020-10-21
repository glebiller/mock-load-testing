package fr.kissy.mockloadtesting;

import fr.kissy.mockloadtesting.model.Human;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static org.jeasy.random.FieldPredicates.inClass;
import static org.jeasy.random.FieldPredicates.named;

@RestController
public class AllocateController {
    private static final int SCALE = 10000;

    @GetMapping("/allocate")
    public String allocate(@RequestParam(defaultValue = "1000") Integer objects, @RequestParam(defaultValue = "5") Integer depth) {
        final EasyRandom generator = new EasyRandom(
                new EasyRandomParameters()
                        .objectPoolSize(objects)
                        .randomizationDepth(depth)
                        .excludeField(named("counted").and(inClass(Human.class)))
        );
        long count = generator.nextObject(Human.class).familySize();
        return String.format("%d Human objects allocated — %tQ", count, new Date());
    }
}
