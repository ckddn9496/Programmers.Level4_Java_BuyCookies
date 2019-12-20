# Programmers.Level4_Java_BuyCookies
## 프로그래머스 > 코딩테스트 연습 > 2018 서머코딩 > 쿠키 구입

### 1. 문제설명

문제: https://programmers.co.kr/learn/courses/30/lessons/49995

input으로 바구니에 들어있는 쿠키의 개수가 `int[] cookie`로 들어온다. 이 과자 바구니 중 `l`번부터 `m`번 바구니 까지를 첫째 아들에게 주고, `m+1`번부터 `r`번 바구니 까지를 둘째 아들에게 주려고 한다. 두 아들이 받는 과자의 수가 같도록 주려고 할때 최대로 줄 수 있는 과자의 수를 return하는 문제

단, 같은 양으로 바구니를 나눌 수 없다면 0을 return

### 2. 풀이

중간 지점을 정한 후 왼쪽과 오른쪽으로 두 인덱스를 갖고 과자의 수를 더해가며 조건을 만족할 때의 쿠키의 수가 최대인지 검사해 주었다.

```java

class Solution {
	public int solution(int[] cookie) {
		int max = 0;
		int len = cookie.length;
		if (len == 1) {
			return 0;
		} else if (len == 2) {
			return cookie[0] == cookie[1] ? cookie[0] : 0;
		}
		
		for (int i = 0; i < len - 1; i++) {
			int leftIdx = i;
			int rightIdx = i + 1;
			int leftSum = cookie[leftIdx];
			int rightSum = cookie[rightIdx];
			
			if (leftSum == rightSum) {
				max = Math.max(leftSum, max);
			}
			
			while(true) {
				
				if (leftSum < rightSum) {
					if (leftIdx == 0)
						break;
					leftIdx--;
					leftSum += cookie[leftIdx];
				
				} else if (leftSum > rightSum) {
					if (rightIdx == len - 1)
						break;
					rightIdx++;
					rightSum += cookie[rightIdx];
					
				} else {
					max = Math.max(leftSum, max);
					if (leftIdx == 0 || rightIdx == len - 1)
						break;
					
					leftIdx--;
					rightIdx++;
					leftSum += cookie[leftIdx];
					rightSum += cookie[rightIdx];
				}
			}
		}
		
		return max;
	}
}

```


### 3. 더 좋은 방법

다른 사람의 풀이를 보며 더 좋은 방법이 있었다. 바구니에 들은 쿠키의 수의 누적하여 갖고있는 배열을 이용한 방법이다. `int[] sum;`으로 길이가 `cookie.length + 1`, `sum[i]`은 `1 ~ i`번 까지의 쿠키의 합을 갖고있는 배열이다. `l`과 `r`에 대해 중간 나누는 지점 `mid`를 정하면 쿠키의 합은 `sum[mid] - sum[l - 1]`과 `sum[r] - sum[m]`으로 구할 수 있다.

위의 방법과 달리 `cookie`배열을 다시 찾을 필요가 없기 때문에 더 빠르고 `mid`를 정하는 방법에 이분탐색을 사용하여 검사 횟수를 크게 줄여 냈었다.

```java
public int find(int start, int end) {
    int i = start, j = end;
    while(start < end) {
        int mid = (start + end) / 2;
        int s1 = pSum[mid] - pSum[i - 1], s2 = pSum[j] - pSum[mid];
        if(s1 == s2) {
            return s1;
        }else if(s1 < s2) {
            start = mid + 1;
        }else {
            end = mid;
        }
    }
    return 0;
}
```
