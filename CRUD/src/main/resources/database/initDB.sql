CREATE TABLE IF NOT EXISTS clients
(
    id    INTEGER PRIMARY KEY,
    first_name  VARCHAR(200) NOT NULL ,
    last_name VARCHAR(254) NOT NULL ,
    date_birthday VARCHAR(20)  NOT NULL
);
 CREATE SEQUENCE clients_id_seq START WITH 1 INCREMENT BY 1;