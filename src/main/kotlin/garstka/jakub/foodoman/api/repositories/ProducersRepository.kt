package garstka.jakub.foodoman.api.repositories

import garstka.jakub.foodoman.api.v1.model.Producer
import org.springframework.data.jpa.repository.JpaRepository

interface ProducersRepository: JpaRepository<Producer, Long> {
}