import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String A , B;
        int score;

        Scanner sc = new Scanner(System.in);
        A = sc.nextLine();
        B = sc.nextLine();
        score = fill(A , B);
        System.out.println("\n"+A + "\n" + B);
        System.out.println(score);
    }

    public static int fill(String A, String B) {
        int[][] s = new int[A.length() + 2][B.length() + 2];
        int m = 0;
        s[1][1] = 0;
        for (int k = 2; k < A.length() + 2; k++) {
            s[1][k] = -(k - 1);
        }
        for (int k = 2; k < B.length() + 2; k++) {
            s[k][1] = -(k - 1);
        }
        for (int j = 2; j < A.length() + 2; j++) {
            for (int i = 2; i < B.length() + 2; i++) {
                int del = s[i][j - 1] - 1;
                int ins = s[i - 1][j] - 1;
                if (A.charAt(j - 2) == B.charAt(i - 2)) {
                    s[i][j] = max(s[i - 1][j - 1] + 2, del, ins);
                } else if (A.charAt(j - 2) != B.charAt(i - 2)) {
                    s[i][j] = max(s[i - 1][j - 1] - 2, del, ins);
                }
            }
        }
        for (int i = 0; i < B.length() + 2; i++) {
            for (int j = 0; j < A.length() + 2; j++) {
                if (i == 0 && j > 1) {
                    System.out.print(A.charAt(j - 2) + " ");
                } else if (j == 0 && i > 1) {
                    System.out.print(B.charAt(i - 2) + " ");
                } else {
                    System.out.print(s[i][j] + " ");
                }

                //System.out.println("\t");
            }
            System.out.println("\n");
        }
        strings(s, A.length(), B.length(), A, B);
        return s[A.length() + 1][B.length() + 1];
    }

    public static void strings(int[][]s , int a , int b , String A , String B){
        int v = b;
        int aa = 0;
        int bb = 0;
        Character[] string1 = new Character[a];
        Character[] string2 = new Character[b];
        while(v != 0){
            int n = max(s[a-1][b-1] , s[a][b-1] , s[a-1][b]);
            if( n == s[a-1][b-1] &&  s[a-1][b] == n && s[a][b-1] == n)
                n = s[a-1][b-1];
            if(s[a][b-1] == s[a-1][b] && s[a-1][b] == n ){
                n = s[a][b-1];
            }
            if(n == s[a-1][b-1]){
                string2[bb] = B.charAt(b-1);
                string1[aa] = A.charAt(a-1);
                aa++;
                bb++;
            }
            else if (n == s[a-1][b] ){
                string1[aa] = '-';
                string2[bb] = B.charAt(b-1);
                aa++;
                bb++;
                string1[aa] = A.charAt(a-1);
            }
            else{
                string2[bb] = '-';
                string1[aa] = A.charAt(a-1);
                aa++;
                bb++;
                string2[bb] = B.charAt(b-1);
            }
            v--;
            a--;
            b--;
        }
        System.out.println(makeString(string1) + "\n" + makeString(string2));
    }

    public static String makeString(Character[] C){
        String s123 = "";
        for(int mm = C.length-1 ; mm > -1 ; mm--){
            if(C[mm] != null){
                s123 = s123 + C[mm];
            }
        }
        return s123;
    }

    public static int max(int a , int b , int c){
        if(a > b && a > c){
            return a;
        }
        else if(b > a && b > c ){
            return b;
        }
        else return c;
    }


}
