# 📦 API REST MVC - Proyecto CRUD en Spring Boot

Este proyecto es una API RESTful desarrollada con **Spring Boot** siguiendo el patrón **MVC (Modelo-Vista-Controlador)**. Permite realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre una entidad determinada, utilizando Java y la arquitectura REST.

## 🛠 Tecnologías utilizadas

- ✅ Java 17+
- ✅ Spring Boot
- ✅ Spring Web
- ✅ Spring Data JPA
- ✅ Maven
- ✅ Base de datos (MySQL o H2)
- ✅ Postman (para pruebas)
- 🚧 (Próximamente Swagger para documentación automática)

---

## 📁 Estructura del Proyecto
```
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── pe/idat/
│ │ │ ├── controller/
│ │ │ ├── model/
│ │ │ ├── repository/
│ │ │ └── service/
│ │ └── resources/
│ │ ├── application.properties
│ │ └── ...
├── pom.xml
└── README.md
```
---

## 📌 Características del Proyecto

- 🔄 API REST con operaciones CRUD completas
- 🧩 Arquitectura MVC limpia
- 📦 Persistencia con Spring Data JPA
- 🔐 Validaciones básicas de datos
- 🧪 Endpoints probados con Postman

---

## 🚀 Instalación y ejecución

### Prerrequisitos

- Java 17 o superior
- Maven instalado
- MySQL (si usas base de datos externa) o H2
- Postman (para pruebas)

### Pasos para ejecutar


```bash
# 1. Clona el repositorio
git clone https://github.com/grisel15naye/ApiRestMVC.git

# 2. Accede al proyecto
cd ApiRestMVC

# 3. Ejecuta el proyecto con Maven o desde IntelliJ IDEA
./mvnw spring-boot:run
```
### Configuración de base de datos
Edita el archivo src/main/resources/application.properties para definir tu conexión:
```
server.port=tuPuerto
server.servlet.context-path=/rest
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/TUBASEDEDATOS?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=USERNAME
spring.datasource.password=PASSWORD
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.hibernate.use-new-id-generator-mappings=true
logging.level.org.hibernate.SQL=debug
```

## 📮 Endpoints disponibles
| Método | Endpoint              | Descripción                |
| ------ | --------------------- | -------------------------- |
| GET    | `/api/entidades`      | Listar todas las entidades |
| GET    | `/api/entidades/{id}` | Obtener entidad por ID     |
| POST   | `/api/entidades`      | Crear nueva entidad        |
| PUT    | `/api/entidades/{id}` | Actualizar entidad         |
| DELETE | `/api/entidades/{id}` | Eliminar entidad           |

🔧 Reemplaza entidades por el nombre real de tu modelo (por ejemplo, usuarios, productos, etc).

---
## 🧪 Pruebas y Documentacion
### 🔎 Documentación Swagger
- URL: http://localhost:8080/swagger-ui.html
- APO-DOCS: http://localhost:8080/rest/v3/api-docs
- Generada automáticamente a partir de tus controladores
### 🔎 Colección Postman
- https://documenter.getpostman.com/view/37774025/2sB2x6jrHs

---
## 👩‍💻 Autor
- Nombre: Grisel Naye
- GitHub: @grisel15naye






