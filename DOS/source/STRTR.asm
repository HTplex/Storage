DATA SEGMENT
	STRG1 DB 'TRANSFER SUCCESSED'
					 ;123456789012345678901234567890
	NUM DB 18					
DATA ENDS

EXTRA SEGMENT
	STRG2 DB 18 DUP(?)
EXTRA ENDS

CODE SEGMENT
	ASSUME CS:CODE,DS:DATA,ES:EXTRA
	START:
	MOV AX,DATA
	MOV DS,AX
	MOV AX,EXTRA
	MOV ES,AX


	MOV CX,18
	MOV DI,0
	LOOP1:
		MOV AL,[STRG1][DI]
		MOV ES:[STRG2][DI],AL
		INC DI
	LOOP LOOP1

	MOV AH,4CH
	INT 21H
CODE ENDS
	END START


