import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author bigData-zj
 * @date 2023/8/9 22:21
 * @description $FunctionDescription
 * @version 1.0
 */
object ReceiverAPI {
    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf()
            .setMaster("local[4]")
            .setAppName("ReceiveWorkCount")
        val ssc = new StreamingContext(conf, Seconds(10))
        
        ssc.start()
        ssc.awaitTermination()
    }
}
