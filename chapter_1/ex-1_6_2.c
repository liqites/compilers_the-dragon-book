#include <stdio.h>

void main() {
    int w, x, y, z;
    int i = 3; int j = 4;
    {
        int i = 5;
        w = i + j; // w = 9
    }
    x = i + j; // x = 7
    {
        int j = 6;
        i = 7;
        y = i + j; // y = 13
    }
    z = i + j; // z = 11

    printf("w = %d\n", w);
    printf("x = %d\n", x);
    printf("y = %d\n", y);
    printf("z = %d\n", z);
}