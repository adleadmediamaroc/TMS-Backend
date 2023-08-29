package projet.pfe.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.pfe.tms.models.MisajourOpportunity;

import java.util.List; 
import java.util.Optional;

@Repository
public interface MisajourOpportunityRepo extends JpaRepository<MisajourOpportunity, Long> {
    List<MisajourOpportunity> findByOpportunity_OpportunityId(Long opportunityId);
    MisajourOpportunity findBymisajourId(Long MisajourId);
    
}
