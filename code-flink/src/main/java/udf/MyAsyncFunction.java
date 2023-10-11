package udf;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.async.AsyncFunction;
import org.apache.flink.streaming.api.functions.async.ResultFuture;
import org.apache.flink.streaming.api.functions.async.RichAsyncFunction;

/**
 * @author bigData-zj
 * @version 1.0
 * @date 2023/10/9 13:41
 * @description java - vertx mysql异步io 实现
 */
public class MyAsyncFunction<IN, OUT> extends RichAsyncFunction<IN, OUT>{

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
    }

    @Override
    public void asyncInvoke(IN input, ResultFuture<OUT> resultFuture) throws Exception {

    }

    @Override
    public void timeout(IN input, ResultFuture<OUT> resultFuture) throws Exception {

    }
}
