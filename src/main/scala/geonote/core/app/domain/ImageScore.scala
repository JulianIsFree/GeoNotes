package geonote.core.app.domain

import geonote.core.app.domain.ImageScore.{COLUMN_ID, COLUMN_REFERENCE_IMAGE_ID, COLUMN_SCORE}
import jakarta.persistence._

@Entity
@IdClass(classOf[ImageScore.ImageScoreId])
class ImageScore {
  @Id
  @Column(name = COLUMN_ID)
  private var id: Long = _

  @Id
  @ManyToOne(cascade = Array(CascadeType.ALL))
  @JoinColumn(name = COLUMN_REFERENCE_IMAGE_ID, referencedColumnName = Image.COLUMN_URL)
  @SecondaryTable(name = Image.TABLE_NAME)
  private var url: Image = _

  @Column(name = COLUMN_SCORE)
  private var score: Long = _

  def getId: Long = id
  def setId(id: Long): Unit = this.id = id

  def getUrl: Image = url
  def setUrl(image: Image): Unit = this.url = image

  def getScore: Long = score
  def setScore(score: Long): Unit = this.score = score
}

object ImageScore {
  class ImageScoreId {
    private var url: Image = _
    private var id: Long = _

    def getId: Long = id

    def setId(id: Long): Unit = this.id = id

    def getUrl: Image = url

    def setUrl(image: Image): Unit = this.url = image
  }

  final val COLUMN_REFERENCE_IMAGE_ID = "url"
  final val COLUMN_ID = "id"
  final val COLUMN_SCORE = "score"
}