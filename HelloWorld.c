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
