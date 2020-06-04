﻿INSERT INTO AUTOMOVIL (MODELO, ANO, DETALLE, ID_FABRICANTE)
SELECT AUTO_TEMP.MODELO, AUTO_TEMP.ANNO, AUTO_TEMP.DETALLE, FAB_AUTOS.ID
FROM AUTO_TEMP CROSS JOIN FAB_AUTOS 
WHERE AUTO_TEMP.FABRICANTE_NOMBRE = FAB_AUTOS.NOMBRE
GO
DROP TABLE AUTO_TEMP