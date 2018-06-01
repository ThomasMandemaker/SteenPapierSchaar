//import java.io.*;
//import java.net.Socket;
//
//public class QuizClient extends Thread implements QuizConstants
//{
//    private static ObjectInputStream fromServer;
//    private static ObjectOutputStream toServer;
//
//    static GUI gui = new GUI();
//
//    private boolean gameFinished = false;
//
//    private boolean waiting = true;
//
//    private Question[] questionList = Question.questionList;
//
//    private String host = "localhost";
//
//    public QuizClient()
//    {
//        start();
//    }
//
//    public void start()
//    {
//        connectToServer();
//    }
//
//    private void connectToServer()
//    {
//        try
//        {
//            Socket socket = new Socket(host, 4000);
//
//
//            toServer = new ObjectOutputStream(socket.getOutputStream());
//            fromServer = new ObjectInputStream(socket.getInputStream());
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//        new Thread(() ->
//        {
//            System.out.println("PLESEWORKMYDUDE");
//            try
//            {
//                int player = fromServer.readInt();
//                System.out.println("GOTEEM");
//                System.out.println(player);
//
//                while (!gameFinished)
//                {
//                    waitForAnswer();
//                    sendAnswer();
//                    receiveAnswer();
//                }
//            } catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//        }).start();
//    }
//
//    private void waitForAnswer() throws InterruptedException
//    {
//        while (waiting)
//            Thread.sleep(100);
//        waiting = true;
//    }
//
//    public static void sendAnswer() throws Exception
//    {
//        toServer.writeObject(gui.getCurrentQuestion());
//        toServer.writeBoolean(gui.getAnswer());
//
//    }
//
//    public boolean receiveAnswer() throws Exception
//    {
//        return fromServer.readBoolean();
//    }
//
//    public static void main(String[] args)
//    {
//        new QuizClient();
//    }
//}