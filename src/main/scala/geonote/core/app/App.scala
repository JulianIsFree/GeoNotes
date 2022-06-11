package geonote.core.app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * Logic is concentrated in NoteController
 */
@SpringBootApplication
class App {}

object App {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[App], args: _*)
  }
}
