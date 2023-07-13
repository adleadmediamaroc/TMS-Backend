package projet.pfe.tms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.pfe.tms.dto.ClientDTO;
import projet.pfe.tms.models.Client;
import projet.pfe.tms.services.ClientService;

import java.util.List;

@RestController
@RequestMapping("api/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping("/")
    public List<Client> getAllClients(){
        return this.clientService.listClients();
    }

    @GetMapping("/{id}")
    public ClientDTO getClientById(@PathVariable Long id){
        return this.clientService.loadClientByClientId(id);
    }

    @PostMapping("/add-client")
    public ResponseEntity<String> addNewClient(@RequestBody ClientDTO clientDto){
        if (this.clientService.loadClientByEmail(clientDto.getEmail()) != null)
            return ResponseEntity.badRequest().body("Cet email est déjà utilisé");

        if(this.clientService.addNewClient(clientDto) != null)
            return ResponseEntity.ok("Le client a été ajouté avec succès");

        return ResponseEntity.badRequest().body("Une erreur s'est produite lors de l'ajout du client");
    }

    @PutMapping("/update-client/{id}")
    public ResponseEntity<String> updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDto){

        if(this.clientService.updateClient(id, clientDto) != null )
            return ResponseEntity.ok("Le client a été modifié avec succès");

        return ResponseEntity.badRequest().body("Une erreur s'est produite lors de la modification du client");
    }

    @DeleteMapping("/delete-client/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id){
        this.clientService.deleteClient(id);
        return ResponseEntity.ok("Le client a été supprimé avec succès");
    }

    @PutMapping("/update-client/{id}/affect-commercial/{staffId}")
    public ResponseEntity<String> affectCommercialToClient(@PathVariable Long id, @PathVariable Long staffId){
        if(this.clientService.affectCommercialToClient(id, staffId) != null )
            return ResponseEntity.ok("Le commercial a été affecté au client avec succès");

        return ResponseEntity.badRequest().body("Une erreur s'est produite lors de l'affectation du commercial au client");
    }

    @DeleteMapping("/update-client/{id}/delete-commercial")
    public ResponseEntity<String> deleteCommercialOfClient(@PathVariable Long id){
        if(this.clientService.deleteCommercialOfClient(id) != null)
            return ResponseEntity.ok("Le commercial a été supprimé avec succès");
        return ResponseEntity.badRequest().body("Une erreur s'est produite lors de la suppression du commercial");
    }

    @GetMapping("/Total-Clients")
    public int countTotalClients() {
        return this.clientService.countTotalClients();
    }

    @GetMapping("/Total-Active-Clients")
    public int countActiveClients() {
        return this.clientService.countActiveClients();
    }

    @GetMapping("/Total-Inactive-Clients")
    public int countInactiveClients() {
        return this.clientService.countInactiveClients();
    }

    @PutMapping("/{clientId}/active")
    public ResponseEntity<String> updateClientActiveStatus(@PathVariable Long clientId, @RequestParam boolean active) {
        Client client = clientService.updateClientActiveStatus(clientId, active);
        if (client != null) {
            return ResponseEntity.ok("Le statut de client a été mis à jour avec succès");
        } else {
            return ResponseEntity.badRequest().body("Echec de la mise à jour du statut de client");
        }
    }
}
