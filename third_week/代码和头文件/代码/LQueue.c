#include <stdio.h>
#include <stdlib.h>
#include "LQueue.h"
#include <string.h>

 
void InitLQueue(LQueue *Q)
{
   	Q->front = NULL;
   	Q->rear = NULL;
    Q->length = 0;
}
void DestoryLQueue(LQueue *Q)
{
	if(Q == NULL)
	{ 
	  	printf("摧毁失败，该队列不存在！\n");
	   	return; 
    }
    else if(IsEmptyLQueue(Q))
    {
    	free(Q);
	}
	else 
	{
		Node *save = Q->front;
		while (save != NULL)
		{
			save = save->next;
			free(Q->front);
		} 
		free(Q);
	    printf("摧毁成功！\n");	
	}
}
Status IsEmptyLQueue(const LQueue *Q)
{
       return Q->length == 0?TRUE:FALSE;
}
Status GetHeadLQueue(LQueue *Q, void **e)
{
  if(IsEmptyLQueue(Q) != TRUE)
   {    
        *e = (void *)malloc(Q->front->size);
   	    memcpy(*e, Q->front->data, Q->front->size);
   	 	return TRUE;
   }
   return FALSE;
}
int LengthLQueue(LQueue *Q)
{
	 return Q->length;
}
Status EnLQueue(LQueue *Q, void *data,int size)
{
		Node *p = (Node *)malloc(sizeof(Node));
		p->data = (void *)malloc(size);
	    memcpy(p->data, data, size);
		p->size = size; 
		p->next = NULL;
		Q->length++;
		if(Q->rear != NULL)
	      Q->rear->next = p;
		else 
			Q->front = p; 
		Q->rear = p; 
	  return TRUE; 
	
}
Status DeLQueue(LQueue *Q)
{
	if(!IsEmptyLQueue(Q))
	{
		Q->length--;
		if(Q->front == Q->rear)
		 {  
		    free(Q->front->data);   //结构体申请过的也要清空 
		 	free(Q->front);
		 	Q->front = Q->rear = NULL;
		 }
		else 
		{
		  Node *p = Q->front->next;
		  free(Q->front);
		  Q->front = p;
		 }
		return TRUE;  
	}
	return FALSE;
}
void ClearLQueue(LQueue *Q)
{
		Node *save = Q->front;
		while (save != NULL)
		{
			save = save->next;
			free(Q->front->data);
			free(Q->front);
	    	Q->front = save;
		} 
		Q->front = Q->rear = NULL;
		Q->length = 0;
		printf("清空成功！"); 
}
Status TraverseLQueue(const LQueue *Q, void (*foo)(void *q,size_t a))
{ 
	    Node *save = Q->front;
		if (save == NULL)
		  return FALSE; 
		while(save != NULL)
		{
			(*foo)(save->data,save->size);
			save = save->next;
		}
		return TRUE;
	    
}	
void LPrint(void *q, size_t size) 
{
	if (size == sizeof(double))
		printf("%lf\t", *(double *)q);
	if (size == sizeof(char))
		printf("%c\t", *(char *)q);
	if (size == sizeof (int))
		printf("%d\t", *(int *)q);
		
}
void windows()
{
	printf("***************************************************************\n"); 
	printf("**************软件工程一班：陈起廷(3118004951)*****************\n"); 
	printf("********************链队列操作实现*****************************\n");
	printf("*********************1.初始化队列******************************\n");
	printf("*********************2.进队      ******************************\n");
	printf("*********************3.出队      ******************************\n"); 
	printf("*********************4.队头的值  ******************************\n");
	printf("*********************5.摧毁队列  ******************************\n");
	printf("*********************6.清空队列  ******************************\n");
	printf("*********************7.队列判空  ******************************\n"); 
	printf("*********************8.遍历队列  ******************************\n");
	printf("*********************9.队列长度  ******************************\n");  
	printf("*********************0.退出      ******************************\n");
	printf("请输入你想要的操作：");     
}
int SizeChoose(void **e) 
{   
     int choice;
	 printf("请输入你想该队列位置存储并显示的数据类型(1-3)\n1.整数类型  2.字符类型  3.浮点数类型:\n");
	 while(1)
	 {  
	    scanf("%d", &choice);
	    if (choice > 0&&choice < 4)
	        printf("输入你所选数据类型的数据:\n");
	 	switch(choice)
	 	{  
	       case 1:{ int a;
		            scanf("%d",&a);
		            *e = (void *)malloc(sizeof(int)); 
                    memcpy(*e, &a, sizeof(int));
			        return sizeof(int);
			      }
	 	   case 2:{ char a;
		            scanf("%s",&a);
		            *e = (void *)malloc(sizeof(char)); 
					memcpy(*e, &a, sizeof(char));
				    return sizeof(char);
			       }
	 	   case 3:{ double a;
	 	            scanf("%lf",&a);
	 	            *e = (void *)malloc(sizeof(double)); 
	 	            memcpy(*e, &a, sizeof(double));
	 	            return sizeof(double);
			       }
	 	   default:printf("输入错误\n");
		}
	 }
 } 































