CREATE OR REPLACE TYPE DIREC3 AS OBJECT(
    CALLE VARCHAR(25),
    CIUDAD VARCHAR(20),
    CODIGO_POST NUMBER(5)
);
/

CREATE OR REPLACE TYPE TABLA_ANIDADA2 AS TABLE OF DIREC3;
/

CREATE TABLE ALMACEN(
    ID NUMBER(2),
    APELLIDOS VARCHAR2(35),
    DIREC TABLA_ANIDADA2
)NESTED TABLE DIREC STORE AS DIREC_ANIDADA;

INSERT INTO ALMACEN VALUES(
    1,
    'FREEMAN',
    TABLA_ANIDADA2(
        DIREC3('GUAREÑA','MADRID',28044)
    )
);

SELECT A.DIREC FROM ALMACEN A WHERE A.ID='1';
SELECT D.CALLE FROM ALMACEN A, TABLE(DIREC) D WHERE A.ID=1;

UPDATE TABLE(SELECT A.DIREC FROM ALMACEN A WHERE A.ID='1')PRIMERA
SET VALUE(PRIMERA)=DIREC3('PINZÓN','TOLEDO',45555)
WHERE VALUE(PRIMERA)=DIREC3('GUAREÑA','MADRID',28044);

INSERT INTO TABLE()