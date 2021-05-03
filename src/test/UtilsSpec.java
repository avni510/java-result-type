package test;

import main.Result;
import main.Utils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class UtilsSpec {

    @Test
    public void combineErrors_returnsAListOfErrors() {
        ArrayList results = new ArrayList();
        results.add(Result.ok(1));
        results.add(Result.error("message 1"));
        results.add(Result.ok(2));
        results.add(Result.error("message 2"));

        Optional<List<String>> combinedErrors = Utils.combineErrors(results);

        assertTrue(combinedErrors.isPresent());
        List<String> errors = combinedErrors.get();
        assertEquals(errors.size(), 2);
        assertTrue(errors.contains("message 1"));
        assertTrue(errors.contains("message 2"));
    }

    @Test
    public void combineErrors_returnsAnEmptyOptionalIfNoErrors() {
        ArrayList results = new ArrayList();
        results.add(Result.ok(1));
        results.add(Result.ok(2));

        Optional<List<String>> combinedErrors = Utils.combineErrors(results);

        assertFalse(combinedErrors.isPresent());
    }
}
