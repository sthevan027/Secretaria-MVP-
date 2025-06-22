CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE tb_agenda(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY NOT NULL,
    sender_number VARCHAR(20) NOT NULL,
    sender_name VARCHAR(100) NOT NULL,
    assunto VARCHAR(100) NOT NULL,
    data_hora TIMESTAMP WITHOUT TIME ZONE NOT NULL
);
