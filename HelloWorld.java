class HelloWorld {
     //Le a lib em C
     static {
         System.loadLibrary("HelloWorld");
     }
     
     // declara os metodos que serão chamados na lib em "c" 
     private native void print();
     private native double dobro(double x);

     public static void main(String[] args) {
         // Executa um metodo "c"
         new HelloWorld().print();
         // Chama um metodo passando parametro e retornando um valor
         double valor =  new HelloWorld().dobro(3);
         System.out.printf("O dobro de 3 é: "+String.valueOf(valor));
     }
}
