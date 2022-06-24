FROM openjdk:18

LABEL Thomas="jek_02@web.de"

ADD backend/target/todoAppImInternet.jar todoAppImInternet.jar

CMD [ "sh", "-c", "java -Dserver.port=$PORT -Dspring.data.mongodb.uri=$URI -jar /todoAppImInternet.jar" ]
