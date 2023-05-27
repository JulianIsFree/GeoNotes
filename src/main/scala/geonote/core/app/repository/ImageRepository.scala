package geonote.core.app.repository

import geonote.core.app.domain.Image
import org.springframework.data.jpa.repository.JpaRepository

import java.util.Optional

trait ImageRepository extends JpaRepository[Image, Long] {
  def findOneByUrl(url: String): Optional[Image]
}
