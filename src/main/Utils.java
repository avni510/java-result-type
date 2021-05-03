package main;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Utils {

    public static <T, U> Optional<List<U>> combineErrors(
            List<Result<T, U>> results
    ) {
        List<U> errorResults = results.stream()
                .filter(result -> !result.isSuccessful())
                .map(Result::getError)
                .collect(Collectors.toList());

        if (errorResults.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(errorResults);
        }
    }
}
