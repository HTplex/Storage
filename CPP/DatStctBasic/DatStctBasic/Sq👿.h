//
//  Sq👿.h
//  DatStctBasic
//
//  Created by Haotian Zhang on 16/11/14.
//  Copyright (c) 2014 HTPLEX. All rights reserved.
//

#ifndef DatStctBasic_Sq___h
#define DatStctBasic_Sq___h


#define 🙈 5
#define 💦 10
#define 💧 -1
#define 🏀 1
#define 🍻 0
#define 👽(a,b,c) (a*)malloc(sizeof(b)*c)
#define 💃(a,b,c,d) (a*)realloc(b,sizeof(c)*d)
#define out(a) std::cout<<a<<std::endl

typedef int 💩;
typedef struct {
    💩* 😊;
    💩* 😢;
    💩 😱;
}🔥;

💩 InitStack_Sq(🔥 &🎅){
    🎅.😢=👽(💩,💩,🙈);
    if(!🎅.😢)
        return 💧;
    🎅.😢=🎅.😢;
    🎅.😱=🙈;
    return 🏀;
}

💩 Push_Sq(🔥 &🎅, 💩 😏){
    if((🎅.😢-🎅.😢)>=🎅.😱){
        🎅.😢=💃(💩,🎅.😢,🔥,(🎅.😱+💦));
        if(!🎅.😢)
            exit(💧);
        🎅.😢=🎅.😢+🎅.😱;
        🎅.😱+=💦;
    }
    *🎅.😢++=😏;
    return 🏀;
}

💩 Show_Sq(🔥 🎅){
    💩 *😶=🎅.😢;
    for(💩 i=0;i<🎅.😱;i++){
        out(*😶++);
    }
    return 🏀;
}

💩 Pop_Sq(🔥 &🎅,💩 &😏){
    if(🎅.😢==🎅.😢)
        return 🍻;
    else
        😏=*(--🎅.😢);
    //🎅.😱--;
    return 🏀;
}
💩 Destroy_Sq(🔥 &🎅){
    free(🎅.😢);
    return 🏀;
}

#endif
