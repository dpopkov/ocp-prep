# Commands not using Maven or IDE

## Create, compile and run single module

### Check java version
`java -version`

### Compile old way
* `cd jmodule1`
* `javac -d out src/com/modularity/greeter/Main.java src/module-info.java`

### Run module
`java -p out -m greeter/com.modularity.greeter.Main`

### Compile module specifying module source path
* `cd ../jmodule2`
* `javac -d out --module-source-path src -m greeter`

### Run module exactly the same way
`java -p out -m greeter/com.modularity.greeter.Main`

### Compilation Flags
* Single module:  
`javac -d {dir} {all source files, including module-info.java}`
* Multiple modules:
```
javac -d {dir} \
    --module-source-path {src_dir} \
    -m {module_name}, {module_name}
```
* Running a module:
```
java -p {module_path} \
    -m {module}/{fully qualified main class}
java --module-path {module_path} \
    --module {module}/{fully qualified main class}
```

## Working with Two Modules

### Compile module specifying module source path and modules
* `cd ../jmodule3`
* ` javac -d out --module-source-path src -m greeter.cli,greeter.hello`  
or
* ` javac -d out --module-source-path src -m greeter.cli`   (compiler compiles all required modules)

### Run module
* `java -p out -m greeter.cli/greeter.cli.Main`


