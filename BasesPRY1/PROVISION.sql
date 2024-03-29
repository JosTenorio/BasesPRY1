﻿CREATE TABLE [dbo].[PROVISION]
(
	[ID_PARTE] INT NOT NULL, 
    [ID_PROVEEDOR] INT NOT NULL,
	[PRECIO_PROVEEDOR] DECIMAL(18, 2) NOT NULL DEFAULT 0,
    CONSTRAINT CH_PRECPROV CHECK ([PRECIO_PROVEEDOR] >= 0),
    [POR_GANANCIA] DECIMAL(18, 2) NOT NULL DEFAULT 0,
    CONSTRAINT CH_PORGAN CHECK ([POR_GANANCIA] >= 0),
    [PRECIO_PUBLICO] DECIMAL(18, 2) NOT NULL DEFAULT 0,
    CONSTRAINT CH_PRECPUB CHECK ([PRECIO_PUBLICO] >= 0),
    CONSTRAINT FK_PROVI_PARTE FOREIGN KEY (ID_PARTE) REFERENCES PARTE (ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FK_PROVI_PROV FOREIGN KEY ([ID_PROVEEDOR]) REFERENCES PROVEEDOR (ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT PK_PROVI PRIMARY KEY (ID_PARTE, [ID_PROVEEDOR])
)
