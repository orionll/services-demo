Example of using services in Java 11.
==============================================
## Prerequisites
You need JDK 11+ to build the project (run `mvn -version` to make sure that it is using JDK 11+).

## Building
Run the following command to build the project:
```
mvn clean install
```

## Running
Under mypaint-core/target:
```
java -p libs/org.eclipse.swt.win32.win32.x86_64.jar;mypaint-core-1.0-SNAPSHOT.jar -m mypaint.core
```
(Substitute `;` with `:` on Linux)

To use the plugin, copy mypaint-ext/target/mypaint-ext-1.0-SNAPSHOT.jar to mypaint-core/target/plugins/
