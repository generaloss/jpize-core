#include "jpize_stb_freetype_FreeTypeStroker.h"
#include <ft2build.h>
#include FT_FREETYPE_H
#include FT_STROKER_H

JNIEXPORT void JNICALL Java_jpize_stb_freetype_FreeTypeStroker_set (
        JNIEnv *, jclass, jlong strokerPtr, jint radius, jint lineCap, jint lineJoin, jint miterLimit) {
    FT_Stroker stroker = reinterpret_cast<FT_Stroker>(strokerPtr);
    FT_Stroker_Set(
        stroker,
        radius,
        static_cast<FT_Stroker_LineCap>(lineCap),
        static_cast<FT_Stroker_LineJoin>(lineJoin),
        miterLimit
    );
}

JNIEXPORT void JNICALL Java_jpize_stb_freetype_FreeTypeStroker_done (JNIEnv *, jclass, jlong strokerPtr) {
    FT_Stroker_Done(reinterpret_cast<FT_Stroker>(strokerPtr));
}