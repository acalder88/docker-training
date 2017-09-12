FROM python:3.6-alpine3.6
WORKDIR /opt/jars
COPY requirements.txt ./
COPY consumer.py ./
ENV KAFKA_BROKERS=localhost:9092
RUN pip install --no-cache-dir -r requirements.txt
CMD [ "python", "./consumer.py" ]
