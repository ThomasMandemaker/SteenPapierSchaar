import javafx.application.Application;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SteenPapierSchaarServer extends Thread implements QuizConstants
{
    private Question[] questionList = {new Question("Valenine's day is banned in Saudi Arabia.", true),
            new Question("A slug\\'s blood is green.", true),
            new Question("Approximately one quarter of human bones are in the feet.", true),
            new Question("The total surface area of two human lungs is approximately 70 square metres.", true),
            new Question("In West Virginia, USA, if you accidentally hit an animal with your car, you are free to take it home to eat.", true),
            new Question("In London, UK, if you happen to die in the House of Parliament, you are technically entitled to a state funeral, because the building is considered too sacred a place.", false),
            new Question("It is illegal to pee in the Ocean in Portugal.", true),
            new Question("You can lead a cow down stairs but not up stairs.", false),
            new Question("Google was originally called \\\"Backrub\\\".", true),
            new Question("Buzz Aldrin\\'s mother\\'s maiden name was \\\"Moon\\\"", true),
            new Question("The loudest sound produced by any animal is 188 decibels. That animal is the African Elephant.", false),
            new Question("No piece of square dry paper can be folded in half more than 7 times.", false),
            new Question("Chocolate affects a dog\\'s heart and nervous system; a few ounces are enough to kill a small dog.", true)
    };
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

                    Socket player2 = serverSocket.accept();

                    new DataOutputStream(player2.getOutputStream()).writeInt(PLAYER2);

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