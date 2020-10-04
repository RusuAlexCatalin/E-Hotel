package hotel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;   
public class Plecare extends Frame {
    private Camera[] ap = new Camera[50]; 
     private int n,pret,suma,cinr;
     private String dataCurenta,factura="",serie;
    TextArea area=new TextArea();
    Plecare(){
        dataCurenta=DataCurenta();
        loadFile();
        Button b3=new Button("Memoreaza");
        area=new TextArea("Nu exista plecari");
        for(int i=0;i<50;i++)
 {if(ap[i].getStare().equals("ocupat"))
   {if(ap[i].getDp().equals(dataCurenta))
      serie=ap[i].getSeria();
          cinr=ap[i].getcinr();
          factura=factura+ap[i].getNume()+" "+ap[i].getPrenume()+"\n"+"C.I: "+ap[i].getSeria()+" "+ap[i].getcinr()+"\n"+"Perioada inregistare:"+ap[i].getDs()+"-"+ap[i].getDp()+"\n"+"Numar persoane:"+ap[i].getnrp()+"\n";
          suma=0;
          Cauta();
       }
 }
       if(factura!="")
       {
           area=new TextArea(factura);
        }
       area.setBounds(20,50, 250,200); 
        b3.setBounds(290,50,120,30);
        add(b3);
        add(area);
        area.setEditable(false);
        setLayout(null); 
        setVisible(true);
    addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                try {
                    writeFile();
                } catch (IOException ex) {
                    Logger.getLogger(Plecare.class.getName()).log(Level.SEVERE, null, ex);
                }
                MainWindow m=new MainWindow();
             m.pack();
 m.setBounds(300,200,300,150);
 m.setVisible(true);
                dispose();  
            }  
        });
     b3.addActionListener(new ActionListener(){  
    public void actionPerformed(ActionEvent e){ 
        try {
            writeFactura();
        } catch (IOException ex) {
            Logger.getLogger(Plecare.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    });
    }
     public void writeFactura() throws IOException 
   {
    FileWriter  fileWriter      = new FileWriter("facturi.txt");
    PrintWriter printWriter = new PrintWriter(fileWriter);
    printWriter.print(area.getText());
    printWriter.close();
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
 public void Cauta()
 {
 for(int i=0;i<50;i++)
 {if(ap[i].getStare().equals("ocupat"))
   {if(ap[i].getDp().equals(dataCurenta))
     { if(ap[i].getSeria().equals(serie) && ap[i].getcinr()==(cinr))
     {Calculeaza(ap[i].getDs(),ap[i].getDp(),ap[i].getnr());
     ap[i]=new CameraLibera(i+1,"liber");
     }
    }
   }
 }
 factura=factura+"Plata:"+suma+" RON\n\n";
}
 public int Calculeaza(String ds,String dp,int nrcamera)
 {pret=0;
 String[] v1,v2;
int diferenta,zi1,luna1,an1,zi2,luna2,an2,d=0,i=1;
         v1=ds.split("/");
         v2=dp.split("/");
         zi1=Integer.parseInt(v1[0]);
         luna1=Integer.parseInt(v1[1]);
         an1=Integer.parseInt(v1[2]);
         zi2=Integer.parseInt(v2[0]);
         luna2=Integer.parseInt(v2[1]);
         an2=Integer.parseInt(v2[2]);
         if(zi2<zi1 || luna2<luna1)
         {if(an2>an1) luna2=luna2+12;
         if(luna2>luna1) d=luna2-luna1;
             while(i<=d)
             {   if(luna1>12) luna1=luna1-12;
                 if(luna1==1 || luna1==3 || luna1==5 || luna1==7 || luna1==8 ||luna1==10 || luna1==12) zi2=zi2+31;
         if(luna1==4 || luna1==6 || luna1==9 || luna1==11) zi2=zi2+30;
         if(luna1==2)
         {
             if(an2%4==0 && an2%100==0 || an2%400==0) zi2=zi2+29;
             else  zi2=zi2+28;
         }
         luna1++;
         i++;
             }
             }
         diferenta=zi2-zi1;
             if(nrcamera%2==0) pret=pret +50;
             else pret=pret +25;
             pret=pret*diferenta;
             suma=suma+pret;
        return suma;
 }
 public String DataCurenta() {    
     
   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
   LocalDateTime now = LocalDateTime.now();  
   return (dtf.format(now));  
     
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
   