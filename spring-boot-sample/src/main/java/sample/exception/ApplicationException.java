package sample.exception;


public class ApplicationException extends RuntimeException {

    private final int code;
    private final String customMessage;

    public ApplicationException(int code, String customMessage) {
        super();
        this.code = code;
        this.customMessage = customMessage;
    }

    public int getCode() {
        return code;
    }

    public String getCustomMessage() {
        return customMessage;
    }


}
