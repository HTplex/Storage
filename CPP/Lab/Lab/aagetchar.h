//
//  aagetchar.h
//  Lab
//
//  Created by Haotian Zhang on 22/9/14.
//  Copyright (c) 2014 HTPLEX. All rights reserved.
//
#include<stdio.h>
#ifndef Lab_aagetchar_h
#define Lab_aagetchar_h
int c;
    void lab(){
    printf(">>chario1\n");
            c=getchar();
            while(c!=EOF){
                putchar(c);
               c=getchar();
}


#endif
