data segment
 x dw 5
 y dw 2
 data ends

 code segment
 assume ds:data,cs:code
 start:
 mov ax,data
 mov ds,ax
 and x,ax
 mov ah,4ch˜
 int 21h
 code ends
 end start