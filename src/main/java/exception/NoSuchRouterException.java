package exception;

/**
 * Created by wenpengkun on 2017/12/09.
 * 城市节点路由不通异常
 *
 */
public class NoSuchRouterException extends RuntimeException{

    public NoSuchRouterException() {
        super(ErrorCodes.NO_SUCH_ROUTE.toString());
    }

}
