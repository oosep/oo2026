package ee.joosep.decathlon.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tulemus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String spordiala;
    private Double vaartus;
    private Double punktid;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sportlane_id")
    private Sportlane sportlane;
}