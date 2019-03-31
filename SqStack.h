
typedef enum Status
{ 
    ERROR = 0,OK = 1
}Status;

typedef int ElemType;

typedef struct SqStack{
	   int size;
       ElemType	*elem;
       int top;      //用于栈顶指针
}SqStack;

//顺序栈(基于数组的)
Status initStack(SqStack *s,int sizes);//初始化栈
Status isEmptyStack(SqStack *s);//判断栈是否为空
Status getTopStack(SqStack *s,ElemType *e); //得到栈顶元素
Status clearStack(SqStack *s);//清空栈
Status destroyStack(SqStack *s);//销毁栈
Status stackLength(SqStack *s,int *length);//检测栈的大小 
Status pushStack(SqStack *s,ElemType data);//入栈
Status popStack(SqStack *s,ElemType *data);//出栈


