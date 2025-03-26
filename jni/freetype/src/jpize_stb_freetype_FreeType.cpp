#include "headers/jpize_stb_freetype_FreeType.h"
#include <ft2build.h>
#include FT_FREETYPE_H
#include FT_STROKER_H

static FT_Error lastError = 0;

JNIEXPORT jint JNICALL Java_jpize_stb_freetype_FreeType_getLastErrorCode (JNIEnv *, jclass) {
    return lastError;
}

JNIEXPORT jlong JNICALL Java_jpize_stb_freetype_FreeType_initFreeType (JNIEnv *, jclass) {
    FT_Library library;
    lastError = FT_Init_FreeType(&library);
    if(lastError)
        return 0;

    return reinterpret_cast<jlong>(library);
}

JNIEXPORT jlong JNICALL Java_jpize_stb_freetype_FreeType_newMemoryFace (
        JNIEnv *env, jclass, jlong libraryPtr, jobject byteBuffer, jint bufferSize, jint faceIndex) {
    if(!libraryPtr || !byteBuffer)
        return 0;

    FT_Library library = reinterpret_cast<FT_Library>(libraryPtr);
    FT_Face face;

    void* buffer = env->GetDirectBufferAddress(byteBuffer);
    if(!buffer)
        return 0;

    lastError = FT_New_Memory_Face(library, static_cast<const FT_Byte*>(buffer), bufferSize, faceIndex, &face);
    if(lastError)
        return 0;

    return reinterpret_cast<jlong>(face);
}

JNIEXPORT jlong JNICALL Java_jpize_stb_freetype_FreeType_strokerNew (JNIEnv *, jclass, jlong libraryPtr) {
    if(!libraryPtr)
        return 0;

    FT_Library library = reinterpret_cast<FT_Library>(libraryPtr);
    FT_Stroker stroker;

    lastError = FT_Stroker_New(library, &stroker);
    if(lastError)
        return 0;

    return reinterpret_cast<jlong>(stroker);
}

JNIEXPORT void JNICALL Java_jpize_stb_freetype_FreeType_doneFreeType (JNIEnv *, jclass, jlong libraryPtr) {
    if(!libraryPtr)
        return;

    FT_Library library = reinterpret_cast<FT_Library>(libraryPtr);
    FT_Done_FreeType(library);
}
