#include "duLinkedList.h"
#include <stdio.h>
#include <stdlib.h>
/**
 *  @name        : Status InitList_DuL(DuLinkedList *L)
 *	@description : initialize an empty linked list with only the head node
 *	@param		 : L(the head node)
 *	@return		 : Status
 *  @notice      : None
 */
Status InitList_DuL(DuLinkedList *L) {
    if(*L!=(*L)->next)
    {DuLinkedList pre=(*L)->prior,pnext=(*L)->next;
     while(1)
       { pnext->data=0;    //双指针初始化，pre向前初始化，pnext向后初始化，两者相遇则初始化结束 
         pre->data=0;
         pre=pre->prior;
         if(pre==*L||pnext==pre)
          return SUCCESS;
         else
           pnext=pnext->next;
	   }
	}
}

/**
 *  @name        : void DestroyList_DuL(DuLinkedList *L)
 *	@description : destroy a linked list
 *	@param		 : L(the head node)
 *	@return		 : status
 *  @notice      : None
 */
void DestroyList_DuL(DuLinkedList *L) {
	if(*L==(*L)->next)
	  return;
	else
	 { DuLinkedList head=(*L)->next,pre=NULL;
	   while((*L)!=head)
	     { pre=(*L)->next;
	       free(*L);
	       *L=pre;
		 }
	   free(*L);
	   *L=NULL;
	}
} 


/**
 *  @name        : Status InsertBeforeList_DuL(DuLNode *p, LNode *q)
 *	@description : insert node q before node p
 *	@param		 : p, q
 *	@return		 : status
 *  @notice      : None
 */
Status InsertBeforeList_DuL(DuLNode *p, DuLNode *q) {
	if(p==p->next)
	  return ERROR;
	else
	  { DuLNode *pre=p->prior;
	    p->prior=q;
	    q->next=p;
	    q->prior=pre;
	    pre->next=q;
		return SUCCESS;  	  
	  }
}

/**
 *  @name        : Status InsertAfterList_DuL(DuLNode *p, DuLNode *q)
 *	@description : insert node q after node p
 *	@param		 : p, q
 *	@return		 : status
 *  @notice      : None
 */
Status InsertAfterList_DuL(DuLNode *p, DuLNode *q) {
     if(p==p->next)
	  return ERROR;	
     else
       {DuLNode *pre=p->next;
        p->next=q;
        q->prior=p;
        q->next=pre;
        pre->prior=q; 
        return SUCCESS; 
	   }
}

/**
 *  @name        : Status DeleteList_DuL(DuLNode *p, ElemType *e)
 *	@description : delete the first node after the node p and assign its value to e
 *	@param		 : p, e
 *	@return		 : status
 *  @notice      : None
 */
Status DeleteList_DuL(DuLNode *p, ElemType *e) {
	if(p==p->next)
	  return ERROR;
	else
	 { DuLNode *rem=p->next;
	   *e=rem->data;
	   p->next=rem->next;
	   rem->next->prior=p;
	   free(rem);
       rem=NULL;
       return SUCCESS;
	 }
}
 
/**
 *  @name        : void TraverseList_DuL(DuLinkedList L, void (*visit)(ElemType e))
 *	@description : traverse the linked list and call the funtion visit
 *	@param		 : L(the head node), visit 
 *	@return		 : Status
 *  @notice      : None
 */
void TraverseList_DuL(DuLinkedList L, void (*visit)(ElemType e)) {
        DuLinkedList back=L->next;    //单指针遍历 
	    while(back!=L)
	     { (*visit)(back->data);
	       back=back->next;
		 }
}
void print(ElemType e) 
{ printf("%d\t",e);
}
/*用户界面*/ 
void window()
{  printf("******************      双向循环链表操作界面   *********************\n");
   printf("***************          1. 创建链表              ******************\n"); 
   printf("***************          2. 插入节点(前插)        ******************\n");
   printf("***************          3. 插入节点(后插)        ******************\n");
   printf("***************          4. 打印链表              ******************\n");
   printf("***************          5. 删除链表              ******************\n");
   printf("***************          6. 删除节点并返回值      ******************\n");
   printf("***************          7. 初始化链表            ******************\n");
   printf("***************          0. 退出界面               ******************\n");
   printf("请输入你想要的操作(输入数字)："); 
} 
DuLinkedList Createlist(int len)
{  DuLinkedList p1,p2=NULL,head;
   int i=0;
   head=(DuLinkedList)malloc(Capacity);  //空头节点
   p1=head;
   while(i<len)
     { p2=(DuLinkedList)malloc(Capacity);
       printf("请输入第%d个节点的值：",++i);
	   scanf("%d",&p2->data);
       p1->next=p2;
       p2->prior=p1;
       p1=p2;
     }
   p1->next=head;
   head->prior=p1;
   return head;
}
DuLinkedList Create(int t)       //创造一个节点 
{ DuLinkedList p=(DuLinkedList)malloc(Capacity);
  p->next=NULL;
  p->prior=NULL;
  p->data=t;
  return p;
}


