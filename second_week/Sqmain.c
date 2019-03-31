#include "SqStack.h"
#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	void windows(); 
	static SqStack *stack;
	static int choice,sign = 0;            //选择操作;
	while(1)
	{
	  windows();
	  if(stack == NULL)
	    stack = (SqStack *)malloc(sizeof(SqStack));//创建一个结构体实现数组栈 
	  scanf("%d",&choice);
	  
	  switch (choice)
	  { 
	    case 1:{
		         int sizes;
		         printf("输入栈空间的大小(整数): ");
		         
				 while (scanf("%d",&sizes) != EOF && sizes < 0)
				 {
				 	printf("输入错误"); 
				 }  
				 if(initStack(stack, sizes))         //用sign做标记确定已经初始化 
				     sign = 1;
				 system("pause");                   
		       }break;
	   case 2:{
	           if(sign == 0)
	               break;
		       if(isEmptyStack(stack))
		          printf("该栈为空！\n");
		      else
		          {
				    if(stack == NULL)
		              printf("该栈不存在！\n");
		            else
				     printf("该栈不为空！\n");
			     }
		        system("pause");
	          }break;
	   case 3:{ 
	            if(sign == 0)
	               break;
		        int outer;
		        if(getTopStack(stack, &outer))
		          printf("栈顶元素为%d\n",outer);
		        else
		          printf("该栈不存在或者栈为空！\n");
				system("pause");
	          }break;
	   case 4:{ 
	            if(sign == 0)
	               break;
	   	        if(clearStack(stack))
	   	          printf("清空成功！\n");
	   	        else
	   	          printf("该栈不存在或者栈为空");
	   	        system("pause");
	   	      }break;
	   case	5:{  
	            if(sign == 0)
	               break;
		        if(destroyStack(stack))
	   	         {
	   	         	printf("销毁成功！\n");
	   	         	stack = NULL;
	   	         	sign = 0;
				 }
	   	        else
	   	          printf("该栈不存在");
	   	        system("pause");
	          }break;
	   case 6:{
	   	       if(sign == 0)
	               break;
		       int length;
		       if(stackLength(stack, &length))
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
			   if(!pushStack(stack, element))
		         printf("压入成功\n");
		      system("pause");   
	          }break;
	   case 8:{ 
	            if(sign == 0)
	               break;
	            int data;
	   	        if(popStack(stack, &data))
	   	          printf("弹出的元素为%d\n",data);
	   	        else
	   	          printf("栈为空或栈不存在\n");
	   	        system("pause"); 
	          }break;
	   case 0:{
	   	        free(stack);
	   	        stack = NULL;
	   	        exit(0);
	          }break;
	   default:{
	            printf("输入错误!!!\n");
	            system("pause");
	           }       
	   }
	 if(sign == 0)
	 {
	   	printf("未初始化前不能进行任何操作，请输入1进行初始化!\n");
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
	printf("**************     数组栈实现测试     *************\n");
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
