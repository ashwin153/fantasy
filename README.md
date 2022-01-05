<p align="center">
    A daily fantasy sports toolkit.
</p>

<p align="center">
  <a href="https://github.com/ashwin153/fantasy/actions/workflows/ci.yml">
    <img
      src="https://github.com/ashwin153/fantasy/workflows/ci/badge.svg?branch=main"
      alt="Continuous Integration"
    />
  </a>
</p>

```bash
fantasy/
├── .github/      # Continuous integration.
├── third_party/  # External dependencies.
├── tools/        # Bazel configuration.
└── fantasy/      # Source code.
```

The repository is built and tested by [Bazel].

```bash
# Install Bazelisk.
# https://github.com/bazelbuild/bazelisk
sudo apt-get update -y
sudo apt-get build-dep -y python3
sudo apt-get install npm
npm install -g @bazel/bazelisk

# Build and test source code.
# https://docs.bazel.build/versions/master/install.html
bazel build //...
bazel test //...
```

The repository is linted by Pre-Commit.

```bash
# Install pre-commit hooks.
# https://pre-commit.com/
curl https://pre-commit.com/install-local.py | python -
pre-commit install
```
