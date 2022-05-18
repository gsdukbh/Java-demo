//
// Created by 李家威 on 2022/5/10.
//
#include "library.h"
#include <math.h>
extern "C" {
void print(const char* text) {
    StrPrint cpp_ins;
    std::string str = text;
    cpp_ins.print(str);
}
void helloM(){
    hello();
}
int max(int a,int b){
  return  maxy(a,b);
}

}