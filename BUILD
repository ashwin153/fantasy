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

####################################################################################################
#                                         Python Toolchain                                         #
#             https://github.com/kku1993/bazel-hermetic-python/blob/master/BUILD.bazel             #
####################################################################################################
load("@rules_python//python:defs.bzl", "py_runtime", "py_runtime_pair")

py_runtime(
    name = "python3_runtime",
    files = ["@python3_interpreter//:files"],
    interpreter = "@python3_interpreter//:python_bin",
    python_version = "PY3",
    visibility = ["//visibility:public"],
)

py_runtime_pair(
    name = "python_runtime_pair",
    py2_runtime = None,
    py3_runtime = ":python3_runtime",
)

toolchain(
    name = "python_toolchain",
    toolchain = ":python_runtime_pair",
    toolchain_type = "@bazel_tools//tools/python:toolchain_type",
)
