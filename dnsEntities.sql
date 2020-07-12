-- Query creazione Database, il nome non è vincolato
CREATE DATABASE dnsentities;

USE dnsentities;

-- Tabella contacts che gestisce i contatti, è importante che gli id abbiano 16 caratteri (codice fiscale)
CREATE TABLE contacts (
	idContact CHAR(16) PRIMARY KEY, 
	firstName VARCHAR(48) NOT NULL, 
	lastName VARCHAR(48) NOT NULL, 
	contactType VARCHAR(48) NOT NULL
);

-- Tabella domains per la gestione dei domini (sarebbe opportuno gestire registrant e admin con una foreign key)
CREATE TABLE domains (
	domainname VARCHAR(64) PRIMARY KEY,
	registrant CHAR(16) NOT NULL,
	admin CHAR(16) NOT NULL,
	dnssec BOOLEAN
);

-- Tabella contracts per la gestione dei contratti (non è obbligatorio ma sarebbe opportuno che registrar, registrant e admin siano foreign key)
CREATE TABLE contracts (
	contractNumber INT PRIMARY KEY AUTO_INCREMENT,
	registrar CHAR(16) NOT NULL,
	registrant CHAR(16) NOT NULL,
	admin CHAR(16),
	domainName VARCHAR(64) NOT NULL
);

-- Popolamento con valori validi la tabella contacts
INSERT INTO contacts(idContact, firstName, lastName, contactType)
	VALUES
		('RCCRCC90A01A111A', 'Rocco', 'Rossi', 'Registrar'),
		('AGGRCC90A01A111A', 'Giovanni', 'Rossi', 'Registrant'),
		('GGILNN90A01A111A', 'Giuseppe', 'Bianchi', 'Admin'),
		('FBLAPS90A01A111A', 'Fabiana', 'Di Netta', 'Tech'),
		('RSARCC90A01A111A', 'Rosa', 'Verdi', 'Admin')
);

-- Popolamento domains
INSERT INTO domains(domainname, registrant, admin, dnssec)
	VALUES
		('www.test.it', 'AGGRCC90A01A111A', 'GGILNN90A01A111A', 1),
		('www.notSec.it', 'AGGRCC90A01A111A', 'RSARCC90A01A111A', 0),
		('www.test2.it', 'AGGRCC90A01A111A', 'GGILNN90A01A111A', 0)
);

-- Popolamento contracts
INSERT INTO contracts(registrar, registrant, admin, domainName)
	VALUES
		('RCCRCC90A01A111A', 'AGGRCC90A01A111A', 'GGILNN90A01A111A', 'www.test.it'),
		('RCCRCC90A01A111A', 'AGGRCC90A01A111A', 'RSARCC90A01A111A', 'www.notSec.it')
);

-- NB: Tutte le tabelle rispettano i vincoli di integrità referenziali anche in vista di eventuali foreign key come detto precedentemente
