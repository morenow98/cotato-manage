package cotato.cotatomanage.service;

import org.springframework.stereotype.Service;

@Service
public class ValidateService {

    private static final int MIN_AGE = 22;
    private static final int MAX_AGE = 30;

    public void checkAge(int age) {
        if (!isInRange(age)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isInRange(int age) {
        return MIN_AGE <= age && age <= MAX_AGE;
    }
}
