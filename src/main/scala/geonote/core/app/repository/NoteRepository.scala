package geonote.core.app.repository

import geonote.core.app.domain.Note
import org.springframework.data.jpa.repository.JpaRepository


// Specification of interface for interaction with db
trait NoteRepository extends JpaRepository[Note, Long]
