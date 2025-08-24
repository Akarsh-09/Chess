import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;
// import java.util.*;
// import javax.swing.Timer;
// import javax.swing.border.Border;

public class VirtualBoard
{
    int n = 8;
    Piece[][] board = new Piece[8][8];

    // Type -> 
    // 0 -> Empty JPanel
    // 1 -> Pawns
    // 2 -> Rook
    // 3 -> Knight
    // 4 -> Bishop
    // 5 -> Queen
    // 6 -> King

    VirtualBoard()
    {
        for(int i = 0; i < n; i++)
        {
            if(i == 1)
            {
                for(int j = 0; j < n; j++)
                {
                    Piece p = new Piece(new JCheckBox(), (Integer)1, 0);
                    board[i][j] = p;
                }
            }
            else if(i == n - 2)
            {
                for(int j = 0; j < n; j++)
                {
                    Piece p = new Piece(new JCheckBox(), (Integer)1, 1);
                    board[i][j] = p;
                }
            }
            else if(i > 1 && i < n - 2)
            {
                for(int j = 0; j < n; j++)
                {
                    int a = i%2, b = j%2;
                    int color = 3 - (a^b);
                    System.out.println(color);
                    Piece p = new Piece(new JCheckBox(), (Integer)0, color);
                    board[i][j] = p;
                }
            }
            else
            {
                if(i == 0)
                {
                    for(int j = 0; j < n - 3; j++)
                    {
                        Piece p = new Piece(new JCheckBox(), (Integer)(j + 2), 0);
                        board[i][j] = p;
                    }
                    for(int j = n - 3; j < n; j++)
                    {
                        Piece p = new Piece(new JCheckBox(), (Integer)(9 - j), 0);
                        board[i][j] = p;
                    }
                }
                else
                {
                    for(int j = 0; j < n - 3; j++)
                    {
                        Piece p = new Piece(new JCheckBox(), (Integer)(j + 2), 1);
                        board[i][j] = p;
                    }
                    for(int j = n - 3; j < n; j++)
                    {
                        Piece p = new Piece(new JCheckBox(), (Integer)(9 - j), 1);
                        board[i][j] = p;
                    }
                }
            }
        }
    }
}