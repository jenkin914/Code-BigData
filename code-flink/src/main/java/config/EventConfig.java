package config;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zj
 * @date 2023/4/24 11:41
 * @description 配置类 - 事件程序
 **/
@Data
public class EventConfig implements Serializable {
    private Boolean isReadConfigFromConsul = true;
    private String eventInfoJsonPath = "./config/eventInfo.json";
    private String applicationPath = "./config/application.yml";
    private String checkPath = "file:///opt/wangan/data/checkpoints";
    private ConsulConfig consulConfig = new ConsulConfig();
    private MysqlConfig mysqlConfig = new MysqlConfig();
    private KafkaConfig kafkaConfig = new KafkaConfig();
    private ElasticConfig elasticConfig = new ElasticConfig();
    private Long enableCheckPointing = 600000L;
    private Long minPauseBetweenCheckpoints = 60000L;
}
