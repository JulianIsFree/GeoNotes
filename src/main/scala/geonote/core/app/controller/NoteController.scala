package geonote.core.app.controller

import geonote.core.app.domain.Note
import geonote.core.app.repository.NoteRepository
import geonote.core.app.response.{Response, ResponsePage}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.{GetMapping, RequestParam, RestController}

import java.util.Optional
import scala.jdk.CollectionConverters.CollectionHasAsScala

@RestController
class NoteController {
  @Autowired
  private var repo: NoteRepository = _

  @GetMapping(value = Array("/notes/create/nogeo"))
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

  @GetMapping(value = Array("/notes/create/geo"))
  def createNoteWithGeo(@RequestParam(value = "content", defaultValue = Note.DEFAULT_CONTENT) content: String,
                        @RequestParam(value = "x") xStr: String, @RequestParam(value = "y") yStr: String): Response = try {
    var note = new Note
    note.setContent(content)
    note.setTime(System.currentTimeMillis())

    note.setHasGeo(true)
    note.setX(xStr.toDouble)
    note.setY(yStr.toDouble)

    note = repo.save(note)
    Response.onSuccess(note)
  } catch {
    case e: Throwable => Response.onFail(e.getMessage)
  }

  @GetMapping(value = Array("notes/get/page"))
  def getPage(@RequestParam(value = "page",   defaultValue = "0")   page: String,
              @RequestParam(value = "size",   defaultValue = "10")  size: String): ResponsePage = try {
    val pageable = PageRequest.of(page.toInt, size.toInt)
    val resultPage = repo.findAll(pageable)
    val result = resultPage.getContent
    ResponsePage.onSuccess(resultPage.getTotalPages, page.toInt, result.size(), result.asScala.toArray)
  } catch {
    case e: Throwable => ResponsePage.onFail(e.getMessage)
  }

  @GetMapping(value = Array("/notes/get/all"))
  def getAllNotes: Response = try {
    Response.onSuccess(repo.findAll().asScala.toArray)
  } catch {
    case e: Throwable => Response.onFail(e.getMessage)
  }

  @GetMapping(value = Array("/notes/remove"))
  def removeNote(@RequestParam(value = "idString") idString: String): Response = try {
    val id = idString.toLong
    val note: Optional[Note] = repo.findById(id)
    if (note.isEmpty) throw new Exception(s"No such note id: $idString")
    repo.deleteById(id)
    Response.onSuccess(note.get)
  } catch {
    case e: Throwable => Response.onFail(e.getMessage)
  }
}
