
	ARRAY DB ..........
	MAX DW ?

	SUB1 PROC NEAR
	MOV CX,OFFSET SUM
	SUB CX,OFFSET ARRAY
	DEC CX
	MOV DI,1
	LOOP1:
	CMP AX,[ARRAY][DI]
	JG B
	JMP B2
	B:
		MOV AX,[ARRAY][DI]
	B2:
	INC DI
	LOOP LOOP1
	MOV MAX,AX
	RET
	SUB1 ENDP
