assume cs:code

data segment
	db '00/00/00 00:00:00','$'
	db 9,8,7,4,2,0

data ends

;2133605 张浩天

code segment
assume cs:code,ds:data
start:

clear macro
	mov ah,6
	mov al,0
	mov bh,70h   ;清屏宏
	mov ch,0
	mov cl,0
	mov dh,24
	mov dl,79
	int 10h
endm

	;mov ax,extra
	;mov es,ax
	mov ax,data
	mov ds,ax


	mov di,0
	mov si,18 

	mov cx,6
loop1:
	mov al,[si]
	out 70h,al
	in al,71h
	mov ah,al								;get
	mov cl,4
	shr ah,cl
	and al,0fh

	add ah,30h
	add al,30h

	mov [di],ah
	mov [di+1],al

	inc si
	add di,3
	loop loop1

	clear										;清屏

	mov ah,2
	mov bh,0
	mov dh,12
	mov dl,15
	int 10h

	mov ax,data
	mov ds,ax
	mov dx,0
	mov ah,9									;out
	int 21h

;	mov ax,extra
;	mov ds,ax
;	mov dx,0
;	mov ah,9
;	int 21h

	mov ah,4ch
	int 21h

code ends
end start