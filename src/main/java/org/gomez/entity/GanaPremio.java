package org.gomez.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(GanaPremioId.class)
@Table(name = "ganaPremio")
public class GanaPremio {
    // declaracion de atributos y sus anotaciones
    @Id
    @ManyToOne
    @JoinColumn(name = "CodPelicula")
    private Pelicula pelicula;

    @Id
    @ManyToOne
    @JoinColumn (name = "CodPremio")
    private Premio premio;

    @Column(nullable = false,name = "Anyo")
    private Integer year;

    @Override
    public String toString() {
        return "| pelicula: " + pelicula +
                " ~ gano el premio " + premio + " ~ en el año=" + year + " |"
                ;
    }

}
