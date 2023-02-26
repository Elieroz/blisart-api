 FROM mysql:debian

 RUN apt update && apt install -y locales locales-all

 ENV LC_ALL en_US.UTF-8
 ENV LANG en_US.UTF-8
 ENV LANGUAGE en_US.UTF-8

 ENV MYSQL_ROOT_PASSWORD arthas
 ENV MYSQL_DATABASE blizzard
 ENV MYSQL_USER arthas
 ENV MYSQL_PASSWORD arthas
