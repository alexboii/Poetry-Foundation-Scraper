/**
  * Created by alex on 6/26/17.
  */

import java.net.URL

import scala.collection.JavaConverters._

import org.jsoup.nodes.{Document, Element}
import org.jsoup.select.Elements
import org.jsoup.{HttpStatusException, Jsoup}
import scala.async.Async.{async, await}


class Scraper {

  def parse(body: Document, url: URL, id: Int): Poem = {

    def formatSet = (x: Elements) => x.asScala.map(x => x.text() + "\n").toSet.toVector;

    try {
      val title = body.select("span.hdg.hdg_1").text();
      val author_name = body.select("span.hdg.hdg_utility").select("a").text();
      val content = body.select("div.poem").select("div").asScala.toVector.map(x => x.text() + "\n").tail;
      val keywords = formatSet(body.select("div.feature-bd.feature-bd_emphasize"));
      val terms = formatSet(body.select("a[href^=https://www.poetryfoundation.org/poems-and-poets/poems#poetic-terms]"));
      val period = formatSet(body.select("a[href^=https://www.poetryfoundation.org/poems-and-poets/poets#school-period]"));
      val region = formatSet(body.select("a[href^=https://www.poetryfoundation.org/poems-and-poets/poets#geography]"));

      return Poem(id, title, author_name, content, URL, keywords, period, region, terms);

    }

    // TODO: Include scala-async library / mechanism so that we can search year of poem

  }


}
