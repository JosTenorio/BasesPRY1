﻿CREATE TABLE [dbo].[PARTE]
(
	[ID] INT NOT NULL IDENTITY, 
    [NOMBRE] NVARCHAR(50) NOT NULL, 
    [ID_FABRICANTE] INT NOT NULL,
    [ID_MARCA] INT NOT NULL, 
    CONSTRAINT FK_PARTE_FABPARTE FOREIGN KEY ([ID_FABRICANTE]) REFERENCES FAB_PARTES (ID)
        ON DELETE NO ACTION
        ON UPDATE CASCADE,
    CONSTRAINT FK_PARTE_MARCA FOREIGN KEY ([ID_MARCA]) REFERENCES MARCA (ID)
        ON DELETE NO ACTION
        ON UPDATE CASCADE,
    CONSTRAINT AK_PARTE UNIQUE(NOMBRE, ID_FABRICANTE, ID_MARCA),
    CONSTRAINT PK_PARTE PRIMARY KEY (ID)
)
