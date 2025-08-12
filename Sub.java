import java.util.*;

public class Sub {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a[][] = new int[3][3];
        int b[][] = new int[3][3];
        int e[][] = new int[3][3];

        System.out.println("Enter values of matrix a:");
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                a[i][j] = sc.nextInt();

        System.out.println("Enter values of matrix b:");
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                b[i][j] = sc.nextInt();

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                e[i][j] = a[i][j] - b[i][j];

        System.out.println("Resultant :");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(e[i][j] + " ");
            }
            System.out.println();
        }
    }
}
