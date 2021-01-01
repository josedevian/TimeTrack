//
// Created by Jose Devian on 1/1/21.
//

#include <string.h>
#include <jni.h>

JNIEXPORT jint JNICALL
Java_id_ac_ui_cs_mobileprogramming_josedevian_timetrack_MainActivity_increaseTaskCount(JNIEnv *env, jobject thiz, jint count) {
    count = count + 1;
    return count;
}
