import java.util.*;
import java.lang.*;

public class Topu{
    public static int lcs (char x[],char y[],int m,int n){
        if(m==0||n==0){
            return 0;
        }
        if(x[m-1]==y[n-1]){
            return 1+lcs(x,y,m-1,n-1);

        }else {
            return max(lcs(x,y,m,n-1),lcs(x,y,m-1,n));
        }
    }
    static int max(int l1, int l2) {
        return (l1 > l2) ? l1 : l2;
    }
public static void main(String arg[]){
Topu lcs=new Topu();
String x="ABCDEFGH";
String y="abcdefgh";
 
char arr1[]=x.toCharArray();
char arr2[]=y.toCharArray();

int len1=arr1.length;
int len2=arr2.length;
System.out.println("LCS for the sequences  ABCDEFGH and abcdefgh is:");
System.out.print(lcs(arr1, arr2, len1, len2));

}
}