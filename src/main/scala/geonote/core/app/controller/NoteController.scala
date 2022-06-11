package geonote.core.app.controller

import geonote.core.app.{Note, Response}
import geonote.core.app.repository.NoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{GetMapping, RequestParam, RestController}

import scala.jdk.CollectionConverters.CollectionHasAsScala

@RestController
class NoteController {
  @Autowired
  private var repo: NoteRepository = _

  @GetMapping(value = Array("/notes/create"))
  def createNoteWithoutGeo(@RequestParam(value = "content", defaultValue = Note.DEFAULT_CONTENT) content: String): Response = try {
    var note = new Note
    note.setHasGeo(false)
    note.setContent(content)
    note.setTime(System.currentTimeMillis())
    note = repo.save(note)
    Response.onSuccess(note)
  } catch {
    case e: Throwable => Response.onFail(e.getMessage)
  }

  @GetMapping(value = Array("/notes/get/all"))
  def getAllNotes: Response = try {
    Response.onSuccess(repo.findAll().asScala.toArray)
  } catch {
    case e: Throwable => Response.onFail(e.getMessage)
  }
}
