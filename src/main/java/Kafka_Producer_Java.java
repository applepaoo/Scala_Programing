import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;


import java.util.Properties;

public class Kafka_Producer_Java {

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put("bootstrap.servers", "140.128.98.31:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("enable.auto.commit", "false");
        props.put("auto.offset.reset", "earliest");
        Producer<String, String> producer = new KafkaProducer<>(props);
        System.out.println("準備傳送");






          for (int i = 0; i < 88; i++) {
            System.out.println("傳送開始");
            producer.send(new ProducerRecord<String, String>("TEST", Integer.toString(i), Integer.toString(i)));

            System.out.println(new ProducerRecord<String, String>("TEST", Integer.toString(i), Integer.toString(i)));

            System.out.println("傳送結束");

        }






        producer.close();
        System.out.println("Message sent successfully");





    }
}
