DROP DATABASE chal01DB;
CREATE DATABASE chal01DB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER 'chal01'@'localhost' IDENTIFIED BY 'chal01';

GRANT SELECT ON chal01DB.* TO 'chal01'@'localhost';

CREATE TABLE chal01DB.users (
	username varchar(40) NOT NULL PRIMARY KEY,
	password varchar(40) NOT NULL
);
INSERT INTO chal01DB.users 
VALUES	
    ('aaaaaa','coucou,lulz'),
    ('ChocoCacao','pass1234'),
    ('Pupper','1337Pupps'),
    ('SlimShady','Pl33ZeSt4ndUpp'),
    ('Guy','correctHorseBatteryStaple'),
    ('SexyLady','banane'),
    ('Mustang1997','7991gnatsuM'),
    ('Jean-Luc Mongrain','PANTOUFFFFEEEE!!'),
    ('BigShaq','SKIIAAAAAAAAAA'),
    ('acdc','shookMeAllNightLong'),
    ('admin','af%\"n5677?%$1xWf_23xGSDd');	

