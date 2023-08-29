package projet.pfe.tms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.pfe.tms.dto.MisajourOpportunityDTO;
import projet.pfe.tms.models.Opportunity;
import projet.pfe.tms.models.MisajourOpportunity;
import projet.pfe.tms.repositories.OpportunityRepo;
import projet.pfe.tms.repositories.MisajourOpportunityRepo;
import projet.pfe.tms.services.OpportunityService;
import projet.pfe.tms.services.MisajourOpportunityService;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MisajourOpportunityServiceImp implements MisajourOpportunityService {
    private final MisajourOpportunityRepo MisajourOpportunityRepo;
    private final OpportunityService OpportunityService;

    @Autowired
    public MisajourOpportunityServiceImp(MisajourOpportunityRepo MisajourOpportunityRepo, OpportunityService OpportunityService) {
        this.MisajourOpportunityRepo = MisajourOpportunityRepo;
        this.OpportunityService = OpportunityService;
    }

    @Autowired
    private OpportunityRepo OpportunityRepo;

    @Transactional
    
    @Override
    public MisajourOpportunity addOpportunityMisajour(MisajourOpportunityDTO MisajourDto) {

        MisajourOpportunity MisajourOpportunity = new MisajourOpportunity();

        MisajourOpportunity.setStatus(MisajourDto.getStatus());
        MisajourOpportunity.setComment(MisajourDto.getComment());
        Long opportunityId = MisajourDto.getOpportunityId();
        Opportunity Opportunity = OpportunityRepo.findById(opportunityId)
                .orElseThrow(() -> new NoSuchElementException("Opportunity not found with id: " + opportunityId));
        MisajourOpportunity.setOpportunity(Opportunity);

        MisajourOpportunityRepo.save(MisajourOpportunity);

        return MisajourOpportunity;
    }

    @Override
    public void deleteOpportunityMisajour(Long id) {
        MisajourOpportunityRepo.deleteById(id);
    }
    
    @Override
    public List<MisajourOpportunity> listMAJRs() {
        return this.MisajourOpportunityRepo.findAll();
    }

    @Override
    public MisajourOpportunity loadMAJRById(Long id) {
        return this.MisajourOpportunityRepo
                .findById(id)
                .orElse(null);
    }

    @Override
    public MisajourOpportunityDTO loadMAJRByOpportunityId(Long id) {

        MisajourOpportunity misajour = this.loadMAJRById(id);

        if(misajour != null){

            MisajourOpportunityDTO misajourDTO = new MisajourOpportunityDTO();
            misajourDTO.setStatus(misajour.getStatus());
            misajourDTO.setComment(misajour.getComment());
            misajourDTO.setDateCreated(misajour.getDateCreated());

            misajourDTO.setOpportunityId(misajour.getOpportunity().getOpportunityId());

            return misajourDTO;
        }
        return null;
    }

    @Override
    public List<MisajourOpportunity> getAllMisajoursByOpportunity(Long id) {
        Opportunity Opportunity = OpportunityRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Opportunity not found with id: " + id));
        return Opportunity.getMJrsOpportunity();
    }

}