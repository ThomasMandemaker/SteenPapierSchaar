import javafx.application.Application;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class QuizServer extends Thread implements QuizConstants
{
    private Question[] questionList = Question.questionList;

    public void start()
    {

        new Thread(() ->
        {
            try
            {
                ServerSocket serverSocket = new ServerSocket(42069);
                while (true)
                {
                    Socket player1 = serverSocket.accept();

                    new DataOutputStream(player1.getOutputStream()).writeInt(PLAYER1);

                    new ObjectOutputStream(player1.getOutputStream()).writeObject(questionList);

                    Socket player2 = serverSocket.accept();

                    new DataOutputStream(player2.getOutputStream()).writeInt(PLAYER2);

                    new ObjectOutputStream(player2.getOutputStream()).writeObject(questionList);

                    new Thread(new HandleQuiz(player1, player2)).start();
                }
            } catch (Exception e)
            {

            }
        }).start();
    }

    class HandleQuiz implements Runnable, QuizConstants
    {
        private Socket player1;
        private Socket player2;

        private DataInputStream fromPlayer1;
        private DataOutputStream toPlayer1;
        private DataInputStream fromPlayer2;
        private DataOutputStream toPlayer2;

        public HandleQuiz(Socket player1, Socket player2)
        {
            this.player1 = player1;
            this.player2 = player2;
        }

        public void run()
        {
            try
            {
                fromPlayer1 = new DataInputStream(player1.getInputStream());
                toPlayer1 = new DataOutputStream(player1.getOutputStream());
                fromPlayer2 = new DataInputStream(player2.getInputStream());
                toPlayer2 = new DataOutputStream(player2.getOutputStream());

                while(true)
                {
                    int question = fromPlayer1.readInt();
                    boolean answer = fromPlayer1.readBoolean();
                    
                    toPlayer1.writeBoolean(answer == questionList[question].getTrueFalse());

                    question = fromPlayer2.readInt();
                    answer = fromPlayer2.readBoolean();

                    toPlayer2.writeBoolean(answer == questionList[question].getTrueFalse());
                }
            }
            catch (Exception e)
            {

            }
        }
    }
}