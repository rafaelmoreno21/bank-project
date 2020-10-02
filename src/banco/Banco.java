/* Proyecto el cual simula a las operaciones realizadas dentro de una página de un banco, donde se debe de crear un usuario
Y luego iniciar sesión con el mismo. 

Se puede transferir de un usuario a otro (requiriendo de dos usuarios creados para dicha acción) y también se puede revisar
el estado de cuenta de cada usuario.

Se puede consultar los datos y el saldo de las cuentas mediante el menú, así como también las operaciones realizadas en
la sesión actual (Desaparecen al cerrar el proyecto)

Permite cambiar la contraseña del usuario.

Y también guarda todos los datos de los usuarios registrados al cerrar el programa, leyendolos al abrirlo.

El archivo debe de estar inicializado anteriormente con un número menor a 0 para poder funcionar, y sólo puede ser modificado
mediante NetBeans, puesto a que el NotePad añade un carácter invisible al número inicial que arruina el Integer.parseInt.


Johandry López 29.714.201
Rafael Moreno 28.467.527

*/
package banco;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Banco {
    // Se declaran las variables a utilizar próximamente
    static int n;
    // Los arrays representan todos los datos de cada usuario
    static String[] usuarios=new String[100];
    static String[] contraseñas=new String[100];
    static String[] nombres=new String[100];
    static String[] cedulas=new String[100];
    static String[] numeros=new String[100];
    static int[] saldos=new int[100]; 
    static String[] operaciones=new String[100];
    static String[] directiorios=new String[100];
    static String aux;
    static String contra;
    // Los "inicio" se utilizan para validar la existencia de un usuario al iniciar sesión o transferir.
    static String inicio;
    static String inicio2;
    static String inicio4;
    // La variable "opc" se utiliza al momento de realizar un switch
    static String opc;
    // La variable "sesion" se refiere al usuario actual, mientras que la "sesion2" se refiere al usuario a transferir, u auxiliar.
    static int sesion;
    static int sesion2;
    // La variable "monto" se refiere al monto a transferir.
    static String monto;
   
    static File archivo=new File("Usuarios.txt");
    static FileWriter escribir;
    static PrintWriter linea;
    static FileReader leer;
    static BufferedReader br;
    
    // El método Menu() se refiere al menú principal de inicio de sesión
    public static void Menu() {
          do {
        if (!archivo.exists()){
            
            try {
                archivo.createNewFile();
                escribir = new FileWriter(archivo,true);
                linea = new PrintWriter(escribir);
                linea.println(n);
                linea.close();
                escribir.close();
                
            } catch (IOException ex) {
                Logger.getLogger(Banco.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
          while (!archivo.exists());
       
        String[] options = {"Iniciar sesión", "Registrar Usuario", "Salir",};
        int seleccion = JOptionPane.showOptionDialog(null, "Bienvenido al sistema bancario.\n¿Qué desea realizar?", "BANCO ONLINE", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
        // El switch determina el menú siguiente, que puede ser para registrar o iniciar sesión, también para salir
        switch (seleccion) {
            
            default:
                Inicio();
                break;
            case 1:
                
                Registro();
               break;
                
            case 2:
                
                archivo.delete();
                JOptionPane.showMessageDialog(null,"¡Gracias por usar nuestro servicio!","¡Hasta luego!",JOptionPane.DEFAULT_OPTION);
               //Los datos que se resiven en consola son para verificar que todos los datos de los usuarios se estén recibiendo
               // correctamente.
                
                try{     
                archivo.createNewFile();
                escribir = new FileWriter(archivo,true);
                linea = new PrintWriter(escribir);
                linea.println(n);
                 for (int i=0;i<n;i++){
                
                linea.println(usuarios[i]);
                linea.println(contraseñas[i]);
                linea.println(nombres[i]);
                linea.println(cedulas[i]);
                linea.println(numeros[i]);
                linea.println(saldos[i]);
                
                if(i<n-1){
                linea.println();
                linea.println();}
                 }
                 linea.println("end");
                 linea.close();
                 escribir.close();
                 }
            catch (IOException ex) {};
            
            if (usuarios[0]==null) {
            try{   
            archivo.delete();
            archivo.createNewFile();
            escribir = new FileWriter(archivo,true);
            linea = new PrintWriter(escribir);
            linea.println("-1");
            linea.close();
            escribir.close();
            }
             catch (IOException ex) {};
            
            }
            

                break;
            
            
        }
        
    }
    
    public static void Registro(){

   // Variables utilizadas al momento de verificar datos
    boolean blanco=false;
    boolean entrada=true;
    
    
    
   
    
   
    
    
    do{
    
        entrada=true;
        do{
    usuarios[n] = JOptionPane.showInputDialog(null,"Ingrese su nombre de usuario","Nickname",JOptionPane.DEFAULT_OPTION);    
        }
        // El valor "null" se recibe al momento de presionar Cancelar o darle a la X en el cuadro de diálogo
        while (usuarios[n]==null);
       
    for (int i=0;i<usuarios[n].length();i++) {
      
            if (usuarios[n].charAt(i)==' ') {
            
            entrada=false;
            break;
            }
            else entrada=true;
              
        }
    // Se determinan los límites del usuario
    if (entrada==false)
        
    {
        
        JOptionPane.showMessageDialog(null,"No puede ingresar espacios","ERROR",JOptionPane.ERROR_MESSAGE);
        
    }
    
    if (usuarios[n].length()>20) 
    {
        
        JOptionPane.showMessageDialog(null,"Debe de ingresar un usuario de máximo 20 carácteres","ERROR",JOptionPane.ERROR_MESSAGE);
   
    }
    
    else if (usuarios[n].length()<5 && usuarios[n].length()>0)
    {
        
         JOptionPane.showMessageDialog(null,"Debe de ingresar un usuario de mínimo 5 carácteres","ERROR",JOptionPane.ERROR_MESSAGE);
        
    } 
    
    else if (usuarios[n].length()<1)
    {
        
         JOptionPane.showMessageDialog(null,"El espacio está en blanco!","ERROR",JOptionPane.ERROR_MESSAGE);
        
    } 
    
    
    
       
    }
    while ( usuarios[n].length()>20 || usuarios[n].length()<1|| usuarios[n].length()<5 && usuarios[n].length()>0 || entrada==false );
       
    //Contraseñas
    
    do{
    
        entrada=true;
        do {
    contraseñas[n] = JOptionPane.showInputDialog(null,"Ingrese su contraseña","Contraseña",JOptionPane.DEFAULT_OPTION);}
        while (contraseñas[n]==null);
    
    for (int i=0;i<contraseñas[n].length();i++) {
        
            if (contraseñas[n].charAt(i)==' ') {
            
            entrada=false;
            break;
            }
            else entrada=true;
              
        }
    
    if (entrada==false)
        
    {
        
        JOptionPane.showMessageDialog(null,"No puede ingresar espacios","ERROR",JOptionPane.ERROR_MESSAGE);
        
    }
    
    
    else if (contraseñas[n].length()<5 && contraseñas[n].length()>0)
    {
        
         JOptionPane.showMessageDialog(null,"Debe de ingresar una contraseña de mínimo 5 carácteres","ERROR",JOptionPane.ERROR_MESSAGE);
        
    } 
    
    else if (contraseñas[n].equals(""))
    {
        
         JOptionPane.showMessageDialog(null,"El espacio está en blanco!","ERROR",JOptionPane.ERROR_MESSAGE);
        
    } 
    
    
    
       
    }
    while (contraseñas[n].equals("") || contraseñas[n].length()<5 && contraseñas[n].length()>0 || entrada==false);
    
    // Nombre y Apellido del usuario
    do {
        entrada=true;
       
        do {
         blanco=false;
        nombres[n] = JOptionPane.showInputDialog(null,"Ingrese su nombre y apellido","Nombre ",JOptionPane.DEFAULT_OPTION);
        
        if (nombres[n].equals("")) 
        {
            
            JOptionPane.showMessageDialog(null,"El espacio está en blanco!","ERROR",JOptionPane.ERROR_MESSAGE);
            blanco=true;
        }
        }
        while (nombres[n]==null || blanco==true );
        
        // Se realiza la validación de datos de modo que no se ingresen números u algunos caracteres especiales
        for (int i=0;i<nombres[n].length();i++) {
        
            if (Character.isDigit(nombres[n].charAt(i))==true || nombres[n].charAt(i)=='.' || nombres[n].charAt(i)==',' || nombres[n].charAt(i)=='%') {
            
            entrada=false;
            break;
            }
            else entrada=true;
              
        }
    
         
        
        if (entrada==false || nombres[n].charAt(0)==' ')
        {
            
            JOptionPane.showMessageDialog(null,"Error! Ha ingresados carácteres numéricos!","Error!",JOptionPane.ERROR_MESSAGE);
            
        }
        
       
    }
    
    while (entrada==false  || nombres[n].charAt(0)==' ' );
    
    // Número de cédula
    do {
        entrada=true;
        do {
        cedulas[n] = JOptionPane.showInputDialog(null,"Ingrese su cédula","Cédula ",JOptionPane.DEFAULT_OPTION);    }
        while(cedulas[n]==null);
        // Se realiza la validación de datos de manera que no se ingresen letras
        for (int i=0;i<cedulas[n].length();i++) {
        
            if (Character.isDigit(cedulas[n].charAt(i))==false) {
            
            entrada=false;
            break;
            }
            else entrada=true;
              
        }
    
       
        
        if (entrada==false || cedulas[n].charAt(0)==' ')
        {
            
            JOptionPane.showMessageDialog(null,"Error! Ha ingresados carácteres no numéricos!","Error!",JOptionPane.ERROR_MESSAGE);
            
        }
        
       
    }
    
    while (entrada==false  || nombres[n].charAt(0)==' ' );
    /*"0105" representa al número del banco, "0617" representa el número de oficina, "34" Cuenta D.C, y todos los números consiguientes son el Cliente número de cuenta
    Por lo tanto al crear un nuevo usuario, será un número mayor al anterior*/
    numeros[n]="0150061743000000000"+(n+1);
    // Por último, la cuenta recibe 100.000 unidades de saldo al iniciar, para poder utilzar las demás opciones
    saldos[n]=100000;
    //La variable "n" se le suma un número para poder crear un usuario nuevo y que no reemplace al anterior
    n++;
    Menu();
    }
    
    public static void Inicio() {
        
        boolean inicio3=false;
        
        do {
        inicio = JOptionPane.showInputDialog(null,"Ingrese su nombre de usuario","Nickname",JOptionPane.DEFAULT_OPTION);}
        while (inicio==null || inicio.equals(""));
        
        do {
        inicio2 = JOptionPane.showInputDialog(null,"Ingrese su contraseña","Contraseña",JOptionPane.DEFAULT_OPTION);}
        while (inicio2==null || inicio2.equals(""));
        
        for (int i=0;i<n;i++)
        {
            //Se valida que ambos datos concuerden con el registro
           if (inicio.equals(usuarios[i]) && inicio2.equals(contraseñas[i]))
           {       
           
               inicio3=true;
               //Se le otorga un valor a la variable "sesion" haciendo referencia al usuario que está en línea
               sesion=i;
               break;
           
           }
           // De no concordar los datos, el inicio de sesión es falso
           else if (i==n && !inicio.equals(usuarios[i]))
               
           {
               
               inicio3=false;
               
           }
            
        }
        
        if (inicio3==true) 
        {
            
            JOptionPane.showMessageDialog(null,"Bienvenido al sistema "+nombres[sesion],"BANCO ONLINE",JOptionPane.DEFAULT_OPTION);
            Sesion();
        }
        
        else if (inicio3==false)
        {
            
            JOptionPane.showMessageDialog(null,"No se han encontrado usuarios que coincidan!","ERROR",JOptionPane.ERROR_MESSAGE);
            Menu(); 
        }
        
    }
    // Este método sería la página principal del banco
    public static void Sesion() {
       
        do {
        opc= JOptionPane.showInputDialog(null,"Elija la opción que desea realizar, estimado "+nombres[sesion]+"\n"
                                        +"                                              "+usuarios[sesion]+"\n"
                                        +"1.-Consultas.\n"
                                        +"2.-Transferencias.\n"
                                        +"3.-Cambiar contraseña.\n"
                                        +"4.-Cerrar Sesión\n","BANCO ONLINE",JOptionPane.DEFAULT_OPTION);
        }
        while (!"1".equals(opc) && !"2".equals(opc) && !"3".equals(opc) && !"4".equals(opc) && opc!=null);
        
        if (opc==null || opc.equals("4")) {
            
            JOptionPane.showMessageDialog(null,"Cerrando sesión...",null,JOptionPane.DEFAULT_OPTION);
            Menu();
        }
        
        switch (opc) {
        
            case "1":
                
                Consultas();
                break;
                
            case "2":
                
                Transferencias();
                break;
                
                
            case "3":
                
                Cambio();
                break;
            
        }
    }
    
    public static void Consultas() {
        
   do {
        opc= JOptionPane.showInputDialog(null,"Elija la opción que desea realizar, estimado "+nombres[sesion]+"\n"
                                        +"                                              "+usuarios[sesion]+"\n"
                                        +"1.-Estado de cuenta.\n"
                                        +"2.-Operaciones realizadas.\n"
                                        +"3.-Volver al menú anterior\n","BANCO ONLINE",JOptionPane.DEFAULT_OPTION);
        }
        while (!"1".equals(opc) && !"2".equals(opc) && !"3".equals(opc) && opc!=null);
        
        if (opc==null || opc.equals("3")) {
            Sesion();
        }
        // El estado actual de la cuenta utiliza los datos del usuario en línea, es decir usuario número "sesion"
        switch (opc) {
        
            case "1":
                
                JOptionPane.showMessageDialog(null,"Número de cuenta: "+numeros[sesion]+"\n"
                                                 + "Saldo actual: "+saldos[sesion]+"\n"
                                                 + "Nombre: "+nombres[sesion]+"\n"
                                                 + "Número de cédula:"+cedulas[sesion],"BANCO ONLINE",JOptionPane.DEFAULT_OPTION);
                Consultas();
                break;
                
            case "2":
                
                JOptionPane.showMessageDialog(null,operaciones[sesion],"BANCO ONLINE",JOptionPane.DEFAULT_OPTION);
                Consultas();
                break;
      
            
        }
        
    }
    
    public static void Transferencias(){
        boolean entrada=true;
         do {
        opc= JOptionPane.showInputDialog(null,"Elija la opción que desea realizar, estimado "+nombres[sesion]+"\n"
                                        +"                                              "+usuarios[sesion]+"\n"
                                        +"1.-Transferir a mismo banco.\n"
                                        +"2.-Volver al menú anterior\n","BANCO ONLINE",JOptionPane.DEFAULT_OPTION);
        }
        while (!"1".equals(opc) && !"2".equals(opc) && opc!=null);
        
        if (opc==null || opc.equals("2")) {
            Sesion();
        }
        
        switch (opc) {
        
            case "1":
                
        boolean inicio3=false;
        // Los datos introducidos abajo deben de ser exactamente iguales a los del registro
        do {
        inicio = JOptionPane.showInputDialog(null,"Ingrese el número de cuenta del usuario a transferir","Número de cuenta",JOptionPane.DEFAULT_OPTION);}
        while (inicio==null || inicio.equals(""));
        
        do {
        inicio2 = JOptionPane.showInputDialog(null,"Ingrese la cédula de identidad del usuario a transferir","Cédula de Identidad ",JOptionPane.DEFAULT_OPTION);}
        while (inicio2==null || inicio2.equals(""));
        
        do {
        inicio4 = JOptionPane.showInputDialog(null,"Ingrese el nombre del usuario a transferir","Nombre ",JOptionPane.DEFAULT_OPTION);}
        while (inicio4==null || inicio4.equals(""));
        
        do {
          entrada=true;
        monto = JOptionPane.showInputDialog(null,"Ingrese el monto a transferir","Monto ",JOptionPane.DEFAULT_OPTION);
        for (int i=0;i<monto.length();i++) {
        
            if (Character.isDigit(monto.charAt(i))==false) {
            
            entrada=false;
            break;
            }
            else entrada=true;
              
        }
    
       
        
        if (entrada==false || monto.charAt(0)==' ')
        {
            
            JOptionPane.showMessageDialog(null,"Error! Ha ingresados carácteres no numéricos!","Error!",JOptionPane.ERROR_MESSAGE);
            
        }
        }
        while (entrada==false || monto==null || monto.equals("")  );
        
        for (int i=0;i<n;i++)
        {
            // Se verifica que los datos concuerden con el registro
           if (inicio.equals(numeros[i]) && inicio2.equals(cedulas[i]) && inicio4.equals(nombres[i]))
           {       
           
               inicio3=true;
               // Ahora la variable "sesion2" consigue el valor de la persona a la cual se le transferirá
               sesion2=i;
               break;
           
           }
           
           else if (i==n && !inicio.equals(usuarios[i]))
               
           {
               
               inicio3=false;
               
           }
            
        }
       // Se verifica que la cuenta contenga el saldo correspondiente para realizar la operación
        if (Integer.parseInt(monto)>saldos[sesion])
        { 
            
            JOptionPane.showMessageDialog(null,"Usted no posee el saldo necesario para realizar esta operación.","ERROR!",JOptionPane.ERROR_MESSAGE);
            inicio3=false;
            }
        
        //Se realiza el intercambio de saldo entre los usuarios
        if (inicio3==true) 
        {
            saldos[sesion]=saldos[sesion]-Integer.parseInt(monto);
            saldos[sesion2]=saldos[sesion2]+Integer.parseInt(monto);
            if(operaciones[sesion]!=null) {
            operaciones[sesion]=operaciones[sesion]+"Salida de Bs. "+monto+" al usuario "+usuarios[sesion2]+"\n"+numeros[sesion2]+"\n\n";}
            else {operaciones[sesion]="Salida de Bs. "+monto+" al usuario "+usuarios[sesion2]+"\n"+numeros[sesion2]+"\n\n";}
            if(operaciones[sesion2]!=null){
            operaciones[sesion2]=operaciones[sesion2]+"Entrada de Bs. "+monto+" del usuario "+usuarios[sesion]+"\n"+numeros[sesion]+"\n\n";}
            else {operaciones[sesion2]="Entrada de Bs. "+monto+" del usuario "+usuarios[sesion]+"\n"+numeros[sesion]+"\n\n";}
            JOptionPane.showMessageDialog(null,"Transferencia exitosa","BANCO ONLINE",JOptionPane.DEFAULT_OPTION);
            Sesion();
        }
        
        else if (inicio3==false)
        {
            if(Integer.parseInt(monto)<saldos[sesion]) {
            JOptionPane.showMessageDialog(null,"No se han encontrado usuarios que coincidan!","ERROR",JOptionPane.ERROR_MESSAGE);}
            Transferencias();
        }
                
                break;
      
            
        }
        
        
    }
    
    public static void Cambio() {
        boolean entrada=true;
         do {
        opc= JOptionPane.showInputDialog(null,"Elija la opción que desea realizar, estimado "+nombres[sesion]+"\n"
                                        +"                                              "+usuarios[sesion]+"\n"
                                        +"1.-Cambiar contraseña.\n"
                                        +"2.-Volver al menú anterior.\n","BANCO ONLINE",JOptionPane.DEFAULT_OPTION);
        }
        while (!"1".equals(opc) && !"2".equals(opc) && opc!=null);
        
        if (opc==null || opc.equals("2")) {
            Sesion();
        }
        
        switch (opc) {
        
            case "1": 
                do {
                contra=JOptionPane.showInputDialog(null,"Introduzca su antigua contraseña","BANCO ONLINE",JOptionPane.DEFAULT_OPTION);}
                while (contra==null || contra.equals(""));
                
                if (!contra.equals(contraseñas[sesion])) {
                    
                    JOptionPane.showMessageDialog(null,"La contraseña introducida no es correcta!","ERROR!",JOptionPane.ERROR_MESSAGE);
                    Cambio();
                }
                
                else {
                    do{
    
        entrada=true;
        do {
    contraseñas[sesion] = JOptionPane.showInputDialog(null,"Ingrese su contraseña","Contraseña",JOptionPane.DEFAULT_OPTION);}
        while (contraseñas[sesion]==null);
    
    for (int i=0;i<contraseñas[sesion].length();i++) {
        
            if (contraseñas[sesion].charAt(i)==' ') {
            
            entrada=false;
            break;
            }
            else entrada=true;
              
        }
    
    if (entrada==false)
        
    {
        
        JOptionPane.showMessageDialog(null,"No puede ingresar espacios","ERROR",JOptionPane.ERROR_MESSAGE);
        
    }
    
    
    else if (contraseñas[sesion].length()<5 && contraseñas[sesion].length()>0)
    {
        
         JOptionPane.showMessageDialog(null,"Debe de ingresar una contraseña de mínimo 5 carácteres","ERROR",JOptionPane.ERROR_MESSAGE);
        
    } 
    
    else if (contraseñas[sesion].equals(""))
    {
        
         JOptionPane.showMessageDialog(null,"El espacio está en blanco!","ERROR",JOptionPane.ERROR_MESSAGE);
        
    } 
    
    
    
       
    }
    while (contraseñas[sesion].equals("") || contraseñas[sesion].length()<5 && contraseñas[sesion].length()>0 || entrada==false);
                   
                    JOptionPane.showMessageDialog(null,"La contraseña ha sido cambiada exitosamente","BANCO ONLINE",JOptionPane.DEFAULT_OPTION);
                    
                    Cambio();
                    
                }
                
                break;
        
        
    }
        
    }
    
    public static void main(String[] args) {
       
        try {
             leer = new FileReader(archivo);
             br = new BufferedReader(leer);
             aux=br.readLine();
             n=Integer.parseInt(aux);
                 br.close();
                 leer.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Banco.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(n>=0) {
            try{
                leer = new FileReader(archivo);
                br = new BufferedReader(leer);
                n=0;
                 do {
                br.readLine();
                usuarios[n]=br.readLine();
                contraseñas[n]=br.readLine();
                nombres[n]=br.readLine();
                cedulas[n]=br.readLine();
                numeros[n]=br.readLine();
                saldos[n]=Integer.parseInt(br.readLine());
                aux=br.readLine();
                n++;
                 }
        
                    while (!"end".equals(aux));
                 br.close();
                 leer.close();
            }
            catch (IOException ex) {};
            
        }
        
        else n=0;
        
        Menu();
    

    }
    
     
    
    
    
    
}
