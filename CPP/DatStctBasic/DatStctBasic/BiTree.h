//
//  BiTree.h
//  DatStctBasic
//
//  Created by Haotian Zhang on 27/10/14.
//  Copyright (c) 2014 HTPLEX. All rights reserved.
//
#include <iostream>
#ifndef DatStctBasic_BiTree_h
#define DatStctBasic_BiTree_h
#define MAX_TREE_SIZE 100
#define ERROR -1
typedef int Telemtype;
typedef Telemtype SqBiTree[MAX_TREE_SIZE];
SqBiTree bt;




/*typedef struct BitNode{
    Telemtype data;
    struct BitNode *Lchild, *Rchild;
}BitNode, *BiTree;
#endif

int CreatBiTree(BiTree &T){
    if(BiTree BT=(BitNode*)malloc(sizeof(BitNode))){
        BT->Lchild=NULL;
        BT->Rchild=NULL;
    }
    return ERROR;
}

int PreOrederTraverse(BiTree T, int (*visit)(Telemtype e)){
    while(T){
        PreOrederTraverse(T->Lchild, visit);
        PreOrederTraverse(T->Rchild, visit);
    }
    visit(T->data);
    return 0;
}
int visit(Telemtype e){
    std::cout<<e<<std::endl;
    return 0;
    
}*/