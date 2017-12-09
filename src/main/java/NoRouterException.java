/**
 * Created by wenpengkun on 2017/12/09.
 */
public class NoRouterException extends Exception{


    public NoRouterException() {
    }


    public NoRouterException(String message) {
        super(message);
        System.out.println(message);
    }
}
