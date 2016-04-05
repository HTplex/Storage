//
//  testgroup.h
//  Lab
//
//  Created by Haotian Zhang on 15/9/14.
//  Copyright (c) 2014 HTPLEX. All rights reserved.
//

#ifndef Lab_testgroup_h
#define Lab_testgroup_h
#include <String>
#include "stdio.h"
#include <iostream>
void test(){
    std::string s="so this is working";
    int i=1092;
    long c=3703998329358934L;
    long d=c-i;
    int m=0352;
    int x=0x983c;
    std::cout<<2*m<<std::endl;
    std::cout<<(4*x)<<std::endl;
    std::cout<<d<<std::endl;
}
#endif
