FROM maven:3-jdk-8-alpine

WORKDIR /app


COPY . /app

ENV ABCD=REMOTE

CMD ["mvn", "test"]