# bazel-nested-module-build-error-repro

Running `bazel query //...` from the root workspace shows:
```
//lib_a:lib_a
//lib_a:lib_a_deployjars_internal_rule
```

But running `bazel build //lib_a:lib_a` fails:
```
ERROR: no such package '@@[unknown repo 'maven' requested from @@]//': The repository '@@[unknown repo 'maven' requested from @@]' could not be resolved: No repository visible as '@maven' from main repository
ERROR: /bazel-nested-module-build-error-repro/lib_a/BUILD.bazel:3:12: no such package '@@[unknown repo 'maven' requested from @@]//': The repository '@@[unknown repo 'maven' requested from @@]' could not be resolved: No repository visible as '@maven' from main repository and referenced by '//lib_a:lib_a'
ERROR: Analysis of target '//lib_a:lib_a' failed; build aborted: Analysis failed
INFO: Elapsed time: 0.326s, Critical Path: 0.00s
INFO: 1 process: 1 internal.
ERROR: Build did NOT complete successfully
FAILED:
    Fetching repository @@bazel_tools~cc_configure_extension~local_config_cc; Restarting.
```

Furthermore, running `cd lib_a && bazel build //:lib_a ` works fine:
```
INFO: Analyzed target //:lib_a (85 packages loaded, 3600 targets configured).
INFO: Found 1 target...
Target //:lib_a up-to-date:
  bazel-bin/lib_a
```

But then running `bazel query //...` from the root WORKSPACE fails:
```
ERROR: error loading package under directory '': error loading package 'lib_a/bazel-lib_a/external/rules_java~7.3.2/toolchains': at /bazel-nested-module-build-error-repro/lib_a/bazel-lib_a/external/rules_java~7.3.2/toolchains/default_java_toolchain.bzl:17:6: Every .bzl file must have a corresponding package, but '//java:defs.bzl' does not have one. Please create a BUILD file in the same or any parent directory. Note that this BUILD file does not need to do anything except exist.
```