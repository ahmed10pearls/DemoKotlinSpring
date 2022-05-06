package demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
	println("Hello World")
	runApplication<DemoApplication>(*args)
}


@RestController
class MessageResource(val service: MessageService){
	/*@GetMapping
	fun index(): List<Message> = listOf(
		Message("1" , "Ahmed!"),
		Message("2", "Hello!"),
	)*/

	@GetMapping
	fun index(): List<Message> = service.findMessages()

	@PostMapping
	fun post(@RequestBody message: Message)
	{
		service.post(message)
	}

}

@Service
class MessageService(val db: MessageRepository) {

	fun findMessages(): List<Message> {
		return db.findMessages()
	}

	fun post(message: Message){
		db.save(message)
	}
}

interface MessageRepository : CrudRepository<Message, String> {

	@Query("select * from messages")
	fun findMessages(): List<Message>

}

@Table("MESSAGES")
data class Message(val id: String?, val text: String)
