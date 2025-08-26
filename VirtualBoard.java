import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;
// import java.util.*;
// import javax.swing.Timer;
// import javax.swing.border.Border;

public class VirtualBoard
{
    int n = 8;
    Piece[][] board;  //            (y, x)
    // int[][] currentState = new int[8][8];

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
        board = new Piece[8][8];
        for(int i = 0; i < n; i++)
        {
            if(i == 1)
            {
                for(int j = 0; j < n; j++)
                {
                    Piece p = new Piece(new JCheckBox(), (Integer)1, 0, j, i);
                    board[i][j] = p;
                    // currentState[i][j] = 1;
                }
            }
            else if(i == n - 2)
            {
                for(int j = 0; j < n; j++)
                {
                    Piece p = new Piece(new JCheckBox(), (Integer)1, 1, j, i);
                    board[i][j] = p;
                    // currentState[i][j] = 1;
                }
            }
            else if(i >= 1 && i <= n - 2)
            {
                for(int j = 0; j < n; j++)
                {
                    int a = i%2, b = j%2;
                    int color = 3 - (a^b);
                    Piece p = new Piece(new JCheckBox(), (Integer)0, color, j, i);
                    board[i][j] = p;
                    // currentState[i][j] = 0;
                }
            }
            else
            {
                if(i == 0)
                {
                    for(int j = 0; j < n - 3; j++)
                    {
                        Piece p = new Piece(new JCheckBox(), (Integer)(j + 2), 0, j, i);
                        board[i][j] = p;
                        // currentState[i][j] = j + 2;
                    }
                    for(int j = n - 3; j < n; j++)
                    {
                        Piece p = new Piece(new JCheckBox(), (Integer)(9 - j), 0, j, i);
                        board[i][j] = p;
                        // currentState[i][j] = 9 - j;
                    }
                }
                else
                {
                    for(int j = 0; j < n - 3; j++)
                    {
                        Piece p = new Piece(new JCheckBox(), (Integer)(j + 2), 1, j, i);
                        board[i][j] = p;
                        // currentState[i][j] = j + 2;
                    }
                    for(int j = n - 3; j < n; j++)
                    {
                        Piece p = new Piece(new JCheckBox(), (Integer)(9 - j), 1, j, i);
                        board[i][j] = p;
                        // currentState[i][j] = 9 - j;
                    }
                }
            }
        }
    }
}