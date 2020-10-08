# jar_generation
Generate jars for query workloads.

1. Install JDK and JRE.

```
sudo apt update
sudo apt install openjdk-8-jre-headless openjdk-8-jdk-headless
```

2. Compile Spark.

```
git clone https://github.com/tson1111/dataloading.git
git clone https://github.com/tson1111/newspark.git
cd dataloading
./build/sbt package
cd ../newspark
./build/sbt package
```

3. Generate jars.

```
./gen.sh
```

4. Git commit & push. (`git add workload` would be enough.)
