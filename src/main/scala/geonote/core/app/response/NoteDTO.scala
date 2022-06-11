package geonote.core.app.response

import geonote.core.app.domain.Note
import geonote.core.app.util.CodeHelper

import scala.annotation.meta.param

/**
 * Data Transfer Object (DTO) in Response to represent [[param note]]
 * @param note - prototype of this NoteDTO
 */
class NoteDTO(note: Note) {
  private var id: Long = note.getId
  private var content: String = note.getContent
  private var time: String = CodeHelper.timeToString(note.getTime)
  private var hasGeo: Boolean = note.getHasGeo
  private var x: Double = note.getX
  private var y: Double = note.getY

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

  def getTime: String = time
  def setTime(time: String): Unit = this.time = time
}

object NoteDTO {
  def from(note: Note): NoteDTO = new NoteDTO(note)
  def from(note: Array[Note]): Array[NoteDTO] = note map NoteDTO.from
  def to(note: NoteDTO): Note = CodeHelper.notImplemented("feel free to support")
}
