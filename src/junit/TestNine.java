package junit;

import org.junit.Test;

public class TestNine {

	@Test
	public void nine() {
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print(j + "*" + i + "=" + i * j + "\t");
			}
			System.out.print("\n");
		}
	}

	@Test
	public void zhishu() {
		boolean b;
		int r = 0;
		for (int i = 1; i <= 10000; i++) {
			b = true;
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					b = false;
					break;
				}
			}
			if (b) {
				if (++r % 10 == 0) {
					System.out.print(i + "\n");
				} else {
					System.out.print(i + ",");
				}
			}

		}
	}
	
	@Test
	public void order() {
		int a[]={3,1,7,5,15,30,27};
		int t=0;
		for(int i=0; i<a.length; i++){
			for(int j=i+1;j<a.length; j++){
				if(a[i]>a[j])
				{
					t=a[i];
					a[i]=a[j];
					a[j]=t;
				}
			}
		}
		for(int i=0;i<a.length;i++)
		{
			System.out.print(a[i]+ ",");
		}
		
	}
}
