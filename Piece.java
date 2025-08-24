import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;
// import java.util.*;
// import javax.swing.Timer;
// import javax.swing.border.Border;

public class Piece
{
    int color; // 1 -> White, 0 -> Black, 2 -> NULL
    Integer type;
    JCheckBox cell;

    public Piece(JCheckBox cell, Integer type, int color)
    {
        this.color = color;
        this.type = (Integer)type;
        this.cell = cell;
        this.cell.setFocusable(false);

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
        // this.cell.setBounds(0, 0, 80, 80);
    }

    // void move() {}
}

// class Pawn extends Piece
// {
//     public Pawn(JCheckBox cell, int type, int color) {
//         super(cell, type, color);
//     }

//     void move()
//     {
        
//     }
// }

// class Rook extends Piece
// {
//     public Rook(JCheckBox cell, int type, int color) {
//         super(cell, type, color);
//     }

//     void move()
//     {

//     }
// }

// class Knight extends Piece
// {
//     public Knight(JCheckBox cell, int type, int color) {
//         super(cell, type, color);
//     }

//     void move()
//     {

//     }
// }

// class Bishop extends Piece
// {
//     public Bishop(JCheckBox cell, int type, int color) {
//         super(cell, type, color);
//     }

//     void move()
//     {

//     }
// }

// class Queen extends Piece
// {
//     public Queen(JCheckBox cell, int type, int color) {
//         super(cell, type, color);
//     }

//     void move()
//     {

//     }
// }

// class King extends Piece
// {
//     public King(JCheckBox cell, int type, int color) {
//         super(cell, type, color);
//     }

//     void move()
//     {

//     }
// }