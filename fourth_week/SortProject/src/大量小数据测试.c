#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "qgsort.h"
#include <time.h>
/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	int n, i, k = 0; 
	long sum = 0;
    int *arr;
    while (k<1000000)
	{   n = 100;
		arr = (int *)malloc(sizeof(int) * n);   
		for (i = 0; i<n; i++)  
		{  
		        arr[i] = rand();  
		} 
	    clock_t start = clock();
	    QuickSort(arr,n);
	    clock_t diff = clock()-start;
	    sum+=diff;
	    free(arr);
		k++;   
	}
	printf("����(�ǵݹ��)100������100000�������е�����ʱΪ%ldms\n",sum); 
	sum = 0;
	k = 0; 
	while (k<1000000)
	{   n = 100;
		arr = (int *)malloc(sizeof(int) * n);   
		for (i = 0; i<n; i++)  
		{  
		        arr[i] = rand();  
		} 
	    clock_t start = clock();
	    QuickSort_Recursion(arr,0,n-1);
	    clock_t diff = clock()-start;
	    sum+=diff;
	    free(arr);
		k++;   
	}
	printf("����(�ݹ��)100������100000�������е�����ʱΪ%ldms\n",sum); 
	sum = 0;
	k = 0;
	while (k<100000)
	{   n = 100;
		arr = (int *)malloc(sizeof(int) * n);   
		for (i = 0; i<n; i++)  
		{  
		        arr[i] = rand();  
		} 
	    clock_t start = clock();
	    MergeSort(arr,0,n-1);
	    clock_t diff = clock()-start;
	    sum += diff;
	    free(arr);
		k++;   
	}
	printf("�鲢����100������100000�������е�����ʱΪ%ldms\n",sum); 
	sum = 0;
	k = 0;
	while (k<100000)
	{   n = 100;
		arr = (int *)malloc(sizeof(int) * n);   
		for (i = 0; i<n; i++)  
		{  
		        arr[i] = rand();  
		} 
	    clock_t start = clock();
	    insertSort(arr,n);
	    clock_t diff = clock()-start;
	    sum+=diff;
	    free(arr);
		k++;   
	}
	printf("��������100������100000�������е�����ʱΪ%ldms\n",sum); 
	sum = 0;
	k = 0;
	while (k<100000)
	{   n = 100;
		arr = (int *)malloc(sizeof(int) * n); 
		int max = 0;  
		for (i = 0; i<n; i++)  
		{  
		      arr[i] = rand(); 
			  if (arr[i] > max)
			    max = arr[i]; 
		} 
	    clock_t start = clock();
	    CountSort(arr,n,max);
	    clock_t diff = clock()-start;
	    sum+=diff;
	    free(arr);
		k++;   
	}
	printf("��������100������100000�������е�����ʱΪ%ldms\n",sum);
	sum = 0;
	k = 0;
	while (k<100000)
	{   n = 100;
		arr = (int *)malloc(sizeof(int) * n);   
		for (i = 0; i<n; i++)  
		{  
		        arr[i] = rand();  
		} 
	    clock_t start = clock();
	    RadixCountSort(arr,n);
	    clock_t diff = clock()-start;
	    sum+=diff;
	    free(arr);
		k++;   
	}
	printf("��������100������100000�������е�����ʱΪ%ldms\n",sum); 
}
