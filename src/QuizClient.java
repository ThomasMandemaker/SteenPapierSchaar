import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.Socket;

public class QuizClient extends Thread implements QuizConstants
{
    private DataInputStream fromServer;
    private DataOutputStream toServer;

    private boolean gameFinished = false;

    private boolean waiting = true;

    private Question[] questionList;

    private String host = "localhost";

    public void start()
    {
        connectToServer();
    }

    private void connectToServer()
    {
        try
        {
            Socket socket = new Socket(host, 42069);

            fromServer = new DataInputStream(socket.getInputStream());
            questionList = ObjectInputStream
            toServer = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e)
        {

        }

        new Thread(() ->
        {
            try
            {
                int player = fromServer.readInt();

                while (!gameFinished)
                {
                    waitForAnswer();
                    sendAnswer();
                    receiveAnswer();
                }
            } catch (Exception e)
            {

            }
        }).start();
    }

    private void waitForAnswer() throws InterruptedException
    {
        while (waiting)
            Thread.sleep(100);
        waiting = true;
    }

    private void sendAnswer()
    {
        toServer.writeInt();
    }

    private void receiveAnswer()
    {

    }
}