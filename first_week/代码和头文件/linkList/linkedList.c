#include "linkedList.h"
#include <stdio.h>
#include <stdlib.h>

/**
 *  @name        : Status InitList(LinkList *L);
 *	@description : initialize an empty linked list with only the head node without value
 *	@param		 : L(the head node)
 *	@return		 : Status
 *  @notice      : None
 */
Status InitList(LinkedList *L) {
   if(((*L)->next)==NULL)
     return ERROR;   //��������ڵ㣬�򷵻ش��� 
   else
     {LinkedList move=*L;
	  while((move=move->next)!=NULL)
        move->data=0;
	  return SUCCESS; }//�����нڵ㣬���������ʼ������  
}

/**
 *  @name        : void DestroyList(LinkedList *L)
 *	@description : destroy a linked list, free all the nodes
 *	@param		 : L(the head node)
 *	@return		 : None
 *  @notice      : None
 */
void DestroyList(LinkedList *L) {
    while((*L)!=NULL)
     {  LinkedList pre=(*L)->next;
        free(*L);               //��ֹ��;ָ��ĳ��� 
        *L=NULL;
        *L=pre;
	 }
}

/**
 *  @name        : Status InsertList(LNode *p, LNode *q)
 *	@description : insert node q after node p
 *	@param		 : p, q
 *	@return		 : Status
 *  @notice      : None
 */
Status InsertList(LNode *p, LNode *q) {
    if(p!=NULL)             //��p��ΪNULL��ִ�в������ 
	{if(p->next==NULL)
      {p->next=q;
       q->next=NULL;}
      else
	  {LNode *pre=p->next;
	   p->next=q;
	   q->next=pre;}
	return SUCCESS; 
    }
    else 
	  return ERROR;
}
/**
 *  @name        : Status DeleteList(LNode *p, ElemType *e)
 *	@description : delete the first node after the node p and assign its value to e
 *	@param		 : p, e
 *	@return		 : Status
 *  @notice      : None
 */
Status DeleteList(LNode *p, ElemType *e) {
    if(p->next==NULL||p==NULL)
       return ERROR;
    else 
	  {LNode *pre=p->next;//������һ�ڵ��Է���ɾ������ 
	   *e=pre->data;
	   p->next=pre->next;
	   free(pre);
	   pre=NULL;
	   return SUCCESS;    
	 }   
}

/**
 *  @name        : void TraverseList(LinkedList L, void (*visit)(ElemType e))
 *	@description : traverse the linked list and call the funtion visit
 *	@param		 : L(the head node), visit 
 *	@return		 : None
 *  @notice      : None
 */
void TraverseList(LinkedList L, void (*visit)(ElemType e)) {
	while((L=L->next)!=NULL)
	 { (*visit)(L->data);}
}

/**
 *  @name        : Status SearchList(LinkedList L, ElemType e)
 *	@description : find the first node in the linked list according to e 
 *	@param		 : L(the head node), e
 *	@return		 : Status
 *  @notice      : None
 */
Status SearchList(LinkedList L, ElemType e) {
	while((L=L->next)!=NULL)
	 { if(L->data==e)
	     return SUCCESS; 
	 }
	return ERROR; 
} 

/**
 *  @name        : Status ReverseList(LinkedList *L)
 *	@description : reverse the linked list 
 *	@param		 : L(the head node)
 *	@return		 : Status
 *  @notice      : None
 */
Status ReverseList(LinkedList *L) {
	if(*L==NULL)
	  return ERROR;
	else
	 { LinkedList pmid=(*L)->next,pnext=pmid->next,prehead=(*L)->next;   //pmidΪ�����ýڵ㣬pnextΪ�����ýڵ����һ�ڵ�
	   while(pmid!=NULL)                                                 //preheadΪ��ת�ڵ�ǰ�Ľڵ� 
	    { pmid->next=prehead;         //�������ò��� 
	      prehead=pmid;
	      pmid=pnext;
	      if(pnext!=NULL)
	        pnext=pnext->next; 
	    }
	  (*L)->next->next=NULL;          //���ú��β�ڵ��ÿ� 
	  (*L)->next=prehead;             //�������ú��ͷ���	   
	 return SUCCESS;}
} 

/**
 *  @name        : Status IsLoopList(LinkedList L)
 *	@description : judge whether the linked list is looped
 *	@param		 : L(the head node)
 *	@return		 : Status
 *  @notice      : None
 */
Status IsLoopList(LinkedList L) {
	LinkedList pslow=L,pfast=L;            //����ָ�뷨��������һ�̴�����ѭ�������� 
    while((pslow=pslow->next)!=NULL&&(pfast=pfast->next->next)!=NULL)
      {if(pslow==pfast)
         return SUCCESS;  
	  }
	return  ERROR;
} 

/**
 *  @name        : LNode* ReverseEvenList(LinkedList *L)
 *	@description : reverse the nodes which value is an even number in the linked list, input: 1 -> 2 -> 3 -> 4  output: 2 -> 1 -> 4 -> 3
 *	@param		 : L(the head node)
 *	@return		 : LNode(the new head node)
 *  @notice      : choose to finish 
 */
LNode* ReverseEvenList(LinkedList *L) {
   LNode *odd=(*L)->next,*even=odd->next;//�����ڵ��ż���ڵ�
   LNode *befodd=*L,*afteven=even->next;  //�����ڵ��ǰ�ڵ��ż���ڵ�ĺ�ڵ�
   while(1)
    { even->next=odd;
      odd->next=afteven;
      befodd->next=even;
      befodd=odd;              //���½ڵ� 
	  odd=afteven;
	  if(odd!=NULL&&odd->next!=NULL)
	   {even=odd->next;
	    afteven=even->next;}
	  else
	    break;    
	 }
	return *L;
}

/**
 *  @name        : LNode* FindMidNode(LinkedList *L)
 *	@description : find the middle node in the linked list
 *	@param		 : L(the head node)
 *	@return		 : LNode
 *  @notice      : choose to finish 
 */
LNode* FindMidNode(LinkedList *L) {
   if((*L)->next==NULL)
     return NULL;
   else
    { LinkedList pslow=(*L)->next,pfast=(*L)->next->next;
      while(1)
       { if(pfast==NULL||pfast->next==NULL)
           return pslow;
         pslow=pslow->next;
         pfast=pfast->next->next;
	   }
	}
}
 /* @description :Create an list in need*/
LNode* CreateList(int len)
{ LNode *node=NULL,*head=(LNode *)malloc(Capacity);
  int i=1;
  head->next=NULL; 
  while(i<=len)
   { node=(LNode *)malloc(Capacity);
     node->next=head->next;
     head->next=node;
     printf("�����%d���ڵ��ֵ��",i);
     scanf("%d",&node->data);
     i++;
   }
   return head;
}
/*print the list */
void print(ElemType e) 
{ printf("%d\t",e);
}
/*�û�����*/ 
void window()
{  printf("******************      �������������    *********************\n");
   printf("***************         1. ��������          ******************\n"); 
   printf("***************         2. ����ڵ�          ******************\n");
   printf("***************         3. ��ӡ����          ******************\n");
   printf("***************         4. �������м�ڵ�    ******************\n");
   printf("***************         5. �ж��Ƿ�ѭ��      ******************\n");
   printf("***************         6. ��������          ******************\n");
   printf("***************         7. ����Ԫ��          ******************\n");
   printf("***************         8. ɾ������          ******************\n");
   printf("***************         9. ɾ���ڵ�          ******************\n");
   printf("***************         10.��ʼ������        ******************\n");
   printf("***************         11.��תż���ڵ�      ******************\n");
   printf("***************         0.�˳�����           ******************\n");
   printf("����������Ҫ�Ĳ���(��������)��"); 
} 
LNode* Create(int t)
{ LNode *p=(LNode *)malloc(Capacity);
  p->next=NULL;
  p->data=t;
  return p;
}


