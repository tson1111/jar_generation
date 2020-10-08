#!/bin/bash

for dataset in review ycsb
do
	for wk in zipfian1.5 zipfian2 uni
	do
		for spark_environ in dataloading newspark
		do
			for i in {0..199}
			do
				mkdir workload/${dataset}/${wk}/${spark_environ}
				cp workload/${dataset}/${wk}/q${i}.scala ${spark_environ}/examples/src/main/scala/org/apache/spark/examples/sql/SQLDataSourceExample.scala
				cd ${spark_environ}
				./build/sbt "project examples" package
				mv ${spark_environ}/examples/target/scala-2.11/jars/spark-examples_2.11-2.4.0.jar ../workload/${dataset}/${wk}/${spark_environ}
				echo "Dataset: $dataset , workload: $wk , Query : $i , Spark: ${spark_environ} Done."
				cd ../
			done
		done
	done
done


