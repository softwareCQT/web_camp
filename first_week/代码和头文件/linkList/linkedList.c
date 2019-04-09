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
     return ERROR;   //若无链表节点，则返回错误 
   else
     {LinkedList move=*L;
	  while((move=move->next)!=NULL)
        move->data=0;
	  return SUCCESS; }//链表有节点，进行链表初始化操作  
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
        free(*L);               //防止迷途指针的出现 
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
    if(p!=NULL)             //若p不为NULL，执行插入操作 
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
	  {LNode *pre=p->next;//保存下一节点以方便删除操作 
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
	 { LinkedList pmid=(*L)->next,pnext=pmid->next,prehead=(*L)->next;   //pmid为待逆置节点，pnext为待逆置节点的下一节点
	   while(pmid!=NULL)                                                 //prehead为待转节点前的节点 
	    { pmid->next=prehead;         //链表逆置操作 
	      prehead=pmid;
	      pmid=pnext;
	      if(pnext!=NULL)
	        pnext=pnext->next; 
	    }
	  (*L)->next->next=NULL;          //逆置后的尾节点置空 
	  (*L)->next=prehead;             //定义逆置后的头结点	   
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
	LinkedList pslow=L,pfast=L;            //快慢指针法，相遇那一刻代表有循环在其中 
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
   LNode *odd=(*L)->next,*even=odd->next;//奇数节点和偶数节点
   LNode *befodd=*L,*afteven=even->next;  //奇数节点的前节点和偶数节点的后节点
   while(1)
    { even->next=odd;
      odd->next=afteven;
      befodd->next=even;
      befodd=odd;              //更新节点 
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
     printf("输入第%d个节点的值：",i);
     scanf("%d",&node->data);
     i++;
   }
   return head;
}
/*print the list */
void print(ElemType e) 
{ printf("%d\t",e);
}
/*用户界面*/ 
void window()
{  printf("******************      单链表操作界面    *********************\n");
   printf("***************         1. 创建链表          ******************\n"); 
   printf("***************         2. 插入节点          ******************\n");
   printf("***************         3. 打印链表          ******************\n");
   printf("***************         4. 找链表中间节点    ******************\n");
   printf("***************         5. 判断是否循环      ******************\n");
   printf("***************         6. 逆置链表          ******************\n");
   printf("***************         7. 查找元素          ******************\n");
   printf("***************         8. 删除链表          ******************\n");
   printf("***************         9. 删除节点          ******************\n");
   printf("***************         10.初始化链表        ******************\n");
   printf("***************         11.反转偶数节点      ******************\n");
   printf("***************         0.退出界面           ******************\n");
   printf("请输入你想要的操作(输入数字)："); 
} 
LNode* Create(int t)
{ LNode *p=(LNode *)malloc(Capacity);
  p->next=NULL;
  p->data=t;
  return p;
}


