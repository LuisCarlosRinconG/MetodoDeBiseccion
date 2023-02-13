package AnalisisNumerico;

import com.singularsys.jep.JepException;
import org.nfunk.jep.JEP;

//Desarrlloar un problema que encuentre raices mediante el metodo de biseccion 
public class Ejercicio {
    
    //declaramos las variables que vamos a utilizar
    JEP jep;
    //la funcion que va a recibir
    private String entrada="";
    private double resultado = 0.0; 
    private String error;
    private double errorOperacion;
    private double error1;
    
    //variables para el metodo de falsa posicion
    private double xi ;
    private double xu ;
    private double xr;
    private double Fxi;
    private double Fxu;
    private double Fxr;
    private double test;
    private double xrAnterior;
    
public Ejercicio(){
        
    }


//recibe los valores del intervalo a
    public void setIntervaloA(double xi){
        this.xi= xi;
    }
    
//recibe los valores del intervalo a
    public void setIntervaloB(double xu){
        this.xu= xu;
    }
    
    
    public void setFuncion(String entrada){
        this.entrada = entrada;
    }

    
    public double getResultadoFuncion(){

        return this.resultado;
    }
    
    public String getError(){
        return this.error;
    }
    
    
    public double getErrorOperacion(){
        return this.errorOperacion;
    }
    

public void evaluar() throws JepException{
    jep = new JEP();
    //añade los valores de pi, euler, funciones trigonometricas, etc.
        this.jep.addStandardFunctions();
        this.jep.addStandardConstants();
        
     //obtiene la funcion digitada por el usuario
        this.jep.parseExpression(this.entrada);
        
        
        // evaluamos los intervalos xi y xu en la funcion para obtener Fxi y Fxu 
        this.jep.addVariable("x", this.xi);
        Fxi= this.jep.getValue();
        this.jep.addVariable("x", this.xu);
        Fxu = this.jep.getValue();
        
        xr= xu-((Fxu)*(xi-xu))/(Fxi-Fxu);
        
        this.jep.addVariable("x", xr);
        Fxr = this.jep.getValue();
        
     
       test = xi*xr;
       
       //inicio del while 
       do{
           xrAnterior=xr;
       
       if(test < 0){
       
         xi=xr;
         
         this.jep.addVariable("x", this.xi);
        Fxi= this.jep.getValue();
        this.jep.addVariable("x", this.xu);
        Fxu = this.jep.getValue();
        
        xr= xu-((Fxu)*(xi-xu))/(Fxi-Fxu);
        
        this.jep.addVariable("x", xr);
        Fxr = this.jep.getValue();
       
        test = xi*xr;
        
        error1=((xr-xrAnterior)/xr) * 100;
        
       }
               else{
         xu=xr;
         
         this.jep.addVariable("x", this.xi);
        Fxi= this.jep.getValue();
        this.jep.addVariable("x", this.xu);
        Fxu = this.jep.getValue();
        
        xr= xu-((Fxu)*(xi-xu))/(Fxi-Fxu);
        
        this.jep.addVariable("x", xr);
        Fxr = this.jep.getValue();
        
        test = xi*xr;
       }

}while(error1==0.0001); //fin del do while 
       
       
        this.errorOperacion=error1;
            
             this.resultado = xr;
        
        //captura el error
        this.error = (this.jep.hasError())? "Existe un error, revisa tus entradas de datos.":"Solucion exitosa.";
    }

            
          
}





    /**
    public void evaluar() throws JepException{
        jep = new JEP();
        //añade los valores de pi, euler, funciones trigonometricas, etc.
        this.jep.addStandardFunctions();
        this.jep.addStandardConstants();
        
         //obtiene la funcion digitada por el usuario
        this.jep.parseExpression(this.entrada);
        
                 //intervalos

              
        this.jep.addVariable("x", this.xi);
        Fxi= this.jep.getValue();
        this.jep.addVariable("x", this.xu);
        Fxu = this.jep.getValue();
        this.jep.addVariable("x", xr);
        xr= this.jep.getValue();
        
        System.out.println("El punto a es : " + aAnterior);
        System.out.println("El punto b es: " + bAnterior);
        System.out.println("El intervalo a evaluado en la funcion: " + Fxi);
        System.out.println("El intervalo b evaluado en la funcion: " + Fxr);
        System.out.println("El punto medio evaluado en la funcion es: " + Fxr);
        System.out.println("El punto medio es " + Pmedio);
        System.out.println("\n");
        
        //el bucle se repetira hasta que el error sea de 0.0001 dando el resultado buscado

            
        a=aAnterior;
        b=bAnterior;
         for(int i=0;i<=18;i++){
        //se evalua que la funcion del intertvalo a y b esten dados por uno negativo y uno positivo de lo contrario cambiara el valor de intervalos
      
        this.jep.addVariable("x", a);
        Fa= this.jep.getValue();
        this.jep.addVariable("x", b);
        Fb= this.jep.getValue();
        this.jep.addVariable("x", Pmedio);
        FPmedio= this.jep.getValue();
        
        if((Fa>0 && Fb<0) || (Fa<0 && Fb>0)){
        a=bAnterior;
        b=Pmedio;
        this.jep.addVariable("x", a);
        Fa= this.jep.getValue();
        this.jep.addVariable("x", b);
        Fb= this.jep.getValue();
        this.jep.addVariable("x", Pmedio);
        FPmedio= this.jep.getValue();
        
        if((Fa<0 && Fb<0) || (Fa>0 && Fb>0)){
        b=aAnterior;
        a=Pmedio;
        }
        }
        
        else if((Fa>0 && Fb<0) || (Fa<0 && Fb>0)){
        b=aAnterior;
        a=Pmedio;
        this.jep.addVariable("x", a);
        Fa= this.jep.getValue();
        this.jep.addVariable("x", b);
        Fb= this.jep.getValue();
        this.jep.addVariable("x", Pmedio);
        FPmedio= this.jep.getValue();
        
        if((Fa<0 && Fb<0) || (Fa>0 && Fb>0)){
        b=bAnterior;
        a=Pmedio;
        }
        }
        
        aAnterior=a;
        bAnterior=b;
        
        Pmedio=(aAnterior+bAnterior)/2;
        
        
        error1=((Pmedio-a)/Pmedio) * 100;
        
            System.out.println("El punto a es a es: " + aAnterior);
            System.out.println("El punto b es: " + bAnterior);
            System.out.println("El intervalo a evaluado en la funcion: " + Fa);
            System.out.println("El intervalo b evaluado en la funcion: " + Fb);
            System.out.println("El punto medio evaluado en la funcion es: " + FPmedio);
            System.out.println("El punto medio es " + Pmedio);
            System.out.println("El error es: " + error1);
            System.out.println("\n");

           
    }// Fin del bucle
             this.errorOperacion=error1;
            
             this.resultado = Pmedio;
        
        //captura el error
        this.error = (this.jep.hasError())? "Existe un error, revisa tus entradas de datos.":"Solucion exitosa.";
          
        }*/