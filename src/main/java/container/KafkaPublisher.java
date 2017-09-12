package container;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.*;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

@Component
public class KafkaPublisher {

  public void publishMessage(String message) {
    ProducerRecord<String, String> record = new ProducerRecord<String, String>("docker-topic", "test-key", message);
    publish(record);
  }

  private <K, V> void publish(ProducerRecord<K, V> record) {
    try (KafkaProducer<K, V> producer = getKafkaProducer()) {
      CompletableFuture<RecordMetadata> future = new CompletableFuture();
      producer.send(record, (recordMetadata, e) -> {
        if (e != null) {
          future.completeExceptionally(e);
        }
        future.complete(recordMetadata);
      });
      future.get(3, TimeUnit.SECONDS);

    } catch (TimeoutException | InterruptedException | ExecutionException e) {
      throw new ContainerException("Error communicating with kafka.", e);
    }
  }

  private <K, V> KafkaProducer<K, V> getKafkaProducer() {
    Properties properties;
    try {
      properties = PropertiesLoaderUtils.loadAllProperties("kafka-producer.properties");
      properties.setProperty("bootstrap.servers", getEndpoints());
    } catch (IOException e) {
      throw new ContainerException("Error loading kafka properties.", e);
    }
    return new KafkaProducer<>(properties);
  }

  private String getEndpoints() {
    return System.getenv("KAFKA_BROKERS");
  }
}
