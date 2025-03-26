#include "jpize_stb_freetype_FreeTypeGlyphMetrics.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jint JNICALL Java_jpize_stb_freetype_FreeTypeGlyphMetrics_getWidth
  (JNIEnv *, jclass, jlong metricsPtr) {
    return reinterpret_cast<FT_Glyph_Metrics*>(metricsPtr)->width;
}

JNIEXPORT jint JNICALL Java_jpize_stb_freetype_FreeTypeGlyphMetrics_getHeight
  (JNIEnv *, jclass, jlong metricsPtr) {
    return reinterpret_cast<FT_Glyph_Metrics*>(metricsPtr)->height;
}

JNIEXPORT jint JNICALL Java_jpize_stb_freetype_FreeTypeGlyphMetrics_getHoriBearingX
  (JNIEnv *, jclass, jlong metricsPtr) {
    return reinterpret_cast<FT_Glyph_Metrics*>(metricsPtr)->horiBearingX;
}

JNIEXPORT jint JNICALL Java_jpize_stb_freetype_FreeTypeGlyphMetrics_getHoriBearingY
  (JNIEnv *, jclass, jlong metricsPtr) {
    return reinterpret_cast<FT_Glyph_Metrics*>(metricsPtr)->horiBearingY;
}

JNIEXPORT jint JNICALL Java_jpize_stb_freetype_FreeTypeGlyphMetrics_getHoriAdvance
  (JNIEnv *, jclass, jlong metricsPtr) {
    return reinterpret_cast<FT_Glyph_Metrics*>(metricsPtr)->horiAdvance;
}
