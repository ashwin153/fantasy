scala_library(
    name = "optimize",
    srcs = glob([
        "src/main/scala/**/*.scala",
    ]),
    deps = [
        "//third_party/jvm/glpk",
    ],
    visibility = [
        "//visibility:public",
    ],
)

scala_test(
    name = "tests",
    srcs = glob([
        "src/test/scala/**/*.scala",
    ]),
    deps = [
        "//fantasy/optimize",
        "//third_party/jvm/glpk",
    ],
    jvm_flags = [
        # Executable Scala rules do not set the java.library.path. As a workaround, explicitly set
        # the java.library.path to the directory containing the generated *.dylib and *.so files.
        # For some reason, the $RUNFILES_DIR is set to an invalid path when building in sandboxed
        # mode. Therefore, spawn_strategy must be set to standalone.
        # https://github.com/bazelbuild/rules_scala/issues/139
        "-Djava.library.path=$RUNFILES_DIR/fantasy/third_party/jvm/glpk",
    ],
)
