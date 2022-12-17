
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GHighLow extends HighLow implements ActionListener{
    private JFrame frame;
    private JPanel panel;
    private ImageIcon cardBack;
    private JButton dealcardButton;
    private JLabel CurrentCardIcon, NextCardIcon, Insructions;
    
    private ImageIcon []cards = new ImageIcon[52];
    private Deck deck;
    private boolean buttonpressed;


    public GHighLow() {
        super();
        this.deck = new Deck();
        String suit = "";
        int i = 0;
        int value;

        for (int y = 1; y <= 13; y++) {
            value = y;
            for (int x = 1; x <= 4; x++) {
                switch (x) {
                    case 1:
                        suit = "Clubs";
                        break;
                    case 2:
                        suit = "Diamonds";
                        break;
                    case 3:
                        suit = "Hearts";
                        break;
                    case 4:
                        suit = "Spades";
                        break;
                }
                
                cards[i] = new ImageIcon(Integer.toString(value) + suit + ".png");
                i++;
            }
        }
        
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(350, 275));
        this.panel.setLayout(null);
        
        this.frame = new JFrame("Cards");
        this.frame.setSize(350,250);
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.add(panel);
        
        this.cardBack = new ImageIcon("cardBack.png");
        
        this.CurrentCardIcon = new JLabel(null,this.cardBack,SwingConstants.CENTER);
        this.NextCardIcon =    new JLabel(null,this.cardBack,SwingConstants.CENTER);
        this.Insructions = new JLabel("");
        this.Insructions.setBounds(65, 175, 300, 25);
        
        (this.NextCardIcon).setIcon(cardBack);
        this.NextCardIcon.setBounds(75, 30, 80, 125);
        this.CurrentCardIcon.setBounds(155, 30, 80, 125);
        this.panel.add(this.NextCardIcon);
        this.panel.add(this.CurrentCardIcon);
        
        this.dealcardButton = new JButton("Deal");
        this.dealcardButton.setBounds(115, 150, 80, 25);
        this.dealcardButton.addActionListener(this);
        this.panel.add(dealcardButton);
        this.panel.add(this.Insructions);
        
        this.frame.setVisible(true);
    }
    
    public void SetCardLabel(Card card, JLabel label)
    {
        String suit = card.GetSuitAsString();
        int value = card.GetValue();
        int num = 0;
        switch (suit) {
            case "Clubs":
                num = (value * 4) - 4;
                break;
            case "Diamonds":
                num = (value * 4) - 3;
                break;
            case "Hearts":
                num = (value * 4) - 2;
                break;
            case "Spades":
                num = (value * 4) - 1;
                break;
                
        }
        (this.NextCardIcon).setIcon(cards[num]);
    }
    @Override
    protected int PlayARound() {
        int CorrectGuesses = 0;
        deck.Shuffle();
        boolean playing = true;
        Card CurrentCard = deck.DealCard();
        Card NextCard;
        Insructions.setText("");

        while (playing) {
            DisplayCurrentCard(CurrentCard);
            char ans = GuessPrompt();
            NextCard = deck.DealCard();
            String result;
            Insructions.setText("Press the 'Deal' button to deal a card");
            while(buttonpressed == false){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            DisplayNextCard(NextCard);
            
            if ((CurrentCard.GetValue() < NextCard.GetValue() && ans == 'h') || (CurrentCard.GetValue() > NextCard.GetValue() && ans == 'l')) {
                CorrectGuesses++;
                result = "Your prediction was correct, You made " + CorrectGuesses + " correct guesses";
                DisplayResult(result);
            } 
            else {
                result = "Your prediction was incorrect, You made " + CorrectGuesses + " correct guesses";
                DisplayResult(result);
                playing = false;
            }
            Insructions.setText("");
            
            CurrentCard = NextCard;
            buttonpressed = false;
        }  
        
        return CorrectGuesses;
    }

    @Override
    protected void DisplayCurrentCard(Card CurrentCard) {
        SetCardLabel(CurrentCard, this.CurrentCardIcon);
    }

    @Override
    protected void DisplayNextCard(Card NextCard) {
        SetCardLabel(NextCard, this.CurrentCardIcon);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dealcardButton) {
            buttonpressed = true;
        }
    }
}
