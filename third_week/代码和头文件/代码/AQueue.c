#include <stdio.h>
#include <stdlib.h>
#include "AQueue.h"
#include <String.h>
 
void InitAQueue(AQueue *Q)   //��ʼ������ 
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
    free(Q);           //������������QΪ�ձ�� 
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
Status GetHeadAQueue(AQueue *Q, void **e)   //�õ���ͷ��ֵ 
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
Status EnAQueue(AQueue *Q, void *data,size_t size)  //��� 
{
	if (IsFullAQueue(Q))
		return FALSE;
	else 
	{
	    Q->data[Q->rear] = (void *)malloc(size); 
	    memcpy(Q->data[Q->rear], data, size);  //��ֵ��ͬ���͵Ķ��� 
	    Q->size[Q->rear] = size;
	    if(Q->rear+1 == MAXQUEUE)
		  	Q->rear = 0;
	    else 
	        Q->rear++;
		return TRUE;  
	}
} 
Status DeAQueue(AQueue *Q)  //���� 
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
	printf("**************�������һ�ࣺ����͢(3118004951)*****************\n"); 
	printf("********************ѭ�����в���ʵ��***************************\n");
	printf("*********************1.��ʼ������******************************\n");
	printf("*********************2.����      ******************************\n");
	printf("*********************3.����      ******************************\n"); 
	printf("*********************4.��ͷ��ֵ  ******************************\n");
	printf("*********************5.�ݻٶ���  ******************************\n");
	printf("*********************6.��ն���  ******************************\n");
	printf("*********************7.�����п�  ******************************\n"); 
	printf("*********************8.��������  ******************************\n");
	printf("*********************9.���г���  ******************************\n");  
	printf("*********************0.�˳�      ******************************\n");
	printf("����������Ҫ�Ĳ�����");     
}
int SizeChoose(void **e) 
{   
     int choice;
	 printf("����������ö���λ�ô洢����ʾ����������(1-3)\n1.��������  2.�ַ�����  3.����������:\n");
	 while(1)
	 {  
	    scanf("%d", &choice);
	    if (choice > 0&&choice < 4)
	        printf("��������ѡ�������͵�����:\n");
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
	 	   default:printf("�������\n");
		}
	 }
 } 

































