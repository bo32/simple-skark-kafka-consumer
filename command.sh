spark-submit \
    --packages \
org.apache.spark:spark-sql-kafka-0-10_2.11:2.3.1,org.apache.kafka:kafka-clients:2.0.0,org.apache.spark:spark-streaming-kafka-0-10_2.11:2.3.1 \
    --class com.david.spark.kafka.SparkKafkaConsumer \
    target/spark-kafka-consumer-1.0-SNAPSHOT.jar