package projet.pfe.tms.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "misajour_opportunity")
public class MisajourOpportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "misajourid")
    private Long misajourId;

    @Column(name = "status", nullable = false, length = 100)
    private String status;

    @Column(name = "datecreated", nullable = false)
    private LocalDateTime dateCreated = LocalDateTime.now();

    @Column(name = "comment", length = 255)
    private String comment;


    @ManyToOne(optional = false)
    @JsonIgnore
    @JsonBackReference
    @JoinColumn(name = "opportunity_id", referencedColumnName = "opportunityid")
    private Opportunity opportunity;

}
