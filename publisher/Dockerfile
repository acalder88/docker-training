FROM openjdk:8
COPY publisher.jar /opt/jars/
COPY TaskListRunner.sh /
WORKDIR /opt/jars
ENV KAFKA_BROKERS=172.30.255.78:9092
ENTRYPOINT /TaskListRunner.sh
