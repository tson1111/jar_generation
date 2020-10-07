/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.spark.examples.sql

import java.util.Properties

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.internal.SQLConf

object SQLDataSourceExample {

  case class Person(name: String, age: Long)

  def main(args: Array[String]) {

    val spark = SparkSession
      .builder()
      .appName("Spark SQL data sources example")
      .config("spark.sql.parquet.enableVectorizedReader", "false")
      .config(SQLConf.QUERY_BIT_VECTOR.key, "/local/cong/bv")
      .getOrCreate()

    q1(spark)
    spark.stop()
  }

  private def warm(spark: SparkSession): Unit = {
    import spark.implicits._
    val time1 = System.currentTimeMillis()
    val df = spark.read
      .parquet("/home/congding/test/parquet_cpp.parquet")

    val res = df.filter($"cool" === 7).count()
    // scalastyle:off println
    System.out.println(res)
    val time2 = System.currentTimeMillis()
    System.out.println(time2-time1)
    // scalastyle:on println
  }


  private def q1(spark: SparkSession): Unit = {
    import spark.implicits._
    val time1 = System.currentTimeMillis()
    val df = spark.read
      .parquet("/local/cong/ycsb.pqt")

    val res = df
      .filter($"linear_score"===12)
      .filter($"weighted_score"===93)
      .count()
    // scalastyle:off println
    System.out.println(res)
    val time2 = System.currentTimeMillis()
    System.out.println(time2-time1)
    // scalastyle:on println
  }
}

