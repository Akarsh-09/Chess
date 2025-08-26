import javax.swing.*;
// import java.awt.*;
import java.awt.event.*;
import java.util.*;
// import javax.swing.Timer;
// import javax.swing.border.Border;

public class MainProject
{
    Frame frame;
    Piece CurrSel;
    int n = 8;
    int x, y;
    public ArrayList<Pair> possible;

    public MainProject()
    {
        frame = new Frame();
        possible = new ArrayList<Pair>();
        
        for(x = 0; x < n; x++)
            for(y = 0; y < n; y++)
            {
                int finalX = x, finalY = y;
                frame.vB.board[y][x].cell.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        // System.out.println("Clicked here");
                        // System.out.println("x = " + finalX + ", y = " + finalY);
                        CurrSel = frame.vB.board[finalY][finalX];
                        CurrSel.showPossible(e, CurrSel, frame.vB, possible);

                        // System.out.println("All possible: ");

                        // try {
                        //     System.out.println(possible.size());
                        // }
                        // catch(Exception ee)
                        // {
                        //     ee.printStackTrace();
                        // }
                        // System.out.println("All possible: ");
                        // System.out.println("All possible: ");
                        
                        // System.out.println(possible.size());
                        for(int k = 0; k < possible.size(); k++)
                        {
                            // System.out.println(frame.vB.board[possible.get(k).second][possible.get(k).first].cell);
                            // System.out.println("\n\n\n");
                            frame.vB.board[possible.get(k).second][possible.get(k).first].cell.setIcon(new ImageIcon("Resources/Final/Possible.png")); // Green
                            // System.out.println(frame.vB.board[possible.get(k).second][possible.get(k).first].cell);
                            // frame.vB.board[possible.get(k).second][possible.get(k).first].cell.setIcon(new Color(180, 235, 40)); // Green
                            frame.repaint();
                            frame.checkboard.repaint();

                            // System.out.println(possible.size() + " - " + possible.get(k).first + ", " + possible.get(k).second);
                        }
                    }
                });
            }
    }

    public static void main(String[] args) {
        new MainProject();
    }
}

class Pair {
    public int first;
    public int second;

    Pair() {}

    Pair(int first, int second)
    {
        this.first = first;
        this.second = second;
    }
}