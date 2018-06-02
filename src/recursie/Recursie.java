package recursie;

public class Recursie
{
    public static void main(String[] args)
    {
        new Recursie();
    }

    public Recursie()
    {
        run(5321123);
    }


    public int run(int n)
    {
        if (n == 1)
        {
            System.out.println(n);
            return n;
        }
        if(n % 2 == 0)
        {
            System.out.println(n);
            return run(n /2);
        }
        else
        {
            System.out.println(n);
            return run(3 * n + 1);
        }
    }
}
