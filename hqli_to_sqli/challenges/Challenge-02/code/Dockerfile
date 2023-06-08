FROM tomcat:9.0.31-jdk11-openjdk

COPY setup.sh setup.sh
COPY admin_secret.c admin_secret.c
COPY flag.c flag.c
COPY database.sql database.sql
COPY start.sh start.sh

RUN bash setup.sh

