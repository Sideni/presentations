DROP DATABASE chal05DB;
CREATE DATABASE chal05DB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER 'chal05'@'localhost' IDENTIFIED BY 'chal05';

GRANT SELECT ON chal05DB.* TO 'chal05'@'localhost';

CREATE TABLE chal05DB.users (
	username varchar(40) NOT NULL PRIMARY KEY,
	password varchar(40) NOT NULL,
	role ENUM('admin', 'normal') NOT NULL
);
INSERT INTO chal05DB.users 
VALUES 	('ChocoCacao','w4Z_2_easy_2_FiNd','normal'),
	('SexyLady','4ng3l_ze_rockst4R','normal'),
	('Mustang1997','C0Ro114_2001','normal'),
	('Pupper','SKIIAAAAAAAAAA','normal'),
	('SlimShady','PleaZeStaNdUuPpp!!','normal'),
	('Guy','correctingBatteringHorsingStaple','normal'),
	('Jean-Luc Mongrain','PENTOOOFFFFEEEE!!','normal'),
	('BigShaq','1337Pupps','normal'),
	('acdc','2Bucks','normal'),
	('ImSuchAnAdmin','85$?gdRTa2@!2"9*7vshjER1#=','admin');

