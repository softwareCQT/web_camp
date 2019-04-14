#include <stdio.h>
#include <stdlib.h>
#include "qgsort.h"
#include <string.h> 

/* run this program using the console pauser or add your own getch, system("pause") or input loop */
int main(int argc, char *argv[]) {
	void memu(); //�����˵� 
	int n, i;
	FILE *fp;
	int *arr;
	int choice;
	while (1)
	{
	    printf("�����������ȡ���ݵĸ���:\n"); 
	    scanf("%d",&n);
	    if (n < 0)
		{
		 	printf("�������\n");
		 	continue;
		} 
		fp = fopen("D://data.txt","r");
		if (fp == NULL)
		{
			printf("��ȡ����ʧ��,�����������д������\n");
			break;
		}
	    else 
	    {  
	       arr = (int *)malloc(sizeof(int)*n); 
	       for (i=0; i < n&&!feof(fp);i++)
	    	  fscanf(fp,"%d\t",&arr[i]);
		   if (feof(fp))
		   	 printf("���ݲ������裬���������뵽���ɳ��������ɸ�������ݰɣ�\n");
		   memu();
		   scanf("%d",&choice); 
	       switch(choice)
		   {
		  	case 1:	{ 
		  	         insertSort(arr,n);
					 break;
		            } 
		    case 2:{
		    	    QuickSort_Recursion(arr,0,n-1);
					break;
		           }
		    case 3:{
		    	    QuickSort(arr,n);
				    break;
			       } 
		    case 4:{
		    	    MergeSort(arr,0,n-1);
				    break;
		           } 
		    case 5:{
		    	    int max;
		  	        for (i = 0; i<n; i++)    
				       if (arr[i] > max)
						 max = arr[i]; 
			       	CountSort(arr,n,max); 
			     	break;
			       } 
		    case 6:{
		    	    RadixCountSort(arr,n);
				    break;
			       }
		    case 7:{
		    	    int k = 0;
		    	    printf("�����������ҵ��ڼ������:");
		    	    do
		    	    { if (k < 0)
		    	        printf("�����������������\n");
					  scanf("%d",k);}
		    	    while (k < 0);
			        Search(arr,k,n,0,n-1);
				    break;
			       }
		    case 0:{ 
		            free(arr);
		            exit(0);
				    break;
			       }
		    default:printf("�������");   
		} 
		if (choice <= 7 && choice >= 0)
		{
			printf("����������Ϊ��");
			for (i = 0; i < n; i++)
			  printf("%d\t",arr[i]);
		}
		free(arr);
		system("pause");
		system("cls");
		fclose(fp); 
	  } 
	} 
   return 0;
}
void memu()//չʾ�û�����
{
    printf("\n\n*********************************************************************************************************\n");
    printf("**********************************�������һ�� ����͢****************************************************\n");
    printf("********************************�� �� �� �� ֮ ���� �� �� �� ��*****************************************\n");
    printf("*\t\t\t\t\t\t\t\t\t\t\t\t\t\t*\n");
    printf("*\t\t\t\t\t\t\t\t\t\t\t\t\t\t*\n");
    printf("*\t\t\t\t         1.  ��������        \t\t\t\t\t\t*\n");
    printf("*\t\t\t\t         2.  ��������        \t\t\t\t\t\t*\n");
    printf("*\t\t\t\t         3.  �ǵݹ��������  \t\t\t\t\t\t*\n");
    printf("*\t\t\t\t         4.  �鲢����        \t\t\t\t\t\t*\n");
    printf("*\t\t\t\t         5.  ��������        \t\t\t\t\t\t*\n");
    printf("*\t\t\t\t         6.  ��������        \t\t\t\t\t\t*\n");
    printf("*\t\t\t\t         7.  �ҵ���k����     \t\t\t\t\t\t*\n");
    printf("*\t\t\t\t         0.  �˳�            \t\t\t\t\t\t*\n");
    printf("*\t\t\t\t\t\t\t\t\t\t\t\t\t\t*\n");
    printf("*********************************************************************************************************\n");
    printf("����������Ҫ���������֣�\n");
}
