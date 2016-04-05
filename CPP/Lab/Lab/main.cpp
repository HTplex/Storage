//
//  main.cpp
//  Lab

/*  this is a place for me to learn c programming language, its a old lanugaue but its the most classic language, creat most of the programs and OS, i think is is nessessary for me to know the semi-root from this thing so that i can know how other language works. thats why! know is much better than not know, i am not regret to learn java first, that give me the general vision of programming structral and classy, its much voluable for now. So...take care.

*/

//  Created by Haotian Zhang on 15/9/14.
//  Copyright (c) 2014 HTPLEX. All rights reserved.
//
//#include "aagetchar.h"
//#include "testgroup.h"
#include <iostream>
#include <stdio.h>
//#include <string.h>
#define forever while(true)

/*void ShellSort(int length, int *Vic);
int strlength(char s[]);
int func(int a, int b);
int func2(int &a, int &b);               //stand by
int strlenth(char *);
int charlen(char *);
void strcopy(char*, char*);
void times(void (*p)(int&, int&),int& a,int b,int c);
void add(int &a,int &b);
void time(int &a,int &b);*/

#define max(a,b) (a<b?b:a)

int func(int a, int b){
    b=a+2;
    return a*b;
}

int func2(int &a, int &b){                                           //need learn more
    return 0;
}

int strlength(char s[]){
    int i=0;
    for(int k=0;s[k]!='\0';k++)
        i++;
    return i;
}

void shellsortadv(int para, int length, int Vic[]){
    
}

void ShellSort(int length, int *Vic){
    
    for(int gap=length/2;gap>0;gap/=2){
        for(int i=gap;i<length;i++){
            for(int n=i-gap;n>=0&&Vic[n]>Vic[n+gap];n-=gap){
                int temp=Vic[n];
                Vic[n]=Vic[n+gap];
                Vic[n+gap]=temp;
            }
        }
    }
}

void swep(int &a, int &b){
    int temp=a;
    a=b;
    b=temp;
}

void reverse(int a){
    if(a/10)
    reverse(a/10);
    putchar('0'+a%10);
}

int strlenth(char *c){
    char e='0';
    int i;
    for(i=0;e!='\0';++i){
        e=*(c+i);
    }
    return i;
}

int charlen(char* a){
    char *p;
    p=a;
    while(*p!='\0')
        p++;
    return (int)(p-a);
}

void strcopy(char *a, char *b){
    while((*a=*b)!='\0'){
        a++;
        b++;
    }
}

void strcppy(char *a, char *b){
    while((*a++= *b++)){};
}

void times(void (*p)(int&, int&),int& a,int b,int c){
    for(int i=0;i<c;i++)
    p(a,b);
}

void add(int &a,int &b){
    a+=b;
}

void time(int &a,int &b){
    a*=b;
}

struct hellostruct{
    int a;
    int b;
    int c;
};

struct gostruct{
    hellostruct a;
    hellostruct b;
    hellostruct c;
};

struct hellostruct cratstruct(int a, int b, int c){
    hellostruct m;
    m.a=a;
    m.b=b;
    m.c=c;
    return m;
}
struct {
    int a;
    int b;
    int c;
}*sample;



int main(int argc, const char * argv[]) {
    
    bool checker=true;
    
    
    printf("input program id(a for index):\t");
    int i=getchar();
    
    switch(i){
            
        case ('0'):{
            //checker=false;
            //test();
        }break;
            
        case ('a'):{
            printf("i think this is the index looks like");
            
        }break;
            
        case ('b'):{                         //chario1
            printf(">>chario1\n");
            int c=getchar();
            while(c!=EOF){
                putchar(c);
                c=getchar();
            }
            
        }break;
            
        case ('c'):{                        //charCount
            printf(">>charCount\n");
            int nc=0;
            int c;
            int m;
            while((m=getchar())!=EOF){
                nc++;
                c=getchar();
                printf("%d",nc);
                printf("c: ");
                putchar(c);
                printf("\n");
                printf("m: ");
                putchar(m);
                printf("\n");
            }
        }break;
            
        case ('d'):{                        //charCount2
            printf("charcount2\n");
            int db;
            for(db=0; getchar()!=EOF;db++){};
            printf("%d", db);
        }break;
            
        case ('e'):{                       //lineCount
            printf("linecount\n");
            int line;
            int c;
            while((c=getchar())!=EOF){
                if(c=='\n')
                    line++;
            }
            printf("%d",line);
        }break;
            
        case ('f'):{                        //elimate continus blanks
            int a;
            while((a=getchar())!=EOF){
                if(a==' '){
                    putchar(a);
                    a=getchar();
                    while(a==' '){
                        a=getchar();
                    };
                }
                putchar(a);
            }
        }break;
            
        case ('g'):{                        //replace
            int a;
            while((a=getchar())!=EOF){
                if(a=='\t') printf("\\t");
                else if(a=='\b') printf("\\b");
                else if(a=='\\') printf("\\\\");
                else putchar(a);
            }
        }break;
            
        case ('h'):{
            int a=3;
            int b=9;
            func(a,b);
            printf("%d\t%d\t%d",a,b,func(a,b));
        }break;
            
        case ('i'):{
            
            int i=1092;
            long c=3703998329358934L;
            long d=c-i;
            int m=0352;
            int x=0x983c;
            std::cout<<2*m<<std::endl;
            std::cout<<(4*x)<<std::endl;
            std::cout<<d<<std::endl;
        }break;
            
        case ('j'):{
            char a[]="jerk";
            std::cout<<strlength(a)<<std::endl;
        }break;
            
        case ('k'):{
            std::cout<<"enum"<<std::endl;
            int m;
            std::cin>>m;
            switch(m){
                case 1:{
                    std::cout<<"enum boolean{ no, yes};"<<std::endl;
                    enum boolean{ no, yes};
                    boolean a=no;
                    boolean b=yes;
                    std::cout<<a<<std::endl;
                    std::cout<<b<<std::endl;
                }break;
                    
                case 2:{
                    enum days {sun=1, mon, tue, wed, thu, fri, sat};
                    days n=tue;
                    days k=fri;
                    std::cout<<n<<std::endl;
                    std::cout<<k<<std::endl;
                }break;
                    
                case 3:{
                    enum crazy {a=2, r=0, k=1};
                    std::cout<<"you know whats gonna happenes";
                }break;
            }
            
        }break;
            
        case ('l'):{
            const int i=0;
            const char worn[]="this is not a game!";
            std::cout<<i<<std::endl;
            std::cout<<worn<<std::endl;
        }break;
            
        case ('m'):{
            int a=3;
            int b=1;
            int c=2;
            int d=(a>b)?a:(b>c)?b:c;
            std::cout<<d<<std::endl;
        }break;
            
        case ('n'):{
            int array[]={3,6,7,9,6,2,5,8,0};
            ShellSort(9,array);
            for(int i=0;i<9;i++)
                std::cout<<array[i];
            checker=false;
        }break;
            
        case ('o'):{
            std::cout<<"are you srue?";
            int m;
            std::cin>>m;
            forever{
                std::cout<<"5";
            }
        }break;
            
            
            
            
        case('p'):{
            std::cout<<"it is all about pointers"<<std::endl;
            int a;
            std::cin>>a;
            switch(a){
                case(1):{
                    int a=10;
                    int *p;
                    p=&a;
                    std::cout<<*p<<std::endl;
                }break;
                case (2):{
                    int *t;
                    int a=10;
                    t=&a;
                    (*t)++;
                    ++*t;
                    std::cout<<a<<std::endl;
                }
                case (3):{
                    char c[]="this is nice";
                    std::cout<<strlenth(c)<<std::endl;                  //including \0
                }break;
                case(4):{
                    int a[]={1,6,3,4,6,5};
                    std::cout<<*a<<std::endl;
                    std::cout<<*(a+1)<<std::endl;
                    std::cout<<*a+1<<std::endl;
                }break;
                case(5):{
                    // static char *allocp= allocbuf;
                    
                    
                }break;
                    /*  case(6):{
                     char *p="this isstring";                       //ignore the warning
                     std::cout<<p<<std::endl;
                     std::cout<<*p<<std::endl;
                     
                     }break;
                     
                     case(7):{
                     int a=charlen("how about this time");
                     std::cout<<a<<std::endl;
                     }
                     break;
                     case(8):{
                     char *a[3]={"this","is","nice"};
                     char **p=a;
                     char *m="ok";
                     std::cout<<p[1][1]<<std::endl;
                     }*/
                case(9):{
                    int a=0;
                    int *t=&a;
                    int **m=&t;
                    std::cout<<m<<std::endl;
                    std::cout<<*m<<std::endl;
                    std::cout<<**m<<std::endl;
                }break;
                case(10):{
                    std::cout<<"pointer to functions"<<std::endl;
                    int a=3;
                    times(add,a,4,6);
                    int b=5;
                    times(time,b,4,6);
                    std::cout<<a<<std::endl;
                    std::cout<<b<<std::endl;
                }
                    
            }
            
            
        }break;
            
        case('q'):{
            std::cout<<"this time it is about struct"<<std::endl;
            int m;
            std::cin>>m;
            switch (m) {
               case(0):{
                   hellostruct *a;
                   hellostruct k;
                   a=&k;
                   a->a=0;
                   a->b=3;
                   a->c=a->a+a->b;
                   std::cout<<a->a<<std::endl;
                   std::cout<<a->b<<std::endl;
                   std::cout<<a->c<<std::endl;
               }break;
            
                case (1):{
                    std::cout<<"this is just for fun"<<std::endl;
                    for(int i=0;i<256;i++){
                        std::cout<<i<<" ";
                        std::cout<<(char)(i)<<std::endl;
                    }
                }break;
            
                case(2):{
                    gostruct a;
                    a.a.a=0;
                    a.a.b=1;
                    a.a.c=2;
                    //...
                }break;
                case(3):{
                    sample->a=1;
                    sample->b=2;
                    sample->c=2;
                    //...
                }break;
                case(4):{
                    struct m{
                        int a;
                        int b;
                    }mk[]={1,2,3,4,5,6};
                    std::cout<<mk[0].a<<' '<<mk[2].b<<std::endl;
                }break;
                case(5):{
                    typedef struct line{
                        int a;
                        int b;
                        union{
                            char c;
                            int d;
                            int* f;
                        } u;
                    }superline;
                }
            }
        }break;
            
            
        default:{
            std::cout<<"DIE"<<std::endl;
            
        }
            
    }
    
    
    
    
    
    printf("\n\n");
    return 0;
}