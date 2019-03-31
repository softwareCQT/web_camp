
typedef enum Status
{ 
    ERROR = 0,SUCCESS = 1
}Status;

typedef struct SqStack{
	   int size;
       char *Array;
       int top;      
}C_Stack;                 //������׺���ʽת���ɺ�׺���ʽ��ջ


  //������׺���ʽת���ɺ�׺���ʽ�ķ��� 
Status C_initStack(C_Stack *s,int sizes);//��ʼ��ջ
Status C_isEmptyStack(C_Stack *s);//�ж�ջ�Ƿ�Ϊ��
char C_getTop(C_Stack *s); //�õ�ջ��Ԫ��
void C_push(C_Stack *s,char data);//��ջ
char C_pop(C_Stack *s);//��ջ�����س�ջԪ�� 

//�����ǽ���׺���ʽ��ջ��ֵ�ķ���
typedef struct Stack{
	   int size;
       double *sum;
       int top;      
}S_Stack; 

Status S_initStack(S_Stack *s,int sizes);//��ʼ��ջ
Status S_isEmptyStack(S_Stack *s);//�ж�ջ�Ƿ�Ϊ��
double S_getTop(S_Stack *s); //�õ�ջ��Ԫ��
void S_push(S_Stack *s,double data);//��ջ
double S_pop(S_Stack *s);//��ջ�����س�ջԪ�� 
double symbol(double x1,double x2,char a); //�ĸ����ŵ��������� 
void change(char *a,char *sign,C_Stack *s);  //����׺���ʽת���ɺ�׺���ʽ 
double caclulate(S_Stack *s, char *a, char *b); //������ʽ��ֵ������ 
void windows();  //�û����� 
