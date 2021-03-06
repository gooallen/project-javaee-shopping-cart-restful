-- any sql that is to be pre-loaded before testsuite runs goes in here
-- statements must be a single line without returns
DROP TABLE PLATFORM_USER IF EXISTS
DROP TABLE PLATFORM_USER_ROLE IF EXISTS
DROP TABLE PLATFORM_ROLE IF EXISTS

CREATE TABLE PLATFORM_USER (ID INTEGER IDENTITY NOT NULL, PWHASH VARCHAR, USERNAME VARCHAR, VERSION INTEGER, CREATED_DATE DATETIME, UPDATED_DATE DATETIME, PRIMARY KEY (ID))
CREATE TABLE PLATFORM_ROLE (ID INTEGER IDENTITY NOT NULL, ROLENAME VARCHAR, VERSION INTEGER, CREATED_DATE DATETIME, UPDATED_DATE DATETIME, PRIMARY KEY (ID))
CREATE TABLE PLATFORM_USER_ROLE (ROLE_ID INTEGER NOT NULL, USER_ID INTEGER NOT NULL, PRIMARY KEY (ROLE_ID, USER_ID))

ALTER TABLE PLATFORM_USER_ROLE ADD CONSTRAINT FK_PLATFORM_USER_ROLE_ROLE_ID FOREIGN KEY (ROLE_ID) REFERENCES PLATFORM_ROLE (ID)
ALTER TABLE PLATFORM_USER_ROLE ADD CONSTRAINT FK_PLATFORM_USER_ROLE_USER_ID FOREIGN KEY (USER_ID) REFERENCES PLATFORM_USER (ID)