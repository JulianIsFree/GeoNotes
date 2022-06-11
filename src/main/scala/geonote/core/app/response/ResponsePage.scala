package geonote.core.app.response

import geonote.core.app.domain.Note
import geonote.core.app.response.Response.{FAIL, SUCCESS}

class ResponsePage(status: Int, error: String, notes: Array[NoteDTO],
                   var totalPages: Int, var page: Int, var size: Int) extends Response(status, error, notes) {
  def setTotalPages(totalPages: Int): Unit = this.totalPages = totalPages
  def getTotalPages: Int = this.totalPages

  def setPage(page: Int): Unit = this.page = page
  def getPage: Int = this.page

  def setSize(size: Int): Unit = this.size = size
  def getSize: Int = this.size
}

object ResponsePage {
  def onSuccess(totalPages: Int, page: Int, size: Int, notes: Array[Note]): ResponsePage =
    new ResponsePage(SUCCESS, null, NoteDTO.from(notes), totalPages, page, size)
  def onFail(error: String): ResponsePage = new ResponsePage(FAIL, error, null, 0, 0, 0)
}