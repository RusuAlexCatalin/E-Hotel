package hotel;
public class Camera {
   private int nr,cinr,nrpersoane;
    private String stare,nume,prenume,serie,ds,dp;
    public Camera(int nr,String stare,String nume,String prenume,String serie,int cinr,String ds,String dp,int nrpersoane)
    {
        this.nr=nr;
        this.stare=stare;
       this.nume=nume;
       this.prenume=prenume;
       this.serie=serie;
       this.cinr=cinr;
       this.ds=ds;
       this.dp=dp;
       this.nrpersoane=nrpersoane;
        }
 @Override
 public String toString(){
  return String.format( "%s %s %s %s %s %s %s %s %s\n",nr,stare,nume,prenume,serie,cinr,ds,dp,nrpersoane);
 }
 public String afisaj(){
 return String.format( "%s %s\n",nr,stare );
 }
 public String getStare(){ return stare;}
 public String getNume(){ return nume;}
 public String getPrenume(){ return prenume;}
 public String getSeria(){ return serie;}
 public String getDs(){ return ds;}
 public String getDp(){ return dp;}
 public int getnr(){ return nr;}
 public int getcinr(){ return cinr;}
 public int getnrp(){ return nrpersoane;}
}