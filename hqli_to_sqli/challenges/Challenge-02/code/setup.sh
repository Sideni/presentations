#!/bin/bash

apt-get update
apt-get -y install apache2
apt-get -y install default-mysql-server
apt-get -y install expect
service mysql restart
expect -c "
set timeout 10 
spawn mysql_secure_installation 
expect \"Enter current password for root (enter for none):\"
send \"\r\"
expect \"Set root password? \[Y/n\]\" 
send \"y\r\"
expect \"New password:\"
send \"Whatever_The_Root_Password_Shall_Be_1234\!\r\"
expect \"Re-enter new password:\"
send \"Whatever_The_Root_Password_Shall_Be_1234\!\r\"
expect \"Remove anonymous users?\" 
send \"y\r\" 
expect \"Disallow root login remotely?\" 
send \"y\r\" 
expect \"Remove test database and access to it?\" 
send \"y\r\" 
expect \"Reload privilege tables now?\" 
send \"y\r\" 
expect eof"

apt-get -y purge expect

echo '[mysqld]' >> /etc/mysql/my.cnf
echo 'secure_file_priv=""' >> /etc/mysql/my.cnf

sed 's/}/\/\*\* rw,\n}/' /etc/apparmor.d/usr.sbin.mysqld
/etc/init.d/apparmor reload

service mysql restart

apt-get -y install php libapache2-mod-php php-mysql

sed 's/DirectoryIndex.*/DirectoryIndex index.php index.html index.cgi index.pl index.xhtml index.htm/' /etc/apache2/mods-enabled/dir.conf

mysql < database.sql
rm database.sql

apt-get -y install gcc

gcc admin_secret.c -o /admin_secret
chmod -rw /admin_secret
chmod +x /admin_secret
rm admin_secret.c

gcc flag.c -o /our_final_objective_is_to_take_down....
chmod -rw /our_final_objective_is_to_take_down....
chmod +x /our_final_objective_is_to_take_down....
rm flag.c

chmod 777 /var/www/html/

useradd tomcat
chown -R tomcat:tomcat /usr/local/tomcat

rm setup.sh

