#!/bin/bash

for dataset in ycsb
do
	for wk in zipfian1.5 zipfian2 uni
	do
		for spark_environ in dataloading newspark
		do
			mkdir workload/${dataset}/${wk}/${spark_environ}
			for i in {0..199}
			do
				cp workload/${dataset}/${wk}/q${i}.scala ${spark_environ}/examples/src/main/scala/org/apache/spark/examples/sql/SQLDataSourceExample.scala
				cd ${spark_environ}
				./build/sbt "project examples" package
				cd ../
				mv ${spark_environ}/examples/target/scala-2.11/jars/spark-examples_2.11-2.4.0.jar workload/${dataset}/${wk}/${spark_environ}/q${i}.jar
				echo "Dataset: $dataset , workload: $wk , Query : $i , Spark: ${spark_environ} Done."
			done
		done
	done
done


