import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author bigData-zj
 * @date 2023/8/9 20:20
 * @description $FunctionDescription
 * @version 1.0
 */
object StreamWordCount {
    def main(args: Array[String]): Unit = {
        //1.初始化 Spark 配置信息
        val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("StreamWordCount")
        //2.初始化 SparkStreamingContext
        val ssc = new StreamingContext(sparkConf, Seconds(3))
        //3.通过监控端口创建 DStream，读进来的数据为一行行
        val lineStreams: ReceiverInputDStream[String] = ssc.socketTextStream("172.26.64.1", 9999)
        //将每一行数据做切分，形成一个个单词
        val wordStreams: DStream[String] = lineStreams.flatMap(_.split("\\s+"))
        //将单词映射成元组（word,1）
        val wordAndOneStreams: DStream[(String, Int)] = wordStreams.map(a => {
            print(a)
            (a, 1)
        })
        //将相同的单词次数做统计
        val wordAndCountStreams: DStream[(String, Int)] = wordAndOneStreams.reduceByKey(_ + _)
        //打印
        wordAndCountStreams.print()
        //启动 SparkStreamingContext
        ssc.start()
        ssc.awaitTermination()
        
    }
}
