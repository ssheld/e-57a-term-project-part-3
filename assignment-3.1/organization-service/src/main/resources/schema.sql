DROP TABLE IF EXISTS organizations;

CREATE TABLE organizations (
    organization_id        VARCHAR(100) PRIMARY KEY NOT NULL,
    organization_name      TEXT NOT NULL,
    contact_name           TEXT NOT NULL,
    contact_email          TEXT NOT NULL,
    contact_phone          TEXT   NOT NULL);

INSERT INTO organizations (organization_id, organization_name, contact_name, contact_email, contact_phone)
VALUES ('d3a393a8-66c2-11ea-bc55-0242ac130003', 'Dunder Mifflin', 'Michael Scott', 'beammeupscotty@gmail.com', '5330953434');

INSERT INTO organizations (organization_id, organization_name, contact_name, contact_email, contact_phone)
VALUES ('32bc7e4a-66c3-11ea-bc55-0242ac130003', 'The Krusty Krab', 'Eugene Krabs', 'Eugene@KrustyKrab.com', '8768758866');

INSERT INTO organizations (organization_id, organization_name, contact_name, contact_email, contact_phone)
VALUES ('7a6926e4-66c3-11ea-bc55-0242ac130003', 'Los Pollos Hermanos', 'Gustavo Fring', 'Gus@hermanopollos.com', '9876543211');