#include<stdio.h>
#include<stdlib.h>
#include"BinaryTree.h"

#define MAXSIZE 1000

void TestBinaryTree();
void windows();
void init(BiTNode **T);
void destroy(BiTNode **T);
void create(BiTree *T);
Status Print(char ch);
void PreOrder(BiTNode *T);
void InOrder(BiTNode *T);
void PostOrder(BiTNode *T);
void LevelOrder(BiTNode *T);
void Evaluation();
void cls();

int main(void)
{
    TestBinaryTree();
    return 0;
}

void TestBinaryTree()
{
    BiTree T = NULL;
    int choice;

    windows();
    printf("请输入您的选择:");
     
    while(scanf("%d",&choice) != EOF)
    {
        switch(choice)
        {
        case 1:
            init(&T);
            break;
        case 2:
            destroy(&T);
            break;
        case 3:
            create(&T);
            break;
        case 4:
            PreOrder(T);
            break;
        case 5:
            InOrder(T);
            break;
        case 6:
            PostOrder(T);
            break;
        case 7:
            LevelOrder(T);
            break;
       case 8:
            if(T == NULL)
            {
                printf("空树\n");
                break;
            }
            InOrderWithoutRecursion(T,Print);
            printf("\n");
            break;
        case 9:
            Evaluation();
            break;
        case 10:
            cls();
            break;
        case 0:
		    exit(0);    
        default: 
            printf("无此选项！\n");
        }
        printf("请输入您的选择:");
    }
}   
void windows()
{
    printf("**********************二叉树***********************\n");
    printf("**********作者:陈起廷(3118004951) 软件工程1班******\n");
    printf("**********1------初始化二叉树**********************\n");
    printf("**********2------销毁二叉树  **********************\n");
    printf("**********3------构造二叉树  **********************\n");
    printf("**********4------前序遍历  ************************\n");
    printf("**********5------中序遍历  ************************\n");
    printf("**********6------后序遍历  ************************\n");
    printf("**********7------层序遍历  ************************\n");
    printf("**********8------中序遍历非递归********************\n");
    printf("**********9------前缀表达式求值********************\n");
    printf("**********10------清屏     ************************\n");
    printf("********** 0------结束     ***********************\n");
}

void init(BiTNode **T)
{   
    if (*T == NULL)
	{
	   printf("树不存在!\n");	
	   return;
	} 
    if(InitBiTree(T))
    {    
	  printf("初始化成功！\n");
	} 
    else
    {
        printf("未知错误\n");
        exit(0);
    }
}

void destroy(BiTNode **T)
{
    if(*T == NULL)
    {
        printf("树不存在\n");
    }
    else
    {
        DestroyBiTree(*T);
        *T = NULL;
        printf("销毁成功\n");
    }
}

void create(BiTree *T)
{
    if(*T != NULL)
    {
        printf("树已存在，请先销毁！\n");         //防止树未销毁就再次构造
        return;
    }
    char t[MAXSIZE],ch;
    char *temp;
    int i = 0;

    printf("请输入您要输入的各节点数据（数据大小在10以内，不包含10,#数量应与数据匹配）:");
    while((ch = getchar()) != '\n')
    {
        if(ch == ' ')
            continue;                           //忽略空格
        else
            t[i++] = ch;
    }
    t[i] = '\0';                                //字符串结束标志
    temp = t;
    CreateBiTree(T,&temp);

    printf("生成成功！\n");
}

Status Print(char ch)
{
    printf("%c ",ch);
}

void PreOrder(BiTNode *T)
{
    if(T == NULL)
    {
        printf("空树\n");
        return;
    }
    PreOrderTraverse(T,Print);
    printf("\n");
}

void InOrder(BiTNode *T)
{
    if(T == NULL)
    {
        printf("空树\n");
        return;
    }
    InOrderTraverse(T,Print);
    printf("\n");
}

void PostOrder(BiTNode *T)
{
    if(T == NULL)
    {
        printf("空树\n");
        return;
    }
    PostOrderTraverse(T,Print);
    printf("\n");
}

void LevelOrder(BiTNode *T)
{
    if(T == NULL)
    {
        printf("空树\n");
        return;
    }
    LevelOrderTraverse(T,Print);
    printf("\n");
}

void Evaluation()                       //求前缀表达式的值
{
    char ch[MAXSIZE],t;
    int i = 0;
    BiTNode *T = NULL;

    printf("请输入前缀表达式:");
    while((t = getchar()) != '\n')
    {
        if(t >= '0' && t <= '9')
        {
            ch[i++] = t;

            int temp = 1;                       //读到数字，加两个 '#'
            while(temp <= 2)
            {
                ch[i++] = '#';
                temp++;
            }
        }
        else if(t == '+' || t == '-' || t == '*' || t == '/')
            ch[i++] = t;
    }

    ch[i] = '\0';

    char *tev = ch;
    CreateBiTree(&T,&tev);

    int answer = Value(T);

    printf("前缀表达式的值为:%d\n",answer);
}

void cls()
{
    system("cls");
    windows();
}
