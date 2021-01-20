import java.util.*;
class Solution{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter size of matrix : ");
		int row = sc.nextInt();
		int col= sc.nextInt();
		int matrix[][]=new int[row][col];
		int sol[][] = new int[col][row];
		System.out.println("enter the elements : ");
		for(int i=0; i<row ;i++){
			for(int j=0; j<col; j++){
				System.out.println("matrix["+(i+1)+","+(j+1)+"]:");
				matrix[i][j]=sc.nextInt();
			}
		}
		for(int i=0; i<col; i++){
			for(int j=0; j<row; j++){
				sol[i][j]= matrix[col-j-1][i];
			}
		}
		System.out.println("Solution : ");
		for(int i=0; i<row ;i++){
			for(int j=0; j<col; j++){
				System.out.print(sol[i][j]+" ");
			}
			System.out.println();
		}
		
	}
}