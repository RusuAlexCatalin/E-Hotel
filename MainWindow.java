package hotel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class MainWindow extends Frame{
     private Camera[] ap = new Camera[50]; 
  private int n;
    MainWindow(){
   Button b1=new Button("Inregistrare");
   Button b2=new Button("Plecare");
   b1.setBounds(30,80,100,30);
   b2.setBounds(160,80,100,30);
   add(b1);
   add(b2);
setLayout(null); 
setVisible(true);
 addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        });
  b1.addActionListener(new ActionListener(){  
    public void actionPerformed(ActionEvent e){ 
        
            Inregistrare i=new Inregistrare();
             i.pack();
 i.setBounds(300,200,450,500);
 i.setVisible(true);
dispose();
    }  
    });
   b2.addActionListener(new ActionListener(){  
    public void actionPerformed(ActionEvent e){  
            Plecare p=new Plecare();
             p.pack();
 p.setBounds(300,200,450,300);
 p.setVisible(true);
dispose();
    }  
    });
} 
 }


