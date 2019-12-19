FROM openjdk:8
RUN echo "Hello"
ADD proxy.jar /opt/jars/
RUN chmod +x /opt/jars/proxy.jar
WORKDIR /opt/jars
ENV USER=EAFIT
EXPOSE 8080
ENTRYPOINT java -jar /opt/jars/proxy.jar
