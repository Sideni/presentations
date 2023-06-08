DROP DATABASE chal02DB;
CREATE DATABASE chal02DB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER 'chal02'@'localhost' IDENTIFIED BY 'chal02';

GRANT SELECT ON chal02DB.* TO 'chal02'@'localhost';

CREATE TABLE chal02DB.users (
	username varchar(40) NOT NULL PRIMARY KEY,
	password varchar(40) NOT NULL
);
INSERT INTO chal02DB.users 
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
    ('admin','af%\"n5277?%$1xWf_23xGSDd');	

