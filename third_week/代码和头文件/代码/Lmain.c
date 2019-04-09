#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "LQueue.h"

int main()
{
	int choice;
	LQueue *Queue = NULL;
	
	while(1)
	{
		windows();
		scanf("%d", &choice);
		switch(choice)
		{
			case 1:{
				    if (Queue == NULL)
					{
						Queue = (LQueue *)malloc(sizeof(LQueue));
						InitLQueue(Queue);
                        printf("��ʼ���ɹ�\n");
					}
					else 
					    printf("�Ѿ���ʼ��\n");
				    break;
			        }
		   case 2:{
		  	       if (Queue != NULL)
		  	       { 
		             void *a = NULL;
		             int b = SizeChoose(&a); 
		  	       	 if(EnLQueue(Queue, a, b))
		  	       	   printf("���ӳɹ�\n");
		  	       	 else 
		  	       	   printf("��������������ʧ��\n");
				   }
			       break;
		        }
		 case 3:{
		 	     if (Queue != NULL) 
		 	     { 
					if(DeLQueue(Queue))
					  printf("���ӳɹ�\n");
					else 
					  printf("����Ϊ�գ�����ʧ��\n");
			     }
			     break;
		         }
		case 4:{ 
		         if (Queue != NULL)
		        {
		           void *num = NULL;
		           if(GetHeadLQueue(Queue, &num))
		             { 
 		               printf("��ͷ��ֵΪ��");
		               LPrint(num, Queue->front->size);
		               printf("\n");
					} 
				  else 
				    printf("����Ϊ�գ��޷�����ֵ\n");	
				}
		      	break;
		      }		
	    case 5:{
	    	    if (Queue != NULL)
	    	    {
	    	         DestoryLQueue(Queue);
	    	         Queue = NULL;
				}
				break;
			    }
		case 6:{
			    if (Queue != NULL)
			    {
			    	ClearLQueue(Queue);
		     	}
	     		break;
	           }	
	    case 7:{
	    	     if (Queue != NULL)
	    	     {
	    	     	if (IsEmptyLQueue(Queue))
	    	     	  printf("����Ϊ��\n");
	    	     	else 
	    	     	  printf("���в�Ϊ��\n");
				 }
			    break;
	           }	
	    case 8:{
	    	    if (Queue != NULL)
	    	     {
	    	     	 if(TraverseLQueue(Queue, LPrint))
	    	     	   printf("�����ɹ�\n");
	    	     	 else 
	    	     	   printf("����Ϊ�գ�����ʧ��\n");
				 }
	            break;
		        }
		case 9:{
			    if (Queue != NULL)
			     {
			     	printf("���еĳ���Ϊ%d",LengthLQueue(Queue));
				 }
			     break;
	         	}
		case 0:{ if(Queue != NULL)
			        DestoryLQueue(Queue);
	    	     Queue = NULL; 
	    	     exit(0); 
			     break;
		       }
	    default:printf("�������\n");		   		 	  	
	   }
	if(Queue == NULL)
	   printf("���ʼ�����ٽ�����������\n");
	 system("pause");
	 system("cls");  
	}
    return 0;	
} 
