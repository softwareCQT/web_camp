#include <stdio.h>
#include <stdlib.h>
#include "linkedList.h"

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
    int choice,sign=0; 
	static int len;     //选择操作序号和链表长度,退出系统的标记 
    static LinkedList head=NULL;
    while(1)
    { window();
      scanf("%d",&choice);
      switch(choice)
       { case 1:{printf("输入链表长度\nlen=");
                 scanf("%d",&len);
                 if(head==NULL)
	              {head=CreateList(len);
	               ReverseList(&head);
				  }
				 else
				   {printf("已创建\n"); 
				    system("pause");
				   }
				 }
				break;
	     case 2:if(head!=NULL)
		         {int in;
		          do
				    {printf("请选择被插入的节点：");
				     scanf("%d",&in); } 
				  while(in<1||in>len);
				 LinkedList ined=head;
				 printf("输入要插入节点的数据(整数):");
				 scanf("%d",&in); 
				 while(in--) 
				   ined=ined->next;  //循环取地址插入链表 
				 InsertList(ined,Create(in));}
				 break;
	     case 3:{if(head!=NULL)
		          TraverseList(head,print);
		         else
		           printf("链表为空\n"); 
				 system("pause");}
				 break;
		 case 0:{sign=1;} 
		         break;
		 case 4:{printf("中间节点的值为%d\n",FindMidNode(&head)->data);
		         system("pause");}
				 break;
		 case 5:{if(IsLoopList(head)==SUCCESS)
                  printf("该链表存在循环结构\n");
				 else
				  printf("该链表不存在循环结构\n");
				 system("pause");}
				break;
		 case 6:ReverseList(&head);break;		 
		 case 7:{printf("请输入想查找的数据：");
		         int t;
		         scanf("%d",&t);
		         if(SearchList(head,t)==SUCCESS)
		           printf("查找成功\n");
		         else 
		           printf("查找失败\n");
				 system("pause");} 
			     break;
	     case 8:if(head!=NULL)
		        { DestroyList(&head);
		          head=NULL;
		         }break;
		 case 9:{int elem;
		         LinkedList temp=head;                 //用来循环得出节点 
		         printf("删除哪个节点之后的后节点？请输入(0~%d):",len);
				 scanf("%d",&elem);
				 while(elem--)   
				    temp=temp->next;
		         DeleteList(temp, &elem);
		         printf("删除的节点的数据为%5d\n",elem);
			     system("pause");
		        }break;
		 case 10:if(head!=NULL)
		           InitList(&head);
				 break;
		 case 11:if(head!=NULL)
		           head=ReverseEvenList(&head);
		         break;
		 }
		
      if(sign==1) 
	   { DestroyList(&head);
	     break;}
	  system("cls");
	}
}
