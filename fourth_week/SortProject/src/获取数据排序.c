#include <stdio.h>
#include <stdlib.h>
#include "qgsort.h"
#include <string.h> 

/* run this program using the console pauser or add your own getch, system("pause") or input loop */
int main(int argc, char *argv[]) {
	void memu(); //声明菜单 
	int n, i;
	FILE *fp;
	int *arr;
	int choice;
	while (1)
	{
	    printf("请输入你想获取数据的个数:\n"); 
	    scanf("%d",&n);
	    if (n < 0)
		{
		 	printf("输入错误\n");
		 	continue;
		} 
		fp = fopen("D://data.txt","r");
		if (fp == NULL)
		{
			printf("获取数据失败,请打开其他程序写入数据\n");
			break;
		}
	    else 
	    {  
	       arr = (int *)malloc(sizeof(int)*n); 
	       for (i=0; i < n&&!feof(fp);i++)
	    	  fscanf(fp,"%d\t",&arr[i]);
		   if (feof(fp))
		   	 printf("数据不足所需，仍能排序，请到生成程序中生成更多的数据吧！\n");
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
		    	    printf("请输入你想找到第几大的数:");
		    	    do
		    	    { if (k < 0)
		    	        printf("输入错误，请重新输入\n");
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
		    default:printf("输入错误！");   
		} 
		if (choice <= 7 && choice >= 0)
		{
			printf("排序后的数组为：");
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
void memu()//展示用户界面
{
    printf("\n\n*********************************************************************************************************\n");
    printf("**********************************软件工程一班 陈起廷****************************************************\n");
    printf("********************************数 据 结 构 之 排序 程 序 设 计*****************************************\n");
    printf("*\t\t\t\t\t\t\t\t\t\t\t\t\t\t*\n");
    printf("*\t\t\t\t\t\t\t\t\t\t\t\t\t\t*\n");
    printf("*\t\t\t\t         1.  插入排序        \t\t\t\t\t\t*\n");
    printf("*\t\t\t\t         2.  快速排序        \t\t\t\t\t\t*\n");
    printf("*\t\t\t\t         3.  非递归快速排序  \t\t\t\t\t\t*\n");
    printf("*\t\t\t\t         4.  归并排序        \t\t\t\t\t\t*\n");
    printf("*\t\t\t\t         5.  计数排序        \t\t\t\t\t\t*\n");
    printf("*\t\t\t\t         6.  基数排序        \t\t\t\t\t\t*\n");
    printf("*\t\t\t\t         7.  找到第k大数     \t\t\t\t\t\t*\n");
    printf("*\t\t\t\t         0.  退出            \t\t\t\t\t\t*\n");
    printf("*\t\t\t\t\t\t\t\t\t\t\t\t\t\t*\n");
    printf("*********************************************************************************************************\n");
    printf("请输入你想要的排序数字：\n");
}
