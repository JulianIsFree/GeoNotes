package geonote.core.app.domain

import jakarta.persistence._


@Entity
class Note {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = Note.COLUMN_ID)
  private var id: Long = _

  @Column(name = Note.COLUMN_CONTENT)
  private var content: String = _

  @Column(name = Note.COLUMN_TIME)
  private var time: Long = _

  @Column(name = Note.COLUMN_HAS_GEO)
  private var hasGeo: Boolean = _

  @Column(name = Note.COLUMN_GEO_X)
  private var x: Double = _

  @Column(name = Note.COLUMN_GEO_Y)
  private var y: Double = _

  def hasGeoMark: Boolean = hasGeo

  // getters and setters for hibernate jpa
  def getId: Long = id
  def setId(id: Long): Unit = this.id = id

  def getContent: String = content
  def setContent(description: String): Unit = this.content = description

  def setHasGeo(hasGeo: Boolean): Unit = this.hasGeo = hasGeo
  def getHasGeo: Boolean = hasGeo

  def getX: Double = x
  def setX(x: Double): Unit = this.x = x

  def getY: Double = y
  def setY(y: Double): Unit = this.y = y

  def getTime: Long = time
  def setTime(time: Long): Unit = this.time = time
}

object Note {
  final val DEFAULT_CONTENT = "I found myself in smoke far away from home"

  final val TABLE_NAME = "geotable"

  final val COLUMN_ID = "noteid"
  final val COLUMN_CONTENT = "content"
  final val COLUMN_TIME = "timestamp"
  final val COLUMN_HAS_GEO = "hasgeo"
  final val COLUMN_GEO_X = "geox"
  final val COLUMN_GEO_Y = "geoy"
}