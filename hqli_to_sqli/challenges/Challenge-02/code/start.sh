#!/bin/bash

service mysql restart
service apache2 restart
su - tomcat
catalina.sh run

