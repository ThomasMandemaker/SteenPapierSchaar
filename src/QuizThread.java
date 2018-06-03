import java.io.*;
import java.net.Socket;

public class QuizThread extends Thread
{
    private Socket socket;
    private boolean answer;

    public static int correctCount = 0;

    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    private static int finished = 0;

    public QuizThread(Socket clientSocket)
    {
        this.socket = clientSocket;
    }

    @Override
    public void run()
    {

        try
        {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            objectOutputStream.flush();

            while (true)
            {
                try
                {
                    Question q = null;
                    Object object = objectInputStream.readObject();
                    boolean answer = objectInputStream.readBoolean();
                    if(object instanceof Question)
                        q = (Question) object;

                    boolean correct = (answer == q.getTrueFalse());

                    if(correct)
                        correctCount++;

                    objectOutputStream.flush();

                    if(q.getQuistionString().equals(Question.questionList[12].getQuistionString()))
                        finished = 1;

                    objectOutputStream.writeInt(finished);
                    objectOutputStream.writeBoolean(correct);
                }catch (ClassNotFoundException el)
                {
                    el.printStackTrace();
                }
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void sendFinalScore(int finalScore)
    {
        System.out.println("In sendFinalScore");
        try
        {
            objectOutputStream.flush();
            objectOutputStream.writeInt(finalScore);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}