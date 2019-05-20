#include<stdio.h>
#include<stdlib.h>
#include"BinaryTree.h"

#define MAXSIZE 1000

/*----------------------------------队列------------------------------------------------*/
typedef struct queue
{
    BiTNode *data[MAXSIZE];
    int head;
    int fail;
}queue;                              //该数据结构用于层序遍历

void initQueue(queue *Q);
Status isEmpty(queue *Q);
BiTNode *out(queue *Q);
void in(queue *Q,BiTNode *v);
int caculate(int a,int b,char symbol);

/*---------------------------------栈-----------------------------------------------------*/
typedef struct stack
{
    BiTNode *data[MAXSIZE];         //该栈用于非递归遍历二叉树
    int top;
}stack;

void initStack(stack *S);
Status isEmptyStack(stack *S);
void push(stack *S,BiTNode *T);
BiTNode *pop(stack *S);


/*---------------------------------------------------------------------------------------------*/
Status InitBiTree(BiTree *T)
{
    *T = NULL;
    return SUCCESS;
}

Status DestroyBiTree(BiTree T)
{
    if(T == NULL)
        return SUCCESS;

    DestroyBiTree(T->lchild);
    DestroyBiTree(T->rchild);
    free(T);
}

Status CreateBiTree(BiTree *T, char** definition)           //应该传入二级指针，要在函数中修改指针的指向
{
    char ch = **definition;
    (*definition)++;

    if(ch == '#')
    {
        *T = NULL;
    }
    else if((ch >= '0' && ch <= '9') || ch == '+' || ch == '-' || ch == '*' || ch == '/')
    {
        *T = (BiTNode *)malloc(sizeof(BiTNode));
        if(*T == NULL)
        {
            printf("内存不足\n");
            exit(0);
        }
        (*T)->data = ch;
        CreateBiTree(&((*T)->lchild),definition);
        CreateBiTree(&((*T)->rchild),definition);
    }
    else
    {
        printf("无效的输入！\n");
        exit(0);
    }

    return SUCCESS;
}

Status PreOrderTraverse(BiTree T, Status (*visit)(TElemType e))
{
    if(T == NULL)
        return SUCCESS;

    (*visit)(T->data);
    PreOrderTraverse(T->lchild,visit);
    PreOrderTraverse(T->rchild,visit);
}

Status InOrderTraverse(BiTree T, Status (*visit)(TElemType e))
{
    if(T == NULL)
        return SUCCESS;

    InOrderTraverse(T->lchild,visit);
    (*visit)(T->data);
    InOrderTraverse(T->rchild,visit);
}

Status PostOrderTraverse(BiTree T, Status (*visit)(TElemType e))
{
    if(T == NULL)
        return SUCCESS;

    PostOrderTraverse(T->lchild,visit);
    PostOrderTraverse(T->rchild,visit);
    (*visit)(T->data);
}

Status LevelOrderTraverse(BiTree T, Status (*visit)(TElemType e))
{
    queue myQueue;
    BiTNode *node;

    initQueue(&myQueue);
    in(&myQueue,T);

    while(!isEmpty(&myQueue))
    {
        node = out(&myQueue);                   //根节点出队
        (*visit)(node->data);

        if(node->lchild != NULL)
            in(&myQueue,node->lchild);            //左结点不为NULL则入队

        if(node->rchild != NULL)
            in(&myQueue,node->rchild);            //右节点不为NULL则入队
    }
}

int Value(BiTree T)
{
    if(T == NULL)
        return 0;

    int left,right;             //左子树与右子树的值

    left = Value(T->lchild);
    right = Value(T->rchild);

    if(T->lchild == NULL && T->rchild == NULL)      //叶子结点，返回其自身的值
        return T->data - '0';
    else                                               //不是叶子结点，返回其左右子树与其对应运算符运算的值
    {
        return caculate(left,right,T->data);
    }

}

Status InOrderWithoutRecursion(BiTree T, Status (*visit)(TElemType e))
{
    stack S;
    BiTNode *temp;

    initStack(&S);
    push(&S,T);
    temp = T;

    while(!isEmptyStack(&S) || temp != NULL)             //第一次进入循环于遍历完左子树后栈都为空
    {
        while(temp != NULL)                             //遍历左子树
        {
            push(&S,temp);
            temp = temp->lchild;
        }
        if(!isEmpty(&S))                                //进入右子树，回到循环继续遍历其左子树
        {
            (*visit)((S.data[S.top])->data);
            temp = pop(&S);
            temp = temp->rchild;
        }
    }

}

int caculate(int a,int b,char symbol)
{
    switch(symbol)
    {
    case '+':
        return a+b;
    case '-':
        return a-b;
    case '*':
        return a*b;
    case '/':
        return a/b;
    }
}

/*---------------------------队列操作函数-------------------------------------------------*/

void initQueue(queue *Q)
{
    Q->head = 0;
    Q->fail = 0;
}

Status isEmpty(queue *Q)
{
    return Q->fail == Q->head;
}

Status isFull(queue *Q)
{
    return Q->head%MAXSIZE - Q->fail%MAXSIZE == 1;
}

BiTNode *out(queue *Q)                  //出队
{
    BiTNode *temp = Q->data[Q->head];
    Q->head = (Q->head + 1) % MAXSIZE;

    return temp;
}

void in(queue *Q,BiTNode *v)           //入队
{
    if(isFull(Q))
    {
        printf("队列空间已满！\n");
        exit(0);
    }
    Q->data[Q->fail] = v;
    Q->fail = (Q->fail + 1) % MAXSIZE;
}

/*----------------------栈操作函数--------------------------------*/

void initStack(stack *S)
{
    S->top = -1;
}
Status isEmptyStack(stack *S)
{
    return S->top == -1;
}
void push(stack *S,BiTNode *T)
{
    S->data[++(S->top)] = T;
}
BiTNode *pop(stack *S)
{
    BiTNode *T = S->data[(S->top)--];

    return T;
}
