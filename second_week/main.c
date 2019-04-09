#include <stdio.h>
#include <stdlib.h>
#include "Caclulate.h" 

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
   char	a[200] = {0};
   char sign[50] = {0}; 
   C_Stack *c_stack = (C_Stack *)malloc(sizeof(C_Stack));
   S_Stack *s_stack = (S_Stack *)malloc(sizeof(S_Stack)); //创建并声明栈结构 
   
   C_initStack(c_stack,50);
   S_initStack(s_stack,50);//初始化栈 
   
    while (1) 
    {
   	 int isNumber = 1;
   	 int choice = 0;
	 windows();
	 scanf("%d", &choice);
	 
	 switch(choice)
     {
	  case 1:{
	  	      printf("请输入四则表达式:");
	          scanf("%s",a);
	          int temp;
          	  for (temp = 0; a[temp] != '\0'; temp++)
           	  {
	          		if(a[temp] == '-'||a[temp] == '+'||a[temp] == '*'||a[temp] == '/'||a[temp] == '('
					                               ||a[temp] == ')'||(a[temp] <= '9'&&a[temp] >='0'))
                    	continue;
               		else
                	{
		          	   printf("输入错误，请重新输入:");
		     		   isNumber = 0;
		     		   break;
	            	}
              }
		     if(isNumber == 1)
             {
     	        change(a, sign, c_stack);
     	       printf("结果是：%12.4lf\n",caclulate(s_stack, a, sign)); 
             }     
            } break;
      case 0:{
      	       free(c_stack);
               free(s_stack);
               exit(0);
	          }
	  default:printf("没有该选项，请重选:\n");
      }
      system("pause");
      system("cls");
   }
   return 0;
}

