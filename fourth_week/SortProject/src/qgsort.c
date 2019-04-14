#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "qgsort.h"

void insertSort(int *a,int n)  //���������㷨	 
{
	int i; 
	for ( i = 1; i < n; i++) 
	{
	   int insert = a[i];
	   int j = i-1;   //�Ҳ����λ��
	   while (j >= 0&&insert < a[j])
	   {
	   	  a[j+1] = a[j];
		  j--; 
	   } 
	   a[j+1] = insert;//���� 
	}
} 
void MergeArray(int *a,int begin,int mid,int end)  //�鲢����ĺϳ� 
 {
 	int i = begin, j = mid + 1, k = 0;   // i ��j�ֱ�Ϊ����������Ŀ�ͷ 
    int *temp = (int *)malloc(sizeof(int)*(end-begin+1));
    while (i <= mid && j <= end)  //˭��ͳ�˭ ����Ȼ��һ�����ݱ����� 
    {  
		if(a[i]<a[j])
		    temp[k++]=a[i++];
		else 
		    temp[k++]=a[j++];	
	}
	while (i <= mid)     //��ʣ�µ�ȫ���� 
      temp[k++] = a[i++];
    while (j <= end)     //ͬ�� 
      temp[k++] = a[j++];
    for (i = begin, k = 0; k < end-begin+1; i++, k++) //����ճ�� 
      a[i] = temp[k];
    free(temp);
    temp = NULL;
 } 
void MergeSort(int *a,int begin,int end)  //�鲢����ķ��� 
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
		int i = begin + 1, j = end; //������������ָ�룬һ����ǰ��һ���ں�
	    while (i <= j)
	    {
	        while (i <= j&&a[j] >= a[begin])  //β�ȶ�������ѡȡ����-1; 
	    	   j--;
	    	while (i <= j&&a[i] <= a[begin])  //ͷ�󶯣�С��ѡȡ����+1; 
	    	   i++;	    	                        
	    	if (i<= j)   //����ָ�붼ֹͣ�󣬱��ǿ��Խ��������� 
	    	 {
	    	 	int temp = a[i];
	    	 	a[i] = a[j];
	    	 	a[j] = temp;
			 }
		}
	  int temp = a[j];   //��ѡȡֵ�������ֽ���λ�� 
	  a[j] = a[begin];
	  a[begin] = temp;
	  QuickSort_Recursion(a, begin, j-1);
	  QuickSort_Recursion(a, j+1, end);
	} 
} 
void QuickSort(int *a,int size)  //���ŵķǵݹ�ʵ�� 
{
	int b[10000],puApo = -1;   //��һ�����鵱ջ��;
	int high = size - 1, low = 0;
	int mid; //������ס�綨λ�� 
	b[++puApo] = high; //ѹջ���� 
    b[++puApo] = low;
	while (puApo != -1)  //ջΪ����˵���Ѿ��ź����� 
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
   int i = begin + 1, j = end; //������������ָ�룬һ����ǰ��һ���ں�
    while (i <= j)
	{
        while (i <= j&&a[j] >= a[begin])  //β�ȶ�������ѡȡ����-1; 
    	   j--;
    	while (i <= j&&a[i] <= a[begin])  //ͷ�󶯣�С��ѡȡ����+1; 
    	   i++;	    	                        
    	if (i<= j)                     //����ָ�붼ֹͣ�󣬱��ǿ��Խ��������� 
    	 {
    	 	int temp = a[i];
    	 	a[i] = a[j];
    	 	a[j] = temp;
		 }
	}
   int temp = a[j];                    //��ѡȡֵ�������ֽ���λ�� 
   a[j] = a[begin];
   a[begin] = temp;
   return j;
}

void CountSort(int *a, int size, int max)    //�������� 
{
	int *count = (int *)malloc(sizeof(int)*(max+1));   //�������� 
	int *buff = (int *)malloc(sizeof(int)*size);  //�������� 
	int i;
    for(i = 0; i <= max; i++)
	  count[i]=0;
	for (i = 0; i < size; i++) //�ȼ���ÿ�������ֵĴ��� 
		count[a[i]]++;
	for (i = 1; i <= max; i++)  //�ۼӴ��� 
	    count[i] += count[i-1];
    for (i = size-1; i >= 0; i--)
	{   
		buff[count[a[i]]-1] = a[i];
		count[a[i]]--;
    }
	memcpy(a,buff,sizeof(int)*size); //��buff���ź�������ݷŵ�a�� 
	free(count);
	free(buff); 
}
 
void RadixCountSort(int *a,int size)        //Ͱ���򣬼��������� 
{
	int *Radix_rem[10];      //����һ��ָ�����飬�ֱ���0-9�ؼ��ֵ�Ͱ
	int loop,remeber = a[0];    //��������λ��ѭ�� 
	    
	for (loop = 1; loop < size; loop++)  //�ҳ��������е����ֵ; 
	    if (a[loop] > remeber)
		  remeber = a[loop]; 
	for (loop = 0; remeber != 0; loop++) 
		  remeber /= 10;
		  
    remeber = loop;         //�ó����������ֵ������λ�� 
    
	for (loop = 0; loop < 10; loop++)      //ΪͰ����ռ� 
	{
	 	Radix_rem[loop] = (int *)malloc(sizeof(int)*(size+1));  
	 	Radix_rem[loop][0] = 0;    //Ͱ�ĵ�һ��0λ��������ס����       
	} 
	
    for (loop = 1; loop <= remeber; loop++ )                //�Ӹ�λ����ʼ�����ֵ��λ��   
    {   
        int i, j;
    	for (i = 0; i < size; i++)   //��λ������ 
    	{
    		int num = getpos(a[i], loop);
    		int index = ++Radix_rem[num][0]; //Ͱ�ĵ�һ��0λ��������ס���� 
    		Radix_rem[num][index] = a[i];
		}
		for (i = 0,j = 0; j < size; i++)  //��С�����ռ�Ͱ������ 
		{   
		    int index;
			for (index = 1; index <= Radix_rem[i][0]; index++)
				a[j++] = Radix_rem[i][index];  
		    Radix_rem[i][0] = 0;  //��ԭ������λ��	
		}
	}
	for (loop = 0; loop < 10; loop++)
	  free(Radix_rem[loop]);
} 
int getpos(int a,int ps)  //�����ҵõ��ĵڼ�λ���� 
{
	int temp = 1;
	int rem;
	for (rem = 1; rem < ps; rem++)
	   temp*=10; 
	return a/temp%10;
} 

void ColorSort(int *a,int size)  //0 1 2 ���� 
{
	int *count = (int *)malloc(sizeof(int)*3);    //������ס 0 1 2�ĸ��� 
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
int Search(int *a,int k,int size,int begin,int end) // �ҵ���k�������  ���ÿ���˼�� 
{
    int position = Partition(a,begin,end);
	while (position != size - k)   //��Ϊ���ҵ�k�󣬴�ĩβ�õ��ж������˳�
	{
	   if (position > size - k)    //λ�ô���ԭ��λ�ã���k�����ǰ�� 
	   	 position = Partition(a,begin,position-1);
	   if (position < size -k)	   //λ��С��ԭ��λ�ã���k�����֮��  
            position = Partition(a,position+1,end);
    }
    return a[position];
}

  
