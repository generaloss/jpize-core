#include "jpize_stb_freetype_FreeType.h"
#include <ft2build.h>
#include FT_FREETYPE_H
#include FT_STROKER_H

static FT_Error lastError = 0;

/*
 * Получение последнего кода ошибки FreeType
 */
JNIEXPORT jint JNICALL Java_jpize_stb_freetype_FreeType_getLastErrorCode
  (JNIEnv *, jclass) {
    return lastError;
}

/*
 * Инициализация библиотеки FreeType
 */
JNIEXPORT jlong JNICALL Java_jpize_stb_freetype_FreeType_initFreeType
  (JNIEnv *, jclass) {
    FT_Library library;
    lastError = FT_Init_FreeType(&library);
    if (lastError) {
        return 0; // Ошибка инициализации
    }
    return reinterpret_cast<jlong>(library);
}

/*
 * Создание шрифта из памяти
 */
JNIEXPORT jlong JNICALL Java_jpize_stb_freetype_FreeType_newMemoryFace
  (JNIEnv *env, jclass, jlong libraryPtr, jobject byteBuffer, jint bufferSize, jint faceIndex) {
    if (!libraryPtr || !byteBuffer) {
        return 0; // Проверка входных данных
    }

    FT_Library library = reinterpret_cast<FT_Library>(libraryPtr);
    FT_Face face;

    void* buffer = env->GetDirectBufferAddress(byteBuffer);
    if (!buffer) {
        return 0; // Ошибка получения данных из ByteBuffer
    }

    lastError = FT_New_Memory_Face(library, static_cast<const FT_Byte*>(buffer), bufferSize, faceIndex, &face);
    if (lastError) {
        return 0; // Ошибка создания face
    }

    return reinterpret_cast<jlong>(face);
}

/*
 * Создание FT_Stroker
 */
JNIEXPORT jlong JNICALL Java_jpize_stb_freetype_FreeType_strokerNew
  (JNIEnv *, jclass, jlong libraryPtr) {
    if (!libraryPtr) {
        return 0;
    }

    FT_Library library = reinterpret_cast<FT_Library>(libraryPtr);
    FT_Stroker stroker;

    lastError = FT_Stroker_New(library, &stroker);
    if (lastError) {
        return 0;
    }

    return reinterpret_cast<jlong>(stroker);
}

/*
 * Завершение работы с FreeType
 */
JNIEXPORT void JNICALL Java_jpize_stb_freetype_FreeType_doneFreeType
  (JNIEnv *, jclass, jlong libraryPtr) {
    if (!libraryPtr) {
        return;
    }

    FT_Library library = reinterpret_cast<FT_Library>(libraryPtr);
    FT_Done_FreeType(library);
}
