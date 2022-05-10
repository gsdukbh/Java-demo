cmake_minimum_required(VERSION 3.22)
project(test)

set(CMAKE_CXX_STANDARD 14)

add_library(test SHARED  library.cpp c_wrapper.cpp)
