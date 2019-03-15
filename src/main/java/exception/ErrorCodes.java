package exception;

import com.sun.tools.internal.ws.wsdl.document.Input;

/**
 * <br>
 * 〈异常编码〉
 *
 * @author wensir
 * @create 2019/3/15
 * @since 1.0.0
 */
public enum  ErrorCodes {

    ERROR_INPUT("10001","ILLEGAL INPUT"),
    NO_SUCH_ROUTE("10404","NO SUCH ROUTE"),
    ERROR_SYS("500","SYS ERROR"),;

    private String codes;
    private String message;

    private ErrorCodes(String codes, String message) {
        this.setCodes(codes);
        this.setMessage(message);
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorCodes{" +
                "codes='" + codes + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}