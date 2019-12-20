
public class Main {

	public static void main(String[] args) {
//		int[] cookie = { 1, 1, 2, 3 }; // 3

		int[] cookie = {1,2,4,5}; // 0

		System.out.println(new Solution().solution(cookie));
	}

}

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