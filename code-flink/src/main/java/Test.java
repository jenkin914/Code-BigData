import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: bigData-zj
 * @date: 2023/6/29 17:16
 * @功能描述: adf
 * @version: 1.0
 */
public class Test {
    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        env.getConfig().disableGenericTypes();

        // 构造数据
        Student student = new Student();
        student.name = "zhangsan";
        student.age = 18;
        List<String> books = new ArrayList<>();
        books.add("hadoop");
        books.add("spark");
        student.books = books;
        HashMap<String, String> sources = new HashMap<>();
        sources.put("lisi", "English");
        sources.put("wangwu", "shuxue");
        student.sources = sources;

        HashMap<String, TypeInformation<?>> configMap = new HashMap<>();
        configMap.put("name", Types.STRING);
        configMap.put("age", Types.INT);
        configMap.put("books", Types.LIST(Types.STRING));
        configMap.put("sources", Types.MAP(Types.STRING, Types.STRING));

        TypeInformation<Student> typeInformation = Types.POJO(Student.class, configMap);
        env.fromElements(student)
                .returns(typeInformation)
                .print();

        env.execute();

    }
}
