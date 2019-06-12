#include <stdio.h>

void main() {
    int w, x, y, z;
    int i = 4;
    int j = 5;
    {
        int j = 7;
        i = 6;
        w = i + j; // w = 13
    }
    x = i + j; // x = 11
    {
        int i = 8;
        y = i + j; // y = 13
    }
    z = i + j; // z = 11

    printf("w = %d\n", w);
    printf("x = %d\n", x);
    printf("y = %d\n", y);
    printf("z = %d\n", z);
}