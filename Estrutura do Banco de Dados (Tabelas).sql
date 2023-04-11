CREATE TABLE SECAO_tb (

ID_SECAO SERIAL PRIMary key,
NOME VARCHAR (80) not null
)


CREATE TABLE PRODUTO_tb(
ID_PRODUTO SERIAL PRIMARY key,
NOME VARCHAR (80) NOT NULL,
VALOR NUMERIC (10,2),    
ID_SECAO INT,
FOREIGN KEY (ID_SECAO) REFERENCES SECAO_tb (ID_SECAO)
);