import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventTarget;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class GUIClient extends Application
{
    private static ObjectInputStream fromServer;
    private static ObjectOutputStream toServer;

    Button correct;
    Button wrong;
    Label questions;
    Label counter;
    Label youWon;

    private int finishID = 0;
    private boolean receivedScore = false;

    private boolean gameFinished = false;
    private boolean waiting = true;
    private boolean answerSelected = false;

    private String host = "localhost";

    private int count = 0;
    private int index = 0;

    public void start(Stage primaryStage)
    {
        BorderPane pane = new BorderPane();
        BorderPane bottomPane = new BorderPane();
        BorderPane topPane = new BorderPane();

        youWon = new Label();
        correct = new Button("Goed");
        wrong = new Button("Fout");
        questions = new Label(Question.questionList[0].getQuistionString());
        questions.setFont(Font.font("Garamond", 20));
        counter = new Label("Goed beantwoord: ");

        correct.setPrefSize(100, 100);
        correct.setStyle("-fx-background-color: chartreuse");
        correct.setOnMouseClicked(e ->
        {
            try
            {
                handleClick(e.getTarget());
            } catch (Exception e1)
            {
                e1.printStackTrace();
            }
        });

        wrong.setPrefSize(100, 100);
        wrong.setStyle("-fx-background-color: red");
        wrong.setOnMouseClicked(e ->
        {
            try
            {
                handleClick(e.getTarget());
            } catch (Exception e1)
            {
                e1.printStackTrace();
            }
        });

        //topPane.setLeft(correct);
        topPane.setCenter(questions);
        //topPane.setRight(wrong);

        bottomPane.setLeft(counter);
        bottomPane.setRight(youWon);

        pane.setRight(wrong);
        pane.setLeft(correct);
        pane.setTop(topPane);
        pane.setBottom(bottomPane);

        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        connectToServer();
    }
    private void connectToServer()
    {
        try
        {
            Socket socket = new Socket(host, 4000);


            toServer = new ObjectOutputStream(socket.getOutputStream());
            toServer.flush();
            fromServer = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        new Thread(() ->
        {
            try
            {
                //int player = fromServer.readInt();
                while (!gameFinished)
                {
                    waitForAnswer();
                    receiveAnswer();
                }

                while(!receivedScore)
                {
                    receiveOthersScore();

                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }).start();
    }

    private void waitForAnswer() throws InterruptedException
    {
        while (waiting)
            Thread.sleep(100);

        waiting = true;
    }

    public void sendAnswer() throws Exception
    {
        if(index == 13)
            return;
        toServer.writeObject(Question.questionList[index]);
        toServer.writeBoolean(answerSelected);
        toServer.flush();
        waiting = false;
    }

    public void receiveAnswer() throws Exception
    {
        finishID = fromServer.readInt();
        boolean curAnswer = fromServer.readBoolean();
        Platform.runLater(() -> questions.setText(Question.questionList[++index].getQuistionString()));
        if(curAnswer)
            Platform.runLater(() -> counter.setText("Aantal goede antwoorden: " + ++count));
        if(finishID == 1)
            gameFinished = true;
    }

    public void receiveOthersScore() throws Exception
    {
        int otherScore = fromServer.readInt();
        if(otherScore > count)
            youWon.setText("You won by: " + (count - otherScore));
        else
            youWon.setText("You lost by: " + (count - otherScore));
        receivedScore = true;
    }

    public void handleClick(EventTarget e) throws Exception
    {
        if(e.equals(correct))
            answerSelected = true;
        else
            answerSelected = false;
        sendAnswer();
    }
}