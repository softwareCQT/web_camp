#include <stdio.h>
#include <stdlib.h>
#include "linkedList.h"

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
    int choice,sign=0; 
	static int len;     //ѡ�������ź�������,�˳�ϵͳ�ı�� 
    static LinkedList head=NULL;
    while(1)
    { window();
      scanf("%d",&choice);
      switch(choice)
       { case 1:{printf("����������\nlen=");
                 scanf("%d",&len);
                 if(head==NULL)
	              {head=CreateList(len);
	               ReverseList(&head);
				  }
				 else
				   {printf("�Ѵ���\n"); 
				    system("pause");
				   }
				 }
				break;
	     case 2:if(head!=NULL)
		         {int in;
		          do
				    {printf("��ѡ�񱻲���Ľڵ㣺");
				     scanf("%d",&in); } 
				  while(in<1||in>len);
				 LinkedList ined=head;
				 printf("����Ҫ����ڵ������(����):");
				 scanf("%d",&in); 
				 while(in--) 
				   ined=ined->next;  //ѭ��ȡ��ַ�������� 
				 InsertList(ined,Create(in));}
				 break;
	     case 3:{if(head!=NULL)
		          TraverseList(head,print);
		         else
		           printf("����Ϊ��\n"); 
				 system("pause");}
				 break;
		 case 0:{sign=1;} 
		         break;
		 case 4:{printf("�м�ڵ��ֵΪ%d\n",FindMidNode(&head)->data);
		         system("pause");}
				 break;
		 case 5:{if(IsLoopList(head)==SUCCESS)
                  printf("���������ѭ���ṹ\n");
				 else
				  printf("����������ѭ���ṹ\n");
				 system("pause");}
				break;
		 case 6:ReverseList(&head);break;		 
		 case 7:{printf("����������ҵ����ݣ�");
		         int t;
		         scanf("%d",&t);
		         if(SearchList(head,t)==SUCCESS)
		           printf("���ҳɹ�\n");
		         else 
		           printf("����ʧ��\n");
				 system("pause");} 
			     break;
	     case 8:if(head!=NULL)
		        { DestroyList(&head);
		          head=NULL;
		         }break;
		 case 9:{int elem;
		         LinkedList temp=head;                 //����ѭ���ó��ڵ� 
		         printf("ɾ���ĸ��ڵ�֮��ĺ�ڵ㣿������(0~%d):",len);
				 scanf("%d",&elem);
				 while(elem--)   
				    temp=temp->next;
		         DeleteList(temp, &elem);
		         printf("ɾ���Ľڵ������Ϊ%5d\n",elem);
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
