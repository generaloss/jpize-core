cmake_minimum_required(VERSION 3.10)
project(jpize_freetype)

# Определяем платформу
if (ANDROID)
    set(TARGET_OS "android")
elseif (WIN32)
    set(TARGET_OS "windows")
elseif (UNIX)
    set(TARGET_OS "linux")
endif()

# Пути к заголовочным файлам JDK
set(JAVA_HOME "/usr/lib/jvm/java-17-openjdk")
include_directories("${JAVA_HOME}/include")
include_directories("${JAVA_HOME}/include/linux") # Меняется для Windows/macOS

# Добавляем исходники
set(SOURCE_FILES src/jpize_stb_freetype_FreeType.cpp)

# Добавляем FreeType
find_package(Freetype REQUIRED)

# Создаем динамическую библиотеку (JNI требует SHARED)
add_library(jpize_freetype SHARED ${SOURCE_FILES})

# Подключаем заголовочные файлы и библиотеки
target_include_directories(jpize_freetype PRIVATE ${FREETYPE_INCLUDE_DIRS})
target_link_libraries(jpize_freetype PRIVATE ${FREETYPE_LIBRARIES})

# Опции компиляции
if (TARGET_OS STREQUAL "android")
    message(STATUS "Compiling for Android")
    target_link_libraries(jpize_freetype PRIVATE android log)
elseif (TARGET_OS STREQUAL "windows")
    message(STATUS "Compiling for Windows")
    include_directories("${JAVA_HOME}/include/win32") # Для Windows
elseif (TARGET_OS STREQUAL "linux")
    message(STATUS "Compiling for Linux")
endif()
