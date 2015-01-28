CREATE TABLE Oddelenie (
  ident INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  nazov VARCHAR(30) NOT NULL CHECK (LENGTH(nazov) > 5),
  kod VARCHAR(4) NOT NULL CHECK (LENGTH(kod) > 1),
  poschodie INTEGER 
);

CREATE TABLE Zamestnanec (
  ident INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  meno VARCHAR(30) NOT NULL CHECK (LENGTH(meno) > 2),
  priezvisko VARCHAR(30) NOT NULL CHECK (LENGTH(priezvisko) > 2),
  vek INTEGER NOT NULL CHECK (vek BETWEEN 5 AND 10)
);

