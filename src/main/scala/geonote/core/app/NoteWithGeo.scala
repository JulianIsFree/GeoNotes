package geonote.core.app

import jakarta.persistence.{Column, Entity, GeneratedValue, GenerationType, Id}


@Entity
class NoteWithGeo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = NoteWithGeo.COLUMN_ID)
  private var id: Long = _

  @Column(name = NoteWithGeo.COLUMN_CONTENT)
  private var content: String = "I found myself in smoke far away from home"

  @Column(name = NoteWithGeo.COLUMN_HAS_GEO)
  private var hasGeo: Boolean = false

  @Column(name = NoteWithGeo.COLUMN_GEO_X)
  private var x: Double = _

  @Column(name = NoteWithGeo.COLUMN_GEO_Y)
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
}

object NoteWithGeo {
  final val TABLE_NAME = "geotable"
  final val COLUMN_ID = "noteid"
  final val COLUMN_CONTENT = "content"
  final val COLUMN_HAS_GEO = "hasgeo"
  final val COLUMN_GEO_X = "geox"
  final val COLUMN_GEO_Y = "geoy"
}