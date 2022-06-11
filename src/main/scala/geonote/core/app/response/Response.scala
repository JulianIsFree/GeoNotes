package geonote.core.app.response

import geonote.core.app.domain.Note

class Response(var status: Int, var error: String, var notes: Array[NoteDTO]) {
  def getStatus: Int = status
  def setStatus(status: Int): Unit = this.status = status

  def getError: String = error
  def setError(error: String): Unit = this.error = error

  def getNotes: Array[NoteDTO] = notes
  def setNodes(notes: Array[NoteDTO]): Unit = this.notes = notes
}

object Response {
  final val SUCCESS = 0
  final val FAIL = 1

  def onFail(error: String): Response = new Response(FAIL, error, null)
  def onSuccess(note: Note): Response = new Response(SUCCESS, null, Array(NoteDTO.from(note)))
  def onSuccess(notes: Array[Note]): Response = new Response(SUCCESS, null, NoteDTO.from(notes))
}