#include <stdio.h>
#include <stdlib.h>
#include <stdlib.h>
int main()
{   
    int n;
	FILE *fp;
	printf("��������������������ݵĸ���:\n"); 
	scanf("%d",&n);
	fp=fopen("D://data.txt","w");
	if (fp == NULL)
  		printf("��ȡ����ʧ��\n");
	else 
  	{
  		for (int i=0; i<n;i++)
 	    {
    	 fprintf(fp,"%d\t",rand());
  	    }
  	     printf("�������ݳɹ����Ͻ�ȥ����������ðɣ�\n");
    }  
    fclose(fp);
}

