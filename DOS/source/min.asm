code segment
assume cs:code
start:
mov dl,1
add dl,30h
mov ah,2
int 21h
mov dl,7
add dl,30hdebug

mov ah,2
int 21h
mov ah,4ch
int 21h
code ends
end start
