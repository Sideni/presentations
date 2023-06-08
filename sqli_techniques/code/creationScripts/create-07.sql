DROP DATABASE chal07DB;
CREATE DATABASE chal07DB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER 'chal07'@'localhost' IDENTIFIED BY 'chal07';

GRANT SELECT ON chal07DB.* TO 'chal07'@'localhost';

CREATE TABLE chal07DB.users (
	username varchar(40) NOT NULL PRIMARY KEY,
	password varchar(40) NOT NULL
);

INSERT INTO chal07DB.users
VALUES	('SexyLady','banane'),
	('Mustang1997','7991gnatsuM'),
	('Jean-Luc Mongrain','PANTOUFFFFEEEE!!'),
	('BigShaq','SKIIAAAAAAAAAA'),
	('GoatSeller','2Bucks'),
	('aaaaa','ShookMeAllNightLong'),
	('admin','af%\"n5677?%$1xWf_23xGSDd'),	
	('ChocoCacao','pass1234'),
	('Pupper','1337Pupps'),
	('SlimShady','Pl33ZeSt4ndUpp'),
	('Guy','correctHorseBatteryStaple');	

CREATE TABLE chal07DB.elMuchoFlag (
	flag varchar(255) NOT NULL
);
INSERT INTO chal07DB.elMuchoFlag VALUES ('FLAG-Much0_3rr0r5_w/_My5Q1');

