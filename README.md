# java_com_c
Criando um lib em c para ser usada no Java

Eu precisei fazer uma rotina que integrasse uma lib em c com java. Abaixo mostrarei um passo-a-passo sem entrar muito em detalhes como consegui realizar isso.


1 - Programa exemplo em java:

HelloWorld.java

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
       double valor = new HelloWorld().dobro(3);  
       System.out.printf("O dobro de 3 é: "+String.valueOf(valor));  
     }  
}  



2 - Compile o código para ver se não tem erro:

1:  javac HelloWorld.java  



3 - Gerar HelloWorld.h :

javah -jni HelloWorld  


Irá criar o arquivo:

HelloWorld.h

/* DO NOT EDIT THIS FILE - it is machine generated */  
#include <jni.h>  
/* Header for class HelloWorld */  
  
#ifndef _Included_HelloWorld  
#define _Included_HelloWorld  
#ifdef __cplusplus  
extern "C" {  
#endif  
/*  
 * Class:   HelloWorld  
 * Method:  print  
 * Signature: ()V  
 */  
 JNIEXPORT void JNICALL Java_HelloWorld_print (JNIEnv *, jobject);  
    
 /*  
  * Class:   HelloWorld  
  * Method:  dobro  
  * Signature: (D)D  
  */  
  JNIEXPORT jdouble JNICALL Java_HelloWorld_dobro (JNIEnv *, jobject, jdouble);  
    
  #ifdef __cplusplus  
 }  
 #endif  
 #endif  



4 - Programa exemplo em c:

 HelloWorld.c

#include <jni.h>  
#include <stdio.h>  
#include "HelloWorld.h"  
     
JNIEXPORT void JNICALL Java_HelloWorld_print (JNIEnv *env, jobject obj)  
{  
   printf("Hello World!\n");  
   return;  
}  
    
JNIEXPORT double JNICALL Java_HelloWorld_dobro (JNIEnv *env, jobject obj, jdouble x)  
{  
    printf("Metodo dobro!\n");  
    return x * 2;  
}  



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
