package sanpuru;
//★ 整数を入力して素因数分解を行う  前田 稔
import java.io.*;

class Soinsu
{   public static void main(String args[])
    {   BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String str;
        int    n,i;

        try
        {   System.out.print("整数値をタイプして下さい : ");
            str = input.readLine();
            n= Integer.parseInt(str.trim());
            i= 2;
            while(n!=1)
            {   if (n%i==0)
                {   System.out.println("n=" + n + "  素因数=" + i);
                    n/= i;
                }
                else	i++;
            }
        }
        catch(IOException e)
        {   System.out.println("Exception : " + e);
        }
    }
}
