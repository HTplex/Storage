//
//  LoosStack.h
//  DatStctBasic
//
//  Created by Haotian Zhang on 19/10/14.
//  Copyright (c) 2014 HTPLEX. All rights reserved.
//

#ifndef DatStctBasic_SqStack_h
#define DatStctBasic_SqStack_h
#define INIT_STACK_SIZE 5
#define STACK_INCREMENT 10
typedef int Elemtype;
typedef struct {
    Elemtype* base;
    Elemtype* top;
    int stacksize;
}SqStack;

int InitStack_Sq(SqStack &s){
    s.base=(Elemtype*)malloc(sizeof(Elemtype)*INIT_STACK_SIZE);
    if(!s.base)
        return OVERFLOW;
    s.top=s.base;
    s.stacksize=INIT_STACK_SIZE;
    return OK;
}

int Push_Sq(SqStack &s, Elemtype e){
    
    if((s.top-s.base)>=s.stacksize){
        
        s.base=(Elemtype*)realloc(s.base, (s.stacksize+STACK_INCREMENT)*sizeof(SqStack));
        if(!s.base)
            exit(OVERFLOW);
        s.top=s.base+s.stacksize;
        s.stacksize+=STACK_INCREMENT;
    }
    
    *s.top++=e;
    return OK;
}

int Show_Sq(SqStack s){
    Elemtype *show=s.base;
    for(int i=0;i<s.stacksize;i++){
        std::cout<<*show++<<std::endl;
    }
    return OK;
}

 int Pop_Sq(SqStack &s,Elemtype &e){
    if(s.top==s.base)
        return ERROR;
     else
         e=*(--s.top);
     //s.stacksize--;
     return OK;
}
int Destroy_Sq(SqStack &s){
    free(s.base);
    return OK;
}
#endif
