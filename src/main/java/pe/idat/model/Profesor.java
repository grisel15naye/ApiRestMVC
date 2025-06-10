package pe.idat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "profesor")
public class Profesor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profesor_id", nullable = false)
    private Long profesorId;

    @Column(nullable = false, length =50 )
    private String nombreP;

    @Column(nullable = false, length =50 )
    private String direccion;

    @Column(nullable = false, length =9 )
    private String telefono;

    @ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE})
    @JsonIgnore
    @JoinTable(
            name="profesor_tecnologia",
            joinColumns=@JoinColumn(name="profesor_id",nullable=false,
                    foreignKey=@ForeignKey(foreignKeyDefinition="foreign key(profesor_id) references profesor(profesor_id)")),
            inverseJoinColumns=@JoinColumn(name="alumno_id",nullable=false,
                    foreignKey=@ForeignKey(foreignKeyDefinition="foreign key(alumno_id) references alumno(alumno_id)")))
    private Set<Alumno> itemsAlumno=new HashSet<>();

    public Profesor(){

    }

    public Profesor(Long profesorId, String nombreP, String direccion, String telefono, Set<Alumno> itemsAlumno) {
        super();
        this.profesorId = profesorId;
        this.nombreP = nombreP;
        this.direccion = direccion;
        this.telefono = telefono;
        this.itemsAlumno = itemsAlumno;
    }

    public Set<Alumno> getItemsAlumno() {
        return itemsAlumno;
    }

    public void setItemsAlumno(Set<Alumno> itemsAlumno) {
        this.itemsAlumno = itemsAlumno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public Long getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(Long profesorId) {
        this.profesorId = profesorId;
    }

    public void addAlumno(Alumno alumno){
        if (!itemsAlumno.contains(alumno)){
            itemsAlumno.add(alumno);
        }
    }
}
