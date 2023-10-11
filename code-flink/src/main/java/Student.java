import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author: bigData-zj
 * @date: 2023/6/29 17:35
 * @功能描述: asd
 * @version: 1.0
 */
@Data
public class Student {
    public String name;
    public Integer age;
    public List<String> books;
    public Map<String, String> sources;
}
