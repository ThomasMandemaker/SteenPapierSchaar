import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class QuizServer implements Runnable
{
    private QuizThread[] quizThreads;

//    private Question[] questionList = Question.questionList;
//
//    public QuizServer()
//    {
//        start();
//    }
//
//    public void start()
//    {
//
//        new Thread(() ->
//        {
//            try
//            {
//                ServerSocket serverSocket = new ServerSocket(4000);
//                while (true)
//                {
//                    socket = serverSocket.accept();
//
//                    //new ObjectOutputStream(player1.getOutputStream()).writeInt(PLAYER1);
//
////                    Socket player2 = serverSocket.accept();
//                    //new ObjectOutputStream(player2.getOutputStream()).writeInt(PLAYER2);
//
//                    //new Thread(new HandleQuiz(player1, player2)).start();
//                }
//            } catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//
//            new QuizThread(socket).start();
//        }).start();
//    }

//    class HandleQuiz implements Runnable, QuizConstants
//    {
//        private Socket player1;
//        private Socket player2;
//
////        private ObjectInputStream fromPlayer1;
////        private ObjectOutputStream toPlayer1;
////        private ObjectInputStream fromPlayer2;
////        private ObjectOutputStream toPlayer2;
//
//        public HandleQuiz(Socket player1, Socket player2)
//        {
//            System.out.println("Test");
//            this.player1 = player1;
//            this.player2 = player2;
//        }
//
//        public void run()
//        {
//            try
//            {
//                ObjectInputStream fromPlayer1 = new ObjectInputStream(player1.getInputStream());
//                ObjectOutputStream toPlayer1 = new ObjectOutputStream(player1.getOutputStream());
//                ObjectInputStream fromPlayer2 = new ObjectInputStream(player2.getInputStream());
//                ObjectOutputStream toPlayer2 = new ObjectOutputStream(player2.getOutputStream());
//
//                toPlayer1.writeInt(PLAYER1);
//                toPlayer1.flush();
//                toPlayer2.writeInt(PLAYER2);
//                toPlayer2.flush();
//
//
//                while(true)
//                {
//                    Object object = fromPlayer1.readObject();
//                    boolean answer = fromPlayer1.readBoolean();
//                    Question question = null;
//                    if(object instanceof Question)
//                        question = (Question)object;
//
//                    boolean correct1 = answer == question.getTrueFalse();
//                    toPlayer1.flush();
//                    toPlayer1.writeBoolean(correct1);
//
//
//                    Object object2 = fromPlayer2.readObject();
//                    if(object2 instanceof Question)
//                        question = (Question)object2;
//                    boolean answer2 = fromPlayer2.readBoolean();
//                    boolean correct2 = answer2 == question.getTrueFalse();
//                    toPlayer2.flush();
//                    toPlayer2.writeBoolean(correct2);
//                }
//            }
//            catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//        }
//    }

    public static void main(String[] args)
    {
        new QuizServer().run();
    }

    @Override
    public void run()
    {
        ServerSocket serverSocket = null;
        Socket socket = null;

        quizThreads = new QuizThread[2];

        int clientCount = 0;

        try
        {
            serverSocket = new ServerSocket(4000);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        while (true)
        {
            try
            {
                socket = serverSocket.accept();
                quizThreads[clientCount] = new QuizThread(socket);
                quizThreads[clientCount].start();
                clientCount++;

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        //send();
    }

    public void send()
    {
        System.out.println("In send");
        while (true)
        {
//            if ((quizThreads[0].finished == 1 && quizThreads[1].finished == 1))
//            {
//                System.out.println("In here");
//                quizThreads[0].sendFinalScore(quizThreads[1].correctCount);
//                quizThreads[1].sendFinalScore(quizThreads[0].correctCount);
//                return;
//            }
        }
    }
}