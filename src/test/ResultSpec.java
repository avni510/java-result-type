package test;

import main.Result;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class ResultSpec {

    @Test
    public void ok_shouldBeSuccessful() {
        ArrayList successfulObject = new ArrayList<>();

        Result<ArrayList, String> result = Result.ok(successfulObject);

        assertTrue(result.isSuccessful());
    }

    @Test
    public void error_shouldNotBeSuccessful() {
        String errorMessage = "Error message";

        Result<Integer, String> result = Result.error(errorMessage);

        assertFalse(result.isSuccessful());
    }

    @Test
    public void ok_shouldAllowAVoidResult() {
        Result<Void, String> result = Result.ok();

        assertTrue(result.isSuccessful());
    }

    @Test
    public void get_shouldReturnSuccessfulObjectIfResultSuccess() {
        ArrayList successfulObject = new ArrayList<>();

        Result<ArrayList, String> result = Result.ok(successfulObject);

        assertEquals(result.get(), successfulObject);
    }

    @Test
    public void get_shouldReturnNullIfResultError() {
        String errorMessage = "Error message";

        Result<Integer, String> result = Result.error(errorMessage);

        assertNull(result.get());
    }

    @Test
    public void map_shouldTransformResultTypeIfResultSuccess() {
        Result<Integer, String> result = Result.ok(1);

        Result<String, String> newResult = result.map(value -> Integer.toString(value));

        assertTrue(newResult.isSuccessful());
        assertEquals(newResult.get(), "1");
    }

    @Test
    public void map_shouldReturnSameResultIfResultError() {
        String errorMessage = "Error message";

        Result<Integer, String> result = Result.error(errorMessage);

        Result newResult = result.map(value -> value * 2);

        assertFalse(newResult.isSuccessful());
        assertEquals(newResult.getError(), "Error message");
    }

    @Test
    public void andThen_shouldApplyAChainOfFunctionsForSuccessfulResults() {
        Result<Integer, String> resultOne = Result.ok(1);

        Result<ArrayList, String> newResult =
                resultOne
                .andThen(value -> Result.ok(Integer.toString(value)))
                .andThen(value -> Result.ok(new ArrayList<>()));

        assertTrue(newResult.isSuccessful());
        assertThat(newResult.get(), instanceOf(ArrayList.class));
    }

    @Test
    public void andThen_shouldNotApplyFunctionsIfResultError() {
        Result<Integer, String> resultOne = Result.ok(1);

        Result<ArrayList, String> newResult =
                resultOne
                        .andThen(value -> Result.error("Error Message"))
                        .andThen(value -> Result.ok(new ArrayList<>()));

        assertFalse(newResult.isSuccessful());
        assertEquals(newResult.getError(), "Error Message");
    }
}
