//
//  LinkList.h
//  DatStctBasic
//
//  Created by Haotian Zhang on 16/10/14.
//  Copyright (c) 2014 HTPLEX. All rights reserved.
//

#ifndef DatStctBasic_LinkList_h
#define DatStctBasic_LinkList_h
typedef int Elemtype;
typedef struct LNode{
    Elemtype data;
    struct LNode *next;
}LNode, *LinkList;

int GetElem_l(LinkList L, int Loc, Elemtype &e){
    LNode *p=L->next;
    int i=1;
    while(p&&i<Loc){
        p=p->next;
        i++;
    }
    if(i>Loc||!p)
        return ERROR;
    e=p->data;
    return OK;
}

int ListInsert_l(LinkList L, int i, Elemtype e){
    LNode *p=L;
    int j=0;
    while(p&&j<i-1){
        p=p->next;
        j++;
    }
    if(!p||j>i-1){
        return ERROR;
    }
    LNode *s=(LinkList)malloc(sizeof(LNode));
    s->data=e;
    s->next=p->next;
    p->next=s;
    return OK;
}

void CreateList_l(LinkList &L, int n){
    L=(LinkList)malloc(sizeof(LNode));
    L->next=NULL;
    for(int i=0;i<n;i++){
        LinkList p=(LinkList)malloc(sizeof(LNode));
        std::cin>>p->data;
        p->next=L->next;
        L->next=p;
    }
}

void MergeList_l(LinkList a, LinkList b, LinkList &c){
    LNode *pa=a->next;
    LNode *pb=b->next;
    LNode *pc=a->next;
    c=pc;
    while(pa&&pb){
        if(pa->data>pb->data){
           // pc->data=pa->data; pc=pc->next; pa=pa->next;
            pc->next=pa;
            pc=pa;
            pa=pa->next;
        }
        else{
          //  pc->data=pb->data; pc=pc->next; pb=pb->next;
            pc->next=pb;
            pc=pb;
            pb=pb->next;
        }
    }
    pc->next=pa?pa:pb;
    free(b);
}


#endif
