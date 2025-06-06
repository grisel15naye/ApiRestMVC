package pe.idat.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "carrera")
public class Carrera implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carreraId;

    @Column(nullable = false, length = 50)
    private String descC;

    @Column(nullable = false)
    private Integer duracion;

    @ManyToMany(mappedBy = "itemsCarrera", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Alumno> itemsAlumno =new HashSet<>();
    public Carrera(){}

    public Carrera(Long carreraId, String descC, Integer duracion, Set<Alumno> itemsAlumno) {
        super();
        this.carreraId = carreraId;
        this.descC = descC;
        this.duracion = duracion;
        this.itemsAlumno = itemsAlumno;
    }

    public Long getCarreraId() {
        return carreraId;
    }

    public void setCarreraId(Long carreraId) {
        this.carreraId = carreraId;
    }

    public String getDescC() {
        return descC;
    }

    public void setDescC(String descC) {
        this.descC = descC;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Set<Alumno> getItemsAlumno() {
        return itemsAlumno;
    }

    public void setItemsAlumno(Set<Alumno> itemsAlumno) {
        this.itemsAlumno = itemsAlumno;
    }
}
