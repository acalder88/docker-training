FROM openjdk:8
RUN echo "Hello"
ADD talker.jar /opt/jars/
RUN chmod +x /opt/jars/talker.jar
WORKDIR /opt/jars
ENV USER=EAFIT
EXPOSE 8080
ENTRYPOINT java -jar /opt/jars/talker.jar
