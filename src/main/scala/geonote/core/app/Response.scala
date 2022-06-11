package geonote.core.app

class Response(val status: Int, val error: String, val note: Array[NoteDTO])

object Response {
  private final val SUCCESS = 0
  private final val FAIL = 1

  def onFail(error: String): Response = new Response(FAIL, error, null)
  def onSuccess(note: Note): Response = new Response(SUCCESS, null, Array(NoteDTO.from(note)))
  def onSuccess(notes: Array[Note]): Response = new Response(SUCCESS, null, NoteDTO.from(notes))
}