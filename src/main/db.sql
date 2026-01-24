CREATE TABLE person
(
    id_person      SERIAL PRIMARY KEY,
    firstName      VARCHAR NOT NULL,
    surname        VARCHAR NOT NULL,
    lastName       VARCHAR NOT NULL,
    birthDate      DATE    NOT NULL,
    passportSeries CHAR(4) NOT NULL,
    passportNumber CHAR(6) NOT NULL,
    phone          VARCHAR NOT NULL UNIQUE,
    country        VARCHAR NOT NULL,
    city           VARCHAR NOT NULL,
    UNIQUE (passportSeries, passportNumber)
);

CREATE TABLE agencies
(
    id_agency SERIAL PRIMARY KEY,
    name      VARCHAR NOT NULL,
    country   VARCHAR NOT NULL,
    city      VARCHAR NOT NULL,
    phone     VARCHAR NOT NULL,
    email     VARCHAR NOT NULL,
    ownerId   INTEGER REFERENCES person (id_person)
);

CREATE TABLE realtors
(
    id_realtor SERIAL PRIMARY KEY,
    personId   INTEGER REFERENCES person (id_person),
    agencyId   INTEGER REFERENCES agencies (id_agency)
);

CREATE TABLE properties
(
    id_property SERIAL PRIMARY KEY,
    country     VARCHAR NOT NULL,
    city        VARCHAR NOT NULL,
    typeHouse   VARCHAR NOT NULL,
    floorCount  INTEGER CHECK (floorCount > 0),
    roomCount   INTEGER CHECK (roomCount > 0),
    ownerId     INTEGER REFERENCES person (id_person)
);

CREATE TABLE salesHistory
(
    id_history  SERIAL PRIMARY KEY,
    sale_date   DATE           NOT NULL,
    propertyId  INTEGER        REFERENCES properties (id_property),
    buyerId     INTEGER        REFERENCES person (id_person),
    realtorId   INTEGER        REFERENCES realtors (id_realtor),
    price       DECIMAL(12, 2) NOT NULL,
    paymentType VARCHAR        NOT NULL
);
