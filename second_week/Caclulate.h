
typedef enum Status
{ 
    ERROR = 0,SUCCESS = 1
}Status;

typedef struct SqStack{
	   int size;
       char *Array;
       int top;      
}C_Stack;                 //用于中缀表达式转换成后缀表达式的栈


  //用于中缀表达式转换成后缀表达式的方法 
Status C_initStack(C_Stack *s,int sizes);//初始化栈
Status C_isEmptyStack(C_Stack *s);//判断栈是否为空
char C_getTop(C_Stack *s); //得到栈顶元素
void C_push(C_Stack *s,char data);//入栈
char C_pop(C_Stack *s);//出栈并返回出栈元素 

//下面是将后缀表达式用栈求值的方法
typedef struct Stack{
	   int size;
       double *sum;
       int top;      
}S_Stack; 

Status S_initStack(S_Stack *s,int sizes);//初始化栈
Status S_isEmptyStack(S_Stack *s);//判断栈是否为空
double S_getTop(S_Stack *s); //得到栈顶元素
void S_push(S_Stack *s,double data);//入栈
double S_pop(S_Stack *s);//出栈并返回出栈元素 
double symbol(double x1,double x2,char a); //四个符号的运算作用 
void change(char *a,char *sign,C_Stack *s);  //把中缀表达式转换成后缀表达式 
double caclulate(S_Stack *s, char *a, char *b); //计算表达式的值并返回 
void windows();  //用户界面 
