﻿CREATE TABLE [dbo].[TELEFONOS_PERSONA]
(
	[CEDULA_PERSONA] NCHAR(9) NOT NULL, 
    [TELEFONO] NCHAR(8) NOT NULL, 
	CONSTRAINT CH1_TELPER CHECK (LEN(TELEFONO) = 8),
	CONSTRAINT CH2_TELPER CHECK (TELEFONO NOT LIKE '%[^0-9]%'),
	CONSTRAINT FK_TELPER_PER FOREIGN KEY ([CEDULA_PERSONA]) REFERENCES PERSONA (CEDULA)
	    ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT PK_TELPER PRIMARY KEY ([CEDULA_PERSONA], TELEFONO)
)
