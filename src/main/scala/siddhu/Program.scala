package siddhu
import org.jsoup.Jsoup

object Program {
  def main(args: Array[String]): Unit = {
    try {
      //Parsing the url and setting timeout of 6 seconds
      val doc = Jsoup.connect("https://www.imdb.com/chart/top/").timeout(0).get()

      val body = doc.select("tbody.lister-list")

      //Printing the rows size in selected item
      System.out.println(body.select("tr").size())

      val data = body.select("tr")

      data.forEach { element =>
        //Scrapping title of the movie
        var title = element.select("td.posterColumn img").attr("alt")
        System.out.println(title)

        //Scrapping the image
        var img = element.select("td.posterColumn img").attr("src")
        System.out.println(img)

        //scrapping the year
        var year = element.select("td.titleColumn span.secondaryInfo").text().replaceAll("[^\\d]", "")
        println(year)

        var rating = element.select("td.ratingColumn.imdbRating").text().trim
        println(rating)
      }
    } catch {
      case exception: Exception => println(exception)
    }
  }
}
