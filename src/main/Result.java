package main;


import java.util.function.Function;

public interface Result<Success, Error> {

    static <Success, Error> Result<Success, Error> ok(final Success value) {
        return new Ok(value);
    }

    static <Success, Error> Result<Success, Error> ok() { return new Ok(); }

    static <Success, Error> Result<Success, Error> error(final Error value) {
        return new Err(value);
    }

    boolean isSuccessful();

    Success get();

    Error getError();

    default <T> Result<T, Error> map(final Function<Success, T> func) {
        if (isSuccessful()) {
            Success successfulInput = get();
            return Result.ok(func.apply(successfulInput));
        } else {
            return (Result<T, Error>) this;
        }
    }

    default <T> Result<T, Error> andThen(final Function<Success, Result<T, Error>> func) {
        if (isSuccessful()) {
            Success successfulInput = get();
            return func.apply(successfulInput);
        } else {
            return (Result<T, Error>) this;
        }
    }

}
