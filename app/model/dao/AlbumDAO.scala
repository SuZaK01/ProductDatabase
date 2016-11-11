package model.dao

import javax.inject.Inject

import model.{Album, Artist, Rating}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import slick.lifted.TableQuery

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by lukasz on 06.11.16.
  */
class AlbumDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  lazy val AlbumTable = TableQuery[AlbumTable]
  lazy val ArtistTable = TableQuery[ArtistTable]

  import driver.api._

  implicit val columnType: BaseColumnType[Rating] = MappedColumnType.base[Rating, Int](Rating.toInt, Rating.fromInt)

  class AlbumTable(tag: Tag) extends Table[Album](tag, "albums") {
    def artistId = column[Long]("artist_id")

    def title = column[String]("title")

    def year = column[Int]("year")

    def rating = column[Rating]("rating")

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def * = (artistId, title, year, rating, id) <> (Album.tupled, Album.unapply)
  }

  class ArtistTable(tag: Tag) extends Table[Artist](tag, "artists") {
    def name = column[String]("name")

    def id = column[Long]("id", O.AutoInc, O.PrimaryKey)

    def * = (name, id) <> (Artist.tupled, Artist.unapply)
  }

  def selectAlbumsAction = db.run(AlbumTable.sortBy(_.year.desc).result)

  val selectAlbumsArtistsImplicitJoin = (for {
    album <- AlbumTable
    artist <- ArtistTable
    if album.artistId === artist.id
  } yield (album, artist)).result

  def selectAlbumsArtistsAction = db.run(selectAlbumsArtistsImplicitJoin)

  val selectAlbumsArtistsExplicitJoin =
    ArtistTable.join(AlbumTable).on {
      case (artist, album) => artist.id === album.artistId
    }.sortBy {
      case (artist, album) => artist.name.asc
    }.result

  def selectAlbumsArtistActionExplicit = db.run(selectAlbumsArtistsExplicitJoin)


  def insertAllAction = db.run {
    for {
      rollingStonesId <- ArtistTable returning ArtistTable.map(_.id) += Artist("Rolling Stones")
      burzumId <- ArtistTable returning ArtistTable.map(_.id) += Artist("Burzum")
      michaelJacksonId <- ArtistTable returning ArtistTable.map(_.id) += Artist("Michael Jackson")
      _ <- AlbumTable ++= Seq(
        Album(rollingStonesId, "Rolling Stones Greatests Hits",1987, Rating.Awesome),
        Album(burzumId, "Det Some Engang War", 1995, Rating.Good),
        Album(michaelJacksonId, "Stranger in the Moscow", 1998, Rating.Awesome)
      )
    } yield ()
  }


  /*  def insertAllAction = db.run {
      for {
        keyboardCatId <- ArtistTable returning ArtistTable.map(_.id) += Artist("Keyboard Cat")
        spiceGirlsId <- ArtistTable returning ArtistTable.map(_.id) += Artist("Spice Girls")
        _ <- AlbumTable ++= Seq(
          Album(keyboardCatId, "Keyboard Cat's Greatests Hits", 2009, Rating.Awesome),
          Album(spiceGirlsId, "SpiceGirls hgraetest hits", 2008, Rating.NotBad)
        )
      } yield ()
    }*/

  /*  def selectSpiceGirls(authorName: String) = db.run(AlbumTable.filter(_.artist === authorName).result)

    def albumsReleasedAfter1990WithNotBad(): Future[Seq[Album]] = db.run(AlbumTable
      .filter(_.year > 1990)
      .filter(_.rating >= (Rating.NotBad: Rating))
      .sortBy(_.artist.desc).result)

    def updateCatsHits() = db.run(AlbumTable
      .filter(_.artist === "Keyboard cat")
      .map(_.title)
      .update("Even greater hits"))

    def insertAction() = db.run(AlbumTable ++= Seq(
      Album("Pink Floyd", "Dark Side of the Moon", 1973, Rating.Awesome),
      Album("Pink Floyd", "Jakaś inna płyta z muzykom ;) łężćńóżźćę", 1974, Rating.Meh))
    )


    val selectionAction: DBIOAction[Seq[String], NoStream, Effect.Read] = AlbumTable.filter(_.artist === "Keyboard cat").map(_.title).result

    val insertAllAction: DBIOAction[Option[Int], NoStream, Effect.Write] = AlbumTable ++= Seq(
      Album("Pink Floyd", "Dark Side of the Moon", 1973, Rating.Awesome),
      Album("Pink Floyd", "Jakaś inna płyta z muzykom ;) łężćńóżźćę", 1974, Rating.Meh))

    def monadicInsertAction(artist: String, title: String, year: Int) = db.run {
      for {
        existing <- AlbumTable.filter { e => e.artist === artist && e.year < year }.result
        rating = existing.length match {
          case 0 => Rating.Awesome
          case _ => Rating.Meh
        }
        _ <- AlbumTable += Album(artist, title, year, rating)
      } yield ()
    }*/


  /*
    val a: Query[AlbumTable, Album, Seq] = AlbumTable
      .filter(_.year > 1990)
      .filter(_.rating >= (Rating.NotBad: Rating))
      .sortBy(_.artist.desc)

    val b: Query[Rep[Rating], Rating, Seq] = AlbumTable
      .filter(_.year > 1990)
      .filter(_.rating >= (Rating.NotBad: Rating))
        .map(_.rating)
  */


}
