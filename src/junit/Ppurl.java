package junit;

public class Ppurl {
	public static void main(String[] args) {
		int arr[] = {51,116,53,120,85,66,71,98,86,100};
		int i,j;
		for(i=0; i<10; i++)
		{
			for(j=0; j<9; j++)
			{
				if(arr[j] > arr[j+1])
				{
					arr[j]^=arr[j+1];
					arr[j+1]^=arr[j];
					arr[j]^=arr[j+1];
				}
			}
		}
		for(i=0; i<10; i++)
		{
			System.out.print(arr[i]);
		}
	}
}
