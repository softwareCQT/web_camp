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
    printf("����������ѡ��:");
     
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
                printf("����\n");
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
            printf("�޴�ѡ�\n");
        }
        printf("����������ѡ��:");
    }
}   
void windows()
{
    printf("**********************������***********************\n");
    printf("**********����:����͢(3118004951) �������1��******\n");
    printf("**********1------��ʼ��������**********************\n");
    printf("**********2------���ٶ�����  **********************\n");
    printf("**********3------���������  **********************\n");
    printf("**********4------ǰ�����  ************************\n");
    printf("**********5------�������  ************************\n");
    printf("**********6------�������  ************************\n");
    printf("**********7------�������  ************************\n");
    printf("**********8------��������ǵݹ�********************\n");
    printf("**********9------ǰ׺���ʽ��ֵ********************\n");
    printf("**********10------����     ************************\n");
    printf("********** 0------����     ***********************\n");
}

void init(BiTNode **T)
{   
    if (*T == NULL)
	{
	   printf("��������!\n");	
	   return;
	} 
    if(InitBiTree(T))
    {    
	  printf("��ʼ���ɹ���\n");
	} 
    else
    {
        printf("δ֪����\n");
        exit(0);
    }
}

void destroy(BiTNode **T)
{
    if(*T == NULL)
    {
        printf("��������\n");
    }
    else
    {
        DestroyBiTree(*T);
        *T = NULL;
        printf("���ٳɹ�\n");
    }
}

void create(BiTree *T)
{
    if(*T != NULL)
    {
        printf("���Ѵ��ڣ��������٣�\n");         //��ֹ��δ���پ��ٴι���
        return;
    }
    char t[MAXSIZE],ch;
    char *temp;
    int i = 0;

    printf("��������Ҫ����ĸ��ڵ����ݣ����ݴ�С��10���ڣ�������10,#����Ӧ������ƥ�䣩:");
    while((ch = getchar()) != '\n')
    {
        if(ch == ' ')
            continue;                           //���Կո�
        else
            t[i++] = ch;
    }
    t[i] = '\0';                                //�ַ���������־
    temp = t;
    CreateBiTree(T,&temp);

    printf("���ɳɹ���\n");
}

Status Print(char ch)
{
    printf("%c ",ch);
}

void PreOrder(BiTNode *T)
{
    if(T == NULL)
    {
        printf("����\n");
        return;
    }
    PreOrderTraverse(T,Print);
    printf("\n");
}

void InOrder(BiTNode *T)
{
    if(T == NULL)
    {
        printf("����\n");
        return;
    }
    InOrderTraverse(T,Print);
    printf("\n");
}

void PostOrder(BiTNode *T)
{
    if(T == NULL)
    {
        printf("����\n");
        return;
    }
    PostOrderTraverse(T,Print);
    printf("\n");
}

void LevelOrder(BiTNode *T)
{
    if(T == NULL)
    {
        printf("����\n");
        return;
    }
    LevelOrderTraverse(T,Print);
    printf("\n");
}

void Evaluation()                       //��ǰ׺���ʽ��ֵ
{
    char ch[MAXSIZE],t;
    int i = 0;
    BiTNode *T = NULL;

    printf("������ǰ׺���ʽ:");
    while((t = getchar()) != '\n')
    {
        if(t >= '0' && t <= '9')
        {
            ch[i++] = t;

            int temp = 1;                       //�������֣������� '#'
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

    printf("ǰ׺���ʽ��ֵΪ:%d\n",answer);
}

void cls()
{
    system("cls");
    windows();
}
