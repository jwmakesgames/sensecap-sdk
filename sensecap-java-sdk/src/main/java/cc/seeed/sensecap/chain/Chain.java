package cc.seeed.sensecap.chain;

import cc.seeed.sensecap.exception.BaseException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Author AG
 * @Description
 * @Date 2020/9/1 15:00
 * @Version V1.0
 */
public class Chain {

    private Map<String, Object> container;

    public static Chain build() {
        Chain chain = new Chain();
        chain.container = new HashMap<>(2);
        return chain;
    }


    /**
     * 过滤条件
     *
     * @param function
     * @return
     * @throws BaseException
     */
    public Chain filter(Function<Map<String, Object>, Object> function) throws BaseException {
        container.put("key", function.apply(container));
        return this;
    }

    public Chain must(Function<Map<String, Object>, Boolean> function) throws BaseException {
        if (!function.apply(container)) {
            throw new BaseException("ValidateFail");
        }
        return this;
    }


    public Chain consumer(Consumer<Map<String, Object>> consumer) {
        consumer.accept(container);
        return this;
    }


    public Chain get(Supplier<Map<String, Object>> supplier) {
        supplier.get();
        return this;
    }

    public Chain biConsumer(BiConsumer<Object, Object> biConsumer, Object var1, Object var2) {
        biConsumer.accept(var1, var2);
        return this;
    }

    /**
     * 输入最终结果
     *
     * @param function
     * @return
     * @throws BaseException
     */
    public Object end(Function<Map<String, Object>, Object> function) throws BaseException {
        return function.apply(container);
    }


    //...

}
