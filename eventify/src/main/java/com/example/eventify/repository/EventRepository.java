package com.example.eventify.repository;

import com.example.eventify.dto.EventSummaryDTO;
import com.example.eventify.model.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface EventRepository  extends JpaRepository<Event, Integer> {

    Optional<Event> findByNameIgnoreCase(String name);

    @Override
    @EntityGraph(attributePaths = {"venue", "categories"})
    Optional<Event> findById(Integer id);

    @Query("""
            select distinct new com.example.eventify.dto.EventSummaryDTO(
                e.name,
                e.eventDate,
                v.name,
                v.city
            )
            from Event e
            join e.venue v
            left join e.categories c
            where (:city is null or lower(v.city) like lower(concat('%', :city, '%')))
              and (:category is null or lower(c.name) like lower(concat('%', :category, '%')))
              and (:minCapacity is null or v.maxCapacity >= :minCapacity)
              and (:fromDate is null or e.eventDate >= :fromDate)
              and (:toDate is null or e.eventDate <= :toDate)
            order by e.eventDate desc, e.id desc
            """)
    Slice<EventSummaryDTO> searchCatalog(@Param("city") String city,
                                         @Param("category") String category,
                                         @Param("minCapacity") Integer minCapacity,
                                         @Param("fromDate") LocalDate fromDate,
                                         @Param("toDate") LocalDate toDate,
                                         Pageable pageable);

    default Slice<EventSummaryDTO> findByCity(String city, Pageable pageable) {
        return searchCatalog(city, null, null, null, null, pageable);
    }

    default Slice<EventSummaryDTO> findByCategory(String category, Pageable pageable) {
        return searchCatalog(null, category, null, null, null, pageable);
    }

    default Slice<EventSummaryDTO> findByCapacity(Integer minCapacity, Pageable pageable) {
        return searchCatalog(null, null, minCapacity, null, null, pageable);
    }

    default Slice<EventSummaryDTO> findByDateBetween(LocalDate fromDate, LocalDate toDate, Pageable pageable) {
        return searchCatalog(null, null, null, fromDate, toDate, pageable);
    }

}
