#include "SqStack.h"
#include <stdio.h>
#include <stdlib.h>

//˳��ջ(���������)
Status initStack(SqStack *s,int sizes)  //��ʼ��ջ
{ 
    if(s == NULL)
	  return ERROR;
	else
	{  
	   s->elem = (ElemType *)malloc(sizeof(ElemType)*sizes);
	   if(s->elem == NULL)
	     {
		   printf("�ռ䲻�㣬��ʼ��ʧ�ܣ�");
		   return ERROR; 
		 }
	   s->size = sizes;
	   s->top = -1;
	   printf("��ʼ���ɹ�");
	   return OK; 
	} 
}
Status isEmptyStack(SqStack *s)   //�ж�ջ�Ƿ�Ϊ��
{
	if(s == NULL)
	{
		 return ERROR;
	}
	 
	else
	  return s->top == -1?OK:ERROR;
}
Status getTopStack(SqStack *s,ElemType *e)   //�õ�ջ��Ԫ��
{ 
   if(s == NULL||isEmptyStack(s))
       return ERROR;
   else
   {
     *e = s->elem[s->top];
     return OK;
   }
}
Status clearStack(SqStack *s)   //���ջ
{ 
   if(s == NULL||isEmptyStack(s))
     return ERROR;
   else
    {
    	s->top = -1;
    	return OK;
	} 
}

Status destroyStack(SqStack *s)  //����ջ
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
Status stackLength(SqStack *s,int *length)   //���ջ�ĳ��� 
{
	if(s == NULL)
	  return ERROR;
	else
	{
		*length = s->top+1;
		 return OK; 
	}
}
Status pushStack(SqStack *s,ElemType data)  //��ջ
{
	if(s == NULL)
	  return ERROR;
	else
	{
	   if(s->top == (s->size-1))	
	     {
	     	printf("ջ���������������ݼ�����ջ");
	     	return ERROR;
		 }
	   else
	   {
	   	 s->elem[++s->top] = data;
	   	 return OK;
	   }
	}
}
Status popStack(SqStack *s,ElemType *data)   //��ջ
{
	if(s == NULL||isEmptyStack(s))
	  return ERROR;
	else
	{   
		*data = s->elem[s->top--];
		return OK;
	}
}

