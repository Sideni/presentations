#!/bin/bash

service mysql restart
su - tomcat
catalina.sh run

