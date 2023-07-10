package org.gomez.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "directores")
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CodDirector")
    private Integer id;

    @Column(nullable = false,name = "Nombre")
    private String nombre;

    @Column(name = "FNacimiento")
    private LocalDateTime fNacimiento;
    @Column(name = "LNacimiento")
    private String lNacimiento;

    @Column(name = "Nacionalidad")
    private String nacionalidad;

    @Column(name = "FMuerte")
    private LocalDateTime fMuerte;
    @Column(name = "LMuerte")
    private String lMuerte;

    @Override
    public String toString() {
        DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("dd,MM,yyyy");
        String formattedFechaNacimiento = (fNacimiento != null) ? fNacimiento.format(dtformat) : null;
        String formattedFechaMuerte = (fMuerte != null) ? fMuerte.format(dtformat) : null;
        return "| id: " + id +
                " ~ nombre: " + nombre +
                " ~ fechaNacimiento: " + formattedFechaNacimiento +
                " ~ lugarNacimiento: " + lNacimiento +
                " ~ nacionalidad: " + nacionalidad +
                " ~ fechaMuerte: " + formattedFechaMuerte +
                " ~ lugarMuerte: " + lMuerte + " |";
    }

}
