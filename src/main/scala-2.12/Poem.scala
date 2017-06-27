import java.net.URL

/**
  * Created by alex on 6/26/17.
  */
case class Poem (Title: String, Author: String, Content: Vector[String], Reference : URL, Keywords : Set[String], Period : String, Year: Int, Region: String, Terms: Vector[String]);
