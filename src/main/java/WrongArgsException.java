public class WrongArgsException extends Exception{
    public WrongArgsException(Throwable cause) {
        initCause(cause);
    }
}
