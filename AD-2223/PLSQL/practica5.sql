CREATE OR REPLACE TYPE EMPLEADO AS OBJECT(
    RUT VARCHAR(10),
    NOMBRE VARCHAR(10),
    CARGO VARCHAR(9),
    FECHAING DATE,
    SUELDO NUMBER(9),
    COMISION NUMBER(9),
    ANTICIPO NUMBER(9),
    MEMBER FUNCTION SUELDO_LIQUIDO RETURN NUMBER,
    MEMBER PROCEDURE AUMENTO_SUELDO(AUMENTO NUMBER));
/

CREATE OR REPLACE TYPE BODY EMPLEADO AS 
    MEMBER FUNCTION SUELDO_LIQUIDO RETURN NUMBER 
    IS
    BEGIN
        RETURN(SUELDO+COMISION)-ANTICIPO;
    END;
    MEMBER PROCEDURE AUMENTO_SUELDO()
    IS
    BEGIN
        SUELDO:=SUELDO+AUMENTO;
    END;
END;
/

ALTER TYPE EMPLEADO REPLACE AS OBJECT(
    RUT VARCHAR(10),
    NOMBRE VARCHAR(10),
    CARGO VARCHAR(9),
    FECHAING DATE,
    SUELDO NUMBER(9),
    COMISION NUMBER(9),
    ANTICIPO NUMBER(9),
    MEMBER FUNCTION SUELDO_LIQUIDO RETURN NUMBER,
    MEMBER PROCEDURE AUMENTO_SUELDO(AUMENTO NUMBER),
    MEMBER PROCEDURE SETANTICIPO(ANTICIPO NUMBER));

CREATE OR REPLACE TYPE BODY EMPLEADO AS 
    MEMBER FUNCTION SUELDO_LIQUIDO RETURN NUMBER 
    IS
    BEGIN
        RETURN(SUELDO+COMISION)-ANTICIPO;
    END;
    MEMBER PROCEDURE AUMENTO_SUELDO()
    IS
    BEGIN
        SUELDO:=SUELDO+AUMENTO;
    END;
    MEMBER PROCEDURE SETANTICIPO(ANTICIPO NUMBER)
    IS
    BEGIN
        SELF.ANTICIPO:=ANTICIPO;
    END;
END;
/

SET SERVEROUTPUT ON;

DROP TABLE EMPLEADOS2:
CREATE TABLE EMPLEADOS2 OF EMPLEADO;
INSERT INTO EMPLEADOS2 VALUES('1', 'Pepa','DIRECTORA',SYSDATE, 2000,500, 0);
INSERT INTO EMPLEADOS2 VALUES('2', 'Juana','COMERCIAL',SYSDATE, 1000,800, 0);
INSERT INTO EMPLEADOS2 VALUES('3', 'Rosa','COMERCIAL',SYSDATE, 1000,800, 0);

DECLARE
    EMP EMPLEADO;
BEGIN
    SELECT VALUE(E) INTO EMP FROM EMPLEADOS2 E WEHRE E.RUT='1';
    DBMS_OUTPUT.PUT_LINE(
        EMP.NOMBRE||' '||EMP.CARGO||' SUELDO: '||EMP.SUELDO||' SUELDO LIQUIDO :'||EMP.SUELDO_LIQUIDO());
    EMP.AUMENTO_SUELDO(400);
    DBMS_OUTPUT.PUT_LINE(
        EMP.NOMBRE||' '||EMP.CARGO||' SUELDO: '||EMP.SUELDO||' SUELDO LIQUIDO :'||EMP.SUELDO_LIQUIDO());
END;
/