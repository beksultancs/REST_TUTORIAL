package rest_tutorial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rest_tutorial.models.Student;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Beksultan
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    @Query("select s from Student s where s.email = :email1")
    Optional<Student> findByEmail(@Param("email1") String email);

    @Query("select case when count(s) > 0 then true else false end " +
            "from Student s where s.email = ?1")
    boolean existsByEmail(String email);
}
