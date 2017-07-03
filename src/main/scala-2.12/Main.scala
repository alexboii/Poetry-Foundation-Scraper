/**
  * Created by alex on 6/26/17.
  */


import java.net.URL

import org.jsoup.{Connection, HttpStatusException, Jsoup}

import scala.collection.JavaConverters._
import scala.util.control.Breaks
import java.io.{File, StringWriter}

import play.api.libs.json._
import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.sun.javaws.exceptions.InvalidArgumentException
import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.write
import org.jsoup.nodes.Document

import scala.concurrent.Future;


object Main extends App {

  System.setProperty("http.proxyHost", "xxx.xxx.xxx")

  val OBJECT_MAX_SIZE = 150000

  for (poem_number <- (1 to OBJECT_MAX_SIZE).par) {

    try{
      val url_string = "https://www.poetryfoundation.org/poems/" + poem_number;
      val response = Jsoup.connect(url_string).followRedirects(true).execute().parse();
      val poem = Scraper.parseAsync(response, url_string, poem_number);
      Indexer.indexAsync(poem);
      print(s"Scrapped $poem_number - ");
    } catch{
      case e: HttpStatusException => println(s"Cannot find poem $poem_number")
      case e: InvalidArgumentException => println(s"Important parameters are missing");
      case e: Exception => println("Unhandled exception");
    }

  }

}

