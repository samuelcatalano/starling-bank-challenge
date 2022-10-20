FROM centos

MAINTAINER Samuel Catalano <samuel.catalano@reclameaqui.com.br>

user root

RUN yum install -y \
     java-11-openjdk \
     java-11-openjdk-devel \
     initscripts

ENV JAVA_HOME /etc/alternatives/jre
RUN mkdir -p /usr/share/starlingbank && \
mkdir /var/run/starlingbank && \
mkdir /var/log/starlingbank

COPY target/starling-bank-challenge-test-0.0.1-SNAPSHOT.jar /usr/share/starlingbank/starling-bank-challenge-test.jar

WORKDIR /usr/share/starlingbank/
EXPOSE 8080 8787

ENV LC_ALL en_US.UTF-8
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US.UTF-8

CMD ["java","-Djava.security.egd=file:/dev/./urandom", "-Dfile.encoding=UTF-8", "-jar","starling-bank-challenge-test.jar"]