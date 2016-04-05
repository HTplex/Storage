assume cs:codesg   
datasg segment  
    db 'conversation',0  
datasg ends    
codesg segment  
    start:  
    mov ax,cs  
    mov ds,ax  
    mov si,offset capital_s  
          
    mov ax,0000h  
    mov es,ax  
    mov di,0200h  
      
    mov cx,offset capital_e - offset capital_s  
    cld  
    rep movsb;上面的代码是复制中断例程的代码  
      
    mov word ptr es:[7ch*4],0200h  
    mov word ptr es:[7ch*4+2],0000h;设置中断向量  
      
    mov ax,datasg  
    mov ds,ax  
    int 7ch;这里是测试代码  
      
    mov ax,4c00h  
    int 21h  
      
capital_s:  
    push ax  
    push ds  
    push si  
    push es  
    push di;将各个寄存器值入栈  
      
    mov ax,0b800h  
    mov es,ax  
    mov di,12*160+36*2 ;设置es:[di]指向显存段地址  
      
    mov ax,0  
    mov si,0  
    mov cx,0;把下面要用到的寄存器先清零  
      
change:  
    mov al,ds:[si]  
    mov cl,al  
    jcxz ok;判断是否是字符串的结尾    
    and al,11011111b;转化为大写  
    mov ah,0a0h;设置显示字体的颜色  
    mov es:[di],ax  
    inc si  
    add di,2  
    jmp short change  
ok: pop di  
    pop es  
    pop si  
    pop ds  
    pop ax  
    iret;恢复相关寄存器的值，返回主程序  
     
capital_e:nop  
        
      
codesg ends  
end start  

