package pe.idat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "alumno")
public class Alumno implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long alumnoId;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false)
    private Integer edad;

    @Column(nullable = false)
    private Integer semestre;

    @Column(nullable = false, length = 20)
    private String genero;

    @ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(
            name="alumno_carrera",
            joinColumns=@JoinColumn(name="alumno_id",nullable=false,
                    foreignKey=@ForeignKey(foreignKeyDefinition="foreign key(alumno_id) references alumno(alumno_id)")),
            inverseJoinColumns=@JoinColumn(name="carrera_id",nullable=false,
                    foreignKey=@ForeignKey(foreignKeyDefinition="foreign key(carrera_id) references carrera(carrera_id)")))
    private Set<Carrera>itemsCarrera=new HashSet<>();

    @JsonIgnore
    @ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(
            name="alumno_materia",
            joinColumns=@JoinColumn(name="alumno_id",nullable=false,
                    foreignKey=@ForeignKey(foreignKeyDefinition="foreign key(alumno_id) references alumno(alumno_id)")),
            inverseJoinColumns=@JoinColumn(name="materia_id",nullable=false,
                    foreignKey=@ForeignKey(foreignKeyDefinition="foreign key(materia_id) references materia(materia_id)")))
    private Set<Materia>itemsMateria=new HashSet<>();

    @ManyToMany(mappedBy = "itemsAlumno", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Profesor> itemsProfesor =new HashSet<>();

    public Alumno(){

    }

    public Alumno(Long alumnoId, String nombre, Integer edad, Integer semestre, String genero, Set<Carrera> itemsCarrera, Set<Materia> itemsMateria, Set<Profesor> itemsProfesor) {
        this.alumnoId = alumnoId;
        this.nombre = nombre;
        this.edad = edad;
        this.semestre = semestre;
        this.genero = genero;
        this.itemsCarrera = itemsCarrera;
        this.itemsMateria = itemsMateria;
        this.itemsProfesor = itemsProfesor;
    }

    public Long getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(Long alumnoId) {
        this.alumnoId = alumnoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Set<Carrera> getItemsCarrera() {
        return itemsCarrera;
    }

    public void setItemsCarrera(Set<Carrera> itemsCarrera) {
        this.itemsCarrera = itemsCarrera;
    }

    public Set<Materia> getItemsMateria() {
        return itemsMateria;
    }

    public void setItemsMateria(Set<Materia> itemsMateria) {
        this.itemsMateria = itemsMateria;
    }

    public Set<Profesor> getItemsProfesor() {
        return itemsProfesor;
    }

    public void setItemsProfesor(Set<Profesor> itemsProfesor) {
        this.itemsProfesor = itemsProfesor;
    }

    public void addCarrera(Carrera carrera){
        if (!itemsCarrera.contains(carrera)){
            itemsCarrera.add(carrera);
        }
    }

        public void addMateria(Materia materia){
        if (!itemsMateria.contains(materia)){
            itemsMateria.add(materia);
        }
    }
}
