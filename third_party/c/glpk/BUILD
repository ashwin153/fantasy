licenses(["notice"])
exports_files(["LICENSE"])

########################################################################################################################
#                                                        GLPK                                                          #
#                                          https://www.gnu.org/software/glpk/                                          #
########################################################################################################################
cc_library(
    name = "glpk",
    srcs = glob([
        "src/**/*.c",
        "src/**/*.h",
    ],
    exclude = [
        "src/proxy/main.c"
    ]),
    hdrs = [
        "src/glpk.h",
    ],
    copts = [
        "-Wno-error",
        "-w",
        "-Ithird_party/glpk/src",
        "-Ithird_party/glpk/src/amd",
        "-Ithird_party/glpk/src/bflib",
        "-Ithird_party/glpk/src/cglib",
        "-Ithird_party/glpk/src/colamd",
        "-Ithird_party/glpk/src/env",
        "-Ithird_party/glpk/src/minisat",
        "-Ithird_party/glpk/src/misc",
        "-Ithird_party/glpk/src/proxy",
        "-Ithird_party/glpk/src/zlib",
        "-DHAVE_ZLIB",
    ],
    includes = [
        "src",
        "src/amd",
        "src/api",
        "src/bflib",
        "src/colamd",
        "src/draft",
        "src/env",
        "src/intopt",
        "src/minisat",
        "src/misc",
        "src/mpl",
        "src/npp",
        "src/proxy",
        "src/simplex",
        "src/zlib",
    ],
    visibility = [
        "//visibility:public"
    ]
)
