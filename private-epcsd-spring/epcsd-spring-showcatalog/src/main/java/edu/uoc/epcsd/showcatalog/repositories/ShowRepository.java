package edu.uoc.epcsd.showcatalog.repositories;

import edu.uoc.epcsd.showcatalog.entities.Category;
import edu.uoc.epcsd.showcatalog.entities.Performance;
import edu.uoc.epcsd.showcatalog.entities.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long> {
    List<Show> findShowsByName(String name);

   @Query("SELECT s FROM Show s JOIN s.categories c WHERE c.name LIKE %?1%")
    List<Show> findShowsByCategoryName(String name);

   Show findShowsById(Long id);
}
