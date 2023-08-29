package projet.pfe.tms.services;
import projet.pfe.tms.dto.MisajourOpportunityDTO;
import projet.pfe.tms.models.MisajourOpportunity;

import java.util.List; 

public interface MisajourOpportunityService {  
    List<MisajourOpportunity> listMAJRs();
    MisajourOpportunity loadMAJRById(Long id);
    MisajourOpportunityDTO loadMAJRByOpportunityId(Long id);
    List<MisajourOpportunity> getAllMisajoursByOpportunity(Long id);
    MisajourOpportunity addOpportunityMisajour(MisajourOpportunityDTO MisajourDto);
    void deleteOpportunityMisajour(Long id);

}
