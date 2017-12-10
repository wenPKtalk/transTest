/**
 * Created by wenpengkun on 2017/12/09.
 * 城市节点路由不通异常
 *
 */
public class NoRouterException extends Exception{


    public NoRouterException() {
    }


    public NoRouterException(String message) {
        super(message);
        System.out.println(message);
    }
}
