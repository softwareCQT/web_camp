#include <stdio.h>
#include <stdlib.h>
#include "Caclulate.h" 

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
   char	a[200] = {0};
   char sign[50] = {0}; 
   C_Stack *c_stack = (C_Stack *)malloc(sizeof(C_Stack));
   S_Stack *s_stack = (S_Stack *)malloc(sizeof(S_Stack)); //����������ջ�ṹ 
   
   C_initStack(c_stack,50);
   S_initStack(s_stack,50);//��ʼ��ջ 
   
    while (1) 
    {
   	 int isNumber = 1;
   	 int choice = 0;
	 windows();
	 scanf("%d", &choice);
	 
	 switch(choice)
     {
	  case 1:{
	  	      printf("������������ʽ:");
	          scanf("%s",a);
	          int temp;
          	  for (temp = 0; a[temp] != '\0'; temp++)
           	  {
	          		if(a[temp] == '-'||a[temp] == '+'||a[temp] == '*'||a[temp] == '/'||a[temp] == '('
					                               ||a[temp] == ')'||(a[temp] <= '9'&&a[temp] >='0'))
                    	continue;
               		else
                	{
		          	   printf("�����������������:");
		     		   isNumber = 0;
		     		   break;
	            	}
              }
		     if(isNumber == 1)
             {
     	        change(a, sign, c_stack);
     	       printf("����ǣ�%12.4lf\n",caclulate(s_stack, a, sign)); 
             }     
            } break;
      case 0:{
      	       free(c_stack);
               free(s_stack);
               exit(0);
	          }
	  default:printf("û�и�ѡ�����ѡ:\n");
      }
      system("pause");
      system("cls");
   }
   return 0;
}

