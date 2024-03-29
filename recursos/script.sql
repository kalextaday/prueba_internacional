-- banco.persona definition

CREATE TABLE `persona` (
  `ID_PERSONA` int NOT NULL,
  `NOMBRE` varchar(32) DEFAULT NULL,
  `APELLIDO` varchar(32) DEFAULT NULL,
  `EDAD` int DEFAULT NULL,
  `CARGO` varchar(64) DEFAULT NULL,
  `CUENTA` varchar(32) DEFAULT NULL,
  `CODIGO_INTERNO` varchar(9) DEFAULT NULL,
  `TIPO_PERSONA` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`ID_PERSONA`)
) ;


-- banco.movimiento definition

CREATE TABLE `movimiento` (
  `ID_MOVIMIENTO` int NOT NULL,
  `TIPO_MOVIMIENTO` varchar(3) DEFAULT NULL,
  `VALOR` decimal(10,0) DEFAULT NULL,
  `MOTIVO` varchar(100) DEFAULT NULL,
  `ID_PERSONA` int DEFAULT NULL,
  PRIMARY KEY (`ID_MOVIMIENTO`),
  KEY `movimiento_FK` (`ID_PERSONA`),
  CONSTRAINT `movimiento_FK` FOREIGN KEY (`ID_PERSONA`) REFERENCES `persona` (`ID_PERSONA`)
);



INSERT INTO banco.persona (ID_PERSONA, NOMBRE, APELLIDO, EDAD, CARGO, CUENTA, CODIGO_INTERNO, TIPO_PERSONA) VALUES(1, 'Jaime', 'Perez', 30, 'na', '717171xxxx6565', '001384529', 'CLI');
INSERT INTO banco.persona (ID_PERSONA, NOMBRE, APELLIDO, EDAD, CARGO, CUENTA, CODIGO_INTERNO, TIPO_PERSONA) VALUES(2, 'Jose', 'Almache', 21, 'Desarrollador', '922515xxxx5155', '008283219', 'EMP');
INSERT INTO banco.persona (ID_PERSONA, NOMBRE, APELLIDO, EDAD, CARGO, CUENTA, CODIGO_INTERNO, TIPO_PERSONA) VALUES(3, 'Alexander', 'Leon', 63, 'na', '121511xxxx3363', '009895754', 'CLI');
INSERT INTO banco.persona (ID_PERSONA, NOMBRE, APELLIDO, EDAD, CARGO, CUENTA, CODIGO_INTERNO, TIPO_PERSONA) VALUES(4, 'Dario', 'Zapata', 42, 'Diseñador', '656546xxxx6656', '003451452', 'EMP');



INSERT INTO banco.movimiento (ID_MOVIMIENTO, TIPO_MOVIMIENTO, VALOR, MOTIVO, ID_PERSONA) VALUES(1, 'CRE', 1500, 'Salario', 1);
INSERT INTO banco.movimiento (ID_MOVIMIENTO, TIPO_MOVIMIENTO, VALOR, MOTIVO, ID_PERSONA) VALUES(2, 'DEB', 10, 'Compra Online', 1);
INSERT INTO banco.movimiento (ID_MOVIMIENTO, TIPO_MOVIMIENTO, VALOR, MOTIVO, ID_PERSONA) VALUES(3, 'DEB', 12, 'Transferencia otro banco', 1);
INSERT INTO banco.movimiento (ID_MOVIMIENTO, TIPO_MOVIMIENTO, VALOR, MOTIVO, ID_PERSONA) VALUES(4, 'CRE', 80, 'Ahorro', 1);
INSERT INTO banco.movimiento (ID_MOVIMIENTO, TIPO_MOVIMIENTO, VALOR, MOTIVO, ID_PERSONA) VALUES(5, 'CRE', 2000, 'Salario', 2);
INSERT INTO banco.movimiento (ID_MOVIMIENTO, TIPO_MOVIMIENTO, VALOR, MOTIVO, ID_PERSONA) VALUES(6, 'DEB', 200, 'Compra', 2);
INSERT INTO banco.movimiento (ID_MOVIMIENTO, TIPO_MOVIMIENTO, VALOR, MOTIVO, ID_PERSONA) VALUES(7, 'DEB', 12, 'Compra', 2);
INSERT INTO banco.movimiento (ID_MOVIMIENTO, TIPO_MOVIMIENTO, VALOR, MOTIVO, ID_PERSONA) VALUES(8, 'CRE', 80, 'Salario', 2);
