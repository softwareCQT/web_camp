
typedef enum Status
{ 
    ERROR = 0,OK = 1
}Status;

typedef int ElemType;

typedef struct SqStack{
	   int size;
       ElemType	*elem;
       int top;      //����ջ��ָ��
}SqStack;

//˳��ջ(���������)
Status initStack(SqStack *s,int sizes);//��ʼ��ջ
Status isEmptyStack(SqStack *s);//�ж�ջ�Ƿ�Ϊ��
Status getTopStack(SqStack *s,ElemType *e); //�õ�ջ��Ԫ��
Status clearStack(SqStack *s);//���ջ
Status destroyStack(SqStack *s);//����ջ
Status stackLength(SqStack *s,int *length);//���ջ�Ĵ�С 
Status pushStack(SqStack *s,ElemType data);//��ջ
Status popStack(SqStack *s,ElemType *data);//��ջ


