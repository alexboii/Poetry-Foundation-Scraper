/**
  * Created by alex on 6/26/17.
  */

import java.net.URL

import scala.collection.JavaConverters._

import org.jsoup.nodes.{Document, Element}
import org.jsoup.select.Elements
import org.jsoup.{HttpStatusException, Jsoup}
import scala.async.Async.{async, await}


class Indexer {
  def parse(body: Document, url: URL) : Poem = {
    val title = body.select("span.hdg.hdg_1").text();
    val author_name = body.select("span.hdg.hdg_utility").select("a").text();
    val content = body.select("div.poem").select("div").asScala.toVector.map(x => x.text() + "\n").tail;
    val keywords = body.select("div.feature-bd.feature-bd_emphasize").select("a").asScala.toVector.map(x => x.text().split("\\W+") + "\n").tail;
    val terms = body.select("a[href^=https://www.poetryfoundation.org/poems-and-poets/poems#poetic-terms]").asScala.toList.map(x => x.text() + "\n").toSet;

    // TODO: Include scala-async library / mechanism so that we can search year of poem

    //    Poem(title, author_name, content, URL, keywords, terms, )

  return ???;
  }
}
