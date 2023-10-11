import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.functions.JoinFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.AsyncDataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.async.AsyncFunction;
import org.apache.flink.streaming.api.functions.async.ResultFuture;
import org.apache.flink.streaming.api.functions.co.CoFlatMapFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;
import udf.MyAsyncFunction;

/**
 * @author bigData-zj
 * @version 1.0
 * @date 2023/10/11 10:45
 * @description df
 */
public class Test02 {
    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env1 = StreamExecutionEnvironment.getExecutionEnvironment();
        env1.setParallelism(1);
        StreamExecutionEnvironment env2 = StreamExecutionEnvironment.getExecutionEnvironment();
        env2.setRuntimeMode(RuntimeExecutionMode.BATCH);

        DataStreamSource<String> stream = env1.socketTextStream("192.168.128.1", 7777);
        SingleOutputStreamOperator<Tuple2<Integer, String>> ds1 = stream
                .map(new MapFunction<String, Tuple2<Integer, String>>() {

                    private int tmpValue = 0;

                    @Override
                    public Tuple2<Integer, String> map(String s) throws Exception {
                        tmpValue += 1;

                        return Tuple2.of(tmpValue, s);
                    }
                });

//        AsyncDataStream
//                .orderedWait(ds1, new MyAsyncFunction(), 10, Time.seconds(10))

        DataStreamSource<Integer> ds2 = env2.fromElements(1, 2, 3, 4, 5, 6, 7, 8);

        ds1.join(ds2)
                .where(new KeySelector<Tuple2<Integer, String>, Integer>() {
                    @Override
                    public Integer getKey(Tuple2<Integer, String> integerIntegerTuple2) throws Exception {
                        System.out.println("key1: " + integerIntegerTuple2);
                        return integerIntegerTuple2.f0;
                    }
                })
                .equalTo(new KeySelector<Integer, Integer>() {
                    @Override
                    public Integer getKey(Integer integer) throws Exception {
                        System.out.println("key2: " + integer);
                        return integer;
                    }
                })
                .window(TumblingProcessingTimeWindows.of(Time.seconds(5)))
                .apply(new JoinFunction<Tuple2<Integer, String>, Integer, Tuple3<Integer, String, Integer>>() {
                    @Override
                    public Tuple3<Integer, String, Integer> join(Tuple2<Integer, String> t, Integer i) throws Exception {
                        return Tuple3.of(t.f0, t.f1, i);
                    }
                })
                .print();

        env1.execute();
    }
}
