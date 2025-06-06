package pe.idat.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "materia")
public class Materia implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long materiaId;
    @Column(nullable = false, length = 50)
    private String descC;

    @Column(nullable = false)
    private Integer creditos;

    @ManyToMany(mappedBy = "itemsMateria", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Alumno> itemsAlumno =new HashSet<>();
    public Materia(){}

    public Long getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(Long materiaId) {
        this.materiaId = materiaId;
    }

    public String getDescC() {
        return descC;
    }

    public void setDescC(String descC) {
        this.descC = descC;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public Set<Alumno> getItemsAlumno() {
        return itemsAlumno;
    }

    public void setItemsAlumno(Set<Alumno> itemsAlumno) {
        this.itemsAlumno = itemsAlumno;
    }

    public Materia(Long materiaId, String descC, Integer creditos, Set<Alumno> itemsAlumno) {
        super();
        this.materiaId = materiaId;
        this.descC = descC;
        this.creditos = creditos;
        this.itemsAlumno = itemsAlumno;

    }
}
