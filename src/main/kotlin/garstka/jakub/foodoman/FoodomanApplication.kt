package garstka.jakub.foodoman
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FoodomanApplication

fun main(args: Array<String>) {
    runApplication<FoodomanApplication>(*args)
}

