cmake_minimum_required(VERSION 3.10)
project(jpize_freetype)

# platform
if (ANDROID)
    set(TARGET_OS "android")
elseif (WIN32)
    set(TARGET_OS "windows")
elseif (UNIX)
    set(TARGET_OS "linux")
endif()

# jdk headers
set(JAVA_HOME "/usr/lib/jvm/java-17-openjdk")
include_directories("${JAVA_HOME}/include")
include_directories("${JAVA_HOME}/include/linux") # Меняется для Windows/macOS

# find sources
file(GLOB_RECURSE SOURCE_FILES src/*.cpp)

# add freetype lib
find_package(Freetype REQUIRED)

# create SHARED library
add_library(jpize_freetype SHARED ${SOURCE_FILES})

# include headers
target_include_directories(jpize_freetype PRIVATE ${FREETYPE_INCLUDE_DIRS})
target_link_libraries(jpize_freetype PRIVATE ${FREETYPE_LIBRARIES})

if(ANDROID)
    set(CMAKE_TOOLCHAIN_FILE "${ANDROID_NDK}/build/cmake/android.toolchain.cmake")
    set(ANDROID_ABI "arm64-v8a")
    set(ANDROID_NATIVE_API_LEVEL 34)
endif()

# compilation options
if (TARGET_OS STREQUAL "android")
    message(STATUS "Compiling for Android")
    target_link_libraries(jpize_freetype PRIVATE android log)
elseif (TARGET_OS STREQUAL "windows")
    message(STATUS "Compiling for Windows")
    include_directories("${JAVA_HOME}/include/win32") # Для Windows
elseif (TARGET_OS STREQUAL "linux")
    message(STATUS "Compiling for Linux")
endif()
