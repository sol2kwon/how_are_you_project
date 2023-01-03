package how_are_you_project.how_are_you.aop.exception;

public class LoginRunnerException extends RuntimeException{
    private static final long SerializableUID = 1L;

    public LoginRunnerException() {
        super();
    }

    public LoginRunnerException(String msg) {
        super(msg);
    }
}
