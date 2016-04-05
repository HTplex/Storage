//
//  HelloNode.h
//  SDS
//
//  Created by Haotian Zhang on 22/9/14.
//  Copyright (c) 2014 HTPLEX. All rights reserved.
//

#ifndef SDS_HelloNode_h
#define SDS_HelloNode_h
typedef char ElemType;
typedef int Status;

    typedef struct LNode{
        ElemType info;
        struct LNode *next;
    }LNode, *LinkList;

Status GetInfo(LinkList L, int i, ElemType &e ){
    LNode *p=L->next;
    int j=1;
    while(p&&j<i){
    p=p->next;
        j++;
    }
    if(!p||j>i)
        return -1;
    e=p->info;
    return 0;
}



#endif
