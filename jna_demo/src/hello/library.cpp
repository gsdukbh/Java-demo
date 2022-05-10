#include "library.h"
#include <iostream>

void hello() {
    std::cout << "Hello, World!" << std::endl;
}

void StrPrint::print(const std::string &text) {
    std::cout << text << std::endl;
}

int maxy(int a, int b) {
    if (a > b) {
        return a;
    } else {
        return b;
    }
}