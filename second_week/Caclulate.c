#include "Caclulate.h"
#include <stdio.h>
#include <stdlib.h>
#include <math.h> 
 //用于中缀表达式转换成后缀表达式的方法 
Status C_initStack(C_Stack *s,int sizes)    //初始化栈
{
	if(s == NULL)
	 return ERROR;
	else
	{
	  s->Array = (char *)malloc(sizes*sizeof(char));
	  s->top = -1;
	  s->size = sizes;
	  return SUCCESS;
	 } 
}
Status C_isEmptyStack(C_Stack *s) //判断栈是否为空
{
	if(s == NULL)
	  return ERROR;
	else
	{
		if(s->top == -1)
		  return SUCCESS;
		else
		  return ERROR;
	}
}
char C_getTop(C_Stack *s)    //得到栈顶元素
{
	return s->Array[s->top];
}
void C_push(C_Stack *s,char data) //入栈 
{
    if(s != NULL&&s->top != s->size-1)
      s->Array[++s->top] = data;
}
char C_pop(C_Stack *s)   //出栈并返回出栈元素 
{
	if(!C_isEmptyStack(s))
	  return s->Array[s->top--];
	else
	  return '\0';
}


//下面是将后缀表达式用栈求值的方法
Status S_initStack(S_Stack *s,int sizes)//初始化栈
{
	if(s != NULL)
	 {
	 	s->sum = (double *)malloc(sizeof(double)*sizes);
	 	s->top = -1;
	 	s->size = sizes;
	 	return SUCCESS;
	 }
    return ERROR;
} 

double S_getTop(S_Stack *s) //得到栈顶元素
{
	if(s != NULL&&s->top != -1)
	  return s->sum[s->top];
}
void S_push(S_Stack *s,double data)//入栈
{
	if(s != NULL&&s->top != s->size-1)
	  s->sum[++s->top] = data;
}
double S_pop(S_Stack *s)//出栈并返回出栈元素 
{
	if(s != NULL&&s->top != -1)
	  return s->sum[s->top--];
}
double symbol(double x1,double x2,char a)  //符号运算 
{
	switch(a)
	{
	   case '*':return x1*x2;
	   case '/':return x1/x2;
	   case '+':return x1+x2;
	   case '-':return x1-x2;
	   default:printf("出现错误！"); 
	}
}
void change(char *a,char *sign,C_Stack *s)     //中缀表达式转换成后缀表达式 
{   int elesign = 0, bsign = 0, asign = 0;
    char *b = (char *)malloc(sizeof(char)*200);
	for (asign = 0; a[asign] != '\0'; asign++)            
	{
	  if(a[asign] == '-'&&(asign == 0||(a[asign-1] >'9'||a[asign-1]<'0'))) //判断为负数 
	  { 
	    sign[elesign++] = '-';
	    asign++;     //不把负号存进去 
	    
	    while (a[asign] =='.'||(a[asign] <='9'&&a[asign]>='0'))
		  b[bsign++] = a[asign++];
		asign--;  
		b[bsign++] = ','; //用逗号分隔开每一个数字，方便计算	
	  }
	  
	 else if(a[asign] <='9'&&a[asign]>='0')  //判断为正数 
	 {
	  sign[elesign++] = '+';
	  
	  while (a[asign] == '.'||(a[asign] <='9'&&a[asign]>='0'))
		  b[bsign++] = a[asign++];
	  asign--;	  
	  b[bsign++] = ','; 	
	 }
	 else if(a[asign] == ')')  //把'('栈上的符号全部输出 
	 { 
	   while(C_getTop(s) != '(')
	     b[bsign++] = C_pop(s);
	   C_pop(s);               //弹出'(' 
	  } 
	 else if(a[asign] == '(')  //把'('推进栈 
	      C_push(s,a[asign]);
	 else if(a[asign] == '/'||a[asign] == '*')
     {
     	if(C_getTop(s) == '*'||C_getTop(s) == '/') //若成立，则把栈里的符号弹出 
     	  b[bsign++] = C_pop(s);    
     	C_push(s,a[asign]);         //把符号推进栈 
	 }
	 else
	 {
	 	if(C_getTop(s) == '*'|| C_getTop(s) == '/'||C_getTop(s) == '+'||C_getTop(s) == '-')  //除了运算符能推出，其他不能推出 
	 	   b[bsign++] = C_pop(s);
	 	C_push(s,a[asign]);
	 }
	} 
   while (b[bsign++] = C_pop(s));
   sign[elesign] = '\0';            //给予结束符 
   for(asign = 0; asign <= bsign; asign++)
      a[asign] = b[asign];
   free(b);
}
double caclulate(S_Stack *s, char *a, char *b)
{   
    int asign = 0, bsign = 0;
    
    while (a[asign] != '\0')
    {
       if(a[asign] >= '0'&&a[asign] <= '9')
       {  
	      double douter = 0; 
	      int sign = -1;    //用来标记小数点的位置 
       	  while (a[asign] != ',') //整数部分 
       	  {
       	  	douter *= 10;
       	  	douter += (double)(a[asign++]-'0');
		    if(a[sign] == '.')
			{
				sign = asign++;  //取标记 
			}  	 
		  }
		 if(sign != -1)
		   douter /= pow(10,asign-sign-1);
		 if(b[bsign++] == '-')
		   douter *= -1.0;
		 S_push(s,douter);
		 asign++;  //把逗号过滤掉 
	  }
	  else
	  {
	  	S_push(s,symbol(S_pop(s),S_pop(s),a[asign++]));
	  }
	}
	return S_pop(s);
}
void windows()
{
    printf("***************************************************\n");
    printf("**********作者:陈起廷(3118004951) 软件工程1班******\n");
    printf("***************************************************\n");
	printf("**************     四则表达式计算器    ************\n");
	printf("**************       1.计算            ************\n");
	printf("**************       0.退出            ************\n");
	printf("请输入您想要的操作(0~1)："); 
}

