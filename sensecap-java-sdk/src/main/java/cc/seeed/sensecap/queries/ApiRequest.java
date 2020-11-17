package cc.seeed.sensecap.queries;

/**
 * @Author AG
 * @Description
 * @Date 2020/9/11 14:04
 * @Version V1.0
 */
public abstract class ApiRequest<T> {

    protected abstract T execute();


}
