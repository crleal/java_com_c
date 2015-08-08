# java_com_c
Criando um lib em c para ser usada no Java

Eu precisei fazer uma rotina que integrasse uma lib em c com java. Abaixo mostrarei um passo-a-passo sem entrar muito em detalhes como consegui realizar isso.


1 - Programa exemplo em java:

    HelloWorld.java


2 - Compile o código para ver se não tem erro:

    javac HelloWorld.java  


3 - Gerar HelloWorld.h :

    javah -jni HelloWorld  

    Irá criar o arquivo:

    HelloWorld.h


4 - Programa exemplo em c:

    HelloWorld.c


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
