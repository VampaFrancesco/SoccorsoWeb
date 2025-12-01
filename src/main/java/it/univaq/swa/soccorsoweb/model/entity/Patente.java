package it.univaq.swa.soccorsoweb.model.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "patente")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String tipo;

    @Column(columnDefinition = "TEXT")
    private String descrizione;
}
