# jar_generation
Generate jars for query workloads.

1. Install JDK and JRE.

```
sudo apt update
sudo apt install openjdk-8-jre-headless openjdk-8-jdk-headless
```

2. Compile Spark.

```
cd dataloading
./build/sbt package
cd ../newspark
./build/sbt package
```

3. Generate jars.

```
./test.sh
```

4. Git commit.
