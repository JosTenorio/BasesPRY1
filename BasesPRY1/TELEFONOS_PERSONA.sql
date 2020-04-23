﻿CREATE TABLE [dbo].[TELEFONOS_PERSONA]
(
	[CEDULA_PERSONA] NCHAR(9) NOT NULL, 
    [TELEFONO] NCHAR(8) NOT NULL, 
	CHECK (LEN(TELEFONO) = 8),
	FOREIGN KEY ([CEDULA_PERSONA]) REFERENCES PERSONA (CEDULA)
	    ON DELETE NO ACTION
        ON UPDATE CASCADE,
	PRIMARY KEY ([CEDULA_PERSONA], TELEFONO)
)
