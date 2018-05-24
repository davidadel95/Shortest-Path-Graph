/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author john
 */
public class GUI extends JFrame{
    int TotalDistances=0;
    JTextField From = new JTextField();
    JLabel image = new JLabel(new ImageIcon("/Users/DavidAdel/Desktop/FinalPic.jpg"));
    JLabel FromText = new JLabel("From");
    JTextField To = new JTextField();
    JLabel ToText = new JLabel ("To");
    JLabel DistanceText = new JLabel("Distance");
    JTextField Distance = new JTextField();
    JButton AddEdge = new JButton("Add Edge");
    JButton ShortestPath = new JButton("Get Shortest Path");
    JLabel FromShortestPathText = new JLabel ("From");
    JTextField FromShortestPath = new JTextField ();
    JLabel ToShortestPathText = new JLabel("To");
    JTextField ToShortestPath = new JTextField();
    int [] x = {215,255,183,260,293,367,398,423,405,440,471,618,484,621,558,525,359,496,688,503,411,337};
    int [] y = {587,527,487,473,679,454,549,617,709,621,644,608,456,466,350,335,346,257,236,153,176,172};
    int [] CountrySelectedFrom= new int[4000];
    int [] CountrySelectedTo=  new int[4000];
    int[] newx = new int [4000];
    int[] newy = new int [4000];
    int DistanceValue []= new int[4000];
    int CounterOfDrawing=0;
    int numberofnodes =22;
    int graph [][] = new int [numberofnodes][numberofnodes];
    String []DistanceDraw = new String[4000];
    int seq [][] = new int [numberofnodes][numberofnodes];
    int FromShortestPathValue;
    int ToShortestPathValue;
    boolean flag = false;
    FloydClass Floyd;
    
    public GUI(){
        
        for (int counteri=0;counteri<numberofnodes;counteri++){
        for (int counterj=0;counterj<numberofnodes;counterj++){
            if (counteri==counterj){
               seq[counteri][counterj]=-1;
            }
            else{
            seq[counteri][counterj]=counterj;
            }
        }
        }
        for (int i =0 ; i <numberofnodes;i++)
        for (int j=0;j<numberofnodes;j++)
        {
            if(i==j)
            {
            graph[i][j]=0;
            }
            else{
            graph[i][j]=2147483647;
            }
        }
       
    setSize(800,800);
    setTitle("Graph Project");   
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(null);
    add(image);
    image.setBounds(100,100,600,600);
    add(From);
    From.setBounds(110, 7, 100, 40);
    add(FromText);
    FromText.setBounds(60,7,40,40);
    add (ToText);
    ToText.setBounds(220,7,40,40);
    add (To);
    To.setBounds(260,7,100,40);
    add(DistanceText);
    DistanceText.setBounds(370,7,80,40);
    add(Distance);
    Distance.setBounds(460,7,100,40);
    add(AddEdge);
    AddEdge.setBounds(580,7,85,40);
    add(ShortestPath);
    ShortestPath.setBounds(540,720,125,40);
    add(FromShortestPathText);
    FromShortestPathText.setBounds(140,720,40,40);
    add(FromShortestPath);
    FromShortestPath.setBounds(190,720,100,40);
    add(ToShortestPathText);
    ToShortestPathText.setBounds(300,720,40,40);
    add(ToShortestPath);
    ToShortestPath.setBounds(340,720,100,40);
    ShortestPath.addActionListener(new FindShortestPath());
   
    this.addMouseListener(new mouselistener());
//    for(int i=0;i<22;i++){
//        System.out.print(x[i]);
//        System.out.print(" ");
//        System.out.print(y[i]);
//        System.out.println("");
//    }
      AddEdge.addActionListener(new AddEdgeListener());
    }
    private class mouselistener implements MouseListener{
        
    @Override
    public void mouseClicked(MouseEvent e) {
//        int x=e.getX();
//    int y=e.getY();
//    System.out.println(x+","+y);
    }

    @Override
    public void mousePressed(MouseEvent e) {
       }

    @Override
    public void mouseReleased(MouseEvent e) {
       }

    @Override
    public void mouseEntered(MouseEvent e) {
     }

    @Override
    public void mouseExited(MouseEvent e) {
          }
    
    }
    
    private class AddEdgeListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
           if(Integer.parseInt(From.getText())<22 && Integer.parseInt(To.getText())<22 && Integer.parseInt(From.getText())>=0 && Integer.parseInt(To.getText())>=0) {  
          
               if(Integer.parseInt(Distance.getText())>=1000){
                   
           CountrySelectedFrom[CounterOfDrawing] =Integer.parseInt(From.getText());  
           CountrySelectedTo[CounterOfDrawing] = Integer.parseInt(To.getText());
           DistanceValue[CounterOfDrawing]=Integer.parseInt(Distance.getText());
           DistanceDraw[CounterOfDrawing] = Distance.getText();
           TotalDistances= TotalDistances+DistanceValue[CounterOfDrawing];

           if (TotalDistances<=20000){
           graph[CountrySelectedFrom[CounterOfDrawing]][CountrySelectedTo[CounterOfDrawing]]= DistanceValue[CounterOfDrawing];
           graph[CountrySelectedTo[CounterOfDrawing]][CountrySelectedFrom[CounterOfDrawing]]= DistanceValue[CounterOfDrawing];
           CounterOfDrawing++;  
           From.setText("");
           To.setText("");
           Distance.setText("");
           repaint();
             }
           else 
           {
               JOptionPane.showMessageDialog(null, "Total Distance Will Be More Than 20000");
                TotalDistances= TotalDistances-DistanceValue[CounterOfDrawing];
           }
               }
               else JOptionPane.showMessageDialog(null, "Distance Must be bigger than 1000");
           }
           else 
           {JOptionPane.showMessageDialog(null, "Enter From 0 to 21");
           }
           
          } 
            catch (java.lang.NumberFormatException w)
            {
            JOptionPane.showMessageDialog(null, "Enter Number");
            From.setText("");
            To.setText("");
            Distance.setText("");
            }
        }   
       
        }
        
    
    private class FindShortestPath implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
                
             Floyd = new FloydClass();
             Floyd.floydWarshall(graph,seq);
             System.out.println ("Sequence Matrix"); 
             for (int i=0;i<22;i++){
                 for (int j=0;j<22;j++){
                     if (seq[i][j]== 2147483647)
                    System.out.print("INF ");
                     else 
                         System.out.print(seq[i][j]+ " ");
                 }
             System.out.println("");
             }
       try { 
        if(Integer.parseInt(FromShortestPath.getText()) <22 && Integer.parseInt(ToShortestPath.getText())<22 && Integer.parseInt(ToShortestPath.getText())>=0 && Integer.parseInt(FromShortestPath.getText())>=0 ){   
        FromShortestPathValue= Integer.parseInt(FromShortestPath.getText());
        ToShortestPathValue=Integer.parseInt(ToShortestPath.getText());
        FromShortestPath.setText(" ");
        ToShortestPath.setText(" ");
        flag = Floyd.GetFinalPath(FromShortestPathValue,ToShortestPathValue,seq);
        if (flag== true){
        repaint();
        From.setEditable(false);
        To.setEditable(false);
        Distance.setEditable(false);
        FromShortestPath.setEditable(false);
        ToShortestPath.setEditable(false);
        }
        if (flag == false){
          JOptionPane.showMessageDialog(null,"No Path Available");
          From.setEditable(false);
          To.setEditable(false);
          Distance.setEditable(false);
          FromShortestPath.setEditable(false);
          ToShortestPath.setEditable(false);
          FromShortestPath.setText(" ");
          ToShortestPath.setText(" ");
        }
        }
        else {
        JOptionPane.showMessageDialog(null,"Range from 0 to 21");
        }
       }catch(java.lang.NumberFormatException w)
            {
            JOptionPane.showMessageDialog(null, "Enter Number");
            From.setEditable(false);
            To.setEditable(false);
            Distance.setEditable(false);
            FromShortestPath.setEditable(false);
            ToShortestPath.setEditable(false);
            FromShortestPath.setText(" ");
            ToShortestPath.setText(" ");
            }
        }
    
    }
      public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(3));
             for (int i=0;i<CounterOfDrawing;i++){
                    g2.setColor(Color.black);
                     g2.drawLine(x[CountrySelectedFrom[i]],y[CountrySelectedFrom[i]],x[CountrySelectedTo[i]],y[CountrySelectedTo[i]]);
                    newx[i] = (x[CountrySelectedFrom[i]]+x[CountrySelectedTo[i]])/2;
                    newy[i] =(y[CountrySelectedFrom[i]]+y[CountrySelectedTo[i]])/2;
                    
                     g.setFont(new Font("TimesRoman", Font.BOLD, 20));
                     g.setColor(Color.green);
                       g.drawString(DistanceDraw[i], newx[i]  , newy[i] );     
                }  
      if (flag == true)
      {
      int SizeOfResult = Floyd.FinalPath.size();
      g2.setColor(Color.red);
      for (int i=0;i<SizeOfResult-1;i++){
          int j=i+1;
      g2.drawLine(x[(int)Floyd.FinalPath.get(i)],y[(int)Floyd.FinalPath.get(i)],x[(int)Floyd.FinalPath.get(j)],y[(int)Floyd.FinalPath.get(j)]);
       
      }
      int NumberOfStops;
      NumberOfStops=Floyd.dist[FromShortestPathValue][ToShortestPathValue]/2000;
      String PrintedString = "Total Distance = "+ Floyd.dist[FromShortestPathValue][ToShortestPathValue] + " And Total Number Of Stops "+ NumberOfStops;
      
      g.drawString(PrintedString,20,740);
     
      }
      }
}