package main;

public class Err<Success, Error> implements Result<Success, Error> {
    private final Error value;

    Err(final Error value) {
        this.value = value;
    }

    public boolean isSuccessful() {
        return false;
    }

    @Override
    public Success get() {
        return null;
    }

    @Override
    public Error getError() {
        return value;
    }
}
