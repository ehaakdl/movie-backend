# 사용할 기본 이미지
FROM centos7.9.2009

RUN mkdir -p /movie

RUN yum -y update

WORKDIR /movie

ADD ./mysql-8.0.28-linux-glibc2.12-x86_64.tar.xz ./
ADD ./openjdk-18_linux-x64_bin.tar.gz ./

RUN tar xzf openjdk-18_linux-x64_bin.tar.gz
RUN tar Jxvf mysql-8.0.28-linux-glibc2.12-x86_64.tar.xz
RUN mv mysql-8.0.28-linux-glibc2.12-x86_64 mysql

ENV JASYPT_PASSWD 1234
ENV JAVA_HOME /movie/jdk-18
ENV PATH $JAVA_HOME/bin:PATH

# 웹
EXPOSE 80
# ssh, sftp
EXPOSE 2200
# 예비용
EXPOSE 10000-100100
