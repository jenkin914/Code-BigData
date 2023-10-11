import config.KafkaConfig;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.Collections;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * @author zj
 * @date:2023/5/17 14:21
 * @description 事件kafka客户端
 */

@Slf4j
@Data
public class EventKafkaClient {
    private String host;
    private String port;
    private String groupId;
    private String keyDeserializer;
    private String valueDeserializer;
    private String keySerializer;
    private String valueSerializer;
    private String bootstrapServers;

    public EventKafkaClient(KafkaConfig kafkaConfig) {
        this.host = kafkaConfig.getHost();
        this.port = kafkaConfig.getPort();
        this.groupId = kafkaConfig.getGroupId();
        this.keyDeserializer = kafkaConfig.getKeyDeserializer();
        this.valueDeserializer = kafkaConfig.getValueDeserializer();
        this.keySerializer = kafkaConfig.getKeySerializer();
        this.valueSerializer = kafkaConfig.getValueSerializer();
        this.bootstrapServers = kafkaConfig.getBootstrapServers();
    }

    private AdminClient getAdminClient() {
        return KafkaAdminClient.create(getKafkaProp());
    }

    /**
     * 获取topic信息，判断topic是否已经存在，不存在则创建
     *
     * @param topic        kafkaTopic name
     * @param partitionNum 创建kafka的partition数量
     * @param replication  创建kafka分区的副本数量
     * @return void
     * @throws RuntimeException InterruptedException | ExecutionException
     */
    public void createTopic(String topic, Integer partitionNum, Integer replication) {
        AdminClient adminClient = getAdminClient();
        try {
            ListTopicsResult listTopicsResult = adminClient.listTopics();
            Set<String> topics = listTopicsResult.names().get();
            if (topics.contains(topic)) {
                log.info("bootstrap.servers:{} 已经存在topic:{}", getBootstrapServers(), topic);
            } else {
                NewTopic newTopic = new NewTopic(topic, partitionNum, new Short(replication.toString()));
                log.info("bootstrap.servers:{},创建topic：{}", getBootstrapServers(), newTopic);
                adminClient.createTopics(Collections.singletonList(newTopic));
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        adminClient.close();
    }

    /**
     * 删除topic
     *
     * @param topic kafkaTopic name
     * @return void
     * @throws RuntimeException InterruptedException | ExecutionException
     */
    public void deleteTopic(String topic) {
        try (AdminClient adminClient = getAdminClient()) {
            log.info("bootstrap.servers:{},删除topic：{}", getBootstrapServers(), topic);
            adminClient.deleteTopics(Collections.singletonList(topic)).all().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取kafka配置(kafka地址，消费者组id， key、value序列化信息)
     *
     * @return java.util.Properties
     */
    private Properties getKafkaProp() {
        // 消费者配置
        Properties properties = new Properties();
        // kafka 地址
        properties.setProperty("bootstrap.servers", getBootstrapServers());
        // 消费者组
        properties.setProperty("group.id", getGroupId());
        // 序列化
        properties.setProperty("key.deserializer", getKeyDeserializer());
        properties.setProperty("value.deserializer", getValueDeserializer());
        properties.setProperty("key.serializer", getKeySerializer());
        properties.setProperty("value.serializer", getValueSerializer());
        return properties;
    }
}
