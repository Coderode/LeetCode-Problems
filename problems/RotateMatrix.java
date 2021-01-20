import java.util.*;
class RotateMatrix{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter size of matrix : ");
		int row = sc.nextInt();
		int col= sc.nextInt();
		int matrix[][]=new int[row][col];

		System.out.println("enter the elements : ");
		for(int i=0; i<row ;i++){
			for(int j=0; j<col; j++){
				System.out.println("matrix["+(i+1)+","+(j+1)+"]:");
				matrix[i][j]=sc.nextInt();
			}
		}
		
		//get the transpose of the matrix 
		for(int i=0; i<col; i++){
			for(int j=i+1; j<row; j++){
				if(i!=j){
					int temp=matrix[i][j];
					matrix[i][j] = matrix[j][i];
					matrix[j][i]=temp;
				}
				
			}
		}
		//print transpose
		System.out.println("Transpose : ");
		for(int i=0; i<row ;i++){
			for(int j=0; j<col; j++){
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
		
		//gettting the rotation
		
		for(int j=0; j<col/2; j++){
			for(int i=0; i<row; i++){
				int temp = matrix[i][j];
				matrix[i][j]=matrix[i][col-j-1];
				matrix[i][col-j-1] = temp;
			}
		}
		
		
		
		System.out.println("Rotate by 90* : ");
		for(int i=0; i<row ;i++){
			for(int j=0; j<col; j++){
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
		
	}
}