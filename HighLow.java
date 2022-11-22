//Liam Tyler
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class HighLow {
    private int m_gamesPlayed = 0;
    private int m_sumOfScores = 0;
    static JFrame f;
    private Deck deck;
    
    
    public HighLow(){
        this.deck = new Deck();
    }
    public void play() {
        boolean playing = true;
        while (playing) {
            m_gamesPlayed++;
            m_sumOfScores += PlayARound();
            char answer = PlayAgainPromt();
            if (answer == 'n') {
                playing = false;
            }
        }
        DisplayFinalStats();
    }

    protected int PlayARound() {
        int CorrectGuesses = 0;
        deck.Shuffle();
        boolean playing = true;
        Card NextCard;
        Card CurrentCard;
        CurrentCard = deck.DealCard();

        while (playing) {
            DisplayCurrentCard(CurrentCard);
            char ans = GuessPrompt();
            NextCard = deck.DealCard();
            String result;
            DisplayNextCard(NextCard);

            if ((CurrentCard.GetValue() < NextCard.GetValue() && ans == 'h') || (CurrentCard.GetValue() > NextCard.GetValue() && ans == 'l')) {
                CorrectGuesses++;
                result = "Your prediction was correct, You made " + CorrectGuesses + " correct guesses";
                DisplayResult(result);
            } else {
                result = "Your prediction was incorrect, You made " + CorrectGuesses + " correct guesses";
                DisplayResult(result);
                playing = false;
            }
            CurrentCard = NextCard;
        }
        return CorrectGuesses;
    }

    protected char GuessPrompt() {
        boolean NoInput = true;
        char answer = 'n';
        while (NoInput) {
            String guess = null;
            guess = JOptionPane.showInputDialog(f, "Will the next card be higher (h) or lower (l)?", guess);
            if ((guess.equals("h")) || (guess.equals("l"))) {
                answer = guess.charAt(0);
                NoInput = false;
            } else {
                JOptionPane.showMessageDialog(f, "Please input lowercase letters.");
            }
        }
        return answer;
    }

    protected char PlayAgainPromt() {
        char answer = 'n';
        UIManager.put("OptionPane.cancelButtonText", "No");
        UIManager.put("OptionPane.okButtonText", "Yes");
        int ans = JOptionPane.showConfirmDialog(f, "Would you like to play again?", "Play Again?",
                JOptionPane.YES_NO_OPTION);

        if (ans == JOptionPane.YES_OPTION) {
            answer = 'y';
        }
        if (ans == JOptionPane.NO_OPTION) {
            answer = 'n';
        }
        UIManager.put("OptionPane.cancelButtonText", "Cancel");
        UIManager.put("OptionPane.okButtonText", "OK");
        return answer;
    }
    protected void DisplayFinalStats(){
        JOptionPane.showMessageDialog(f, "You played a total of " + m_gamesPlayed + " games\n and socred a total of " + m_sumOfScores + " points \n for an overall avg score per game of " + (m_sumOfScores / m_gamesPlayed));
    }

    protected void DisplayCurrentCard(Card CurrentCard) {
        JOptionPane.showMessageDialog(f, "The current card is " + CurrentCard);
    }

    protected void DisplayNextCard(Card NextCard) {
        JOptionPane.showMessageDialog(f, "The Next card is " + NextCard);
    }

    protected void DisplayResult(String result) {
        JOptionPane.showMessageDialog(f, result);
    }
}
