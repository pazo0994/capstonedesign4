#include<stdio.h>
#include<stdlib.h>
#include<string.h>


int main(void) {
	int i, j = 0;
	int a[5];
	int temp = 0;
	int check;
	printf("������ �ϳ����Է��ϼ���\n");
	while (1) {
		for (int i = 0; i < 5; i++)
		{
			printf("%d��°�����Է�:", i + 1);
			scanf_s("%d", &a[i]);
			if (a[i] < 0) { a[i] = -1 * a[i]; }//�����Է½� �����ȯ
		}

		printf("�Է����ּ��� �����Ϸ��� 0 / ����������� 1 / ����������� 2 / ���Է���3\n");

	recheck://goto�����ؼ� �Է°������ѹ� ���Է¹޽��ϴ�.
		scanf_s("%d", &check);
		if (check == 0)
		{
			printf("���α׷��� �����մϴ�.");
			break;
		}
		if (check == 1)
		{
			for (i = 0; i < 5; i++)
			{
				for (j = i + 1; j <= 5; j++)
				{
					if (j >= 5) { break; }
					if (i == j) { continue; }
					else
					{
						if (a[i] > a[j])
						{
							temp = a[i];
							a[i] = a[j];
							a[j] = temp;
						}
					}
				}
			}
			printf("������������ : %d %d %d %d %d", a[0], a[1], a[2], a[3], a[4]);
			break;
		}
		if (check == 2)
		{
			for (i = 0; i < 5; i++)
			{
				for (j = i + 1; j <= 5; j++)
				{
					if (i == j) { continue; }
					else
					{
						if (a[i] < a[j])
						{
							temp = a[i];
							a[i] = a[j];
							a[j] = temp;
						}
					}
				}
			}
			printf("������������ : %d %d %d %d %d", a[0], a[1], a[2], a[3], a[4]);
			break;
		}
		if (check == 3)
		{
			continue;
		}
		else
		{
			printf("0,1,2,3�߿��� �Է����ּ���");
			goto recheck;
		}
	}
	return 0;
}