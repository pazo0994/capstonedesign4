#include<stdio.h>
#include<stdlib.h>
#include<string.h>


int main(void) {
	int i, j = 0;
	int a[5];
	int temp = 0;
	int check;
	printf("정수를 하나씩입력하세요\n");
	while (1) {
		for (int i = 0; i < 5; i++)
		{
			printf("%d번째숫자입력:", i + 1);
			scanf_s("%d", &a[i]);
			if (a[i] < 0) { a[i] = -1 * a[i]; }//음수입력시 양수변환
		}

		printf("입력해주세요 종료하려면 0 / 오름차순출력 1 / 내림차순출력 2 / 재입력은3\n");

	recheck://goto를통해서 입력값의제한및 재입력받습니다.
		scanf_s("%d", &check);
		if (check == 0)
		{
			printf("프로그램을 종료합니다.");
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
			printf("오름차순정렬 : %d %d %d %d %d", a[0], a[1], a[2], a[3], a[4]);
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
			printf("내림차순정렬 : %d %d %d %d %d", a[0], a[1], a[2], a[3], a[4]);
			break;
		}
		if (check == 3)
		{
			continue;
		}
		else
		{
			printf("0,1,2,3중에서 입력해주세요");
			goto recheck;
		}
	}
	return 0;
}