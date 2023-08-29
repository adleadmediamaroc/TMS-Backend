package projet.pfe.tms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import projet.pfe.tms.dto.MisajourOpportunityDTO;
import projet.pfe.tms.models.MisajourOpportunity;
import projet.pfe.tms.services.MisajourOpportunityService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/misajours-opportunity")
public class MisajourOpportunityController {

    private final MisajourOpportunityService MisajourOpportunityService;

    @Autowired
    public MisajourOpportunityController(MisajourOpportunityService MisajourOpportunityService){
        this.MisajourOpportunityService = MisajourOpportunityService;
    }

    @GetMapping("/")
    public List<MisajourOpportunity> getAllMisajoursOfOpportunity(){
        return this.MisajourOpportunityService.listMAJRs();
    }

    @GetMapping("/{id}")
    public MisajourOpportunityDTO getMAJRByOpportunityId(@PathVariable Long id){
        return this.MisajourOpportunityService.loadMAJRByOpportunityId(id);
    }
 
    @PostMapping("/Opportunity/{id}/misajours")
    public ResponseEntity<String> addOpportunityMisajourByOpportunityId(@PathVariable Long id, @RequestBody MisajourOpportunityDTO opportunityDto) {
        opportunityDto.setOpportunityId(id);
        if (MisajourOpportunityService.addOpportunityMisajour(opportunityDto) != null) {
            return ResponseEntity.ok("opportunity's update added successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to add opportunity's update");
        }
    }

    @DeleteMapping("/delete-Opportunity-misajour/{id}")
    public ResponseEntity<String> deleteOpportunityMisajour(@PathVariable Long id) {
        MisajourOpportunityService.deleteOpportunityMisajour(id);
        return ResponseEntity.ok("opportunity's update deleted succesfully");
    }

    @GetMapping("/Opportunity/{id}/misajours")
    public ResponseEntity<List<MisajourOpportunity>> getAllMisajoursByOpportunity(@PathVariable Long id) {
        List<MisajourOpportunity> OpportunityMisajoursList = MisajourOpportunityService.getAllMisajoursByOpportunity(id);
        if (OpportunityMisajoursList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(OpportunityMisajoursList);
        }
    }

}
