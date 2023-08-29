package projet.pfe.tms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projet.pfe.tms.models.Staff;
import projet.pfe.tms.models.Client;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpportunityDTO {
    private Long opportunityid;
    private long clientid;
    private String clientName;
    private long staffid;
    private LocalDateTime dateCreated;
    private String origineAddressType;
    private String origineAddress;
    private String origineCountry;
    private String destinationAddressType;
    private String destinationAddress;
    private String destinationCountry;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String volume;
    private String volumeUnit;
    private String activity;
    private String service;
    private String shipmentType;
    private String shippingCondition;
    private String competition;
    private double expectedRevenue;
    private double expectedBigProfit;


    
}
