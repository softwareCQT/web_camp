#include <stdio.h>
#include <stdlib.h>
#include "duLinkedList.h"

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	int len=0,choice,sign=0;     //分别为链表长度 操作选择 和退出标记 
	DuLinkedList head=NULL;
	while(1)
	{ window();
	  scanf("%d",&choice);
	  switch(choice)
	   { case 1:{printf("请输入循环链表的长度\nlen=");
	             scanf("%d",&len);
	            if(head!=NULL)
	              {printf("已创建\n");
	               system("pause");
	               break;}
	            head=Createlist(len);}
	            break;
	     case 2:{DuLinkedList temp=head;  //循环找到节点 
			     int t;
				 if(head!=NULL) 
				  {do
				   {printf("输入要插入的是第几节点(1~%d)：",len);
				    scanf("%d",&t);}
				  while(t<0||t>len);
				  while(t--)
				    temp=temp->next;
				  printf("输入要插入的是节点数据：");
				  scanf("%d",&t); 
				  len++;
				  InsertBeforeList_DuL(temp,Create(t));}}
				break;
	     case 3:{DuLinkedList temp=head;  //循环找到节点 
			     int t;
			     if(head!=NULL)
				 { do
				   {printf("输入要插入的是第几节点(1~%d)：",len);
				    scanf("%d",&t);}
				    while(t<0||t>len);
				  printf("输入要插入的是节点数据：");
				  scanf("%d",&t); 
				  len++;
				  while(t--)
				    temp=temp->next;
				  InsertAfterList_DuL(temp,Create());}}
				break;
	     case 4:{if(head!=NULL)
		          {TraverseList_DuL(head,print);
				  }
				 else
				   {printf("链表为空\n");
				   }
			     system("pause");
		          }
	            break;
	     case 5:DestroyList_DuL(&head);
	            break;
		 case 6:{int t;
		         do
				 {printf("输入要删除的是第几节点(1~%d)：",len);
				    scanf("%d",&t);}
				 while(t<0||t>len);
		         DeleteList_DuL(head,&t);
		         printf("删除的节点的值为%5d\n",t);
		         len--;
				 system("pause");}
				break;
		 case 7:if(head!=NULL)
		          InitList_DuL(&head);
		        break; 
		 case 0:sign=1;
		        break;
	   }
	if(sign==1)
	  {DestroyList_DuL(&head);
	   break;}
	system("cls");
	}
	return 0;
}
