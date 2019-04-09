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
                        printf("初始化成功\n");
					}
					else 
					    printf("已经初始化\n");
				    break;
			        }
		  case 2:{
		  	       if (Queue != NULL)
		  	       { 
		             void *a = NULL;
                     
		             int b = SizeChoose(&a); 
		  	       	 if(EnAQueue(Queue, a, b))
		  	       	   printf("进队成功\n");
		  	       	 else 
		  	       	   printf("队伍已满，进队失败\n");
				   }
			       break;
		        }
		 case 3:{
		 	     if (Queue != NULL) 
		 	     { 
					if(DeAQueue(Queue))
					  printf("出队成功\n");
					else 
					  printf("队列为空，出队失败\n");
			     }
			     break;
		         }
		case 4:{ 
		         if (Queue != NULL)
		        {
		           void *num = NULL;
		           if(GetHeadAQueue(Queue, &num))
		             { 
 		               printf("队头的值为：");
		               APrint(num, Queue->size[Queue->front]);
		               printf("\n");
					} 
				  else 
				    printf("队列为空，无法返回值\n");	
				}
		      	break;
		      }		
	    case 5:{
	    	    if (Queue != NULL)
	    	    {
	    	         DestoryAQueue(Queue);
	    	         Queue = NULL;
	    	         printf("摧毁成功！\n");
				}
				break;
			    }
		case 6:{
			    if (Queue != NULL)
			    {
			    	ClearAQueue(Queue);
			    	printf("清空成功！\n");
		     	}
	     		break;
	          }	
	    case 7:{
	    	     if (Queue != NULL)
	    	     {
	    	     	if (IsEmptyAQueue(Queue))
	    	     	  printf("队列为空\n");
	    	     	else 
	    	     	  printf("队列不为空\n");
				 }
			    break;
	          }	
	    case 8:{
	    	    if (Queue != NULL)
	    	     {
	    	     	 if(TraverseAQueue(Queue, APrint))
	    	     	   printf("遍历成功\n");
	    	     	 else 
	    	     	   printf("队列为空，遍历失败\n");
				 }
	            break;
		        }
		case 9:{
			    if (Queue != NULL)
			     {
			     	printf("队列的长度为%d",LengthAQueue(Queue));
				 }
			     break;
	         	}
		case 0:{ if(Queue != NULL)
			        DestoryAQueue(Queue);
	    	     Queue = NULL; 
	    	     exit(0); 
			     break;
		       }
	    default:printf("输入错误！\n");		   		 	  	
	   }
	if(Queue == NULL)
	   printf("请初始化后再进行其他操作\n");
	 system("pause");
	 system("cls");  
	}
    return 0;	
} 
