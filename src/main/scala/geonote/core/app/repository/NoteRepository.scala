package geonote.core.app.repository

import geonote.core.app.Note
import org.springframework.data.jpa.repository.JpaRepository

trait NoteRepository extends JpaRepository[Note, Long] {
  def findById(id: Long): Note
}
