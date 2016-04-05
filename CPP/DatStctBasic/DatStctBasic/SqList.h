//
//  List.h
//  DatStctBasic
//
//  Created by Haotian Zhang on 16/10/14.
//  Copyright (c) 2014 HTPLEX. All rights reserved.
//

#ifndef DatStctBasic_List_h
#define DatStctBasic_List_h
#define LIST_INIT_SIZE 100
#define LISTINCREMENT 10
#define OVERFLOW 1
#define OK 0
#define ERROR 0
typedef  int Elemtype;
typedef struct SqList{
    Elemtype *elem;
    int length;
    int size;
}SqList;

int InitList_Sq(SqList &L){
    L.elem=(Elemtype *)malloc(LIST_INIT_SIZE*sizeof(Elemtype));
    if(!L.elem)
        exit(OVERFLOW);
    L.length=0;
    L.size=LIST_INIT_SIZE;
    return OK;
}

int ListInsert_Sq(SqList &L, int loc, Elemtype info){
    if(loc<1||loc>L.size+1)
        return ERROR;
    if(L.length>=L.size){
        Elemtype *newbase=(Elemtype *)realloc(L.elem, (L.size+LISTINCREMENT)*sizeof(Elemtype));
        if(!newbase)
            exit (OVERFLOW);
        L.elem=newbase;
        L.size+=LISTINCREMENT;
    }
    return OK;
}

int ListDel_Sq(SqList &L, int loc, Elemtype &E){
    if(loc<1||loc>L.length)
        return ERROR;
    Elemtype *p=&(L.elem[loc-1]);
    E=*p;
    Elemtype *q=L.elem+L.length-1;
    for(++p;p<=q;++p)
        *(p-1)=*p;
    --L.length;
    return OK;
}
int Locate_Sq(SqList L, Elemtype E, int (*compare)(Elemtype a, Elemtype b)){
    int i=1;
    Elemtype* p=L.elem;
    while(i<=L.length&&!(*compare)(*p++,E))
        ++i;
    if(i<=L.length)
        return i;
    return -1;
}
void MergeList_Sq(SqList a, SqList b, SqList &c){
    c.length=a.length+b.length;
    c.elem=(Elemtype*)malloc(c.size*sizeof(Elemtype));
    Elemtype* pa=a.elem;
    Elemtype* pb=b.elem;
    Elemtype* pc=c.elem;
    if(!c.elem)
        exit (OVERFLOW);
    Elemtype* pa_last=a.elem+a.length-1;
    Elemtype* pb_last=b.elem+b.length-1;
    while(pa<=pa_last&&pb<=pb_last){
        if(*pa<*pb)
            *pc++=*pa++;
        else
            *pc++=*pb++;
    }
    while(pa<=pa_last)
        *pc++=*pa++;
    while(pb<=pb_last)
        *pc++=*pb++;
}

int compare(int a,int b){
    return a==b;
}
int compare(char a,char b){
    return a==b;
}
//int compare(Elemtype a, Elemtype b)...
#endif
