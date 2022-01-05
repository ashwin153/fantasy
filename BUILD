####################################################################################################
#                                           JNI Headers                                            #
#                         https://github.com/bazelbuild/bazel/issues/5497                          #
####################################################################################################
cc_library(
    name = "jni",
    linkstatic = 1,
    srcs = [
        "@bazel_tools//tools/jdk:jni_header",
    ] + select({
         "@bazel_tools//src/conditions:windows": [
            "@bazel_tools//tools/jdk:jni_md_header-windows",
        ],
        "@bazel_tools//src/conditions:darwin": [
            "@bazel_tools//tools/jdk:jni_md_header-darwin",
        ],
        "//conditions:default": [
            "@bazel_tools//tools/jdk:jni_md_header-linux",
        ],
    }),
    includes = [
        "external/bazel_tools/tools/jdk/include",
    ] + select({
        "@bazel_tools//src/conditions:windows": [
             "external/bazel_tools/tools/jdk/include/win32",
        ],
        "@bazel_tools//src/conditions:darwin": [
            "external/bazel_tools/tools/jdk/include/darwin",
        ],
        "//conditions:default": [
            "external/bazel_tools/tools/jdk/include/linux",
        ],
    }),
    visibility = [
        "//visibility:public",
    ],
)
