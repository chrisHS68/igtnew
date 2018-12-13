#FROM mariadb:latest
#ENV MYSQL_ROOT_PASSWORD jenskohler

FROM alpine/git
WORKDIR /app
#To avoid caching !!!!
ARG CACHEBUST=1
RUN git clone https://github.com/chrisHS68/igtnew

FROM maven:3.6.0-jdk-8-alpine
WORKDIR /app
RUN ls -a
COPY --from=0 /app/igtnew /app
RUN mvn clean
RUN mvn
#Exexcuted after Docker run
CMD mvn exec:java -Dexec.mainClass=de.hsma.jens.App
