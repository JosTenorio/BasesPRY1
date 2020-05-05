﻿CREATE TABLE [dbo].[DETALLE]
(
    [ID_ORDEN] INT NOT NULL,
    [ID] INT NOT NULL IDENTITY,
    [PRECIO] DECIMAL(18, 2) NOT NULL DEFAULT 0,
    CHECK ([PRECIO] >= 0),
    [CANTIDAD] INT NOT NULL DEFAULT 1,
    CHECK ([CANTIDAD] >= 1),
    [ID_PARTE] INT NOT NULL, 
    [ID_PROVEEDOR] INT NOT NULL, 
    FOREIGN KEY (ID_ORDEN) REFERENCES ORDEN (ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (ID_PARTE) REFERENCES PARTE (ID)
        ON DELETE NO ACTION
        ON UPDATE CASCADE,
    FOREIGN KEY ([ID_PROVEEDOR]) REFERENCES PROVEEDOR(ID)
        ON DELETE NO ACTION
        ON UPDATE CASCADE,
    PRIMARY KEY (ID, ID_ORDEN)
)
