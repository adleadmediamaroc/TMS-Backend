package projet.pfe.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projet.pfe.tms.models.Opportunity;

import java.util.Optional;

@Repository
public interface OpportunityRepo extends JpaRepository<Opportunity, Long> {

    @Override
    Optional<Opportunity> findById(Long id);
   

}

