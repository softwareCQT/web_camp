#include<stdio.h>
#include<stdlib.h>
#include"BinaryTree.h"

#define MAXSIZE 1000

/*----------------------------------����------------------------------------------------*/
typedef struct queue
{
    BiTNode *data[MAXSIZE];
    int head;
    int fail;
}queue;                              //�����ݽṹ���ڲ������

void initQueue(queue *Q);
Status isEmpty(queue *Q);
BiTNode *out(queue *Q);
void in(queue *Q,BiTNode *v);
int caculate(int a,int b,char symbol);

/*---------------------------------ջ-----------------------------------------------------*/
typedef struct stack
{
    BiTNode *data[MAXSIZE];         //��ջ���ڷǵݹ����������
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

Status CreateBiTree(BiTree *T, char** definition)           //Ӧ�ô������ָ�룬Ҫ�ں������޸�ָ���ָ��
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
            printf("�ڴ治��\n");
            exit(0);
        }
        (*T)->data = ch;
        CreateBiTree(&((*T)->lchild),definition);
        CreateBiTree(&((*T)->rchild),definition);
    }
    else
    {
        printf("��Ч�����룡\n");
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
        node = out(&myQueue);                   //���ڵ����
        (*visit)(node->data);

        if(node->lchild != NULL)
            in(&myQueue,node->lchild);            //���㲻ΪNULL�����

        if(node->rchild != NULL)
            in(&myQueue,node->rchild);            //�ҽڵ㲻ΪNULL�����
    }
}

int Value(BiTree T)
{
    if(T == NULL)
        return 0;

    int left,right;             //����������������ֵ

    left = Value(T->lchild);
    right = Value(T->rchild);

    if(T->lchild == NULL && T->rchild == NULL)      //Ҷ�ӽ�㣬�����������ֵ
        return T->data - '0';
    else                                               //����Ҷ�ӽ�㣬�������������������Ӧ����������ֵ
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

    while(!isEmptyStack(&S) || temp != NULL)             //��һ�ν���ѭ���ڱ�������������ջ��Ϊ��
    {
        while(temp != NULL)                             //����������
        {
            push(&S,temp);
            temp = temp->lchild;
        }
        if(!isEmpty(&S))                                //�������������ص�ѭ������������������
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

/*---------------------------���в�������-------------------------------------------------*/

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

BiTNode *out(queue *Q)                  //����
{
    BiTNode *temp = Q->data[Q->head];
    Q->head = (Q->head + 1) % MAXSIZE;

    return temp;
}

void in(queue *Q,BiTNode *v)           //���
{
    if(isFull(Q))
    {
        printf("���пռ�������\n");
        exit(0);
    }
    Q->data[Q->fail] = v;
    Q->fail = (Q->fail + 1) % MAXSIZE;
}

/*----------------------ջ��������--------------------------------*/

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
