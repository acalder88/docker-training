FROM openjdk:8
RUN echo "Hello"
COPY talker.jar /opt/jars/
COPY TaskListRunner.sh /
WORKDIR /opt/jars
ENV USER=Acalderon
EXPOSE 8080
ENTRYPOINT /TaskListRunner.sh
