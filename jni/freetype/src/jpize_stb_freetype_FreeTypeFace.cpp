#include "jpize_stb_freetype_FreeTypeFace.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jint JNICALL Java_jpize_stb_freetype_FreeTypeFace_getFaceFlags
  (JNIEnv *, jclass, jlong facePtr) {
    return reinterpret_cast<FT_Face>(facePtr)->face_flags;
}

JNIEXPORT jint JNICALL Java_jpize_stb_freetype_FreeTypeFace_getStyleFlags
  (JNIEnv *, jclass, jlong facePtr) {
    return reinterpret_cast<FT_Face>(facePtr)->style_flags;
}

JNIEXPORT jint JNICALL Java_jpize_stb_freetype_FreeTypeFace_getNumGlyphs
  (JNIEnv *, jclass, jlong facePtr) {
    return reinterpret_cast<FT_Face>(facePtr)->num_glyphs;
}

JNIEXPORT jint JNICALL Java_jpize_stb_freetype_FreeTypeFace_getAscender
  (JNIEnv *, jclass, jlong facePtr) {
    return reinterpret_cast<FT_Face>(facePtr)->ascender;
}

JNIEXPORT jint JNICALL Java_jpize_stb_freetype_FreeTypeFace_getDescender
  (JNIEnv *, jclass, jlong facePtr) {
    return reinterpret_cast<FT_Face>(facePtr)->descender;
}

JNIEXPORT jint JNICALL Java_jpize_stb_freetype_FreeTypeFace_getHeight
  (JNIEnv *, jclass, jlong facePtr) {
    return reinterpret_cast<FT_Face>(facePtr)->height;
}

JNIEXPORT jboolean JNICALL Java_jpize_stb_freetype_FreeTypeFace_loadGlyph
  (JNIEnv *, jclass, jlong facePtr, jint glyphIndex, jint loadFlags) {
    return FT_Load_Glyph(reinterpret_cast<FT_Face>(facePtr), glyphIndex, loadFlags) == 0;
}

JNIEXPORT jlong JNICALL Java_jpize_stb_freetype_FreeTypeFace_getGlyph
  (JNIEnv *, jclass, jlong facePtr) {
    return reinterpret_cast<jlong>(reinterpret_cast<FT_Face>(facePtr)->glyph);
}

JNIEXPORT void JNICALL Java_jpize_stb_freetype_FreeTypeFace_doneFace
  (JNIEnv *, jclass, jlong facePtr) {
    FT_Done_Face(reinterpret_cast<FT_Face>(facePtr));
}
