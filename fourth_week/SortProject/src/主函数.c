#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "qgsort.h" 
#include <time.h> 

int main()  
{  
    int n,i,k=0; 
    int *arr;
    while (k<3) 
	{
		if (k == 0)
		   n = 10000;
		else if(k == 1)
		   n = 50000;
		else 
		   n = 200000;   
		arr = (int *)malloc(sizeof(int) * n);   
		for (i = 0; i<n; i++)  
		{  
		        arr[i] = rand();  
		} 
	    clock_t start = clock();
	    QuickSort(arr,n);
	    clock_t diff = clock()-start;
	    printf("快排函数(非递归版)在%d个数据下的用时%dms\n",n,diff);
	    free(arr);
		k++;   
	}
	k = 0;
	while (k<3) 
	{
		if (k == 0)
		   n = 10000;
		else if(k == 1)
		   n = 50000;
		else 
		   n = 200000;   
		arr = (int *)malloc(sizeof(int) * n);   
		for (i = 0; i<n; i++)  
		{  
		        arr[i] = rand();  
		} 
	    clock_t start = clock();
	    QuickSort_Recursion(arr,0,n-1);
	    clock_t diff = clock()-start;
	    printf("快排函数(递归版)在%d个数据下的用时%dms\n",n,diff);
	    free(arr);
		k++;   
	}
	k = 0; 
    while (k<3) 
	{
		if (k == 0)
		   n = 10000;
		else if(k == 1)
		   n = 50000;
		else 
		   n = 200000;    
		arr = (int *)malloc(sizeof(int) * n);   
		for (i = 0; i<n; i++)  
		{  
		        arr[i] = rand();  
		} 
	    clock_t start = clock();
	    MergeSort(arr,0,n-1);
	    clock_t diff = clock()-start;
	    printf("归并排序在%d个数据下的用时%dms\n",n,diff);
	    free(arr);
		k++;   
	}
    k = 0; 
    while (k<3) 
	{
		if (k == 0)
		   n = 10000;
		else if(k == 1)
		   n = 50000;
		else 
		   n = 200000;  
		arr = (int *)malloc(sizeof(int) * n);   
		for (i = 0; i<n; i++)  
		{  
		        arr[i] = rand();  
		} 
	    clock_t start = clock();
	    insertSort(arr,n);
	    clock_t diff = clock()-start;
	    printf("插入排序在%d个数据下的用时%dms\n",n,diff);
	    free(arr);
		k++;   
	}
	k = 0;
	while (k<3) 
	{
		if (k == 0)
		   n = 10000;
		else if(k == 1)
		   n = 50000;
		else 
		   n = 200000;  
		int max = 0;  
		arr = (int *)malloc(sizeof(int) * n);   
		for (i = 0; i<n; i++)  
		{  
		    arr[i] = rand();
			if (arr[i] > max)
			  max = arr[i];  
		} 
	    clock_t start = clock();
	    CountSort(arr,n,max);
	    clock_t diff = clock()-start;
	    printf("计数排序在%d个数据下的用时%dms\n",n,diff);
	    free(arr);
		k++;   
	}
	k = 0;
	while (k<3) 
	{
		if (k == 0)
		   n = 10000;
		else if(k == 1)
		   n = 50000;
		else 
		   n = 200000;    
		arr = (int *)malloc(sizeof(int) * n);   
		for (i = 0; i<n; i++)  
		{  
		    arr[i] = rand(); 
		} 
	    clock_t start = clock();
	    RadixCountSort(arr,n);
	    clock_t diff = clock()-start;
	    printf("基数排序在%d个数据下的用时%dms\n",n,diff);
	    free(arr);
		k++;   
	}
   return 0;			
}  

