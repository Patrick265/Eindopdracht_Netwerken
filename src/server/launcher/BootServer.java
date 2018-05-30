package server.launcher;

import server.logic.Server;

public class BootServer
{
    public static void main(String[] args)
    {
        Thread serverThread = new Thread(new server.logic.Server());
        serverThread.start();
    }
}
