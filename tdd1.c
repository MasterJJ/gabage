#include <stdio.h>




error

int minus(int a, int b) {
  return a - b;
}

int sum(int a, int b) {
  return a + b;
}



int main() {
  int result = 0;
  // insert



  result = sum(5, 5);


  // check
  if (result == 5 ) {
    printf("tdd check ok %d", result);

  } else {
    printf("tdd check failed %d", result);
  }



  return 0;
}
