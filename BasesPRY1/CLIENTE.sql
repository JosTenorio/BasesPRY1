﻿CREATE TABLE [dbo].[CLIENTE]
(
	[ID] INT NOT NULL IDENTITY, 
    [ID_ESTADO] INT NOT NULL,
	CONSTRAINT FK_CL_EST FOREIGN KEY ([ID_ESTADO]) REFERENCES ESTADO (ID),
	CONSTRAINT PK_CLIENTE PRIMARY KEY (ID)
)
