package kbl.test.hdj.repository;

import kbl.test.hdj.entity.Visit;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    @Override
    @EntityGraph(attributePaths = {"patient","hospital"})
    Optional<Visit> findById(Long id);

    @Override
    List<Visit> findAll();
}
