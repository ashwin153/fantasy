# Fantasy
---
![Continuous Integration](https://github.com/ashwin153/fantasy/workflows/ci/badge.svg)

# Structure

```bash
fantasy/
├── assets/       # Static files.
├── docs/         # Documentation website.
├── third_party/  # External dependencies.
├── tools/        # Bazel configuration.
└── fantasy/      # Source code.
```

# Build

```bash
# Install Bazelisk.
# https://github.com/bazelbuild/bazelisk
sudo apt-get update -y
sudo apt-get build-dep -y python3
sudo apt-get install npm
npm install -g @bazel/bazelisk

# Install pre-commit hooks.
# https://pre-commit.com/
curl https://pre-commit.com/install-local.py | python -
pre-commit install

# Build and test source code.
# https://docs.bazel.build/versions/master/install.html
bazel build //...
bazel test //...
```
