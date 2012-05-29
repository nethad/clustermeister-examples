# Clustermeister Examples

This is a repository for examples that use the [Clustermeister API](https://github.com/nethad/clustermeister/). To build an run the examples, you can use [Maven](http://maven.apache.org/).


## Build examples

First, build the examples with:

	mvn clean install

You should see, that the build was successful:

```
...
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
...
```

## Run examples

To be able to run these examples, you first need to deploy nodes that may execute them. Refer to the [documentation](https://github.com/nethad/clustermeister/wiki/) on how to do this.

After that, you are able to run the examples on the command line:

* Fractals

	mvn exec:java -Dexec.mainClass="com.github.nethad.clustermeister.example.fractals.FractalsGUI"

* Text Processing

	mvn exec:java -Dexec.mainClass="com.github.nethad.clustermeister.example.textproc.TextProcessing"

* Async

	mvn exec:java -Dexec.mainClass="com.github.nethad.clustermeister.example.fractals.Async"

or 

	mvn exec:java -Dexec.mainClass="com.github.nethad.clustermeister.example.fractals.AsyncTasks"

