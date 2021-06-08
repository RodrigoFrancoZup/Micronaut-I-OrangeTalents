package br.com.zup.autores

import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface AutorRepository : JpaRepository<Autor, Long> {

    fun findByEmail(email: String): Optional<Autor>

    @Query("SELECT a FROM Autor a WHERE a.email = :email")
    fun buscaByEmailJPQL(email: String): Optional<Autor>

    @Query(value = "SELECT * FROM Autor a WHERE a.email like :email", nativeQuery = true)
    fun buscaByEmailSQL(email: String): Optional<Autor>
}