//
//  SLinkList.h
//  DatStctBasic
//
//  Created by Haotian Zhang on 18/10/14.
//  Copyright (c) 2014 HTPLEX. All rights reserved.
//

#ifndef DatStctBasic_SLinkList_h
#define DatStctBasic_SLinkList_h
#define MAXSIZE 1000
typedef int Elemtype;
typedef struct{
    Elemtype data;
    int cur;
}component, SLinkList[MAXSIZE];

int LocateElem_Sl(SLinkList s, Elemtype e){
    int i=s[0].cur;
    while(i&&s[i].data!=e){
        i=s[i].cur;
    }
    return i;
}

void InitSpace_Sl(SLinkList Space){
    for(int i=0;i<MAXSIZE-1;i++){
        Space[i].cur=i+1;
    }
    Space[MAXSIZE-1].cur=0;
}

int Malloc_Sl(SLinkList &s){
    int i=s[0].cur;
    if(i)s[0].cur=s[i].cur;
    return i;
}

void Free_Sl(SLinkList s, int k){
    s[k].cur=s[0].cur;
    s[0].cur=k;//move right next to the top;
}


#endif
