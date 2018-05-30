public class Question
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
}