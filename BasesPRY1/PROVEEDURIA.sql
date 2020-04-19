﻿CREATE TABLE [dbo].[PROVEEDURIA]
(
	[ID_PARTE] INT NOT NULL, 
    [NOMBRE_PROVEEDOR] VARCHAR(50) NOT NULL,
	[PRECIO_COMPRA] DECIMAL(18, 2) NOT NULL, 
    [PRECIO_VENTA] DECIMAL(18, 2) NOT NULL, 
    FOREIGN KEY (ID_PARTE) REFERENCES PARTE (ID),
    FOREIGN KEY (NOMBRE_PROVEEDOR) REFERENCES PROVEEDOR (NOMBRE),
    PRIMARY KEY (ID_PARTE, NOMBRE_PROVEEDOR)
)
