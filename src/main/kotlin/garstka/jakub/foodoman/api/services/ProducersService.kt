package garstka.jakub.foodoman.api.services

import garstka.jakub.foodoman.api.v1.model.Producer

/**
 * Interfejst wspierający operacje 'crud' dla obiektów typu 'producer'
 *
 */
interface ProducersService: CrudService<Producer, Long>