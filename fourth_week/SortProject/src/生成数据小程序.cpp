#include <stdio.h>
#include <stdlib.h>
#include <stdlib.h>
int main()
{   
    int n;
	FILE *fp;
	printf("请输入你想随机生成数据的个数:\n"); 
	scanf("%d",&n);
	fp=fopen("D://data.txt","w");
	if (fp == NULL)
  		printf("读取数据失败\n");
	else 
  	{
  		for (int i=0; i<n;i++)
 	    {
    	 fprintf(fp,"%d\t",rand());
  	    }
  	     printf("生成数据成功，赶紧去其他程序调用吧！\n");
    }  
    fclose(fp);
}

