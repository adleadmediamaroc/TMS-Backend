package projet.pfe.tms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.pfe.tms.dto.OpportunityDTO;
import projet.pfe.tms.models.Opportunity;
import projet.pfe.tms.services.OpportunityService;
import projet.pfe.tms.dto.ClientDTO;
import projet.pfe.tms.models.Client;
import projet.pfe.tms.services.ClientService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/opportunities")
public class OpportunityController { 

    private final OpportunityService opportunityService;

    @Autowired
    public OpportunityController(OpportunityService opportunityService){
        this.opportunityService = opportunityService;
    }

    @GetMapping("/")
    public List<Opportunity> getAllOpportunities(){
        return this.opportunityService.listOpportunities();
    }
    
    @GetMapping("/dto")
    public List<OpportunityDTO> getAllOpportunitiesDTO(){
        return this.opportunityService.listOpportunitiesDTO();
    }

    @GetMapping("/{id}")
    public OpportunityDTO getOpportunityById(@PathVariable Long id){
        return this.opportunityService.loadOpportunityByOpportunityId(id);
    }

    @PostMapping("/add-opportunity")
    public ResponseEntity<Map<String, String>> addNewOpportunity(@RequestBody OpportunityDTO opportunityDto) {
    Map<String, String> response = new HashMap<>();

    if (this.opportunityService.addNewOpportunity(opportunityDto) != null) {
        response.put("status", "success");
        response.put("message", "L\'Opportunité a été ajouté avec succès");
        return ResponseEntity.ok(response);
    }

    response.put("status", "error");
    response.put("message", "Une erreur s'est produite lors de l'ajout d'Opportunité");
    return ResponseEntity.badRequest().body(response);
    }
   
    @PostMapping("/update-opportunity/{id}")
    public ResponseEntity<Map<String, String>> updateopportunity(@PathVariable Long id, @RequestBody OpportunityDTO opportunityDto){
        Map<String, String> response = new HashMap<>();
        if(this.opportunityService.updateOpportunity(id, opportunityDto) != null ){
            response.put("status", "success");
            response.put("message", "L\'opportunité a été modifié avec succès");
            return ResponseEntity.ok(response);
        }
        response.put("status", "error");
        response.put("message", "Une erreur s'est produite lors de la modification d\'opportunité");
        return ResponseEntity.badRequest().body(response);
    }
}

    

    

