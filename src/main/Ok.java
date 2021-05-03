package main;

public class Ok<Success, Error> implements Result<Success, Error> {
    private final Success value;

    Ok(final Success value) {
        this.value = value;
    }

    Ok() {
        this.value = null;
    }

    @Override
    public boolean isSuccessful() {
        return true;
    }

    @Override
    public Success get() {
        return value;
    }

    @Override
    public Error getError() {
       return null;
    }
}
