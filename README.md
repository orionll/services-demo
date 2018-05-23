Example of using services in Java 10.
==============================================
## Prerequisites
You need JDK 10 to build the project (run `mvn -version` to make sure that it is using JDK 10).

## Building
Run the following command to build the project:
```
mvn clean install
```

## Running
Without plugin:
```
java -p libs/org.eclipse.swt.win32.win32.x64.jar;mypaint-core/target/mypaint-core-1.0-SNAPSHOT.jar -m mypaint.core
```
With plugin:
```
java -p libs/org.eclipse.swt.win32.win32.x64.jar;mypaint-core/target/mypaint-core-1.0-SNAPSHOT.jar;mypaint-ext/target/mypaint-ext-1.0-SNAPSHOT.jar -m mypaint.core
```

(Substitute `;` with `:` on Linux)
