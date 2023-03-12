CREATE OR REPLACE PROCEDURE INSERT_INVI (D_NOM IN DIRECTOR.NOMBRE%TYPE, D_TIT IN DIRECTOR.TITULACION%TYPE, O_INV IN ORQUESTA.COD_ORQUESTA%TYPE)
IS
F_INV  INVITADO.FECHA%TYPE:=sysdate;
S_COD DIRECTOR.COD_DIRECTOR%TYPE := SDIRE.NextVal;
BEGIN
	INSERT INTO DIRECTOR (COD_DIRECTOR,NOMBRE,TITULACION) VALUES(S_COD,D_NOM,D_TIT);
	INSERT INTO INVITADO VALUES(S_COD,O_INV,F_INV);
END INSERT_INVI;
/

CREATE OR REPLACE PROCEDURE BUSQUEDA (C_BUSC IN COMPOSITOR.NOMBRE%TYPE)
IS
CURSOR B_COMP IS 
	SELECT C.COD_COMPOSITOR, C.NOMBRE, N_OBRAS, FECHA_NAC, FECHA_DEF, COUNT(titulo) N_INT
	FROM COMPOSITOR C, OBRA O
	WHERE C.COD_COMPOSITOR=O.COD_COMPOSITOR
	GROUP BY C.COD_COMPOSITOR, C.NOMBRE, N_OBRAS, FECHA_NAC, FECHA_DEF;

REG B_COMP%ROWTYPE;

BEGIN
	OPEN B_COMP;
	FETCH B_COMP INTO REG;
	WHILE(B_COMP%FOUND) LOOP
		IF(REG.NOMBRE LIKE C_BUSC) THEN
		DBMS_OUTPUT.PUT_LINE(REG.NOMBRE||' '||REG.N_OBRAS||' '||REG.FECHA_NAC||' '||REG.FECHA_DEF||' '||REG.N_INT);
		EXIT;
	END IF;
	FETCH B_COMP INTO REG;
	END LOOP;
	CLOSE B_COMP;
END BUSQUEDA;
/
