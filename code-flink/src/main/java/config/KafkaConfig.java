package config;

import lombok.Data;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author zj
 * @date 2023/4/24 11:41
 * @description 配置类 - kafka
 **/
@Data
public class KafkaConfig implements Serializable {
    private String host = "localhost";
    private String port = "50004";
    private String groupId = "event-consumer-group";
    private String keyDeserializer = "org.apache.kafka.common.serialization.StringDeserializer";
    private String valueDeserializer = "org.apache.kafka.common.serialization.StringDeserializer";
    private String keySerializer = "org.apache.kafka.common.serialization.StringSerializer";
    private String valueSerializer = "org.apache.kafka.common.serialization.StringSerializer";
    private String bootstrapServers = null;
    private String topic = null;
    private List<String> topicList = null;
    private Pattern topicPattern = null;
    private OffsetsInitializer offsetsInitializer = null;
    private String topicTrafficLog = "zjg_standardize_traffic_log";
    private String topic3in1 = "zjg_standardize_3in1";
    private String topicPrintBurn = "zjg_standardize_print_burn";
    private String topicTerminalAudit = "zjg_standardize_host_audit";
    private String topicServerAudit = "zjg_standardize_server_audit";
    private String topicSettings = "zjg_event_settings";
    private String topicModel = "zjg_analysis_model";

    public String getBootstrapServers() {
        if (bootstrapServers != null) {
            return this.bootstrapServers;
        }
        return host + ":" + port;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    public List<String> getTopicList() {
        if (topicList != null){
            return topicList;
        }
        return Arrays.asList(topicTrafficLog,
                topic3in1,
                topicPrintBurn,
                topicTerminalAudit,
                topicServerAudit,
                topicSettings,
                topicModel);
    }
}
