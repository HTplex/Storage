DATA SEGMENT
DATA ENDS

CODE SEGMENT
	ASSUME CS:CODE,DS:DATA
	START:
	MOV AX,DATA
	MOV DS,AX

	MOV AH,1
	INT 21H			;INPUT FROM KEYBOARD TO AL

	ADD AL,20H

	MOV DL, AL
	MOV AH, 2;OUTPUT FROM DL TO DISPLAY
	INT 21H

	MOV AH,4CH
	INT 21H
CODE ENDS
END START