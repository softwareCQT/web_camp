#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "qgsort.h"

void insertSort(int *a,int n)  //插入排序算法	 
{
	int i; 
	for ( i = 1; i < n; i++) 
	{
	   int insert = a[i];
	   int j = i-1;   //找插入点位置
	   while (j >= 0&&insert < a[j])
	   {
	   	  a[j+1] = a[j];
		  j--; 
	   } 
	   a[j+1] = insert;//插入 
	}
} 
void MergeArray(int *a,int begin,int mid,int end)  //归并数组的合成 
 {
 	int i = begin, j = mid + 1, k = 0;   // i ，j分别为两个子数组的开头 
    int *temp = (int *)malloc(sizeof(int)*(end-begin+1));
    while (i <= mid && j <= end)  //谁大就吃谁 ，必然有一组数据被吃完 
    {  
		if(a[i]<a[j])
		    temp[k++]=a[i++];
		else 
		    temp[k++]=a[j++];	
	}
	while (i <= mid)     //把剩下的全吃完 
      temp[k++] = a[i++];
    while (j <= end)     //同上 
      temp[k++] = a[j++];
    for (i = begin, k = 0; k < end-begin+1; i++, k++) //复制粘贴 
      a[i] = temp[k];
    free(temp);
    temp = NULL;
 } 
void MergeSort(int *a,int begin,int end)  //归并排序的分区 
 {
 	if (begin >= end)
     return;
    else
     { int mid = (begin+end)/2;
       MergeSort(a,begin,mid);
       MergeSort(a,mid+1,end);
       MergeArray(a,begin,mid,end);
	 }
 }
void QuickSort_Recursion(int *a, int begin, int end)
{
	if (begin > end)
	  return;
	else 
	{
		int i = begin + 1, j = end; //定义两个索引指针，一个在前，一个在后
	    while (i <= j)
	    {
	        while (i <= j&&a[j] >= a[begin])  //尾先动，大于选取数就-1; 
	    	   j--;
	    	while (i <= j&&a[i] <= a[begin])  //头后动，小于选取数就+1; 
	    	   i++;	    	                        
	    	if (i<= j)   //两个指针都停止后，便是可以交换的数据 
	    	 {
	    	 	int temp = a[i];
	    	 	a[i] = a[j];
	    	 	a[j] = temp;
			 }
		}
	  int temp = a[j];   //把选取值交换到分界点的位置 
	  a[j] = a[begin];
	  a[begin] = temp;
	  QuickSort_Recursion(a, begin, j-1);
	  QuickSort_Recursion(a, j+1, end);
	} 
} 
void QuickSort(int *a,int size)  //快排的非递归实现 
{
	int b[10000],puApo = -1;   //开一个数组当栈用;
	int high = size - 1, low = 0;
	int mid; //用来记住界定位置 
	b[++puApo] = high; //压栈操作 
    b[++puApo] = low;
	while (puApo != -1)  //栈为空则说明已经排好序了 
	{
		low = b[puApo--];
		high = b[puApo--];
		mid = Partition(a,low,high); 
		if (low < mid-1)
		{
	        b[++puApo] = mid-1;
	        b[++puApo] = low;
		}
		if (high > mid+1)
		{
			b[++puApo] = high;
	        b[++puApo] = mid+1;
		}
	}
} 
int Partition(int *a, int begin, int end)
{   
   int i = begin + 1, j = end; //定义两个索引指针，一个在前，一个在后
    while (i <= j)
	{
        while (i <= j&&a[j] >= a[begin])  //尾先动，大于选取数就-1; 
    	   j--;
    	while (i <= j&&a[i] <= a[begin])  //头后动，小于选取数就+1; 
    	   i++;	    	                        
    	if (i<= j)                     //两个指针都停止后，便是可以交换的数据 
    	 {
    	 	int temp = a[i];
    	 	a[i] = a[j];
    	 	a[j] = temp;
		 }
	}
   int temp = a[j];                    //把选取值交换到分界点的位置 
   a[j] = a[begin];
   a[begin] = temp;
   return j;
}

void CountSort(int *a, int size, int max)    //计数排序 
{
	int *count = (int *)malloc(sizeof(int)*(max+1));   //计数数组 
	int *buff = (int *)malloc(sizeof(int)*size);  //辅助数组 
	int i;
    for(i = 0; i <= max; i++)
	  count[i]=0;
	for (i = 0; i < size; i++) //先计算每个数出现的次数 
		count[a[i]]++;
	for (i = 1; i <= max; i++)  //累加次数 
	    count[i] += count[i-1];
    for (i = size-1; i >= 0; i--)
	{   
		buff[count[a[i]]-1] = a[i];
		count[a[i]]--;
    }
	memcpy(a,buff,sizeof(int)*size); //将buff里排好序的数据放到a中 
	free(count);
	free(buff); 
}
 
void RadixCountSort(int *a,int size)        //桶排序，即基数排序法 
{
	int *Radix_rem[10];      //定义一个指针数组，分别当做0-9关键字的桶
	int loop,remeber = a[0];    //用来控制位数循环 
	    
	for (loop = 1; loop < size; loop++)  //找出该数组中的最大值; 
	    if (a[loop] > remeber)
		  remeber = a[loop]; 
	for (loop = 0; remeber != 0; loop++) 
		  remeber /= 10;
		  
    remeber = loop;         //得出该数组最大值的数字位数 
    
	for (loop = 0; loop < 10; loop++)      //为桶分配空间 
	{
	 	Radix_rem[loop] = (int *)malloc(sizeof(int)*(size+1));  
	 	Radix_rem[loop][0] = 0;    //桶的第一个0位置拿来记住索引       
	} 
	
    for (loop = 1; loop <= remeber; loop++ )                //从个位数开始到最大值的位数   
    {   
        int i, j;
    	for (i = 0; i < size; i++)   //按位数分配 
    	{
    		int num = getpos(a[i], loop);
    		int index = ++Radix_rem[num][0]; //桶的第一个0位置拿来记住索引 
    		Radix_rem[num][index] = a[i];
		}
		for (i = 0,j = 0; j < size; i++)  //从小到大收集桶的数据 
		{   
		    int index;
			for (index = 1; index <= Radix_rem[i][0]; index++)
				a[j++] = Radix_rem[i][index];  
		    Radix_rem[i][0] = 0;  //还原索引的位置	
		}
	}
	for (loop = 0; loop < 10; loop++)
	  free(Radix_rem[loop]);
} 
int getpos(int a,int ps)  //从左到右得到的第几位数字 
{
	int temp = 1;
	int rem;
	for (rem = 1; rem < ps; rem++)
	   temp*=10; 
	return a/temp%10;
} 

void ColorSort(int *a,int size)  //0 1 2 排序 
{
	int *count = (int *)malloc(sizeof(int)*3);    //用来记住 0 1 2的个数 
	int i;
	for (i = 0; i < 3; i++)
       count[i] = 0;
    for (i = 0; i < size; i++)
       count[a[i]]++;
    i = 0;
    while (count[0]--)
      a[i++] = 0;
    while (count[1]--)
      a[i++] = 1;
    while (count[2]--)
      a[i++] = 2; 
} 
int Search(int *a,int k,int size,int begin,int end) // 找到第k大的数字  利用快排思想 
{
    int position = Partition(a,begin,end);
	while (position != size - k)   //因为是找第k大，从末尾得到判断条件退出
	{
	   if (position > size - k)    //位置大于原设位置，第k大必在前面 
	   	 position = Partition(a,begin,position-1);
	   if (position < size -k)	   //位置小于原设位置，第k大必在之后  
            position = Partition(a,position+1,end);
    }
    return a[position];
}

  
