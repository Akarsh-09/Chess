import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class MainProject
{
    Frame frame;
    Piece CurrSel;
    int n = 8;
    int x, y;
    public ArrayList<Pair> possible;
    public ArrayList<Pair> check;
    Piece last;
    Piece prev;
    boolean turn = true;

    public MainProject()
    {
        frame = new Frame();
        possible = new ArrayList<Pair>();
        last = new Piece();
        prev = new Piece();

        for(int i = 0; i < 2; i++)
            for(int j = 0; j < n; j++)
            {
                frame.vB.board[i][j].cell.setEnabled(false);
                frame.vB.board[i][j].cell.setDisabledIcon(new ImageIcon("Resources/Final/B" + frame.vB.board[i][j].type + ".png"));
                frame.repaint();
                frame.checkboard.repaint();
            }
        
        for(x = 0; x < n; x++)
            for(y = 0; y < n; y++)
            {
                int finalX = x, finalY = y;
                frame.vB.board[y][x].cell.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        if(frame.vB.board[finalY][finalX].cell.isEnabled() == false) {}
                        else
                        {
                            System.out.println("Clicked");
                            CurrSel = frame.vB.board[finalY][finalX];
                            if(CurrSel.color != 0 && CurrSel.color != 1)  // Clicked Empty Piece
                            {
                                if(possible.size() == 0)  // 1st time
                                {
                                    CurrSel.cell.setSelected(false);
                                    last = new Piece();
                                }
                                else  // 2nd time
                                {
                                    for(int i = 0; i < possible.size(); i++)  // If it is possible to capture
                                    {
                                        if(finalX == possible.get(i).first && finalY == possible.get(i).second)  // Capturing empty cell
                                        {
                                            captureCell(last, CurrSel);
                                            prev = CurrSel;

                                            if(turn == true)  // Disabling white pieces
                                            {
                                                for(int k = 0; k < n; k++)
                                                {
                                                    for(int j = 0; j < n; j++)
                                                    {
                                                        Piece p = frame.vB.board[k][j];
                                                        if(p.color == 1)
                                                        {
                                                            p.cell.setEnabled(false);
                                                            p.cell.setDisabledIcon(new ImageIcon("Resources/Final/W" + p.type + ".png"));
                                                        }
                                                        else if(p.color == 0)
                                                            p.cell.setEnabled(true);
                                                    }
                                                }
                                                frame.repaint();
                                                frame.checkboard.repaint();
                                            }
                                            else  // Disabling Black pieces
                                            {
                                                for(int k = 0; k < n; k++)
                                                {
                                                    for(int j = 0; j < n; j++)
                                                    {
                                                        Piece p = frame.vB.board[k][j];
                                                        if(p.color == 0)
                                                        {
                                                            p.cell.setEnabled(false);
                                                            p.cell.setDisabledIcon(new ImageIcon("Resources/Final/B" + p.type + ".png"));
                                                        }
                                                        else if(p.color == 1)
                                                            p.cell.setEnabled(true);
                                                    }
                                                }
                                                frame.repaint();
                                                frame.checkboard.repaint();
                                            }
                                            turn = !turn;
                                            if(turn)
                                            {
                                                frame.WhiteMove.setIcon(new ImageIcon("Resources/Final/WhiteMove.png"));
                                                frame.BlackMove.setIcon(new ImageIcon("Resources/Final/Dissap.png"));
                                            }
                                            else
                                            {
                                                frame.WhiteMove.setIcon(new ImageIcon("Resources/Final/Dissap.png"));
                                                frame.BlackMove.setIcon(new ImageIcon("Resources/Final/BlackMove.png"));
                                            }
                                        }
                                        else  // Resetting empty cell
                                        {
                                            Piece pp = frame.vB.board[possible.get(i).second][possible.get(i).first];
                                            reAttain(pp);
                                            frame.repaint();
                                            frame.checkboard.repaint();
                                        }
                                    }
                                    CurrSel.cell.setSelected(false);
                                    last.cell.setSelected(false);
                                    possible.clear();
                                    frame.repaint();
                                    frame.checkboard.repaint();
                                }
                            }
                            else   // Clicked White or Black Piece
                            {
                                if(possible.size() == 0)  // 1st time
                                {
                                    CurrSel.showPossible(e, CurrSel, frame.vB, possible, 0);
                                    for(int k = 0; k < possible.size(); k++)
                                    {
                                        last = CurrSel;
                                        Piece p = frame.vB.board[possible.get(k).second][possible.get(k).first];
                                        if(p.type == 0)
                                            p.cell.setIcon(new ImageIcon("Resources/Final/Possible.png")); // Green
                                        else
                                        {
                                            p.cell.setEnabled(true);
                                            p.cell.setIcon(new ImageIcon("Resources/Final/Pinned" + (Integer.toString(p.type)) + ".png"));
                                        }
                                        frame.repaint();
                                        frame.checkboard.repaint();
                                    }
                                }
                                else  // 2nd consecutive time
                                {
                                    if(CurrSel == last)
                                    {
                                        for(int j = 0; j < possible.size(); j++)
                                        {
                                            Piece pp = frame.vB.board[possible.get(j).second][possible.get(j).first];
                                            reAttain(pp);
                                            frame.repaint();
                                            frame.checkboard.repaint();
                                        }
                                        last.cell.setSelected(false);
                                        possible.clear();
                                    }
                                    else
                                    {
                                        // turn = !turn;
                                        int svitch = 0;
                                        for(int i = 0; i < possible.size(); i++)
                                        {
                                            if(finalX == possible.get(i).first && finalY == possible.get(i).second)
                                            {
                                                captureCell(last, CurrSel);
                                                prev = CurrSel;
                                                svitch = 1;
                                            }
                                            else
                                            {
                                                Piece pp = frame.vB.board[possible.get(i).second][possible.get(i).first];
                                                reAttain(pp);
                                                if(pp.color == 0)
                                                {
                                                    pp.cell.setEnabled(true);
                                                    String img = "Resources/Final/B" + Integer.toString(pp.type) + ".png";
                                                    pp.cell.setIcon(new ImageIcon(img));
                                                }
                                                else if(pp.color == 1)
                                                {
                                                    pp.cell.setEnabled(true);
                                                    String img = "Resources/Final/W" + Integer.toString(pp.type) + ".png";
                                                    pp.cell.setIcon(new ImageIcon(img));
                                                }
                                                frame.repaint();
                                                frame.checkboard.repaint();
                                            }
                                        }
                                        if(svitch == 1)
                                        {
                                            if(turn == true)  // Disabling white pieces
                                            {
                                                for(int k = 0; k < n; k++)
                                                {
                                                    for(int j = 0; j < n; j++)
                                                    {
                                                        Piece p = frame.vB.board[k][j];
                                                        if(p.color == 1)
                                                        {
                                                            p.cell.setEnabled(false);
                                                            p.cell.setDisabledIcon(new ImageIcon("Resources/Final/W" + p.type + ".png"));
                                                        }
                                                        else if(p.color == 0)
                                                            p.cell.setEnabled(true);
                                                    }
                                                }
                                                frame.repaint();
                                                frame.checkboard.repaint();
                                            }
                                            else  // Disabling Black pieces
                                            {
                                                for(int k = 0; k < n; k++)
                                                {
                                                    for(int j = 0; j < n; j++)
                                                    {
                                                        Piece p = frame.vB.board[k][j];
                                                        if(p.color == 0)
                                                        {
                                                            p.cell.setEnabled(false);
                                                            p.cell.setDisabledIcon(new ImageIcon("Resources/Final/B" + p.type + ".png"));
                                                        }
                                                        else if(p.color == 1)
                                                            p.cell.setEnabled(true);
                                                    }
                                                }
                                                frame.repaint();
                                                frame.checkboard.repaint();
                                            }
                                            turn = !turn;
                                            if(turn)
                                            {
                                                frame.WhiteMove.setIcon(new ImageIcon("Resources/Final/WhiteMove.png"));
                                                frame.BlackMove.setIcon(new ImageIcon("Resources/Final/Dissap.png"));
                                            }
                                            else
                                            {
                                                frame.WhiteMove.setIcon(new ImageIcon("Resources/Final/Dissap.png"));
                                                frame.BlackMove.setIcon(new ImageIcon("Resources/Final/BlackMove.png"));
                                            }
                                        }
                                        last.cell.setSelected(false);
                                        CurrSel.cell.setSelected(false);
                                        possible.clear();
                                    }
                                }
                                frame.repaint();
                                frame.checkboard.repaint();
                            }
                        }

                        // if(prev != null) // Is previious move a giving check?
                        // {
                        //     Piece KingB = new Piece();
                        //     Piece KingW = new Piece();

                        //     for(int i = 0; i < 8; i++)
                        //         for(int j = 0; j < 8; j++)
                        //         {
                        //             if(frame.vB.board[i][j].type == 6 && frame.vB.board[i][j].color == 0)
                        //                 KingB = frame.vB.board[i][j];
                        //             if(frame.vB.board[i][j].type == 6 && frame.vB.board[i][j].color == 1)
                        //                 KingW = frame.vB.board[i][j];
                        //         }

                        //     prev.showPossible(e, prev, frame.vB, check, 1);
                        //     if(prev.color == 0)
                        //     {
                        //         for(int i = 0; i < check.size(); i++)
                        //         {
                        //             if(check.get(i).first == KingW.first && check.get(i).second == KingB.second)
                        //             {
                        //                 Piece 
                        //                 .cell.setIcon(new ImageIcon("Resources/Final/Pinned" + (Integer.toString(p.type)) + ".png"));
                        //             }
                        //         }
                        //     }
                        // }

                        // System.out.println("New = \n");
                        // for(int i = 0; i < n; i++)
                        // {
                        //     for(int j = 0; j < n; j++)
                        //     {
                        //         System.out.print(frame.vB.board[i][j].color + ", " + frame.vB.board[i][j].type + "   ");
                        //     }
                        //     System.out.println();
                        // }
                    }
                });
            }
    }

    void captureCell(Piece from, Piece to)
    {
        int a = from.PosX%2, b = from.PosY%2;
        int col = 3 - (a^b);

        to.cell.setIcon(from.cell.getIcon());
        to.color = from.color;
        to.type = from.type;
        to.isMoved = true;

        from.color = col;
        from.type = 0;
        if(col == 2)
        {
            String img = "Resources/Final/Brown.png";
            from.cell.setIcon(new ImageIcon(img));
        }
        else if(col == 3)
        {
            String img = "Resources/Final/White.png";
            from.cell.setIcon(new ImageIcon(img));
        }
    }

    void reAttain(Piece p)
    {
        if(p.color == 0)
        {
            String img = "Resources/Final/B" + Integer.toString(p.type) + ".png";
            p.cell.setIcon(new ImageIcon(img));
        }
        else if(p.color == 1)
        {
            String img = "Resources/Final/W" + Integer.toString(p.type) + ".png";
            p.cell.setIcon(new ImageIcon(img));
        }
        else if(p.color == 2)
        {
            String img = "Resources/Final/Brown.png";
            p.cell.setIcon(new ImageIcon(img));
        }
        else if(p.color == 3)
        {
            String img = "Resources/Final/White.png";
            p.cell.setIcon(new ImageIcon(img));
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