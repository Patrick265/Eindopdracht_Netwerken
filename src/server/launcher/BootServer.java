package server.launcher;

public class BootServer
{
    public static void main(String[] args)
    {
        Thread serverThread = new Thread(new server.logic.Server());
        serverThread.start();
    }
}
