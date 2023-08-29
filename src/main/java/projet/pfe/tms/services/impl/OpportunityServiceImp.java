package projet.pfe.tms.services.impl;

import org.jboss.jandex.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.pfe.tms.dto.ClientDTO;
import projet.pfe.tms.dto.OpportunityDTO;
import projet.pfe.tms.models.*;
import projet.pfe.tms.repositories.OpportunityRepo;
import projet.pfe.tms.repositories.ClientRepo;
import projet.pfe.tms.services.OpportunityService;
import projet.pfe.tms.services.StaffService;
import projet.pfe.tms.services.ClientService;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OpportunityServiceImp implements OpportunityService {

    private final OpportunityRepo opportunityRepo;
    private final ClientRepo clientRepo;
    private final ClientService clientService;
    private final StaffService staffService;
    
    @Autowired
    public OpportunityServiceImp(OpportunityRepo opportunityRepo,ClientRepo clientRepo,StaffService staffService,ClientService clientService ){
        this.opportunityRepo = opportunityRepo;
        this.clientRepo = clientRepo;
        this.staffService = staffService;
        this.clientService= clientService;

    }

    @Transactional
    @Override
    public Opportunity addNewOpportunity(OpportunityDTO opportunityDTO) {
        Opportunity opportunity = new Opportunity();
        
        opportunity.setOrigineAddressType(opportunityDTO.getOrigineAddressType());
        opportunity.setOrigineCountry(opportunityDTO.getOrigineCountry());
        opportunity.setOrigineAddress(opportunityDTO.getOrigineAddress());
        opportunity.setDestinationAddressType(opportunityDTO.getDestinationAddressType());
        opportunity.setDestinationCountry(opportunityDTO.getDestinationCountry());
        opportunity.setDestinationAddress(opportunityDTO.getDestinationAddress());
        opportunity.setStartDate(opportunityDTO.getStartDate());
        opportunity.setEndDate(opportunityDTO.getEndDate());
        opportunity.setVolume(opportunityDTO.getVolume());
        opportunity.setVolumeUnit(opportunityDTO.getVolumeUnit());
        opportunity.setActivity(opportunityDTO.getActivity());
        opportunity.setService(opportunityDTO.getService());
        opportunity.setShipmentType(opportunityDTO.getShipmentType());
        opportunity.setShippingCondition(opportunityDTO.getShippingCondition());
        opportunity.setCompetition(opportunityDTO.getCompetition());
        opportunity.setExpectedRevenue(opportunityDTO.getExpectedRevenue());
        opportunity.setExpectedBigProfit(opportunityDTO.getExpectedBigProfit());
        opportunity.setClient(this.clientService.loadClientById(opportunityDTO.getClientid()));
        opportunity.setStaff(this.staffService.loadUserById(opportunityDTO.getStaffid()));
        return this.opportunityRepo.save(opportunity);
    }

    @Transactional
    @Override
    public Opportunity updateOpportunity(Long id, OpportunityDTO opportunityDTO) {
        Opportunity opportunity = this.opportunityRepo.findById(id).orElse(null);

        if(opportunity != null){

            opportunity = this.updateData(opportunity, opportunityDTO);


            return this.opportunityRepo.save(opportunity);
        }
        return null;
    }

    @Override
    public Opportunity updateData(Opportunity opportunity, OpportunityDTO opportunityDTO) {
        opportunity.setOrigineAddressType(opportunityDTO.getOrigineAddressType());
        opportunity.setOrigineCountry(opportunityDTO.getOrigineCountry());
        opportunity.setOrigineAddress(opportunityDTO.getOrigineAddress());
        opportunity.setDestinationAddressType(opportunityDTO.getDestinationAddressType());
        opportunity.setDestinationCountry(opportunityDTO.getDestinationCountry());
        opportunity.setDestinationAddress(opportunityDTO.getDestinationAddress());
        opportunity.setStartDate(opportunityDTO.getStartDate());
        opportunity.setEndDate(opportunityDTO.getEndDate());
        opportunity.setVolume(opportunityDTO.getVolume());
        opportunity.setVolumeUnit(opportunityDTO.getVolumeUnit());
        opportunity.setActivity(opportunityDTO.getActivity());
        opportunity.setService(opportunityDTO.getService());
        opportunity.setShipmentType(opportunityDTO.getShipmentType());
        opportunity.setShippingCondition(opportunityDTO.getShippingCondition());
        opportunity.setCompetition(opportunityDTO.getCompetition());
        opportunity.setExpectedRevenue(opportunityDTO.getExpectedRevenue());
        opportunity.setExpectedBigProfit(opportunityDTO.getExpectedBigProfit());
        opportunity.setClient(this.clientService.loadClientById(opportunityDTO.getClientid()));
        opportunity.setStaff(this.staffService.loadUserById(opportunityDTO.getStaffid()));
        

        return opportunity;
    }


    @Override
    public List<Opportunity> listOpportunities() {
        return this.opportunityRepo.findAll();
    }

    @Override
    public List<OpportunityDTO> listOpportunitiesDTO() {
        List<Opportunity> opportunities = this.opportunityRepo.findAll();
        List<OpportunityDTO> opportunitiesDTO = opportunities.stream()
                .map(this::convertToOpportunityDTO)
                .collect(Collectors.toList());
        return opportunitiesDTO; // Return the list of ClientDTOs
    }

    public OpportunityDTO convertToOpportunityDTO(Opportunity opportunity) {
         if(opportunity == null){
            return null;
        }
        OpportunityDTO opportunityDTO = new OpportunityDTO();
        opportunityDTO.setDateCreated(opportunity.getDateCreated());
        opportunityDTO.setOpportunityid(opportunity.getOpportunityId());
        opportunityDTO.setOrigineAddressType(opportunity.getOrigineAddressType());
        opportunityDTO.setOrigineCountry(opportunity.getOrigineCountry());
        opportunityDTO.setOrigineAddress(opportunity.getOrigineAddress());
        opportunityDTO.setDestinationAddressType(opportunity.getDestinationAddressType());
        opportunityDTO.setDestinationCountry(opportunity.getDestinationCountry());
        opportunityDTO.setDestinationAddress(opportunity.getDestinationAddress());
        opportunityDTO.setStartDate(opportunity.getStartDate());
        opportunityDTO.setEndDate(opportunity.getEndDate());
        opportunityDTO.setVolume(opportunity.getVolume());
        opportunityDTO.setVolumeUnit(opportunity.getVolumeUnit());
        opportunityDTO.setActivity(opportunity.getActivity());
        opportunityDTO.setService(opportunity.getService());
        opportunityDTO.setShipmentType(opportunity.getShipmentType());
        opportunityDTO.setShippingCondition(opportunity.getShippingCondition());
        opportunityDTO.setCompetition(opportunity.getCompetition());
        opportunityDTO.setExpectedRevenue(opportunity.getExpectedRevenue());
        opportunityDTO.setExpectedBigProfit(opportunity.getExpectedBigProfit());
        
        if(opportunity.getStaff() != null) {
            opportunityDTO.setStaffid(opportunity.getStaff().getStaffId());
        }
        if(opportunity.getClient() != null) {
            opportunityDTO.setClientid(opportunity.getClient().getClientId());
            opportunityDTO.setClientName(opportunity.getClient().getCompany());
        }
        
        
        return opportunityDTO;
    }

    @Override
    public Opportunity loadOpportunityById(Long id) {
        return this.opportunityRepo
                .findById(id)
                .orElse(null);
    }

    @Override
    public OpportunityDTO loadOpportunityByOpportunityId(Long id) {
        Opportunity opportunity = this.opportunityRepo.findById(id).orElse(null);
        if(opportunity == null){
            return null;
        }
        OpportunityDTO opportunityDTO = new OpportunityDTO();
        opportunityDTO.setOpportunityid(opportunity.getOpportunityId());
        opportunityDTO.setDateCreated(opportunity.getDateCreated());
        opportunityDTO.setOrigineAddressType(opportunity.getOrigineAddressType());
        opportunityDTO.setOrigineCountry(opportunity.getOrigineCountry());
        opportunityDTO.setOrigineAddress(opportunity.getOrigineAddress());
        opportunityDTO.setDestinationAddressType(opportunity.getDestinationAddressType());
        opportunityDTO.setDestinationCountry(opportunity.getDestinationCountry());
        opportunityDTO.setDestinationAddress(opportunity.getDestinationAddress());
        opportunityDTO.setStartDate(opportunity.getStartDate());
        opportunityDTO.setEndDate(opportunity.getEndDate());
        opportunityDTO.setVolume(opportunity.getVolume());
        opportunityDTO.setVolumeUnit(opportunity.getVolumeUnit());
        opportunityDTO.setActivity(opportunity.getActivity());
        opportunityDTO.setService(opportunity.getService());
        opportunityDTO.setShipmentType(opportunity.getShipmentType());
        opportunityDTO.setShippingCondition(opportunity.getShippingCondition());
        opportunityDTO.setCompetition(opportunity.getCompetition());
        opportunityDTO.setExpectedRevenue(opportunity.getExpectedRevenue());
        opportunityDTO.setExpectedBigProfit(opportunity.getExpectedBigProfit());
        if(opportunity.getStaff() != null) {
            opportunityDTO.setStaffid(opportunity.getStaff().getStaffId());
        }
        if(opportunity.getClient() != null) {
            opportunityDTO.setClientid(opportunity.getClient().getClientId());
            opportunityDTO.setClientName(opportunity.getClient().getCompany());
        }
        
        return opportunityDTO;
    }

}
