#include "SqStack.h"
#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	void windows(); 
	static SqStack *stack;
	static int choice,sign = 0;            //ѡ�����;
	while(1)
	{
	  windows();
	  if(stack == NULL)
	    stack = (SqStack *)malloc(sizeof(SqStack));//����һ���ṹ��ʵ������ջ 
	  scanf("%d",&choice);
	  
	  switch (choice)
	  { 
	    case 1:{
		         int sizes;
		         printf("����ջ�ռ�Ĵ�С(����): ");
		         
				 while (scanf("%d",&sizes) != EOF && sizes < 0)
				 {
				 	printf("�������"); 
				 }  
				 if(initStack(stack, sizes))         //��sign�����ȷ���Ѿ���ʼ�� 
				     sign = 1;
				 system("pause");                   
		       }break;
	   case 2:{
	           if(sign == 0)
	               break;
		       if(isEmptyStack(stack))
		          printf("��ջΪ�գ�\n");
		      else
		          {
				    if(stack == NULL)
		              printf("��ջ�����ڣ�\n");
		            else
				     printf("��ջ��Ϊ�գ�\n");
			     }
		        system("pause");
	          }break;
	   case 3:{ 
	            if(sign == 0)
	               break;
		        int outer;
		        if(getTopStack(stack, &outer))
		          printf("ջ��Ԫ��Ϊ%d\n",outer);
		        else
		          printf("��ջ�����ڻ���ջΪ�գ�\n");
				system("pause");
	          }break;
	   case 4:{ 
	            if(sign == 0)
	               break;
	   	        if(clearStack(stack))
	   	          printf("��ճɹ���\n");
	   	        else
	   	          printf("��ջ�����ڻ���ջΪ��");
	   	        system("pause");
	   	      }break;
	   case	5:{  
	            if(sign == 0)
	               break;
		        if(destroyStack(stack))
	   	         {
	   	         	printf("���ٳɹ���\n");
	   	         	stack = NULL;
	   	         	sign = 0;
				 }
	   	        else
	   	          printf("��ջ������");
	   	        system("pause");
	          }break;
	   case 6:{
	   	       if(sign == 0)
	               break;
		       int length;
		       if(stackLength(stack, &length))
		         printf("ջ�ĳ�����%d\n",length);
		       else
		         printf("ջ������\n");
			   system("pause"); 
	          }break;
	   case 7:{
	   	       if(sign == 0)
	               break;
	           int element;
	           printf("��������Ҫѹ��ջ������(����):");
			   scanf("%d",&element);
			   if(!pushStack(stack, element))
		         printf("ѹ��ɹ�\n");
		      system("pause");   
	          }break;
	   case 8:{ 
	            if(sign == 0)
	               break;
	            int data;
	   	        if(popStack(stack, &data))
	   	          printf("������Ԫ��Ϊ%d\n",data);
	   	        else
	   	          printf("ջΪ�ջ�ջ������\n");
	   	        system("pause"); 
	          }break;
	   case 0:{
	   	        free(stack);
	   	        stack = NULL;
	   	        exit(0);
	          }break;
	   default:{
	            printf("�������!!!\n");
	            system("pause");
	           }       
	   }
	 if(sign == 0)
	 {
	   	printf("δ��ʼ��ǰ���ܽ����κβ�����������1���г�ʼ��!\n");
	    system("pause");
	 } 
	 system("cls"); 
	} 
	
	return 0;
}
void windows()
{   
    printf("***************************************************\n");
    printf("**********����:����͢(3118004951) �������1��******\n");
    printf("***************************************************\n");
	printf("**************     ����ջʵ�ֲ���     *************\n");
	printf("**************      1.��ʼ��ջ        *************\n");
	printf("**************      2.�ж�ջ�Ƿ�Ϊ��  *************\n");
	printf("**************      3.�õ�ջ��Ԫ��    *************\n");
	printf("**************      4.���ջ          *************\n");
	printf("**************      5.����ջ          *************\n");
	printf("**************      6.���ջ�Ĵ�С    *************\n");
	printf("**************      7.��ջ            *************\n");
	printf("**************      8.��ջ            *************\n");
	printf("**************      0.�˳�            *************\n");
	printf("����������Ҫ�Ĳ���(0~8)��"); 
}
