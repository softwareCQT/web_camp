#include "LinkStack.h"
#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	void windows(); 
    static LinkStack *stack;
	static int choice, sign;            //选择操作并记录是否初始化 
	
	while(1)
	{
	  windows();
	  if(stack == NULL)
	    stack = (LinkStack *)malloc(sizeof(LinkStack)); //创建一个链栈的头 
	  scanf("%d",&choice);
	  switch (choice)
	  { 
	    case 1:{ 
				 if(initLStack(stack))
				 {
				    printf("初始化成功\n");
				    sign = 1;
				 }
				 system("pause");                   
		       }break;
	   case 2:{ 
	            if(sign == 0)
	               break;
		        if(isEmptyLStack(stack))
		          printf("该栈为空！\n");
		        else
		          printf("该栈不为空！\n");
		        system("pause");
	          }break;
	   case 3:{
	   	        if(sign == 0)
	               break;
		        int outer;
		        if(getTopLStack(stack, &outer))
		          printf("栈顶元素为%d\n",outer);
		        else
		          printf("栈为空！\n");
				system("pause");
	          }break;
	   case 4:{
	   	        if(sign == 0)
	               break;
	   	        if(clearLStack(stack))
	   	          printf("清空成功！\n");
	   	        else
	   	          printf("栈为空\n");
	   	        system("pause");
	   	      }break;
	   case	5:{ 
	            if(sign == 0)
	               break;
		        if(destroyLStack(stack))
	   	         {
	   	         	printf("销毁成功！\n");
	   	         	stack = NULL;
	   	         	sign = 0;
				 }
	   	        else
	   	          printf("该栈不存在\n");
	   	        system("pause");
	          }break;
	   case 6:{
	   	       if(sign == 0)
	               break;
		       int length;
		       if(LStackLength(stack, &length))
		         printf("栈的长度是%d\n",length);
		       else
		         printf("栈不存在\n");
			   system("pause"); 
	          }break;
	   case 7:{
	   	       if(sign == 0)
	               break;
	           int element;
	           printf("请输入您要压入栈的数据(整数):");
			   scanf("%d",&element);
			   pushLStack(stack, element);
		      system("pause");   
	          }break;
	   case 8:{ 
	            if(sign == 0)
	               break;
	            int data;
	   	        if(popLStack(stack, &data))
	   	          printf("弹出的元素为%d\n",data);
	   	        else
	   	          printf("栈为空或栈不存在\n");
	   	        system("pause"); 
	          }break;
	   case 0:{
	   	        if(stack != NULL)
				{
				    free(stack);
	   	            stack = NULL;
				}
	   	      exit(0);
	          }break;
	   default:{
	            printf("输入错误!!!\n");
	            system("pause");
	           }       
	   }
	 if(sign == 0)
	 {
	 	printf("在未初始化前禁止其他操作，请输入1进行初始化！\n");
	 	system("pause");
	 }
	 system("cls"); 
	} 
	return 0;
}
void windows()
{   
    printf("***************************************************\n");
    printf("**********作者:陈起廷(3118004951) 软件工程1班******\n");
    printf("***************************************************\n");
	printf("**************     链表栈实现测试     *************\n");
	printf("**************      1.初始化栈        *************\n");
	printf("**************      2.判断栈是否为空  *************\n");
	printf("**************      3.得到栈顶元素    *************\n");
	printf("**************      4.清空栈          *************\n");
	printf("**************      5.销毁栈          *************\n");
	printf("**************      6.检测栈的大小    *************\n");
	printf("**************      7.入栈            *************\n");
	printf("**************      8.出栈            *************\n");
	printf("**************      0.退出            *************\n");
	printf("请输入您想要的操作(0~8)："); 
}
