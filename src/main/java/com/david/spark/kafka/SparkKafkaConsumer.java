package com.david.spark.kafka;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.MapPartitionsFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;

public class SparkKafkaConsumer {

    public static void main(String[] args) throws InterruptedException {
        String propertiesFilepath = "src/main/resources/kafka.properties";
        Properties kafkaProperties = new Properties();
        try (InputStream is = new FileInputStream(propertiesFilepath)) {
            kafkaProperties.load(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SparkConf conf = new SparkConf().setMaster("local[*]").setAppName("spark-kafka-consumer");
        SparkSession session = SparkSession.builder().config(conf).getOrCreate();

        final MapPartitionsFunction<KafkaMessage, SimpleMessage> mapPartitionsFunction = kafkaMessageToSimpleMessage();

        while (true) {
            Dataset<SimpleMessage> df = session.read()
                .format("kafka")
                .options((Map) kafkaProperties)
                .load()
                .as(Encoders.bean(KafkaMessage.class))
                .mapPartitions(mapPartitionsFunction, Encoders.bean(SimpleMessage.class));
            df.show();
            Thread.sleep(5000);
        }
    }

    public static MapPartitionsFunction<KafkaMessage, SimpleMessage> kafkaMessageToSimpleMessage() {
        return (MapPartitionsFunction<KafkaMessage, SimpleMessage>) it -> {
            List<SimpleMessage> result = new LinkedList<>();
            while(it.hasNext()) {
                KafkaMessage km = it.next();
                SimpleMessage sm = new SimpleMessage(new String(km.getValue()));
                result.add(sm);
            }
            return result.iterator();
        };
    }
}
