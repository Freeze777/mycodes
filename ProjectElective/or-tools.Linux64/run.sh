
javac -d objs -cp lib/com.google.ortools.jar examples/com/google/ortools/samples/$1.java

java -Djava.library.path=lib -cp objs:lib/com.google.ortools.jar com.google.ortools.samples.$1

