package garstka.jakub.foodoman.api.repositories

import garstka.jakub.foodoman.api.v1.model.Producer
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Interfejst wspierający operacje 'crud' z wykorzystaniem 'jpa_repository' dla 'producer'
 *
 */
interface ProducersRepository: JpaRepository<Producer, Long>