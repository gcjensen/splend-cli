# splend-cli
A WIP CLI for the Splend tool

It is built using java and [picocli](https://picocli.info/), and includes
support for generating a native binary using [GraalVM](https://www.graalvm.org/).

### Building

The app is built using maven. To build a fat-jar that contains all the
dependencies, run:
```bash
$ mvn clean package
```
This can then be run like:
```bash
$ java -jar target/splend-1.0-SNAPSHOT.jar <command>
```

#### Building a native binary

To build a native binary using GraalVM and
[native-image tool](https://www.graalvm.org/reference-manual/native-image/), run:
```bash
$ mvn clean verify
```
This can then be run without the need for a JVM:
```bash
$ ./target/splend <command>
```

### Usage

To use the tool, you'll need a config in `~/.splend/config.yaml`, that looks
like:
```yaml
api-url: ... # The URL of your Splend API instance
id:      ... # The ID of the user
token:   ... # The token of the user
```

After building as per the instructions above (and moving the binary into your
path), you can then run the following to see the available commands and options:
```bash
$ splend --help
```