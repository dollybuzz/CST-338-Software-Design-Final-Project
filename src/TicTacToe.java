import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;



public class TicTacToe 
{

   public static void main(String[] args)
   {
      //Creating instance
      TicTacToeGame tttGame = new TicTacToeGame();
      //Setting Title
      tttGame.setTitle("Dalia's Tic Tac Toe Game");
      
      //Setting up JFrame
      tttGame.setSize(600,600);
      tttGame.setLocationRelativeTo(null);
      tttGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      tttGame.setVisible(true);
   }
}


class TicTacToeGame extends JFrame
{
   private boolean gameOver = false;
   private char playerChar = 'X';
   private Cell[][] cells = new Cell[3][3];
   private JPanel panel;
  
   JLabel playerTurn = new JLabel("X's turn to play!");
   
   public TicTacToeGame()
   {
      panel = new JPanel(new GridLayout(3, 3, 0, 0));
      

      for(int r = 0; r < 3; r++)
      {
         for(int c = 0; c < 3; c++)
         {
            panel.add(cells[r][c] = new Cell());
         }
      }
      
      panel.setBorder(new LineBorder(Color.blue, 2));
      playerTurn.setBorder(new LineBorder(Color.red, 3));
      
      add(panel, BorderLayout.CENTER);
      add(playerTurn, BorderLayout.SOUTH);
   }
   
   public boolean isFull()
   {
      for(int r = 0; r < 3; r++)
      {
         for(int c = 0; c < 3; c++)
         {
            if(cells[r][c].getToken() == ' ')
            {
               return false;
            }
         }
      }
      return true;
   }
   
   public boolean isWinning(char token)
   {
      for(int r = 0; r < 3; r++)
      {
         if(cells[r][0].getToken() == token && cells[r][1].getToken() == token && cells[r][2].getToken() == token)
         {
            return true;
         }
      }
      
      for(int c = 0; c < 3; c++)
      {
         if(cells[0][c].getToken() == token && cells[1][c].getToken() == token && cells[2][c].getToken() == token)
         {
            return true;
         }
      }
      
      if(cells[0][0].getToken() == token && cells[1][1].getToken() == token && cells[2][2].getToken() == token)
      {
         return true;
      }
      
      if(cells[0][2].getToken() == token && cells[1][1].getToken() == token && cells[2][0].getToken() == token)
      {
         return true;
      }
      
      return false;
   }
   
   public class Cell extends JPanel
   {
      private char token = ' ';
      
      public Cell()
      {
         setBorder(new LineBorder(Color.magenta, 2));
         addMouseListener(new MyMouseListener());
      }
      
      public char getToken()
      {
         return token;
      }
      
      public void setToken(char clickedToken)
      {
         token = clickedToken;
         repaint();    
      }
      
      protected void paintComponent(Graphics playerToken)
      {
         super.paintComponent(playerToken);
         
         if(token == 'X')
         {
            playerToken.drawLine(10, 10, getWidth()-10, getHeight()-10);
            playerToken.drawLine(getWidth()-10, 10, 10, getHeight()-10);
         }
         else if(token == 'O')
         {
            playerToken.drawOval(10, 10, getWidth()-20, getHeight()-20);
         }
      }
      
      private class MyMouseListener extends MouseAdapter
      {
         
         public void mouseClicked(MouseEvent e)
         {
            if(gameOver)
            {
               return;
            }
            if(token == ' ' && playerChar != ' ')
            {
               setToken(playerChar);
            }
            if(isWinning(playerChar))
            {
               playerTurn.setText(playerChar + " won! Game Over!");
               gameOver = true;
            }
            else if(isFull())
            {
               playerTurn.setText("Tie game! Game Over!");
               playerChar = ' ';
               gameOver = true;
            }
            else
            {
               if(playerChar == 'X')
                  {
                     playerChar = 'O';
                  }
               else
               {
                  playerChar = 'X';
               }
               playerTurn.setText(playerChar + " 's turn.");
            }
         }
      }
   }
}
