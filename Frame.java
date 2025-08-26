import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class Frame extends JFrame {
    JFrame frame;
    JPanel woodenFrame;
    JPanel mould;
    JPanel checkboard;
    int n = 8;
    JPanel[][] panels;
    VirtualBoard vB;

    Frame()
    {
        frame = new JFrame("Chess");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(0xffffff));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        woodenFrame = new JPanel();
        woodenFrame.setBounds(50, 50, 680, 680);
        woodenFrame.setBackground(new Color(50, 50, 50));
        woodenFrame.setBackground(new Color(120, 85, 70)); // Dark Brown
        // woodenFrame.setBackground(new Color(50, 50, 50));// Dark Grey

        mould = new JPanel();
        mould.setBounds(67, 67, 646, 646);
        mould.setBackground(new Color(0,0,0));

        checkboard = new JPanel();
        checkboard.setLayout(null);
        checkboard.setBounds(70, 70, 640, 640);
        checkboard.setBackground(new Color(255, 240, 210)); // Warm Cream
        // board.setBackground(new Color(160, 200, 50)); // Green

        Border border = BorderFactory.createLineBorder(Color.black, 1);

        this.setLayout(null);
        this.setVisible(true);

        vB = new VirtualBoard();
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                int x = 70 + (80*j);
                int y = 70 + (80*i);
                vB.board[i][j].cell.setBounds(x, y, 80, 80);
                vB.board[i][j].cell.setBorder(border);
                vB.board[i][j].cell.setBorderPainted(true);
                this.add((vB.board[i][j].cell));
                this.repaint();
            }
        }
        this.repaint();

        int delta = 0;
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n/2; j++)
            {
                JPanel pan2 = new JPanel();
                pan2.setBounds(70 + (80*(2*j + 1)) - delta, 70 + (80*i), 80, 80);
                pan2.setBackground(new Color(120, 85, 70));
                this.add(pan2);
            }
            delta = 80 - delta;
        }

        // panels = new JPanel[n][n];
        // for(int i = 0; i < n; i++)
        //     for(int j = 0; j < n; j++)
        //     {
        //         panels[i][j] = new JPanel();
        //         if(((i%2)^(j%2)) == 1) // White
        //         {
        //             panels[i][j].setBounds((80*j) ,(80*i), 80, 80);
        //             panels[i][j].setBackground(new Color(255, 240, 210)); // White
        //         }
        //         else // Black
        //         {
        //             panels[i][j].setBounds((80*j) ,(80*i), 80, 80);
        //             panels[i][j].setBackground(new Color(120, 85, 70)); // Brown
        //         }
        //         this.checkboard.add(panels[i][j]);
        //         this.repaint();
        //     }

        this.add(checkboard);
        this.add(mould);
        this.add(woodenFrame);

        this.repaint();
        this.setLayout(null);
        this.setVisible(true);
    }
}
