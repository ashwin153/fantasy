licenses(["notice"])
exports_files(["LICENSE"])

####################################################################################################
#                                          GLPK for Java                                           #
#                                http://glpk-java.sourceforge.net/                                 #
####################################################################################################
java_library(
    name = "glpk",
    srcs = glob([
        "src/main/java/**/*.java",
    ]),
    data = select({
        "@bazel_tools//src/conditions:windows": [
            ":glpk_java.dll",
        ],
        "@bazel_tools//src/conditions:darwin": [
            ":libglpk_java.dylib",
        ],
        "//conditions:default": [
            ":libglpk_java.so",
        ],
    }),
    visibility = [
        "//visibility:public",
    ],
)

####################################################################################################
#                                       glpk_java for macOS                                        #
#             System::loadLibrary on macOS looks for lib$NAME.jnilib or lib$NAME.dylib.            #
####################################################################################################
cc_binary(
    name = "libglpk_java.dylib",
    linkstatic = 0,
    linkshared = 1,
    deps = [
        ":glpk_jni",
    ],
)

####################################################################################################
#                                       glpk_java for Linux                                        #
#                       System::loadLibrary on linux looks for lib$NAME.so.                        #
####################################################################################################
cc_binary(
    name = "libglpk_java.so",
    linkstatic = 0,
    linkshared = 1,
    deps = [
        ":glpk_jni",
    ],
)

####################################################################################################
#                                      glpk_java for Windows                                       #
#                        System::loadLibrary on linux looks for $NAME.dll.                         #
####################################################################################################
cc_binary(
    name = "glpk_java.dll",
    linkstatic = 0,
    linkshared = 1,
    deps = [
        ":glpk_jni",
    ],
)

####################################################################################################
#                                           JNI Wrapper                                            #
#             Dynamically linked library that binds to the native GLPK implementation.             #
####################################################################################################
cc_library(
    name = "glpk_jni",
    srcs = glob([
        "src/main/c/*.c",
        "src/main/c/*.h",
    ]),
    deps = [
        "//:jni",
        "//third_party/c/glpk",
    ],
    copts = [
        "-Ithird_party/c/glpk/src",
    ],
    includes = [
        "src/main/c",
    ],
)
