﻿CREATE TABLE [dbo].[ESTADO]
(
	[ID] INT NOT NULL IDENTITY, 
	[TIPO] NVARCHAR(10) NOT NULL,
	CONSTRAINT AK_EST UNIQUE (TIPO),
	CONSTRAINT PK_EST PRIMARY KEY (ID)
)
