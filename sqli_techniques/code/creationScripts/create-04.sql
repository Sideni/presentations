DROP DATABASE chal04DB;
CREATE DATABASE chal04DB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER 'chal04'@'localhost' IDENTIFIED BY 'chal04';

GRANT SELECT ON chal04DB.* TO 'chal04'@'localhost';

CREATE TABLE chal04DB.users (
	username varchar(40) NOT NULL PRIMARY KEY,
	password varchar(40) NOT NULL
);
INSERT INTO chal04DB.users 
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
    ('admin','af%\"n5477?%$1xWf_23xGSDd');	

