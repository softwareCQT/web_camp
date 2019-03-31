#include "LinkStack.h"
#include <stdio.h>
#include <stdlib.h>

//链栈(基于链表的)
Status initLStack(LinkStack *s)   //初始化
{
	if(s == NULL)
	 {
	 	printf("初始化失败");
	 	return ERROR;
	 }
	else
	 {
	 	s->count = 0;
		s->top = NULL;
		return OK;	
	 }
	  
}
Status isEmptyLStack(LinkStack *s)  //判断链栈是否为空
{
	if(s != NULL)
	  return s->count == 0?OK:ERROR;
	else
	  { 
	    return ERROR;
	  }
}

Status getTopLStack(LinkStack *s,ElemType *e)  //得到栈顶元素
{
	if(s == NULL||isEmptyLStack(s)) 
	  return ERROR;
	else
	{
	   *e = s->top->data;
	   return OK;
	}
}
Status clearLStack(LinkStack *s)   //清空栈
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
      	//更新节点 
      	now = save;
      	save = save->next;
	  }
	 s->count = 0;
	 s->top = NULL;
	 return OK;
	}
}
Status destroyLStack(LinkStack *s)   //销毁栈
{
  if(s == NULL)
   return ERROR;
  else
   {
   	 if(!isEmptyLStack(s))    //若不为空栈则进行清栈操作 
   	 {
   	 	LinkStackPtr now = s->top,save = NULL;
        while (now != NULL) 
        {
      	free(now);
      	//更新节点 
      	now = save;
      	save = save->next;
        }
	 }
    free(s);
    return OK;
   }
}
Status LStackLength(LinkStack *s,int *length)    //检测栈长度
{
  if(s == NULL)
    return ERROR;
  else
  {
    *length = s->count;
    return OK;
  }
}
Status pushLStack(LinkStack *s,ElemType data)   //入栈
{
	if(s == NULL)
    {
     	printf("该栈不存在！\n");
	}
	else
	{
		LinkStackPtr inter = (LinkStackPtr)malloc(sizeof(StackNode));
		if(inter == NULL)
		 { 
		   printf("内存不足，入栈失败！\n");
		 }
		else
		 {
		 	inter->next = s->top;
		 	inter->data = data;
		 	s->top = inter;
		 	s->count++;
		 	printf("入栈成功! \n");
		 	return OK;
		 }
	}
}
Status popLStack(LinkStack *s,ElemType *data)   //出栈
{
	if(s == NULL||isEmptyLStack(s))
	{
	  	printf("该栈不存在或该栈为空栈");
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
