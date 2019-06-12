cmake_minimum_required(VERSION 3.0.0)
project(compilers-principles_techiques_tools VERSION 0.1.0)

include(CTest)
enable_testing()


add_executable(ch1_ex-1_6_1 chapter_1/ex-1_6_1.c)
add_executable(ch1_ex-1_6_2 chapter_1/ex-1_6_2.c)
add_executable(ch1_ex-1_6_4 chapter_1/ex-1_6_4.c)

add_executable(ch1_ex-1_7 chapter_1/ex-1_7.c)
add_executable(hello_main main.cpp)

set(CPACK_PROJECT_NAME ${PROJECT_NAME})
set(CPACK_PROJECT_VERSION ${PROJECT_VERSION})
include(CPack)
