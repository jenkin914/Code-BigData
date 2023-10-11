import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONPath;
import config.KafkaConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironmentFactory;

/**
 * @author bigData-zj
 * @version 1.0
 * @date 2023/8/29 19:54
 * @description a
 */
@Slf4j
public class Test01 {
    public static void main(String[] args) throws Exception {
        log.info("创建执行环境");
        StreamExecutionEnvironmentFactory factory = new StreamExecutionEnvironmentFactory() {
            @Override
            public StreamExecutionEnvironment createExecutionEnvironment(Configuration configuration) {
                return StreamExecutionEnvironment.getExecutionEnvironment();
            }
        };

        StreamExecutionEnvironment env = factory.createExecutionEnvironment(new Configuration());

        env.setParallelism(1);
        env.setRestartStrategy(RestartStrategies.noRestart());

        log.info("读取数据源");
        DataStreamSource<String> stream = env.socketTextStream("192.168.128.1", 7777);

        stream.print();

        env.execute();

    }
}
