data segment
  a db 1,1,1,1
    db 2,2,2,2
    db 3,3,3,3
  b db 1,1,1
    db 2,2,2
    db 3,3,3
    db 4,4,4
 m  dw 3			;a矩阵3行4列
 n  dw 4			;b矩阵4行3列
 p  dw 0
 c  db 9 dup(?)			;c矩阵3行3列
data ends
code segment
   assume cs:code,ds:data
main proc far
start:	mov ax,data
	mov ds,ax
        mov cx,m		
	mov di,0
	mov si,0
rept3:	push p			;用堆栈传参，保存p
	push cx
	mov cx,m		;子程序外循环次数
	call subr1
	pop cx
	pop p
	add p,4			;指向a矩阵下一行
	loop rept3	
	mov ah,4ch
	int 21h
main endp

subr1 proc near
	mov bp,sp		
rept2:	push cx			;共做3列
	mov bx,[bp+4]		;从堆栈中取出p→bx
	mov si,m		;m=3
	sub si,cx		
	mov cx,n		;n=4次			
rept1:				
	mov al,a[bx]		;a的下标0,1,2,3
	mov dl,b[si]
	imul dl			;乘加
   	add c[di],al		
	inc bx
	add si,3		;b的一列
	loop rept1		;循环4次
	inc di
	pop cx
	loop  rept2			
	ret
subr1 endp
	
code ends
     end start
