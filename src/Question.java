import java.io.Serializable;

public class Question implements Serializable
{
    private boolean trueFalse;
    private String quistionString;

    public Question(String quistionID, boolean trueFalse)
    {
        this.trueFalse = trueFalse;
        this.quistionString = quistionID;
    }

    public boolean getTrueFalse()
    {
        return trueFalse;
    }

    public String getQuistionString()
    {
        return quistionString;
    }


    public static Question[] questionList = {
            new Question("Valenine's day is banned in Saudi Arabia.", true),
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

}