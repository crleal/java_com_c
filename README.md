# java_com_c
Criando um lib em c para ser usada no Java

Eu precisei fazer uma rotina que integrasse uma lib em c com java. Abaixo mostrarei um passo-a-passo sem entrar muito em detalhes como consegui realizar isso.


1 - Programa exemplo em java:

HelloWorld.java

1:  class HelloWorld {  
2:     //Le a lib em C  
3:     static {  
4:       System.loadLibrary("HelloWorld");  
5:     }  
6:       
7:     // declara os metodos que serão chamados na lib em "c"   
8:     private native void print();  
9:     private native double dobro(double x);  
10:    
11:     public static void main(String[] args) {  
12:       // Executa um metodo "c"  
13:       new HelloWorld().print();  
14:       // Chama um metodo passando parametro e retornando um valor  
15:       double valor = new HelloWorld().dobro(3);  
16:       System.out.printf("O dobro de 3 é: "+String.valueOf(valor));  
17:     }  
18:  }  



2 - Compile o código para ver se não tem erro:

1:  javac HelloWorld.java  



3 - Gerar HelloWorld.h :

1:    javah -jni HelloWorld  


Irá criar o arquivo:

HelloWorld.h

1:  /* DO NOT EDIT THIS FILE - it is machine generated */  
2:  #include <jni.h>  
3:  /* Header for class HelloWorld */  
4:    
5:  #ifndef _Included_HelloWorld  
6:  #define _Included_HelloWorld  
7:  #ifdef __cplusplus  
8:  extern "C" {  
9:  #endif  
10:  /*  
11:   * Class:   HelloWorld  
12:   * Method:  print  
13:   * Signature: ()V  
14:   */  
15:  JNIEXPORT void JNICALL Java_HelloWorld_print  
16:   (JNIEnv *, jobject);  
17:    
18:  /*  
19:   * Class:   HelloWorld  
20:   * Method:  dobro  
21:   * Signature: (D)D  
22:   */  
23:  JNIEXPORT jdouble JNICALL Java_HelloWorld_dobro  
24:   (JNIEnv *, jobject, jdouble);  
25:    
26:  #ifdef __cplusplus  
27:  }  
28:  #endif  
29:  #endif  



4 - Programa exemplo em c:

 HelloWorld.c

1:  #include <jni.h>  
2:  #include <stdio.h>  
3:  #include "HelloWorld.h"  
4:     
5:  JNIEXPORT void JNICALL Java_HelloWorld_print (JNIEnv *env, jobject obj)  
6:  {  
7:     printf("Hello World!\n");  
8:     return;  
9:  }  
10:    
11:  JNIEXPORT double JNICALL Java_HelloWorld_dobro (JNIEnv *env, jobject obj, jdouble x)  
12:  {  
13:     printf("Metodo dobro!\n");  
14:     return x * 2;  
15:  }  



5 - Compilando o HelloWorld.c:

gcc  -I/usr/lib/jvm/java-8-oracle/include -I/usr/lib/jvm/java-8-oracle/include/linux -c -Wall -Werror -fpic HelloWorld.c  


Irá criar o arquivo: HelloWorld.o


6 - Gerando a lib:

gcc -shared -o libHelloWorld.so HelloWorld.o  


Irá criar o arquivo: libHelloWorld.so


7 - Executando o java:

 java -Djava.library.path=. HelloWorld  


Resultado:

 Hello World!  
 Metodo dobro!  
 O dobro de 3 é: 6.0  
