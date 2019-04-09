#include <stdio.h>
#include <stdlib.h>
#include "AQueue.h"
#include <String.h>
 
void InitAQueue(AQueue *Q)   //初始化队列 
{
	for (int i = 0; i<MAXQUEUE; i++)
	{
	  Q->data[i] = NULL;	
      Q->size[i] = 0; 
	} 
	Q->front = Q->rear =0;
}
void DestoryAQueue(AQueue *Q)
{
	for (int i = 0; i<MAXQUEUE; i++)
	  free(Q->data[i]);
    free(Q);           //在主函数中令Q为空便可 
}
Status IsFullAQueue(const AQueue *Q)
{
	if ((Q->rear+1)%MAXQUEUE == Q->front)
	   return TRUE;
	else 
	   return FALSE;
}
Status IsEmptyAQueue(const AQueue *Q)
{
	return Q->front == Q->rear?TRUE:FALSE;
}
Status GetHeadAQueue(AQueue *Q, void **e)   //得到队头的值 
{
	if(IsEmptyAQueue(Q))
	  return FALSE;
	else
	{
	   *e = (void *)malloc(Q->size[Q->front]);
	   memcpy(*e, Q->data[Q->front], Q->size[Q->front]);
	   return TRUE;	
    } 
}
int LengthAQueue(AQueue *Q)
{
	return ((Q->rear-Q->front+MAXQUEUE)%MAXQUEUE);
}               
Status EnAQueue(AQueue *Q, void *data,size_t size)  //入队 
{
	if (IsFullAQueue(Q))
		return FALSE;
	else 
	{
	    Q->data[Q->rear] = (void *)malloc(size); 
	    memcpy(Q->data[Q->rear], data, size);  //赋值相同类型的东西 
	    Q->size[Q->rear] = size;
	    if(Q->rear+1 == MAXQUEUE)
		  	Q->rear = 0;
	    else 
	        Q->rear++;
		return TRUE;  
	}
} 
Status DeAQueue(AQueue *Q)  //出队 
{
	if (IsEmptyAQueue(Q))
	  return FALSE;
	else 
	{   
	    free(Q->data[Q->front]);
		if(Q->front+1 == MAXQUEUE)
			Q->front = 0;
		else
		    Q->front++;
		return TRUE; 
	}
}
void ClearAQueue(AQueue *Q)
{
	Q->front = Q->rear = 0; 
}
Status TraverseAQueue(const AQueue *Q, void (*foo)(void *q,size_t size))
{   
   if(IsEmptyAQueue(Q))
   		return FALSE;	
   for (int i = Q->front; i != Q->rear; i++)
	{
	  	(*foo)(Q->data[i], Q->size[i]);
	  	if (i+1 == MAXQUEUE)
	  	  i = 0;
	}
   (*foo)(Q->data[Q->rear], Q->size[Q->rear]);
}
void APrint(void *q, size_t size)
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
	printf("********************循环队列操作实现***************************\n");
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

































