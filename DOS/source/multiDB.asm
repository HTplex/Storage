DATA SEGMENT
	X DB 32,98,0A8H,44
	Y DB 'STRING'
	M DW 00EFH,'EF'
	STR EQU ''
	Z DW 'S','T','R','I','N','G'
DATA ENDS

CODE SEGMENT
	ASSUME CS:CODE,DS:DATA
	START:
	MOV AX,DATA
	MOV DS,A
	MOV BH,X
	MOV BH,X+1
	MOV BH,X[2]
	MOV BH,X+3
	MOV X,BH


	MOV AH,4CH

	INT 21H
	CODE ENDS
END START
