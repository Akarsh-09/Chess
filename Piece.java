import javax.swing.*;
// import java.awt.*;
import java.awt.event.*;
// import java.util.*;
// import javax.swing.Timer;
// import javax.swing.border.Border;
import java.util.ArrayList;

public class Piece
{
    int color; // 1 -> White Piece 0 -> Black Piece, 2 -> Empty Place
    Integer type;
    JCheckBox cell;
    int PosX;
    int PosY;
    boolean isMoved = false;

    public Piece(JCheckBox cell, Integer type, int color, int PosX, int PosY)
    {
        this.color = color;
        this.type = (Integer)type;
        this.cell = cell;
        this.cell.setFocusable(false);
        this.PosX = PosX;
        this.PosY = PosY;

        if(color == 0)
        {
            String img = "Resources/Final/B" + Integer.toString(type) + ".png";
            cell.setIcon(new ImageIcon(img));
        }
        else if(color == 1)
        {
            String img = "Resources/Final/W" + Integer.toString(type) + ".png";
            cell.setIcon(new ImageIcon(img));
        }
        else if(color == 2)
        {
            String img = "Resources/Final/Brown.png";
            cell.setIcon(new ImageIcon(img));
        }
        else
        {
            String img = "Resources/Final/White.png";
            cell.setIcon(new ImageIcon(img));
        }
        this.cell.setBounds(0, 0, 80, 80);
    }

    public void addActionListener(ActionListener actionListener) {
    //     // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addActionListener'");
    }

    void showPossible(ActionEvent e, Piece CurrSel, VirtualBoard vB, ArrayList<Pair> possible)
    {
        // System.out.println("Clicked");
        if(CurrSel.cell.isSelected())
        {
            // System.out.println("Came inside");
            int x = CurrSel.PosX;
            int y = CurrSel.PosY;
            int type = CurrSel.type;
            int color = CurrSel.color;
            boolean isMoved = CurrSel.isMoved;

            // System.out.println("x = " + x);
            // System.out.println("y = " + y);
            // System.out.println("type = " + type);
            // System.out.println("color = " + color);
            // System.out.println("isMoved = " + isMoved);
    
            if(type == 0)
            {
                // if(possible.size() == 0)
                // {
                //     return possible;
                // }
                // else
                // {
                //     for(int i = 0; i < possible.size(); i++)
                //     {
                //         if(possible.get(i).first == x && possible.get(i).second == y)
                //         {
                //             move();
                //         }
                //     }
                // }
            }
            else if(type == 1)
            {
                Pawn pawn = new Pawn();
                if(color == 0)
                {
                    pawn.showB(vB, possible, new Pair(x, y), isMoved);
                }
                if(color == 1)
                {
                    pawn.showW(vB, possible, new Pair(x, y), isMoved);
                }
            }
            else if(type == 2)
            {
                Rook rook = new Rook();
                rook.show(vB, possible, new Pair(x, y), color);
            }
            else if(type == 3)
            {
                Knight knight = new Knight();
                knight.show(vB, possible, new Pair(x, y), color);
            }
            else if(type == 4)
            {
                Bishop bishop = new Bishop();
                bishop.show(vB, possible, new Pair(x, y), color);
            }
            else if(type == 5)
            {
                Bishop bishop = new Bishop();
                bishop.show(vB, possible, new Pair(x, y), color);
                Rook rook = new Rook();
                rook.show(vB, possible, new Pair(x, y), color);
            }
            else if(type == 6)
            {
                King king = new King();
                king.show(vB, possible, new Pair(x, y), color);
            }
        }
        // return possible;
    }
}

class Pawn
{
    void showB(VirtualBoard vB, ArrayList<Pair> possible, Pair p, boolean isMoved)
    {
        try {
            if(isMoved == false)
            {
                if((p.second + 2 < 8) && vB.board[p.second + 2][p.first].type == 0 && vB.board[p.second + 1][p.first].type == 0)
                {
                    possible.add(new Pair(p.first, p.second + 2));
                }
            }
            if((p.second + 1 < 8) && vB.board[p.second + 1][p.first].type == 0)
            {
                possible.add(new Pair(p.first, p.second + 1));
            }
            if((p.second + 1 < 8) && (p.first + 1 < 8) && vB.board[p.second + 1][p.first + 1].color == 1)
            {
                possible.add(new Pair(p.first + 1, p.second + 1));
            }
            if((p.second + 1 < 8) && (p.first - 1 >= 0) && vB.board[p.second + 1][p.first - 1].color == 1)
            {
                possible.add(new Pair(p.first - 1, p.second + 1));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    void showW(VirtualBoard vB, ArrayList<Pair> possible, Pair p, boolean isMoved)
    {
        if(isMoved == false)
        {
            if((p.second - 2 >= 0) && vB.board[p.second - 2][p.first].type == 0 && vB.board[p.second - 1][p.first].type == 0)
            {
                possible.add(new Pair(p.first, p.second - 2));
            }
        }
        if((p.second - 1 >= 0) && vB.board[p.second - 1][p.first].type == 0)
        {
            possible.add(new Pair(p.first, p.second - 1));
        }
        if((p.second - 1 >= 0) && (p.first + 1 < 8) && vB.board[p.second - 1][p.first + 1].color == 0)
        {
            possible.add(new Pair(p.first + 1, p.second - 1));
        }
        if((p.second - 1 >= 0) && (p.first - 1 >= 0) && vB.board[p.second - 1][p.first - 1].color == 0)
        {
            possible.add(new Pair(p.first - 1, p.second - 1));
        }
    }
}

class Rook
{
    void show(VirtualBoard vB, ArrayList<Pair> possible, Pair p, int color)
    {
        int xx = p.first, yy = p.second;

        for(int i = xx + 1; i < 8; i++)
        {
            if(vB.board[yy][i].color == color)
                break;
            if(vB.board[yy][i].color == 1 - color)
            {
                possible.add(new Pair(i, yy));
                break;
            }
            possible.add(new Pair(i, yy));
        }

        for(int i = xx - 1; i >= 0; i--)
        {
            if(vB.board[yy][i].color == color)
                break;
            if(vB.board[yy][i].color == 1 - color)
            {
                possible.add(new Pair(i, yy));
                break;
            }
            possible.add(new Pair(i, yy));
        }

        for(int i = yy + 1; i < 8; i++)
        {
            if(vB.board[i][xx].color == color)
                break;
            if(vB.board[i][xx].color == 1 - color)
            {
                possible.add(new Pair(xx, i));
                break;
            }
            possible.add(new Pair(xx, i));
        }

        for(int i = yy - 1; i >= 0; i--)
        {
            if(vB.board[i][xx].color == color)
                break;
            if(vB.board[i][xx].color == 1 - color)
            {
                possible.add(new Pair(xx, i));
                break;
            }
            possible.add(new Pair(xx, i));
        }
    }
}

class Knight
{
    void show(VirtualBoard vB, ArrayList<Pair> possible, Pair p, int color)
    {
        int xx = p.first, yy = p.second;

        if(yy - 2 >= 0 && xx - 1 >= 0 && vB.board[yy - 2][xx - 1].color != color) // NW
        {
            possible.add(new Pair(xx - 1, yy - 2));
        }
        if(xx - 2 >= 0 && yy - 1 >= 0 && vB.board[yy - 1][xx - 2].color != color) // WN
        {
            possible.add(new Pair(xx - 2, yy - 1));
        }
        if(yy + 2 < 8 && xx - 1 >= 0 && vB.board[yy + 2][xx - 1].color != color) // SW
        {
            possible.add(new Pair(xx - 1, yy + 2));
        }
        if(xx - 2 >= 0 && yy + 1 < 8 && vB.board[yy + 1][xx - 2].color != color) // WS
        {
            possible.add(new Pair(xx - 2, yy + 1));
        }
        if(yy - 2 >= 0 && xx + 1 < 8 && vB.board[yy - 2][xx + 1].color != color) // NE
        {
            possible.add(new Pair(xx + 1, yy - 2));
        }
        if(xx + 2 < 8 && yy - 1 >= 0 && vB.board[yy - 1][xx + 2].color != color) // EN
        {
            possible.add(new Pair(xx + 2, yy - 1));
        }
        if(yy + 2 < 8 && xx + 1 < 8 && vB.board[yy + 2][xx + 1].color != color) // SE
        {
            possible.add(new Pair(xx + 1, yy + 2));
        }
        if(xx + 2 < 8 && yy + 1 < 8 && vB.board[yy + 1][xx + 2].color != color) // ES
        {
            possible.add(new Pair(xx + 2, yy + 1));
        }
    }
}

class Bishop
{
    void show(VirtualBoard vB, ArrayList<Pair> possible, Pair p, int color)
    {
        int xx = p.first, yy = p.second;

        for(int i = xx + 1, j = yy + 1; i < 8 && j < 8; i++, j++)
        {
            if(vB.board[j][i].type == 0)
                possible.add(new Pair(i, j));
            if(vB.board[j][i].color == 1 - color)
            {
                possible.add(new Pair(i, j));
                break;
            }
            if(vB.board[j][i].color == color)
                break;
        }

        for(int i = xx - 1, j = yy - 1; i >= 0 && j >= 0; i--, j--)
        {
            if(vB.board[j][i].type == 0)
                possible.add(new Pair(i, j));
            if(vB.board[j][i].color == 1 - color)
            {
                possible.add(new Pair(i, j));
                break;
            }
            if(vB.board[j][i].color == color)
                break;
        }

        for(int i = xx - 1, j = yy + 1; i >= 0 && j < 8; i--, j++)
        {
            if(vB.board[j][i].type == 0)
                possible.add(new Pair(i, j));
            if(vB.board[j][i].color == 1 - color)
            {
                possible.add(new Pair(i, j));
                break;
            }
            if(vB.board[j][i].color == color)
                break;
        }

        for(int i = xx + 1, j = yy - 1; i < 8 && j >= 0; i++, j--)
        {
            if(vB.board[j][i].type == 0)
                possible.add(new Pair(i, j));
            if(vB.board[j][i].color == 1 - color)
            {
                possible.add(new Pair(i, j));
                break;
            }
            if(vB.board[j][i].color == color)
                break;
        }
    }
}

class King
{
    void show(VirtualBoard vB, ArrayList<Pair> possible, Pair p, int color)
    {
        int xx = p.first, yy = p.second;

        if(yy - 1 >= 0 && vB.board[yy - 1][xx].color != color) // N
        {
            possible.add(new Pair(xx, yy - 1));
        }
        if(yy - 1 >= 0 && xx - 1 >= 0 && vB.board[yy - 1][xx - 1].color != color) // NW
        {
            possible.add(new Pair(xx - 1, yy - 1));
        }
        if(xx - 1 >= 0 && vB.board[yy][xx - 1].color != color) // W
        {
            possible.add(new Pair(xx - 1, yy));
        }
        if(yy + 1 < 8 && xx - 1 >= 0 && vB.board[yy + 1][xx - 1].color != color) // SW
        {
            possible.add(new Pair(xx - 1, yy + 1));
        }
        if(yy + 1 < 8 && vB.board[yy + 1][xx].color != color) // S
        {
            possible.add(new Pair(xx, yy + 1));
        }
        if(yy + 1 < 8 && xx + 1 < 8 && vB.board[yy + 1][xx + 1].color != color) // SE
        {
            possible.add(new Pair(xx + 1, yy + 1));
        }
        if(xx + 1 < 8 && vB.board[yy][xx + 1].color != color) // E
        {
            possible.add(new Pair(xx + 1, yy));
        }
        if(xx + 1 < 8 && yy - 1 >= 0 && vB.board[yy - 1][xx + 1].color != color) // NE
        {
            possible.add(new Pair(xx + 1, yy - 1));
        }
    }
}