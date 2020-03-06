import java.util.*;
import java.awt.*;
import javax.swing.*;


/**
 * TriesTry
 */
public class TriesTryDos {



    public static void main(String[] args) {
        GridClass grid = new GridClass(5, 5);
        UserWindow userW = new UserWindow();

        Node2 root = new Node2();
        root.addWord("word", -1);
        root.addWord("world", -1);
        if(root.findWord("worlds", -1)){
            System.out.println("the word is here");
        } else {
            System.out.println("the word is not here");
        }
    }
}

/**
 * Node2
 */
class Node2 {

    public char letter;
    public Node2 route[] = new Node2[27]; // 27 en - 28 es
    public int index=-1;
    public boolean isEnd=false;
    
    // constructors
    Node2(){
        index = -1;
    }
    Node2(char letter){
        this.letter = letter;
        this.index = -1;
    }
    Node2(char letter, boolean isEnd){
        this.letter = letter;
        this.index = -1;
        this.isEnd = isEnd;
    }

    // functions

    /**
     *   addWord(String word, int ix)   
     *
     *   @param word
     *       the word you want to add, it will always receive the
     *       whole word and access the letter it needs
     *   @param ix
     *       when you call this function from main to add a word,
     *       send -1 to ix so the index inmediately jumps up to 0
     *       and it can start correctly
     */
    public void addWord(String word, int ix) {
        ix++;
        if(ix!=word.length()){  // check if we added the last letter of a word (no -> if)
            int isThere = this.findLetter(word.charAt(ix));
            if (isThere<0) {    // if the letter we want is not already there, we add it
                this.index++;
                this.route[index] = new Node2(word.charAt(ix));
                isThere = index;
            }               // regardless, the letter should be in now, so we move on
            this.route[isThere].addWord(word, ix);
        } else {
            // if the last letter we added was the last letter of the word, we mark it
            this.isEnd = true;
        }
    }
    /**
     *  findLetter(chat letter)
     * 
     *  auxiliar for addWord and findWord
     *      returns the index of the ( letter ) on the routes of the node
     *      returns -1 if it doesn't exist ( and if the routes are empty )
     * 
     *  @param letter
     *      letter you want to find
     */
    public int findLetter(char letter){
        for (int i = 0; i <= this.index; i++) {
            if(route[i].letter==letter){
                return i;
            }
        }
        return -1;
    }
    /**
     * findWord(String word, int ix)
     * 
     *   @param word
     *       the word you want to find, it will always receive the
     *       whole word and access the letter it needs
     *   @param ix
     *       when you call this function from main to find a word,
     *       send -1 to ix so the index inmediately jumps up to 0
     *       and it can start correctly
     */
    public boolean findWord(String word, int ix) {
    
        ix++;
        if (ix<word.length()) {
            int isThere = this.findLetter(word.charAt(ix));
            if(isThere<0){
                return false;
            }
            return route[isThere].findWord(word, ix);
        } else {
            if(this.isEnd){
                return true;
            } else {
                return false;
            }
        }
    }
    /**
     * 
     * @param word
     *       the word you want to delete, it will always receive the
     *       whole word and access the letter it needs
     * @param ix
     *       when you call this function from main to delete a word,
     *       send -1 to ix so the index inmediately jumps up to 0
     *       and it can start correctly
     */
    public void deleteWord(String word, int ix) {
        
    }

    // getters and setters
    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public Node2[] getRoute() {
        return route;
    }

    public void setRoute(Node2[] route) {
        this.route = route;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }
}

/**
 * Grid
 * Game grid filled with random letters
 * x -> rows
 * y -> columns
 */
class GridClass {

    GridClass(int x, int y){
        JFrame grid = new JFrame("Boogle");
        Random r = new Random();

        GridLayout gridlayout = new GridLayout(x, y);
        grid.setLayout(gridlayout);

        // Adding random letters in buttons
        JButton cells[] = new JButton[x*y];
        for ( int  i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                cells[((i+1)*(j+1))-1] = new JButton(String.valueOf((char)(r.nextInt(26) + 'A')));
                cells[((i+1)*(j+1))-1].setFont(new Font("SansSerif", Font.BOLD, 50));
                cells[((i+1)*(j+1))-1].setBackground(Color.white);
                cells[((i+1)*(j+1))-1].setForeground(new Color(0, 3, 79));
                grid.add(cells[((i+1)*(j+1))-1]);
            }
        }

        //General
        grid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        grid.setSize(500,500);
        grid.setVisible(true);
    }
}

/**
 * UserWindow
 */
class UserWindow {

    UserWindow(){
        JFrame userW = new JFrame("User Window");
        Font thatFont = new Font("", Font.PLAIN, 16);

        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
        userW.setLayout(flowLayout);
        
        //Title
        JLabel title = new JLabel("  B O O G L E                                    ");
        title.setFont(new Font("", Font.ITALIC, 30));
        userW.add(title);

        //
        JLabel inLabel = new JLabel("Word:                                                 ");
        inLabel.setFont(thatFont);
        userW.add(inLabel);
        JTextField inField = new JTextField("                                                           ");
        userW.add(inField);

        //General
        userW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userW.setSize(300,500);
        userW.setVisible(true);
    }
}