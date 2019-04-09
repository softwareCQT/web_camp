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
	  	printf("�ݻ�ʧ�ܣ��ö��в����ڣ�\n");
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
	    printf("�ݻٳɹ���\n");	
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
		    free(Q->front->data);   //�ṹ���������ҲҪ��� 
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
		printf("��ճɹ���"); 
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
	printf("**************�������һ�ࣺ����͢(3118004951)*****************\n"); 
	printf("********************�����в���ʵ��*****************************\n");
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































