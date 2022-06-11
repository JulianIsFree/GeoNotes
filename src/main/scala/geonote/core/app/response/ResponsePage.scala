package geonote.core.app.response

import geonote.core.app.domain.Note
import geonote.core.app.response.Response.{FAIL, SUCCESS}

/**
 * Represents paginated response from [[geonote.core.app.controller.NoteController]] to client
 * @param status 0 or 1 corresponding to SUCCESS and FAIL
 * @param error error message if any occurred, null else
 * @param length number of notes in result
 * @param notes result of request, null if request failed
 * @param totalPages totally pages with requested size [[param size]] (or less, if last page doesn't contains enough elements)
 * @param page current page
 * @param size requested size of page
 */
class ResponsePage(status: Int, error: String, length: Int, notes: Array[NoteDTO],
                   var totalPages: Int, var page: Int, var size: Int) extends Response(status, error, length, notes) {
  def setTotalPages(totalPages: Int): Unit = this.totalPages = totalPages
  def getTotalPages: Int = this.totalPages

  def setPage(page: Int): Unit = this.page = page
  def getPage: Int = this.page

  def setSize(size: Int): Unit = this.size = size
  def getSize: Int = this.size
}

object ResponsePage {
  def onSuccess(totalPages: Int, page: Int, size: Int, notes: Array[Note]): ResponsePage =
    new ResponsePage(SUCCESS, null, notes.length, NoteDTO.from(notes), totalPages, page, size)
  def onFail(error: String): ResponsePage = new ResponsePage(FAIL, error, 0, null, 0, 0, 0)
}