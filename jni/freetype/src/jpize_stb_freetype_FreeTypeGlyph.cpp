#include "jpize_stb_freetype_FreeTypeGlyph.h"
#include <ft2build.h>
#include FT_FREETYPE_H

JNIEXPORT jlong JNICALL Java_jpize_stb_freetype_FreeTypeGlyph_toBitmap
  (JNIEnv *, jclass, jlong glyphPtr, jint mode) {
    FT_Glyph glyph = reinterpret_cast<FT_Glyph>(glyphPtr);
    if (FT_Glyph_To_Bitmap(&glyph, static_cast<FT_Render_Mode>(mode), nullptr, 1) == 0) {
        return reinterpret_cast<jlong>(glyph);
    }
    return 0;
}

JNIEXPORT jlong JNICALL Java_jpize_stb_freetype_FreeTypeGlyph_getBitmap
  (JNIEnv *, jclass, jlong glyphPtr) {
    return reinterpret_cast<jlong>(&reinterpret_cast<FT_BitmapGlyph>(glyphPtr)->bitmap);
}

JNIEXPORT jint JNICALL Java_jpize_stb_freetype_FreeTypeGlyph_getLeft
  (JNIEnv *, jclass, jlong glyphPtr) {
    return reinterpret_cast<FT_BitmapGlyph>(glyphPtr)->left;
}

JNIEXPORT jint JNICALL Java_jpize_stb_freetype_FreeTypeGlyph_getTop
  (JNIEnv *, jclass, jlong glyphPtr) {
    return reinterpret_cast<FT_BitmapGlyph>(glyphPtr)->top;
}

JNIEXPORT void JNICALL Java_jpize_stb_freetype_FreeTypeGlyph_done
  (JNIEnv *, jclass, jlong glyphPtr) {
    FT_Done_Glyph(reinterpret_cast<FT_Glyph>(glyphPtr));
}
