//
//  LinkListList.h
//  DatStctBasic
//
//  Created by Haotian Zhang on 21/10/14.
//  Copyright (c) 2014 HTPLEX. All rights reserved.
//

#ifndef DatStctBasic_LinkListList_h
#define DatStctBasic_LinkListList_h
typedef int Elemtype;
typedef struct QNode{
    Elemtype info;
    QNode *next;
}QNode, *QueuePrt;

typedef struct {
    QueuePrt head;
    QueuePrt tail;
}LinkListList;

int InitQueue_Link(LinkListList &L){
    if(QueuePrt ini=(QueuePrt)malloc(sizeof(QNode))){
        L.head=ini;
        L.tail=ini;
        L.head->next=NULL;
        return OK;
    }
    else return OVERFLOW;
}

int EnQueue_LLL(LinkListList &L, Elemtype e){
    if(QueuePrt p=(QueuePrt)malloc(sizeof(QNode))){
        p->info=e;
        L.tail->next=p;
        L.tail=p;
        return OK;
    }
       else return OVERFLOW;
}

int DeQueue_LLL(LinkListList &L, Elemtype &e){
    
    if(L.head!=L.tail){
        QNode* ou=L.head->next;
        e=ou->info;
        L.head=ou;
        return OK;
    }
    return ERROR;
}

int Del_LLL(LinkListList &L){
    while(L.head){
        L.tail=L.head->next;
        free(L.head);
        L.head=L.tail;
    }
    return OK;//what the hell is this!!
}
#endif
