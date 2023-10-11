package config;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zj
 * @date 2023/4/24 12:32
 * @description 配置类 - consul
 **/
@Data
public class ConsulConfig implements Serializable {
    private String host = "localhost";
    private int port = 8500;
    private String name = "xkh";
    private String pathConfig = null;
}
