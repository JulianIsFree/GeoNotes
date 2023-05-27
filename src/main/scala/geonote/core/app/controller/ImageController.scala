package geonote.core.app.controller

import geonote.core.app.controller.ImageController.ImageResponse.ImageDTO
import geonote.core.app.controller.ImageController.ImageScoreTopResponse.ImageScoreTopDTO
import geonote.core.app.controller.ImageController.{CreateImageBody, ImageResponse, ImageScoreBody, ImageScoreResponse, ImageScoreTopResponse}
import geonote.core.app.domain.{Image, ImageScore}
import geonote.core.app.repository.{ImageRepository, ImageScoreRepository}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{GetMapping, PostMapping, RequestBody, RestController}

import scala.jdk.CollectionConverters.CollectionHasAsScala
import scala.jdk.OptionConverters.RichOptional


@RestController
class ImageController {
  @Autowired
  private var imageRepo: ImageRepository = _

  @Autowired
  private var scoreRepo: ImageScoreRepository = _

  @PostMapping(value = Array("/image/create"))
  def createImage(@RequestBody body: CreateImageBody): ImageResponse = try {
    val image = imageRepo.findOneByUrl(body.url).toScala match {
      case Some(image) =>
        image
      case None =>
        var image: Image = new Image
        image.setPrompt(body.prompt)
        image.setUrl(body.url)
        image = imageRepo.save(image)
        image
    }
    ImageResponse.onSuccess(image)
  } catch {
    case e: Throwable => ImageResponse.onFail(e.getMessage)
  }

  @PostMapping(value = Array("/image/score"))
  def scoreImage(@RequestBody body: ImageScoreBody): ImageScoreResponse = try {
    scoreRepo.findOneByUrlAndId(body.url, body.user).toScala match {
      case None =>
        var score = new ImageScore
        score.setId(body.user)
        score.setScore(body.score)
        score.setUrl(imageRepo.findOneByUrl(body.url).toScala.get)
        score = scoreRepo.save(score)
      case Some(_) =>
    }

    ImageScoreResponse.onSuccess()
  } catch {
    case e: Throwable => ImageScoreResponse.onFail(e.getMessage)
  }

  @GetMapping(value = Array("/image/top"))
  def scoreImageTop(): ImageScoreTopResponse = try {
    val results = scoreRepo.findTop10UrlsWithTotalScore.asScala
    ImageScoreTopResponse.onSuccess(results)
  } catch {
    case e: Throwable => ImageScoreTopResponse.onFail(e.getMessage)
  }
}

object ImageController {
  class ImageResponse(var status: Int, var error: String, var length: Int, var image: ImageDTO) {
    def getStatus: Int = status

    def setStatus(status: Int): Unit = this.status = status

    def getError: String = error

    def setError(error: String): Unit = this.error = error

    def getImage: ImageDTO = image

    def setImage(image: ImageDTO): Unit = this.image = image

    def setLength(length: Int): Unit = this.length = length

    def getLength: Int = this.length
  }

  object ImageResponse {
    final val SUCCESS = 0
    final val FAIL = 1

    class ImageDTO(image: Image) {
      private var prompt: String = image.getPrompt
      private var url: String = image.getUrl

      def getPrompt: String = prompt

      def setPrompt(prompt: String): Unit = this.prompt = prompt

      def getUrl: String = url

      def setUrl(url: String): Unit = this.url = url
    }

    object ImageDTO {
      def from(image: Image): ImageDTO = new ImageDTO(image)
    }

    def onFail(error: String): ImageResponse = new ImageResponse(FAIL, error, 0, null)

    def onSuccess(image: Image): ImageResponse = new ImageResponse(SUCCESS, null, 1, ImageDTO.from(image))
  }

  case class CreateImageBody(prompt: String, url: String)

  class ImageScoreResponse(var status: Int, var error: String) {
    def getStatus: Int = status

    def setStatus(status: Int): Unit = this.status = status

    def getError: String = error

    def setError(error: String): Unit = this.error = error
  }

  object ImageScoreResponse {
    final val SUCCESS = 0
    final val FAIL = 1

    def onFail(error: String): ImageScoreResponse = new ImageScoreResponse(FAIL, error)

    def onSuccess(): ImageScoreResponse = new ImageScoreResponse(SUCCESS, null)
  }

  class ImageScoreTopResponse(var status: Int, var error: String, var top: Array[ImageScoreTopDTO]) {
    def getStatus: Int = status

    def setStatus(status: Int): Unit = this.status = status

    def getError: String = error

    def setError(error: String): Unit = this.error = error

    def getTop: Array[ImageScoreTopDTO] = top
    def setTop(top: Array[ImageScoreTopDTO]): Unit = this.top = top
  }

  object ImageScoreTopResponse {
    final val SUCCESS = 0
    final val FAIL = 1
    
    class ImageScoreTopDTO(var url: String, var score: Long) {
      def getUrl: String = url
      def setUrl(url: String): Unit = this.url = url
      
      def getScore: Long = score
      def setScore(score: Long): Unit = this.score = score
    }

    object ImageScoreTopDTO {
      def from(url: String, score: Long): ImageScoreTopDTO = new ImageScoreTopDTO(url, score)
      def from(rows: Iterable[Array[AnyRef]]): scala.Array[ImageScoreTopDTO] = {
        var res = Seq.empty[ImageScoreTopDTO]
        for (r <- rows) {
          val url = r(0).toString
          val topScore = r(1).toString.toLong
          res = res ++ Seq(new ImageScoreTopDTO(url, topScore))
        }
        res.toArray
      }
    }

    def onFail(error: String): ImageScoreTopResponse = new ImageScoreTopResponse(FAIL, error, Array.empty)
    def onSuccess(rows: Iterable[Array[AnyRef]]): ImageScoreTopResponse = new ImageScoreTopResponse(SUCCESS, null, ImageScoreTopDTO.from(rows))
  }

  case class ImageScoreBody(user: Int, url: String, score: Long)
}