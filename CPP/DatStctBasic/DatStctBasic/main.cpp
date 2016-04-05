//
//  main.cpp
//  DatStctBasic
//
//  Created by Haotian Zhang on 16/10/14.
//  Copyright (c) 2014 HTPLEX. All rights reserved.
//

#include <iostream>
#include "SqList.h"
#include "SLinkList.h"
#include "DuLinkList.h"
#include "LinkList.h"
//#include "SqStack.h"
#include "MyDuLinkList.h"
#include "LinkListList.h"
#include "BitTree.h"
#include <string>
#include "Sq👿.h"

int main(int argc, const char * argv[]) {
    // insert code here...
    char index;
    std::cin>>index;
    switch (index){
        case ('a'):{
            std::cout<<add(1,2);
            std::cout<<"this is my own double dirction LinkList now\n";
            MyDuLinkList L;
            InitList_MyDLL(L);
            MyDuLNode* show=GetElem_MyDLL(L, 0);
            ListInsert_MyDLL(L, 0, 23);
            ListInsert_MyDLL(L, 1, 27);
            ListDel_MyDLL(L, 1);
            for(int i=0;i<10;i++){
            std::cout<<show->data<<std::endl;
                show=show->next;
            }
            
        }break;
            
        case('b'):{
            🔥 s;
            InitStack_Sq(s);
            Push_Sq(s, 10);
            Push_Sq(s, 22);
            Push_Sq(s, 78);
            Push_Sq(s, 10);
            Push_Sq(s, 22);
            Push_Sq(s, 78);
            int a;
            Pop_Sq(s, a);
            int e1;
            Pop_Sq(s, e1);
            int e2;
            Pop_Sq(s, e2);
            std::cout<<e1<<" "<<e2<<std::endl;
            Show_Sq(s);
        }break;
            
        case('c'):{
            BiTree T;
            InitBiTree(T);
            CreateBiTree(T);
            std::cout<<TreeDepth(T)<<std::endl;
        }break;
        case('0'):{
            double a[3];
            int i=0;
            while(true)
            std::cout<<a[i++];
        }break;
            
        default:{
            std::cout<<"you forgot break again aren't you";
        }break;
    }

        return 0;
}

