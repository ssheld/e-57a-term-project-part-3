DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS user_orgs;

CREATE  TABLE users (
                        user_name VARCHAR(100) NOT NULL ,
                        password VARCHAR(100) NOT NULL ,
                        enabled boolean NOT NULL ,
                        PRIMARY KEY (user_name));

CREATE TABLE user_roles (
                            user_role_id SERIAL,
                            user_name varchar(100) NOT NULL,
                            role varchar(100) NOT NULL,
                            PRIMARY KEY (user_role_id));

CREATE TABLE user_orgs (
                           organization_id   VARCHAR(100)  NOT NULL,
                           user_name         VARCHAR(100)   NOT NULL,
                           PRIMARY KEY (user_name));

INSERT INTO users(user_name,password,enabled) VALUES ('jesus.quintana','@Z2_E?L!@eWL#!seDADJPyLx$fSQ$LY9', true);
INSERT INTO users(user_name,password,enabled) VALUES ('walter.sobchak','LBqgWY5zFEDLd6BU=?6s6h4Wq%xC?ErW', true);

INSERT INTO user_roles (user_name, role) VALUES ('jesus.quintana', 'ROLE_USER');
INSERT INTO user_roles (user_name, role) VALUES ('walter.sobchak', 'ROLE_ADMIN');
INSERT INTO user_roles (user_name, role) VALUES ('walter.sobchak', 'ROLE_USER');

INSERT INTO user_orgs (organization_id, user_name) VALUES ('9375b7f0-8587-11ea-bc55-0242ac130003', 'jesus.quintana');
INSERT INTO user_orgs (organization_id, user_name) VALUES ('ae68b404-8587-11ea-bc55-0242ac130003', 'walter.sobchak');
