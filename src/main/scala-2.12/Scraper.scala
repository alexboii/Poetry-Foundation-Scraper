/**
  * Created by alex on 6/26/17.
  */

import java.net.URL

import scala.collection.JavaConverters._

import org.jsoup.nodes.{Document, Element}
import org.jsoup.select.Elements
import org.jsoup.{HttpStatusException, Jsoup}
//import scala.async.Async.{async, await}
import play.api.libs.json._;

import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.write;


object Scraper {

  implicit val formats = DefaultFormats;

  def parseAsync(body: Document, url: String, id: Int): Poem = {

    def formatSet = (x: Elements) => x.asScala.toVector.map(x => x.text().trim()).distinct;

    var title, author_name = "";
    var content, keywords, terms, period, region = Vector[String]();

    try {
      title = body.select("h1.c-hdgSans.c-hdgSans_2.c-mix-hdgSans_inline").text();

      if (title.isEmpty())
        throw new IllegalArgumentException("No title available");
    }
    try {
      author_name = body.select("span.c-txt.c-txt_attribution").select("a").text();

      if (author_name.isEmpty())
        throw new IllegalArgumentException("No author available");
    }
    try {
      content = body.select("div.c-feature-bd").select("div").asScala.toVector.map(x => x.text());

      if (content.length == 0)
        throw new IllegalArgumentException("No content available");
    }
    try {
      keywords = formatSet(body.select("a[href^=https://www.poetryfoundation.org/poems/browse#topics]"));
    }
    try {
      period = formatSet(body.select("a[href^=https://www.poetryfoundation.org/poets/browse#school-period]"));
    }
    try {
      region = formatSet(body.select("a[href^=https://www.poetryfoundation.org/poets/browse#region]"));
    }


    return Poem(id, title, author_name, content, url, keywords, period, region);

    // TODO: Include scala-async library / mechanism so that we can search year of poem

  }


}
