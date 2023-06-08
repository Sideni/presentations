DROP DATABASE chal09DB;
CREATE DATABASE chal09DB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER 'chal09'@'localhost' IDENTIFIED BY 'chal09';

GRANT SELECT ON chal09DB.* TO 'chal09'@'localhost';

GRANT FILE ON *.* TO 'chal09'@'localhost';

CREATE TABLE chal09DB.users (
	username varchar(40) NOT NULL PRIMARY KEY,
	password varchar(40) NOT NULL
);
INSERT INTO chal09DB.users
VALUES	('SexyLady','banane'),
	('Mustang1997','7991gnatsuM'),
	('Jean-Luc Mongrain','PANTOUFFFFEEEE!!'),
	('BigShaq','SKIIAAAAAAAAAA'),
	('GoatSeller','2Bucks'),
	('ACDC','ShookMeAllNightLong'),
	('admin','af%\"n5677?%$1xWf_23xGSDd'),	
	('ChocoCacao','pass1234'),
	('Pupper','1337Pupps'),
	('SlimShady','Pl33ZeSt4ndUpp'),
	('Guy','correctHorseBatteryStaple');	
CREATE TABLE chal09DB.msg (
	text TEXT,
	author varchar(30),
	date DATE
);
INSERT INTO chal09DB.msg VALUES ('Bienvenue sur mon nouveau site !','admin','2017-01-01'),('Two plus two is four, minus one is three, QUICK MATHS !','BigShaq','2017-09-25'),('Y VONT PORTER DES PANTOUFFES ! DEEEES PANTOUFFFES !','Jean-Luc Mongrain','2017-06-12'),('Who let the dogs out, wouuf wouuf wouuf','Pupper','2017-05-08'),('C\'est vraiment trop bien','Guy','2017-03-24'),('No ketchup. Just sauce, raw sauce ! MAN\'S NOT HOT !!!','BigShaq','2017-09-26'),('The thing goes Skrrrrrrra ! Pa pa ka ka ka ! Ski bi ki pa pa ! And the put put purrrrrt booom ! Skeeeyaah ! Do do ku ku pun pum ! Pum pum ! Ya done now, Big Shaq !','BigShaq','2017-09-29'),('vrooom Vrooommmm VRRROOOOOOMMMM VROOOOOOOOOOOOOOOOMMMMM!!!!!!!!','Mustang1997','2017-08-12'),('Moi, j\'aime les muffins au chocolat !','ChocoCacao','2017-06-30'),('HIGHWAY TO HELLLLLLL !!!','ACDC','2017-02-16'),('Je suis vraiment désolé, je pense que notre base de données a été compromise ... Nous allons vous garder informés pendant que votre identité se fait vendre sur le marché noir...','admin','2017-10-02'),('Mucho security, Mom\'s spaghetti','SlimShady','2017-09-30');

