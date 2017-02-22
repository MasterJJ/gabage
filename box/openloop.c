#include <stdio.h>




// open loop printf msg
void superPrint(int count) {
  int idx = 0;
  for(idx = 0; idx < count; idx++) {

    printf("superPrint \r\n");

  }


  printf("End game \r\n");
  return;
}



int main() {

  superPrint(100);

 return 0;
}
