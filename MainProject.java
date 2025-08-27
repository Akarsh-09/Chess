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
    Piece last;

    public MainProject()
    {
        frame = new Frame();
        possible = new ArrayList<Pair>();
        last = new Piece();
        
        for(x = 0; x < n; x++)
            for(y = 0; y < n; y++)
            {
                int finalX = x, finalY = y;
                frame.vB.board[y][x].cell.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        CurrSel = frame.vB.board[finalY][finalX];
                        if(CurrSel.color != 0 && CurrSel.color != 1)
                        {
                            System.out.println("Empty clicked");
                            if(possible.size() == 0)
                            {
                                CurrSel.cell.setSelected(false);
                                last = new Piece();
                            }
                            else
                            {
                                for(int i = 0; i < possible.size(); i++)
                                {
                                    if(finalX == possible.get(i).first && finalY == possible.get(i).second)
                                    {
                                        captureCell(last, CurrSel);
                                    }
                                    else
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
                        else
                        {
                            if(possible.size() == 0)
                            {
                                CurrSel.showPossible(e, CurrSel, frame.vB, possible);
                                for(int k = 0; k < possible.size(); k++)
                                {
                                    last = CurrSel;
                                    frame.vB.board[possible.get(k).second][possible.get(k).first].cell.setIcon(new ImageIcon("Resources/Final/Possible.png")); // Green
                                }
                            }
                            else
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
                                    for(int i = 0; i < possible.size(); i++)
                                    {
                                        if(finalX == possible.get(i).first && finalY == possible.get(i).second)
                                        {
                                            captureCell(last, CurrSel);
                                        }
                                        else
                                        {
                                            Piece pp = frame.vB.board[possible.get(i).second][possible.get(i).first];
                                            reAttain(pp);
                                            frame.repaint();
                                            frame.checkboard.repaint();
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