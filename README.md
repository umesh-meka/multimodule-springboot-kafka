# multimodule-springboot-kafka
This is multi module project containing Kafka Producer and Kafka Consumer


# Commands to set up and run a Kafka cluster with Zookeeper
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties


# To run the producer and consumer, you can use the following commands in separate terminal windows.
bin/kafka-console-producer.sh --topic custom-topic-name --bootstrap-server localhost:9092
bin/kafka-console-consumer.sh --topic custom-topic-name --from-beginning --bootstrap-server localhost:9092

# Commands to delete topics
# List all topics
bin/kafka-topics.sh --bootstrap-server localhost:9092 --list

# Delete a specific topic
bin/kafka-topics.sh --bootstrap-server localhost:9092 --delete --topic my-topic

# Delete all topics
for t in $(bin/kafka-topics.sh --bootstrap-server localhost:9092 --list); do
  bin/kafka-topics.sh --bootstrap-server localhost:9092 --delete --topic "$t"
done

# Sample commands to consume messages from different topics
bin/kafka-console-consumer.sh --topic topic_string --from-beginning --bootstrap-server localhost:9092
bin/kafka-console-consumer.sh --topic topic_json --from-beginning --bootstrap-server localhost:9092

