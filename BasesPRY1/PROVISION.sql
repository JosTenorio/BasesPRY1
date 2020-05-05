﻿CREATE TABLE [dbo].[PROVISION]
(
	[ID_PARTE] INT NOT NULL, 
    [ID_PROVEEDOR] INT NOT NULL,
	[PRECIO_PROVEEDOR] DECIMAL(18, 2) NOT NULL DEFAULT 0,
    CHECK ([PRECIO_PROVEEDOR] >= 0),
    [POR_GANANCIA] DECIMAL(18, 2) NOT NULL DEFAULT 0,
    CHECK ([POR_GANANCIA] >= 0),
    FOREIGN KEY (ID_PARTE) REFERENCES PARTE (ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY ([ID_PROVEEDOR]) REFERENCES PROVEEDOR (ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    PRIMARY KEY (ID_PARTE, [ID_PROVEEDOR])
)
