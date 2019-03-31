#include "Caclulate.h"
#include <stdio.h>
#include <stdlib.h>
#include <math.h> 
 //������׺���ʽת���ɺ�׺���ʽ�ķ��� 
Status C_initStack(C_Stack *s,int sizes)    //��ʼ��ջ
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
Status C_isEmptyStack(C_Stack *s) //�ж�ջ�Ƿ�Ϊ��
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
char C_getTop(C_Stack *s)    //�õ�ջ��Ԫ��
{
	return s->Array[s->top];
}
void C_push(C_Stack *s,char data) //��ջ 
{
    if(s != NULL&&s->top != s->size-1)
      s->Array[++s->top] = data;
}
char C_pop(C_Stack *s)   //��ջ�����س�ջԪ�� 
{
	if(!C_isEmptyStack(s))
	  return s->Array[s->top--];
	else
	  return '\0';
}


//�����ǽ���׺���ʽ��ջ��ֵ�ķ���
Status S_initStack(S_Stack *s,int sizes)//��ʼ��ջ
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

double S_getTop(S_Stack *s) //�õ�ջ��Ԫ��
{
	if(s != NULL&&s->top != -1)
	  return s->sum[s->top];
}
void S_push(S_Stack *s,double data)//��ջ
{
	if(s != NULL&&s->top != s->size-1)
	  s->sum[++s->top] = data;
}
double S_pop(S_Stack *s)//��ջ�����س�ջԪ�� 
{
	if(s != NULL&&s->top != -1)
	  return s->sum[s->top--];
}
double symbol(double x1,double x2,char a)  //�������� 
{
	switch(a)
	{
	   case '*':return x1*x2;
	   case '/':return x1/x2;
	   case '+':return x1+x2;
	   case '-':return x1-x2;
	   default:printf("���ִ���"); 
	}
}
void change(char *a,char *sign,C_Stack *s)     //��׺���ʽת���ɺ�׺���ʽ 
{   int elesign = 0, bsign = 0, asign = 0;
    char *b = (char *)malloc(sizeof(char)*200);
	for (asign = 0; a[asign] != '\0'; asign++)            
	{
	  if(a[asign] == '-'&&(asign == 0||(a[asign-1] >'9'||a[asign-1]<'0'))) //�ж�Ϊ���� 
	  { 
	    sign[elesign++] = '-';
	    asign++;     //���Ѹ��Ŵ��ȥ 
	    
	    while (a[asign] =='.'||(a[asign] <='9'&&a[asign]>='0'))
		  b[bsign++] = a[asign++];
		asign--;  
		b[bsign++] = ','; //�ö��ŷָ���ÿһ�����֣��������	
	  }
	  
	 else if(a[asign] <='9'&&a[asign]>='0')  //�ж�Ϊ���� 
	 {
	  sign[elesign++] = '+';
	  
	  while (a[asign] == '.'||(a[asign] <='9'&&a[asign]>='0'))
		  b[bsign++] = a[asign++];
	  asign--;	  
	  b[bsign++] = ','; 	
	 }
	 else if(a[asign] == ')')  //��'('ջ�ϵķ���ȫ����� 
	 { 
	   while(C_getTop(s) != '(')
	     b[bsign++] = C_pop(s);
	   C_pop(s);               //����'(' 
	  } 
	 else if(a[asign] == '(')  //��'('�ƽ�ջ 
	      C_push(s,a[asign]);
	 else if(a[asign] == '/'||a[asign] == '*')
     {
     	if(C_getTop(s) == '*'||C_getTop(s) == '/') //�����������ջ��ķ��ŵ��� 
     	  b[bsign++] = C_pop(s);    
     	C_push(s,a[asign]);         //�ѷ����ƽ�ջ 
	 }
	 else
	 {
	 	if(C_getTop(s) == '*'|| C_getTop(s) == '/'||C_getTop(s) == '+'||C_getTop(s) == '-')  //������������Ƴ������������Ƴ� 
	 	   b[bsign++] = C_pop(s);
	 	C_push(s,a[asign]);
	 }
	} 
   while (b[bsign++] = C_pop(s));
   sign[elesign] = '\0';            //��������� 
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
	      int sign = -1;    //�������С�����λ�� 
       	  while (a[asign] != ',') //�������� 
       	  {
       	  	douter *= 10;
       	  	douter += (double)(a[asign++]-'0');
		    if(a[sign] == '.')
			{
				sign = asign++;  //ȡ��� 
			}  	 
		  }
		 if(sign != -1)
		   douter /= pow(10,asign-sign-1);
		 if(b[bsign++] == '-')
		   douter *= -1.0;
		 S_push(s,douter);
		 asign++;  //�Ѷ��Ź��˵� 
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
    printf("**********����:����͢(3118004951) �������1��******\n");
    printf("***************************************************\n");
	printf("**************     ������ʽ������    ************\n");
	printf("**************       1.����            ************\n");
	printf("**************       0.�˳�            ************\n");
	printf("����������Ҫ�Ĳ���(0~1)��"); 
}

