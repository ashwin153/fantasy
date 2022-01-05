workspace(name = "fantasy")
load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

####################################################################################################
#                                             Skylib                                               #
#                            https://github.com/bazelbuild/bazel-skylib                            #
####################################################################################################
http_archive(
    name = "bazel_skylib",
    sha256 = "1c531376ac7e5a180e0237938a2536de0c54d93f5c278634818e0efc952dd56c",
    type = "tar.gz",
    url = "https://mirror.bazel.build/github.com/bazelbuild/bazel-skylib/releases/download/1.0.3/bazel-skylib-1.0.3.tar.gz",
)

####################################################################################################
#                                              Scala                                               #
#                            https://github.com/bazelbuild/rules_scala                             #
####################################################################################################
http_archive(
    name = "io_bazel_rules_scala",
    sha256 = "438bc03bbb971c45385fde5762ab368a3321e9db5aa78b96252736d86396a9da",
    url = "https://github.com/bazelbuild/rules_scala/archive/9bd9ffd3e52ab9e92b4f7b43051d83231743f231.zip",
    type = "zip",
    strip_prefix = "rules_scala-9bd9ffd3e52ab9e92b4f7b43051d83231743f231",
)

load("@io_bazel_rules_scala//:scala_config.bzl", "scala_config")
scala_config()
load("@io_bazel_rules_scala//scala:scala.bzl", "scala_repositories")
scala_repositories()
load("@io_bazel_rules_scala//scala:toolchains.bzl", "scala_register_toolchains")
scala_register_toolchains()
load("@io_bazel_rules_scala//testing:scalatest.bzl", "scalatest_repositories", "scalatest_toolchain")
scalatest_repositories()
scalatest_toolchain()
