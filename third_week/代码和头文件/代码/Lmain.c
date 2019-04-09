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
		  	       	 if(EnLQueue(Queue, a, b))
		  	       	   printf("进队成功\n");
		  	       	 else 
		  	       	   printf("队伍已满，进队失败\n");
				   }
			       break;
		        }
		 case 3:{
		 	     if (Queue != NULL) 
		 	     { 
					if(DeLQueue(Queue))
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
		           if(GetHeadLQueue(Queue, &num))
		             { 
 		               printf("队头的值为：");
		               LPrint(num, Queue->front->size);
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
	    	     	  printf("队列为空\n");
	    	     	else 
	    	     	  printf("队列不为空\n");
				 }
			    break;
	           }	
	    case 8:{
	    	    if (Queue != NULL)
	    	     {
	    	     	 if(TraverseLQueue(Queue, LPrint))
	    	     	   printf("遍历成功\n");
	    	     	 else 
	    	     	   printf("队列为空，遍历失败\n");
				 }
	            break;
		        }
		case 9:{
			    if (Queue != NULL)
			     {
			     	printf("队列的长度为%d",LengthLQueue(Queue));
				 }
			     break;
	         	}
		case 0:{ if(Queue != NULL)
			        DestoryLQueue(Queue);
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
