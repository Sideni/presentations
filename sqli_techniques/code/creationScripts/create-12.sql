CREATE USER chal12 WITH PASSWORD 'chal12';

-- Disconnect users
SELECT pg_terminate_backend(pg_stat_activity.pid)
FROM pg_stat_activity
WHERE pg_stat_activity.datname = 'chal12db'
  AND pid <> pg_backend_pid();

DROP DATABASE chal12DB;
CREATE DATABASE chal12DB WITH OWNER chal12 ENCODING 'UTF8';

\c chal12db;

DROP SCHEMA chal12DB CASCADE;
CREATE SCHEMA chal12DB AUTHORIZATION chal12;

UPDATE pg_language SET lanpltrusted = true WHERE lanname LIKE 'c';

ALTER ROLE chal12 SET search_path=chal12db,public;
GRANT USAGE ON LANGUAGE C TO chal12;
GRANT CREATE ON DATABASE chal12DB TO chal12;
GRANT CONNECT ON DATABASE chal12DB TO chal12;
GRANT EXECUTE ON ALL FUNCTIONS IN SCHEMA chal12DB TO chal12;
GRANT SELECT ON ALL TABLES IN SCHEMA chal12DB TO chal12;
GRANT SELECT, INSERT ON pg_largeobject TO chal12;
ALTER ROLE chal12 WITH SUPERUSER;


SET ROLE chal12;
SET search_path TO chal12DB,public;

CREATE TABLE chal12DB.users (
	username varchar(40) NOT NULL PRIMARY KEY,
	password varchar(40) NOT NULL
);
INSERT INTO chal12DB.users
VALUES	('SexyLady','banane'),
	('Mustang1997','7991gnatsuM'),
	('Jean-Luc Mongrain','PANTOUFFFFEEEE!!'),
	('BigShaq','SKIIAAAAAAAAAA'),
	('GoatSeller','2Bucks'),
	('acdc','ShookMeAllNightLong'),
	('admin','af%''n5677?%$1xWf_23xGSDd'),	
	('ChocoCacao','pass1234'),
	('Pupper','1337Pupps'),
	('SlimShady','Pl33ZeSt4ndUpp'),
	('Guy','correctHorseBatteryStaple');	

CREATE TABLE chal12DB.msg (
	text TEXT,
	author varchar(30),
	date DATE
);
INSERT INTO chal12DB.msg VALUES ('Bienvenue sur mon nouveau site !','admin','2017-01-01'),('Two plus two is four, minus one is three, QUICK MATHS !','BigShaq','2017-09-25'),('Y VONT PORTER DES PANTOUFFES ! DEEEES PANTOUFFFES !','Jean-Luc Mongrain','2017-06-12'),('Who let the dogs out, wouuf wouuf wouuf','Pupper','2017-05-08'),('C''est vraiment trop bien','Guy','2017-03-24'),('No ketchup. Just sauce, raw sauce ! MAN''S NOT HOT !!!','BigShaq','2017-09-26'),('The thing goes Skrrrrrrra ! Pa pa ka ka ka ! Ski bi ki pa pa ! And the put put purrrrrt booom ! Skeeeyaah ! Do do ku ku pun pum ! Pum pum ! Ya done now, Big Shaq !','BigShaq','2017-09-29'),('vrooom Vrooommmm VRRROOOOOOMMMM VROOOOOOOOOOOOOOOOMMMMM!!!!!!!!','Mustang1997','2017-08-12'),('Moi, j''aime les muffins au chocolat !','ChocoCacao','2017-06-30'),('HIGHWAY TO HELLLLLLL !!!','acdc','2017-02-16'),('Je suis vraiment désolé, je pense que notre base de données a été compromise ... Nous allons vous garder informés pendant que votre identité se fait vendre sur le marché noir...','admin','2017-10-02'),('Mucho security, Mom''s spaghetti','SlimShady','2017-09-30');
