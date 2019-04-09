#include <stdio.h>
#include <stdlib.h>
#include "AQueue.h"
#include <string.h>

int main()
{
	int choice;
	AQueue *Queue = NULL;
	
	while(1)
	{
		windows();
		scanf("%d", &choice);
		switch(choice)
		{
			case 1:{
				    if (Queue == NULL)
					{
						Queue = (AQueue *)malloc(sizeof(AQueue));
						InitAQueue(Queue);
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
		  	       	 if(EnAQueue(Queue, a, b))
		  	       	   printf("���ӳɹ�\n");
		  	       	 else 
		  	       	   printf("��������������ʧ��\n");
				   }
			       break;
		        }
		 case 3:{
		 	     if (Queue != NULL) 
		 	     { 
					if(DeAQueue(Queue))
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
		           if(GetHeadAQueue(Queue, &num))
		             { 
 		               printf("��ͷ��ֵΪ��");
		               APrint(num, Queue->size[Queue->front]);
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
	    	         DestoryAQueue(Queue);
	    	         Queue = NULL;
	    	         printf("�ݻٳɹ���\n");
				}
				break;
			    }
		case 6:{
			    if (Queue != NULL)
			    {
			    	ClearAQueue(Queue);
			    	printf("��ճɹ���\n");
		     	}
	     		break;
	          }	
	    case 7:{
	    	     if (Queue != NULL)
	    	     {
	    	     	if (IsEmptyAQueue(Queue))
	    	     	  printf("����Ϊ��\n");
	    	     	else 
	    	     	  printf("���в�Ϊ��\n");
				 }
			    break;
	          }	
	    case 8:{
	    	    if (Queue != NULL)
	    	     {
	    	     	 if(TraverseAQueue(Queue, APrint))
	    	     	   printf("�����ɹ�\n");
	    	     	 else 
	    	     	   printf("����Ϊ�գ�����ʧ��\n");
				 }
	            break;
		        }
		case 9:{
			    if (Queue != NULL)
			     {
			     	printf("���еĳ���Ϊ%d",LengthAQueue(Queue));
				 }
			     break;
	         	}
		case 0:{ if(Queue != NULL)
			        DestoryAQueue(Queue);
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
