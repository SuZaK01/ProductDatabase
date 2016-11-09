package model

/**
  * Created by lukasz on 06.11.16.
  */
case class Album(artistId: Long, title: String, year: Int, rating: Rating, id: Long = 0L)



object Album {

  /*  def createAlbum(fields: (String, String, Int, Long)): Album =
      fields match {
        case (artist, title, year, id) => new Album(artist, title, year, id)
      }

    def extractAlbum(album: Album): Option[(String, String, Int, Long)] = Some((album.artist, album.title, album.year, album.id))*/

  def tupled = (Album.apply _).tupled
}

case class Artist(
                   name: String,
                   id: Long = 0L
                 )

object Artist{
  def tupled = (Artist.apply _).tupled
}

sealed abstract class Rating(val stars: Int)

object Rating {
  def fromInt(stars: Int): Rating = stars match {
    case 5 => Awesome
    case 4 => Good
    case 3 => NotBad
    case 2 => Meh
    case 1 => Aaaargh
    case _ => sys.error("Ratings only apply from 1 to 5")
  }

  def toInt(rating: Rating): Int = rating match {
    case Awesome => 5
    case Good => 4
    case NotBad => 3
    case Meh => 2
    case Aaaargh => 1
  }

  final case object Awesome extends Rating(5)

  final case object Good extends Rating(4)

  final case object NotBad extends Rating(3)

  final case object Meh extends Rating(2)

  final case object Aaaargh extends Rating(1)

}

