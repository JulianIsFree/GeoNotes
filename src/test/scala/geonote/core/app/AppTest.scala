package geonote.core.app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class AppTest {}


object AppTest {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[AppTest], args: _*)
  }
}
