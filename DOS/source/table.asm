DATA SEGMENT
	TABLE DB 30H, 31H, 32H, 33H, 34H, 35H, 36H, 37H
				DB 38H, 39H, 40H, 41H, 42H, 43H, 44H, 45H, 46H
	ASCII DB ?
	NUM DB 12
	DATA ENDS

CODE SEGMENT
	ASSUME DS:DATA, CS:CODE
	START:
	MOV AX, DATA
	MOV DS, AX
	MOV BX, OFFSET TABLE
	MOV AL, NUM
	XLAT
	MOV ASCII, AL
	MOV DL, AL
	MOV AH, 02H
	INT 21H
	MOV AH, 4CH
	INT 21H
CODE ENDS 
END START