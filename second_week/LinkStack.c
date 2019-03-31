#include "LinkStack.h"
#include <stdio.h>
#include <stdlib.h>

//��ջ(���������)
Status initLStack(LinkStack *s)   //��ʼ��
{
	if(s == NULL)
	 {
	 	printf("��ʼ��ʧ��");
	 	return ERROR;
	 }
	else
	 {
	 	s->count = 0;
		s->top = NULL;
		return OK;	
	 }
	  
}
Status isEmptyLStack(LinkStack *s)  //�ж���ջ�Ƿ�Ϊ��
{
	if(s != NULL)
	  return s->count == 0?OK:ERROR;
	else
	  { 
	    return ERROR;
	  }
}

Status getTopLStack(LinkStack *s,ElemType *e)  //�õ�ջ��Ԫ��
{
	if(s == NULL||isEmptyLStack(s)) 
	  return ERROR;
	else
	{
	   *e = s->top->data;
	   return OK;
	}
}
Status clearLStack(LinkStack *s)   //���ջ
{  
   if(s == NULL)
     return ERROR;
   else
    { 
      if(s->top == NULL&&s->count == 0)
        return ERROR;
      LinkStackPtr now = s->top,save = now->next;
      while (now != NULL) 
      {
      	free(now);
      	//���½ڵ� 
      	now = save;
      	save = save->next;
	  }
	 s->count = 0;
	 s->top = NULL;
	 return OK;
	}
}
Status destroyLStack(LinkStack *s)   //����ջ
{
  if(s == NULL)
   return ERROR;
  else
   {
   	 if(!isEmptyLStack(s))    //����Ϊ��ջ�������ջ���� 
   	 {
   	 	LinkStackPtr now = s->top,save = NULL;
        while (now != NULL) 
        {
      	free(now);
      	//���½ڵ� 
      	now = save;
      	save = save->next;
        }
	 }
    free(s);
    return OK;
   }
}
Status LStackLength(LinkStack *s,int *length)    //���ջ����
{
  if(s == NULL)
    return ERROR;
  else
  {
    *length = s->count;
    return OK;
  }
}
Status pushLStack(LinkStack *s,ElemType data)   //��ջ
{
	if(s == NULL)
    {
     	printf("��ջ�����ڣ�\n");
	}
	else
	{
		LinkStackPtr inter = (LinkStackPtr)malloc(sizeof(StackNode));
		if(inter == NULL)
		 { 
		   printf("�ڴ治�㣬��ջʧ�ܣ�\n");
		 }
		else
		 {
		 	inter->next = s->top;
		 	inter->data = data;
		 	s->top = inter;
		 	s->count++;
		 	printf("��ջ�ɹ�! \n");
		 	return OK;
		 }
	}
}
Status popLStack(LinkStack *s,ElemType *data)   //��ջ
{
	if(s == NULL||isEmptyLStack(s))
	{
	  	printf("��ջ�����ڻ��ջΪ��ջ");
	    return ERROR;
    }
    else
    {
       	LinkStackPtr save = s->top;
       	*data = save->data;
       	s->top = save->next;
       	free(save);
       	s->count--;
       	return OK;
	}
}
