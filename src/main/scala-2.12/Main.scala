/**
  * Created by alex on 6/26/17.
  */


import java.net.URL

import org.jsoup.{HttpStatusException, Jsoup}

import scala.collection.JavaConverters._
import scala.util.control.Breaks
import com.fasterxml.jackson._;




object Main extends App {


  System.setProperty("http.proxyHost", "xxx.xxx.xxx")

  var OBJECT_MAX_SIZE = 150000


    // TODO: add parallel statement
    for (poem_number <- OBJECT_MAX_SIZE to OBJECT_MAX_SIZE) {

      try {
        val url_string = "https://www.poetryfoundation.org/poems-and-poets/poems/detail/45089"
        val doc = Jsoup.connect("https://www.poetryfoundation.org/poems-and-poets/poems/detail/54311").get()
        val elems = doc.body();
        val content = doc.select("div.poem").select("div").asScala.toList.map(x => x.text() + "\n").tail;
        val attempt = content(4);

        val keywords = elems.select("div.feature").select("div.feature-bd.feature-bd_emphasize").get(0).select("a").asScala.toList.map(x => x.text() + "\n");
        val terms = elems.select("a[href^=https://www.poetryfoundation.org/poems-and-poets/poems#poetic-terms]").asScala.toList.map(x => x.text() + "\n").toSet;


        val url = new URL(url_string);
        print(s"Scrapping $terms");
      } catch {
        case e: HttpStatusException => println(s"Poem $poem_number does not exist")
      }

    }

}

object MarshallableImplicits {

  implicit class Unmarshallable(unMarshallMe: String) {
    def toMap: Map[String,Any] = JsonUtil.toMap(unMarshallMe)
    def toMapOf[V]()(implicit m: Manifest[V]): Map[String,V] = JsonUtil.toMapOf[V](unMarshallMe)
    def fromJson[T]()(implicit m: Manifest[T]): T =  JsonUtil.fromJson[T](unMarshallMe)
  }

  implicit class Marshallable[T](marshallMe: T) {
    def toJson: String = JsonUtil.toJson(marshallMe)
  }
}

object JsonUtil {
  val mapper = new ObjectMapper() with ScalaObjectMapper
  mapper.registerModule(DefaultScalaModule)
  mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

  def toJson(value: Map[Symbol, Any]): String = {
    toJson(value map { case (k,v) => k.name -> v})
  }

  def toJson(value: Any): String = {
    mapper.writeValueAsString(value)
  }

  def toMap[V](json:String)(implicit m: Manifest[V]) = fromJson[Map[String,V]](json)

  def fromJson[T](json: String)(implicit m : Manifest[T]): T = {
    mapper.readValue[T](json)
  }
}