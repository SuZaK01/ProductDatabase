package controllers

import javax.inject.Inject

import model.{Album, Artist}
import model.dao.AlbumDAO
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.{Action, Controller}

/**
  * Created by lukasz on 06.11.16.
  */
class AlbumController @Inject()(albumDAO: AlbumDAO) extends Controller {

  def showAlbums() = Action.async { implicit request =>
    albumDAO.selectAlbumsAction.map { albums => Ok(albums.toString()) }
  }

  def createData() = Action.async { implicit req =>
    albumDAO.insertAllAction.map { _ => Redirect(routes.AlbumController.showAlbums()) }
  }

  def selAlbumsArtists() = Action.async{implicit req =>
    albumDAO.selectAlbumsArtistsAction.map{ seq => Ok(seq.toString())}
  }

  def selectAlbumArtistExplicitly() = Action.async{implicit req =>
    albumDAO.selectAlbumsArtistActionExplicit.map{ (seq: Seq[(Artist, Album)]) => Ok(seq.to[List].toString)}
  }

  /*
    def showAlbumWithAuthor(authorName: String) = Action.async { implicit request =>
      albumDAO.selectSpiceGirls(authorName).map { case album => Ok(album.toString) }
    }

    def albumsReleasedAfter1990WithNotBad() = Action.async { implicit request =>
      albumDAO.albumsReleasedAfter1990WithNotBad().map { albums => Ok(albums.toString) }
    }

    def updateCatsHits() = Action.async { implicit request =>
      albumDAO.updateCatsHits().map { album => Ok(album.toString) }
    }

    def insertPinkFloyd = Action.async { implicit req =>
      albumDAO.insertAction().map { _ => Redirect(routes.AlbumController.showAlbums()) }
    }

    def newAlbumInsert(artist: String, title: String, year: Int) = Action.async { implicit req =>
      albumDAO.monadicInsertAction(artist, title, year).map { _ => Redirect(routes.AlbumController.showAlbums()) }
    }*/

}
