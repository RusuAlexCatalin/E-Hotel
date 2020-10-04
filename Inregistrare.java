package hotel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.* ;
import java.util.ArrayList;

public class Inregistrare extends Frame  {
  private Camera[] ap = new Camera[50]; 
  private int n,nr=0;
  private int []v;
  private String camere="";
   public  Inregistrare(){  
      Label lnume=new Label("Nume");
      TextField numetxt=new TextField();
      Label lprenume=new Label("Prenume");
      TextField prenumetxt=new TextField();
      Label lCi=new Label("Carte identitate:serie");
      TextField Citxt=new TextField();
      Label lnr=new Label("Numar");
      TextField nrtxt=new TextField();
      Label lds=new Label("Data Sosire");
      TextField dstxt=new TextField();
      Label ldp=new Label("Data plecare");
      TextField dptxt=new TextField();
      Label lnrp=new Label("Numar persoane");
      TextField nrptxt=new TextField();
      Label lnrc=new Label("Numarul camerei inchiriate");
      TextField nrctxt=new TextField();
      Choice c=new Choice();
       Button b=new Button("Inchiriaza");
       Label lMessage=new Label();
lnume.setBounds(30,100,120,30);
numetxt.setBounds(200,100,120,20);
lprenume.setBounds(30,130,120,30);
prenumetxt.setBounds(200,130,120,20);
lCi.setBounds(30,160,120,30);
Citxt.setBounds(200,160,60,20);
lnr.setBounds(280,160,60,20);
nrtxt.setBounds(350,160,60,20);
lds.setBounds(30,190,120,30);
dstxt.setBounds(200,190,120,20);
ldp.setBounds(30,220,120,30);
dptxt.setBounds(200,220,120,20);
lnrp.setBounds(30,250,120,30);
nrptxt.setBounds(200,250,120,20);
lnrc.setBounds(30,280,150,30);
nrctxt.setBounds(200,280,120,20);
c.setBounds(350,280,50,20);
b.setBounds(200,400,120,30);
lMessage.setBounds(200,350,200,30);
add(lnume);
add(numetxt);
add(lprenume);
add(lCi);
add(lds);
add(ldp);
add(lnrp);
add(lnrc);
add(lnr);
add(prenumetxt);
add(Citxt);
add(dstxt);
add(dptxt);
add(nrptxt);
add(nrctxt);
add(nrtxt);
add(lMessage);
lMessage.setForeground(Color.RED);
loadFile();
c.add("");
for(int i=0;i<50;i++)
{if(ap[i].getStare().equals("liber"))
c.add(String.valueOf(i+1));
}
add(c);
add(b);
setLayout(null); 
setVisible(true);
   addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) { MainWindow m=new MainWindow();
             m.pack();
 m.setBounds(300,200,300,150);
 m.setVisible(true); 
                dispose();  
            }  
        });
    c.addItemListener(new ItemListener(){  
        public void itemStateChanged(ItemEvent ie){
           if(camere=="") camere=camere+c.getSelectedItem();
           else  camere=camere+","+c.getSelectedItem();
            nrctxt.setText(camere);
            c.remove(c.getSelectedItem());
        }  
    });
    
   b.addActionListener(new ActionListener(){ 
    public void actionPerformed(ActionEvent e){ double s;String[] d1,d2;int i=0;
    if (numetxt.getText().equals("")){lMessage.setText("Completeaza numele");return;}
    if(!(numetxt.getText().matches("[A-z]+"))){lMessage.setText("Nume gresit");return;}
                if (prenumetxt.getText().equals("")){lMessage.setText("Completeaza prenumele");return;}
                 if(!(prenumetxt.getText().matches("[A-z]+"))){lMessage.setText("Prenume gresit");return;}
                if (Citxt.getText().equals("")){lMessage.setText("Completeaza seria");return;}
                 if(!(Citxt.getText().matches("[A-z]+"))){lMessage.setText("Serie gresita");return;}
                if (nrtxt.getText().equals("")){lMessage.setText("Completeaza numarul");return;}
                try {s=Double.parseDouble(nrtxt.getText());}
 catch(NumberFormatException ex){lMessage.setText("Numar gresit!");return;}
                if (dstxt.getText().equals("")){lMessage.setText("Completeaza data de sorire");return;}
                d1=dstxt.getText().split("/");
                if (dptxt.getText().equals("")){lMessage.setText("Completeaza data de plecare");return;}
                d2=dptxt.getText().split("/");
                if(Integer.parseInt(d1[2])>Integer.parseInt(d2[2])){lMessage.setText("Data sosire/Data plecare gresita");return;}
                else if(Integer.parseInt(d1[1])>Integer.parseInt(d2[1])){lMessage.setText("Data sosire/Data plecare gresita");return;}
                else if(Integer.parseInt(d1[1])==Integer.parseInt(d2[1]) && Integer.parseInt(d1[0])>Integer.parseInt(d2[0]) ){lMessage.setText("Data sosire/Data plecare gresita");return;}
                if (nrptxt.getText().equals("")){lMessage.setText("Completeaza numarul de persoane");return;}
                try {s=Double.parseDouble(nrptxt.getText());}
 catch(NumberFormatException ex){lMessage.setText("Numar persoane gresit!");return;}
                 lMessage.setText("");
                 camere=nrctxt.getText();
            String[] t;
            t=camere.split(",");
            nr=t.length;
                for(i=0;i<nr;i++)
                {int j=Integer.parseInt(t[i]);
                 ap[j-1]=new Camera(j,"ocupat",numetxt.getText(),prenumetxt.getText(),Citxt.getText(),Integer.parseInt(nrtxt.getText()),dstxt.getText(),dptxt.getText(),Integer.parseInt(nrptxt.getText()));
                  }
               try {
            writeFile();
        } catch (IOException ex) {
            Logger.getLogger(Inregistrare.class.getName()).log(Level.SEVERE, null, ex);
        }     
}
   });
 }
   public void loadFile()
 {
     {
 Scanner input=null;
 String p;
 String[] t;
 try {
 input = new Scanner( new File("inregistrari.txt"));
 }
 catch ( FileNotFoundException e) {
 System.err.println("Error open file"); System.exit(1);
 } 
 while(input.hasNext()){
 p = input.nextLine();
 t = p.split(" ");
 if(t[1].equals("ocupat")) 
     ap[n++]=new Camera(Integer.parseInt(t[0]),t[1],t[2],t[3],t[4],Integer.parseInt(t[5]),t[6],t[7],Integer.parseInt(t[8]));
 else
     ap[n++]=new CameraLibera(Integer.parseInt(t[0]),t[1]);
}
}
     }
    public void writeFile() throws IOException 
   {
    FileWriter  fileWriter      = new FileWriter("inregistrari.txt");
    PrintWriter printWriter = new PrintWriter(fileWriter);
   for(int i=0;i<50;i++)
    printWriter.print(ap[i]);
    printWriter.close();
}
}
    
