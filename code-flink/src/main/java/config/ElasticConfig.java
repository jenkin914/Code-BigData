package config;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zj
 * @date 2023/4/24 11:41
 * @description 配置类 - elasticSearch
 **/
@Data
public class ElasticConfig implements Serializable {
    private String http = "http";
    private String host = "localhost";
    private String port = "50020";
    private String index = "event_analysis_result";
    private String username = "elastic";
    private String password = "123asd!@#$";
    private String url = null;
    
    public String getUrl() {
        if (url != null) {
            return url;
        }
        return "http://" + host + ":" + port;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
}
