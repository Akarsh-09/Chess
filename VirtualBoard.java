import javax.swing.*;

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
                    board[i][j] = new Piece(new JCheckBox(), (Integer)1, 0, j, i);
                }
            }
            else if(i == n - 2)
            {
                for(int j = 0; j < n; j++)
                {
                    board[i][j] = new Piece(new JCheckBox(), (Integer)1, 1, j, i);
                }
            }
            else if(i > 1 && i < n - 2)
            {
                for(int j = 0; j < n; j++)
                {
                    int a = i%2, b = j%2;
                    int color = 3 - (a^b);
                    board[i][j] = new Piece(new JCheckBox(), (Integer)0, color, j, i);
                }
            }
            else
            {
                if(i == 0)
                {

                    for(int j = 0; j < n - 3; j++)
                    {
                        board[i][j] = new Piece(new JCheckBox(), (Integer)(j + 2), 0, j, i);
                    }
                    for(int j = n - 3; j < n; j++)
                    {
                        board[i][j] = new Piece(new JCheckBox(), (Integer)(9 - j), 0, j, i);
                    }
                }
                else
                {
                    for(int j = 0; j < n - 3; j++)
                    {
                        board[i][j] = new Piece(new JCheckBox(), (Integer)(j + 2), 1, j, i);
                    }
                    for(int j = n - 3; j < n; j++)
                    {
                        board[i][j] = new Piece(new JCheckBox(), (Integer)(9 - j), 1, j, i);
                    }
                }
            }
        }
    }
}