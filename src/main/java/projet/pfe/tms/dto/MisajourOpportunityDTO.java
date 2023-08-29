package projet.pfe.tms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime; 

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MisajourOpportunityDTO {

    private String status;
    private LocalDateTime dateCreated;
    private String comment;
    private Long opportunityId;
    

}
