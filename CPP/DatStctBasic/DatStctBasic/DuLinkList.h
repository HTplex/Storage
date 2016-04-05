//
//  DuLinkList.h
//  DatStctBasic
//
//  Created by Haotian Zhang on 18/10/14.
//  Copyright (c) 2014 HTPLEX. All rights reserved.
//

#ifndef DatStctBasic_DuLinkList_h
#define DatStctBasic_DuLinkList_h
typedef int Elemtype;
typedef struct DuLNode{
    Elemtype data;
    struct DuLNode *prior;
    struct DuLNode *next;
}DuLNode, *DuLinkList;

DuLNode* GetElem_Dul(DuLinkList L, int i){
    DuLNode *p=L;
    int m;
    for(m=0;m<i;m++){
        if(p->next)
            p=p->next;
       
    }
     return p;
}
int InitList_Dul(DuLinkList &L){
   // L=(DuLinkList)malloc(sizeof(DuLNode));
    DuLNode p;
    p.data=0;

    p.next=NULL;
    L=&p;
    return OK;
}

int ListInsert_Dul(DuLinkList &L, int i, Elemtype e){
    DuLNode *s;
    DuLNode *p;
    p=GetElem_Dul(L, i);
    if(!(s=(DuLinkList)malloc(sizeof(DuLNode))))
        return ERROR;
    s->data=e;
    s->prior=p;
    
    s->next=p->next;
    
    p->next->prior=s;
    return 0;
}


#endif
