package geonote.core.app.domain

import geonote.core.app.domain.Image.{COLUMN_PROMPT, COLUMN_URL}
import jakarta.persistence.{Column, Entity, Id, Table}

@Entity
@Table(name = Image.TABLE_NAME)
class Image {
  @Column(name = COLUMN_PROMPT, columnDefinition = "TEXT", length = 2048)
  private var prompt: String = _

  @Id
  @Column(name = COLUMN_URL, columnDefinition = "TEXT", length = 2048)
  private var url: String = _

  def getPrompt: String = prompt
  def setPrompt(description: String): Unit = this.prompt = description

  def getUrl: String = url
  def setUrl(url: String): Unit = this.url = url
}

object Image {
  final val TABLE_NAME = "image"
  final val COLUMN_IMAGEID = "imageid"
  final val COLUMN_PROMPT = "prompt"
  final val COLUMN_URL = "url"
}