package hotel;
public class CameraLibera extends Camera {
public CameraLibera(int nr,String stare)
    {
        super(nr,stare,null,null,null,0,null,null,0);
     }
@Override
 public String toString(){
 return String.format( afisaj() );
 }
}

 
    

