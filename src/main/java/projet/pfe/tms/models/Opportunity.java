package projet.pfe.tms.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "opportunity")
 
public class Opportunity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "opportunityid")
        private Long opportunityId;

        @Column(name = "date_created")
        private LocalDateTime dateCreated = LocalDateTime.now();

        @Column(name = "origine_Type_address", length = 100)
        private String origineAddressType;

        @Column(name = "origine_address", length = 200)
        private String origineAddress;

        @Column(name = "origine_country", length = 30)
        private String origineCountry;

        @Column(name = "destination_Type_address", length = 100)
        private String destinationAddressType;

        @Column(name = "destination_address", length = 200)
        private String destinationAddress;

        @Column(name = "destination_country", length = 30)
        private String destinationCountry;

        @Column(name = "start_date")
        private LocalDateTime startDate;

        @Column(name = "end_date")
        private LocalDateTime endDate;

        @Column(name = "expected_revenue")
        private double expectedRevenue;

        @Column(name = "expected_big_profit")
        private double expectedBigProfit;

        @Column(name = "volume", length = 30)
        private String volume;

        @Column(name = "volume_unit", length = 15)
        private String volumeUnit;

        @Column(name = "activity", length = 100)
        private String activity;

        @Column(name = "service", length = 100)
        private String service;

        @Column(name = "shipment_type", length = 100)
        private String shipmentType;

        @Column(name = "shipping_condition", length = 100)
        private String shippingCondition;

        @Column(name = "competition", length = 30)
        private String competition;

        @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
                CascadeType.DETACH })
        @JsonIgnore
        @JsonBackReference
        @JoinColumn(name = "staff_id", referencedColumnName = "staffid")
        private Staff staff;
        

        @OneToMany(mappedBy = "opportunity", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
        @JsonIgnore
        private List<MisajourOpportunity> MJrsOpportunity;
        
        @ManyToOne(optional = false)
        @JsonIgnore
        @JsonBackReference
        @JoinColumn(name = "client_id", referencedColumnName = "userid")
        private Client client;
}