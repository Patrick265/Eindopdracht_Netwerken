import presentation.GameFrame;
/**
 * @author Tom Martens, Patrick de Jong
 * @since 23 April 2018
 */
public class Main
{
    public static void main(String[] args)
    {
        Runnable frame = new GameFrame("TreacherousMUD");
        Thread frameThread = new Thread(frame);
        frameThread.start();
    }
}
