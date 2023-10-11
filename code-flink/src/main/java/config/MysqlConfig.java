package config;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zj
 * @date 2023/4/24 11:41
 * @description 配置类 - mysql
 **/
@Data
public class MysqlConfig implements Serializable {
    /**
     * mysql 配置类
     */
    private String host = "localhost";
    private String port = "50013";
    private String user = "mysqluser";
    private String password = "123asd!@#$";
    private String driver = "com.mysql.jdbc.Driver";
    private String database = "xkh_ui_standard";
    private String baseUrl = null;
    private String url = null;
    
    public String getBaseUrl() {
        return "jdbc:mysql://" + host + ":" + port;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getUrl() {
        if (url != null) {
            return this.url;
        }
        return "jdbc:mysql://" + host + ":" + port +
                "/xkh_ui_standard?useUnicode=true" +
                "&characterEncoding=utf8" +
                "&useSSL=false&serverTimezone=GMT%2B8" +
                "&autoReconnect=true" +
                "&useAffectedRows=true";
    }
}
