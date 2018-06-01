import javafx.application.Application;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class QuizServer extends Thread implements QuizConstants
{
    private Question[] questionList = Question.questionList;

    public QuizServer()
    {
        start();
    }

    public void start()
    {

        new Thread(() ->
        {
            try
            {
                ServerSocket serverSocket = new ServerSocket(4000);
                while (true)
                {
                    Socket player1 = serverSocket.accept();

                    //new ObjectOutputStream(player1.getOutputStream()).writeInt(PLAYER1);

                    Socket player2 = serverSocket.accept();

                    //new ObjectOutputStream(player2.getOutputStream()).writeInt(PLAYER2);

                    new Thread(new HandleQuiz(player1, player2)).start();
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }).start();
    }

    class HandleQuiz implements Runnable, QuizConstants
    {
        private Socket player1;
        private Socket player2;

//        private ObjectInputStream fromPlayer1;
//        private ObjectOutputStream toPlayer1;
//        private ObjectInputStream fromPlayer2;
//        private ObjectOutputStream toPlayer2;

        public HandleQuiz(Socket player1, Socket player2)
        {
            System.out.println("Test");
            this.player1 = player1;
            this.player2 = player2;
        }

        public void run()
        {
            try
            {
                ObjectInputStream fromPlayer1 = new ObjectInputStream(player1.getInputStream());
                ObjectOutputStream toPlayer1 = new ObjectOutputStream(player1.getOutputStream());
                ObjectInputStream fromPlayer2 = new ObjectInputStream(player2.getInputStream());
                ObjectOutputStream toPlayer2 = new ObjectOutputStream(player2.getOutputStream());

                System.out.println("GAY");

                toPlayer1.writeInt(PLAYER1);
                toPlayer1.flush();
                toPlayer2.writeInt(PLAYER2);
                toPlayer2.flush();


                while(true)
                {
                    System.out.println("DKLSFJLDKFJSLDKFJ");
                    Object object = fromPlayer1.readObject();
                    Question question;
                    if(object instanceof Question)
                        question = (Question)object;
                    else
                        question = null;
                    boolean answer = fromPlayer1.readBoolean();

                    System.out.println(answer);

                    toPlayer1.writeBoolean(answer == question.getTrueFalse());


                    Object object2 = fromPlayer2.readObject();
                    if(object2 instanceof Question)
                        question = (Question)object2;
                    answer = fromPlayer2.readBoolean();

                    toPlayer2.writeBoolean(answer == question.getTrueFalse());
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
    {
        new QuizServer();
    }
}