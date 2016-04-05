//
//  BitTree.h
//  DatStctBasic
//
//  Created by Haotian Zhang on 14/11/14.
//  Copyright (c) 2014 HTPLEX. All rights reserved.
//

#ifndef DatStctBasic_BitTree_h
#define DatStctBasic_BitTree_h
typedef int Elemtype;

typedef struct BitNode{
    Elemtype data;
    struct BitNode* Lchild;
    struct BitNode* Rchild;
    
}BiNode, *BiTree;


int InitBiTree(BiTree &T){
    T=NULL;
    return OK;
}
void CreateBiTree(BiTree &T){
    char Nil=NULL;
    Elemtype in;
#ifdef CHAR
    scanf("%c", &in);
#endif
    if(in==Nil){
        T=NULL;
    }
    else{
        T=(BiTree)malloc(sizeof(BitNode));
        if(!T)
            exit(OVERFLOW);
        T->data=in;
        CreateBiTree(T->Lchild);
        CreateBiTree(T->Rchild);
    }
}
int TreeDepth(BiTree T){
    int i=0, j=0;
    if(!T)
        return 0;
    if(T->Lchild){
        i=TreeDepth(T->Lchild);
    }
    else i=0;
    if(T->Rchild){
        j=TreeDepth(T->Rchild);
    }
    else j=0;
    return i>j?i+1:j+1;
}


#endif
