﻿CREATE TABLE [dbo].[ORGANIZACION]
(
	[CEDULA_CLIENTE] NCHAR(10) NOT NULL PRIMARY KEY,
    [NOMBRE_CONTACTO] VARCHAR(50) NOT NULL,
    [CARGO_CONTACTO] VARCHAR(50) NOT NULL,
    FOREIGN KEY (CEDULA_CLIENTE) REFERENCES CLIENTE (CEDULA)
)
