
//
//  MyDuLinkList.h
//  DatStctBasic
//
//  Created by Haotian Zhang on 19/10/14.
//  Copyright (c) 2014 HTPLEX. All rights reserved.
//

#ifndef DatStctBasic_MyDuLinkList_h
#define DatStctBasic_MyDuLinkList_h
typedef int Elemtype;


typedef struct MyDuLNode{
    struct MyDuLNode *prior;
    struct MyDuLNode *next;
    Elemtype data;
}MyDuLNode, *MyDuLinkList;

void InitList_MyDLL(MyDuLinkList &L){
    L=(MyDuLNode*)malloc(sizeof(MyDuLNode));
    L->prior=L;
    L->next=L;
    std::cin>>L->data;

}
MyDuLNode* GetElem_MyDLL(MyDuLinkList L,int a){
    MyDuLNode* p=L;
    if(a==0)    return p;
    else{
        while(a>0){
        a--;
        p=p->next;
            if(p==L){
                exit(OVERFLOW);
            }
        }
    }
    return p;
}
void ListInsert_MyDLL(MyDuLinkList L, int n, Elemtype k){
    MyDuLNode *newnode=(MyDuLNode*)malloc(sizeof(MyDuLNode));
    newnode->data=k;
    MyDuLNode *p=GetElem_MyDLL(L, n);
    newnode->prior=p;
    newnode->next=p->next;
    p->next->prior=newnode;
    p->next=newnode;
}

void ListDel_MyDLL(MyDuLinkList L, int n){
    MyDuLNode *p=GetElem_MyDLL(L, n);
    p->next->prior=p->prior;
    p->prior->next=p->next;
    free(p);
}
#endif
