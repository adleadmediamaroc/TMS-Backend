package projet.pfe.tms.services;

import org.springframework.http.ResponseEntity;
import projet.pfe.tms.dto.ClientDTO;
import projet.pfe.tms.models.Client;
import projet.pfe.tms.models.Staff;
import projet.pfe.tms.dto.OpportunityDTO;
import projet.pfe.tms.models.Opportunity;

import java.util.List;

public interface OpportunityService {


    Opportunity addNewOpportunity(OpportunityDTO opportunityDTO);
    Opportunity updateOpportunity(Long id, OpportunityDTO opportunityDTO);
    Opportunity updateData(Opportunity opportunity, OpportunityDTO opportunityDto);
    List<OpportunityDTO> listOpportunitiesDTO();
    List<Opportunity> listOpportunities();
    Opportunity loadOpportunityById(Long id);
    OpportunityDTO loadOpportunityByOpportunityId(Long id);

    

    //Client addNewClient(ClientDTO clientDto);
    //Client updateClient(Long id, ClientDTO clientDTO);
    //Client updateData(Client client, ClientDTO clientDto);
    //void deleteClient(Long id);
    //List<ClientDTO> listClientsDTO();
    //List<Client> listClients();
    //Client loadClientByEmail(String email);
    //Client loadClientById(Long id);
    //ClientDTO loadClientByClientId(Long id);
    //Client loadClientByCompany(String company);
    //Client loadClientByIceClient(String iceClient);
    //Client affectCommercialToClient(Long idClient, Long idStaff);
    //Client deleteCommercialOfClient(Long id);

    //int countTotalClients();
    //int countActiveClients();
    //int countInactiveClients();
    //Client updateClientActiveStatus(Long clientId, boolean active);

}
