FROM maven:3.8.2-jdk-11

WORKDIR /currency-master
COPY . .

CMD mvn spring-boot:run