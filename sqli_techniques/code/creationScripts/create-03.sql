DROP DATABASE chal03DB;
CREATE DATABASE chal03DB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER 'chal03'@'localhost' IDENTIFIED BY 'chal03';

GRANT SELECT ON chal03DB.* TO 'chal03'@'localhost';

CREATE TABLE chal03DB.users (
	username varchar(40) NOT NULL PRIMARY KEY,
	password varchar(40) NOT NULL
);
INSERT INTO chal03DB.users 
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
    ('admin','af%\"n5377?%$1xWf_23xGSDd');	

