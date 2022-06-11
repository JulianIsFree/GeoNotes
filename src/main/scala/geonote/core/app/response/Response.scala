package geonote.core.app.response

import geonote.core.app.domain.Note

/**
 * Represents response from [[geonote.core.app.controller.NoteController]] to client
 * @param status 0 or 1 corresponding to SUCCESS and FAIL
 * @param error error message if any occurred, null else
 * @param length number of notes in result
 * @param notes result of request, null if request failed
 */
class Response(var status: Int, var error: String, var length: Int, var notes: Array[NoteDTO]) {
  def getStatus: Int = status
  def setStatus(status: Int): Unit = this.status = status

  def getError: String = error
  def setError(error: String): Unit = this.error = error

  def getNotes: Array[NoteDTO] = notes
  def setNodes(notes: Array[NoteDTO]): Unit = this.notes = notes

  def setLength(length: Int): Unit = this.length = length
  def getLength: Int = this.length
}

object Response {
  final val SUCCESS = 0
  final val FAIL = 1

  def onFail(error: String): Response = new Response(FAIL, error, 0, null)
  def onSuccess(note: Note): Response = new Response(SUCCESS, null, 1, Array(NoteDTO.from(note)))
  def onSuccess(notes: Array[Note]): Response = new Response(SUCCESS, null, notes.length, NoteDTO.from(notes))
}