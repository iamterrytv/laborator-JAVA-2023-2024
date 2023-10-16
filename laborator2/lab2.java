package laborator2;

import java.util.Scanner;

public class lab2
{
    public static void main(String[] args) {
        problema1();
        problema2();
    }

    public static void problema1() {
        Scanner in = new Scanner(System.in);
        System.out.println("introdu n=");
        int n = in.nextInt();
        int p = 1;
        for(int i = 1; i <= n; i++) {
            p = i * i;
            System.out.println("Patratul este " + p);
        }
    }

    public static void problema2() {

        Scanner in = new Scanner(System.in);
        System.out.println("introdu n=");
        int n = in.nextInt();
        int s = 0;
        int p = 1;
        for(int i = 1; i <= n; i++)
        {
            if(i%2==0){
                s=s+i;
            }
            else{
                p=p*i;
            }
        }
        System.out.println("s="+s);
        System.out.println("p="+p);
    }
}
