import java.io.File
import java.nio.file.Files

import org.json4s._
import org.json4s.jackson.Serialization.write
import org.jsoup.nodes.Document

import org.apache.commons.io._;

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.duration._
import scala.util.{Failure, Success};

object Indexer {

  implicit val formats = DefaultFormats;

  val properties = System.getProperties();
  val separator = properties.get("file.separator").toString();
  val home = properties.get("user.home").toString();


  def indexAsync(poem: Poem): Unit = {


    // serialize poem object to json
    val jsonString = write(poem);

    // write file path
    val file_path = home + separator + "poems" + separator + poem.Author + separator + poem.Id + ".json";
    println(file_path);

    val file = new File(file_path);

    if (!file.getParentFile().exists())
      file.getParentFile().mkdirs();

    if (!file.exists())
      file.createNewFile();

    // write json to file
    try {
      FileUtils.writeStringToFile(file, jsonString);
    } catch{
      case e: Exception => e.printStackTrace();
    }
  }

}
