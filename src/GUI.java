import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
    JButton correct = new JButton("Goed");
    JButton wrong = new JButton("Fout");
    JLabel questions = new JLabel("Hier komt een vraag");
    JLabel counter = new JLabel("goed beantwoord: ");
    private int count = 0;
    private int index = 0;
    private boolean answer = false;

    public GUI() {
        setTitle("Quiz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        setMinimumSize(new Dimension(400, 400));


        correct.setPreferredSize(new Dimension(100, 100));
        correct.setBackground(Color.GREEN);
        correct.addActionListener(this);
        JPanel correctpanel = new JPanel();
        correctpanel.add(correct);
        getContentPane().add(correctpanel, BorderLayout.WEST);


        JPanel wrongpanel = new JPanel();
        wrong.setPreferredSize(new Dimension(100, 100));
        wrong.setBackground(Color.RED);
        wrong.addActionListener(this);
        wrongpanel.add(wrong);
        getContentPane().add(wrongpanel, BorderLayout.EAST);


        JPanel questionpanel = new JPanel();
        questionpanel.add(questions);
        getContentPane().add(questionpanel, BorderLayout.NORTH);

        JPanel counterpanel = new JPanel();
        counterpanel.add(counter);
        getContentPane().add(counterpanel, BorderLayout.SOUTH);
        setVisible(true);

    }


    public void actionPerformed(ActionEvent e) {
        answer = e.getActionCommand().equals(correct.getActionCommand());
        questions.setText(Question.questionList[index].getQuistionString());
        index++;

    }

    public void updateScore()
    {
        counter.setText("goed beantwoord: " + count++);
    }

    public Question getCurrentQuestion()
    {
        return  Question.questionList[index];
    }

    public boolean getAnswer()
    {
        return answer;
    }

//    public static void main(String[] args)
//    {
//        new GUI();
//    }
}