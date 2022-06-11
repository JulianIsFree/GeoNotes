package geonote.core.app

import geonote.core.app.domain.Note
import geonote.core.app.repository.NoteRepository
import org.springframework.boot.{CommandLineRunner, SpringApplication}
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class AppTest {
  @Bean
  def runAppTest(repository: NoteRepository): CommandLineRunner = new CommandLineRunner {
    override def run(args: String*): Unit = {
      var n0 = new Note
      n0.setContent("Hello there")
      n0 = repository.save(n0)

      var n1 = new Note
      n1.setContent("Bye there")
      n1 = repository.save(n1)

      var n2 = new Note
      n2.setContent("hellolleh")
      n2 = repository.save(n2)

      repository.findAll.forEach(System.out.println(_))
    }
  }
}


object AppTest {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[AppTest], args: _*)
  }
}
