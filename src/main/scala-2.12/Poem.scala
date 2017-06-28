import java.net.URL

/**
  * Created by alex on 6/26/17.
  */


// TODO: Perhaps add a year parameter?
case class Poem (Id: Int, Title: String = "", Author: String = "", Content: Vector[String] = Vector(), Reference : URL, Keywords : Vector[String] = Vector(), Periods : Vector[String] = Vector(), Regions: Vector[String] = Vector(), Terms: Vector[String] = Vector());
