#include "SqStack.h"
#include <stdio.h>
#include <stdlib.h>

//顺序栈(基于数组的)
Status initStack(SqStack *s,int sizes)  //初始化栈
{ 
    if(s == NULL)
	  return ERROR;
	else
	{  
	   s->elem = (ElemType *)malloc(sizeof(ElemType)*sizes);
	   if(s->elem == NULL)
	     {
		   printf("空间不足，初始化失败！");
		   return ERROR; 
		 }
	   s->size = sizes;
	   s->top = -1;
	   printf("初始化成功");
	   return OK; 
	} 
}
Status isEmptyStack(SqStack *s)   //判断栈是否为空
{
	if(s == NULL)
	{
		 return ERROR;
	}
	 
	else
	  return s->top == -1?OK:ERROR;
}
Status getTopStack(SqStack *s,ElemType *e)   //得到栈顶元素
{ 
   if(s == NULL||isEmptyStack(s))
       return ERROR;
   else
   {
     *e = s->elem[s->top];
     return OK;
   }
}
Status clearStack(SqStack *s)   //清空栈
{ 
   if(s == NULL||isEmptyStack(s))
     return ERROR;
   else
    {
    	s->top = -1;
    	return OK;
	} 
}

Status destroyStack(SqStack *s)  //销毁栈
{
	if(s == NULL)
     return ERROR;
    else
     {
     	free(s);
     	s = NULL;
     	return OK;
	 }
}
Status stackLength(SqStack *s,int *length)   //检测栈的长度 
{
	if(s == NULL)
	  return ERROR;
	else
	{
		*length = s->top+1;
		 return OK; 
	}
}
Status pushStack(SqStack *s,ElemType data)  //入栈
{
	if(s == NULL)
	  return ERROR;
	else
	{
	   if(s->top == (s->size-1))	
	     {
	     	printf("栈已满，不允许数据继续进栈");
	     	return ERROR;
		 }
	   else
	   {
	   	 s->elem[++s->top] = data;
	   	 return OK;
	   }
	}
}
Status popStack(SqStack *s,ElemType *data)   //出栈
{
	if(s == NULL||isEmptyStack(s))
	  return ERROR;
	else
	{   
		*data = s->elem[s->top--];
		return OK;
	}
}

