import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener{
    JButton correct = new JButton("Goed");
    JButton wrong = new JButton("Fout");
    private int teller = 0;

        public GUI(){

            setTitle("Quiz");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            setMinimumSize(new Dimension(400, 400));


            correct.setPreferredSize(new Dimension(100,100));
            correct.setBackground(Color.GREEN);
            JPanel correctpanel = new JPanel();
            correctpanel.add(correct);
            getContentPane().add(correctpanel, BorderLayout.WEST);


            JPanel wrongpanel = new JPanel();
            wrong.setPreferredSize(new Dimension(100,100));
            wrong.setBackground(Color.RED);
            wrongpanel.add(wrong);
            getContentPane().add(wrongpanel, BorderLayout.EAST);

            JLabel question = new JLabel("Hier komt een vraag");
            JPanel questionpanel = new JPanel();
            questionpanel.add(question);
            getContentPane().add(questionpanel, BorderLayout.NORTH);

            JLabel counter = new JLabel ("teller");
            JPanel counterpanel = new JPanel();
            counterpanel.add(counter);
            getContentPane().add(counterpanel, BorderLayout.SOUTH);
            setVisible(true);

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        correct.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

}
