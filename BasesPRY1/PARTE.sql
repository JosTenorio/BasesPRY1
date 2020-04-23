﻿CREATE TABLE [dbo].[PARTE]
(
	[ID] INT NOT NULL PRIMARY KEY IDENTITY, 
    [NOMBRE] VARCHAR(50) NOT NULL, 
    [NOMBRE_FAB] VARCHAR(50) NOT NULL,
    [MARCA] VARCHAR(50) NOT NULL,
    [MODELO_AUTO] VARCHAR(20) NULL, 
    [ANO_AUTO] NCHAR(4) NULL, 
    FOREIGN KEY (NOMBRE_FAB) REFERENCES FAB_PARTES (NOMBRE)
        ON DELETE NO ACTION
        ON UPDATE CASCADE,
    FOREIGN KEY (MARCA) REFERENCES MARCA (NOMBRE)
        ON DELETE NO ACTION
        ON UPDATE CASCADE,
    FOREIGN KEY (MODELO_AUTO) REFERENCES AUTOMOVIL (MODELO)
        ON DELETE NO ACTION
        ON UPDATE CASCADE,
    FOREIGN KEY (ANO_AUTO) REFERENCES AUTOMOVIL (ANO)
        ON DELETE NO ACTION
        ON UPDATE CASCADE,
)
