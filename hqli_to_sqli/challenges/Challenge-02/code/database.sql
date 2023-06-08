CREATE USER 'hibernate_user'@'localhost' IDENTIFIED BY 'You_thought_it_was_hibernate_password?';

DROP DATABASE IF EXISTS hibernate_DB;
CREATE DATABASE hibernate_DB CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;

GRANT SELECT, INSERT, UPDATE, CREATE ON hibernate_DB.* TO 'hibernate_user'@'localhost';
GRANT FILE ON *.* TO 'hibernate_user'@'localhost';

CREATE TABLE hibernate_DB.RebelLocation (
    id BIGINT(20),
    address VARCHAR(255),
    city VARCHAR(255),
    country VARCHAR(255),
    name VARCHAR(255)
);

INSERT INTO hibernate_DB.RebelLocation VALUES
    (1, "1100 Rue Notre-Dame Ouest", "Montreal", "Canada", "Undergrad Society's Assembly"),
    (2, "3155 ch. des Quatre-Bourgeois", "Quebec", "Canada", "Colombus Knights : Suspected to be hidden warriors for the rebels"),
    (3, "32 Rue des Soeurs Grises", "Montreal", "Canada", "AQT : Supposed to be defending what we're taking down"),
    (4, "1867 St Laurent Blvd", "Ottawa", "Canada", "Ingenium museum : Symbol of the utopian dreams of technology"),
    (5, "77 Massachusetts Ave", "Cambridge", "United States of America", "MIT : Where everything started and where everything shall end !");

CREATE TABLE hibernate_DB.special_force_targets (
    id BIGINT(20),
    coordinates VARCHAR(256),
    target VARCHAR(256),
    comment VARCHAR(1024)
);

INSERT INTO hibernate_DB.special_force_targets VALUES
    (1, "37.563936, -116.85123", "MaN3kys : Leader of operations", "In case an admin gets hacked, they'll only find the decoy information !"),
    (2, "37.629562, -116.849556", "Desini : Chief (not sure if misunderstood with Chef)", "These targets will remain in the database and won't be exposed to our application."),
    (3, "19°56’56.96″S 69°38’1.83″W", "Nezz YomA : Fat128's society founder", "These will be our next operations. However, our most important target must remain outside of this database and sleep on the filesystem until the day comes !"),
    (4, "45.5064954,-73.5715036", "NaNi : Meme lord", "They won't see it coming ! Soon, our flag will shine : FLAG-63bcabf86a9a991864777c631c5b7617");

