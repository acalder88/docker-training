#!/usr/local/bin/python3
from kafka import KafkaConsumer
from time import sleep
from random import randint
import os

class Consumer():
    def __init__(self):
        self.stop_now = False
        self._kafka_consumer = None

    def run(self):
        self._start_kafka_consumer()
        while not self.stop_now:
            try:
                result = self._kafka_consumer.poll(5000)
                self._handle_kafka_poll_result(result)
            except e:
                self._stop()
                raise e

    def _start_kafka_consumer(self):
        topic = "docker-topic"
        kafka_brokers = self._get_kafka_brokers()
        self._kafka_consumer = KafkaConsumer(topic,
                                             bootstrap_servers=kafka_brokers,
                                             auto_offset_reset="earliest",
                                             enable_auto_commit=True
                                             )
    def _handle_kafka_poll_result(self, kafka_poll_result: dict):
        for topic_partition, messages in kafka_poll_result.items():
            if len(messages) == 0:
                return
            for message in messages:  # Ignores all but the last message
                try:
                    self._write_file(message.value)
                except ValueError as e:
                    raise e

    def _get_kafka_brokers(self):
        return os.environ['KAFKA_BROKERS']

    def _write_file(self, message):
        with open("/tmp/test.txt", "a") as myfile:
            myfile.write(str(message) + "\n")

    def _stop(self):
        self.stop_now = True
        if self._kafka_consumer is not None:
            self._kafka_consumer.close()
            self._kafka_consumer.unsubscribe()

def main():
    sleep(10)
    consumer = Consumer()
    consumer.run()

if __name__ == "__main__":
    main()

