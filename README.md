# Arquitecturas Web

Este repositorio contiene la implementación de 4 trabajos integradores relacionados con arquitecturas web y tecnologías backend. Cada trabajo utiliza diferentes patrones de diseño, herramientas y tecnologías para resolver problemas específicos, garantizando modularidad, eficiencia y escalabilidad.

## Tecnologías Utilizadas  

Este proyecto hace uso de las siguientes herramientas y tecnologías:  
- **Java**  
- **Maven**  
- **JDBC**
- **Spring Boot**  
- **Hibernate**  
- **Docker**
- **MySQL**
- **JWT**
- **Postman**

## **Etapas del proyecto**

### 1. **JDBC: Gestión de Datos**
- **Descripción:**  
  Este trabajo implementa la gestión de bases de datos utilizando **JDBC**, desde la creación del esquema hasta la manipulación y consulta de datos.
- **Características principales:**  
  - Creación del esquema de la base de datos.
  - Lectura de datos desde archivos CSV con **Apache Commons CSV**.
  - Consultas avanzadas para:
    - Obtener el producto con mayor recaudación.
    - Listar clientes ordenados por facturación.
- **Patrones de diseño utilizados:**  
  - **Factory Method y Abstract Factory:** Implementados para gestionar la creación de DAOs y la configuración de conexiones a la base de datos.
  - **DAO (Data Access Object):** Utilizado para desacoplar el acceso a datos de la lógica de negocios y facilitar el mantenimiento del código.
- **Tecnologías aplicadas:**  
  - JDBC
  - Apache Commons CSV

---

### 2. **Hibernate: Sistema de Gestión Estudiantes**
- **Descripción:**  
  Este trabajo consiste en el diseño e implementación de un sistema para registrar estudiantes y gestionar su información académica, utilizando **Hibernate/JPA** y consultas en **JPQL**.
- **Características principales:**  
  - Alta de estudiantes y matrícula en carreras.
  - Consultas avanzadas:
    - Listar estudiantes por género o ciudad de residencia.
    - Obtener carreras ordenadas por cantidad de inscriptos.
    - Generar reportes de inscriptos y egresados por carrera y año.
- **Patrones de diseño utilizados:**  
  - **Repository:** Implementado para centralizar las operaciones sobre las entidades de la base de datos a través de una interfaz.
  - **DTO (Data Transfer Object):** Utilizado para transferir datos entre las capas de la aplicación.
  - **Abstract Factory:** Define una arquitectura general para agregar soporte a distintas tecnologías de base de datos.
  - **Factory Method:** Se utiliza para instanciar y devolver los repositories, configurándolos con un EntityManager creado previamente por el EntityManagerFactory.
- **Tecnologías aplicadas:**  
  - Hibernate/JPA  
  - JPQL  

### 3. **Aplicación SpringBoot: Sistema de Estudiantes y Carreras **  
   - **Descripción**: Este trabajo extiende del anterior, implementando un sistema de registro de estudiantes utilizando Spring Boot para exponer servicios RESTful dentro de una arquitectura monolítica. El sistema permite gestionar la información de los estudiantes y sus matrículas en diferentes carreras a través de endpoints REST, facilitando la interacción con el backend mediante solicitudes HTTP.  
   - **Características principales**:  
     - Alta de estudiantes y matrícula en carreras.
     - Generación de reportes de carreras.
     - Recuperación de estudiantes según diversos filtros y orden por criterios específicos.
   - **Frameworks y Tecnologías aplicadas**:  
     - **SpringBoot**: Framework principal para la creación de la aplicación RESTful.
     - **Spring Data JPA y Hibernate**: Gestionan la persistencia de los datos mediante el uso de entidades y el patrón ORM.
     - **Spring MVC**: Organiza la aplicación en tres capas (Modelo-Vista-Controlador).
     - **Postman**: Utilizado para realizar pruebas y validaciones de los endpoints REST.

### 4. **Microservicios RESTful: Aplicación de Alquiler de Monopatines**  
   - **Descripción**: Este trabajo se centra en la creación de un sistema de microservicios utilizando Spring Boot para gestionar el alquiler de monopatines eléctricos en una ciudad. La arquitectura se organiza en varios microservicios independientes que interactúan entre sí a través de APIs RESTful. Cada microservicio tiene su propia base de datos, asegurando un diseño escalable y desacoplado.  
- **Características principales:**  
  - **Microservicios Independientes:**
    - **Administrador:** Gestiona las funciones de administración, como control de precios, reportes, anulación de cuentas y consultas respecto a el uso y estado de monopatines.
    - **Facturación:** Genera facturas respecto a los viajes realizados y a monopatines por estado o distancia.
    - **Mantenimiento:** Registra las acciones de mantenimiento de los monopatines, así como la disponibilidad de los mismos.
    - **Monopatín:** Maneja las operaciones relacionadas con los monopatines, como su uso, ubicación y estado.
    - **Reportes:** Genera reportes de facturación, y sobre el uso y el estado de los monopatines.
    - **Usuario:** Gestiona el registro de usuarios y la asociación de cuentas de Mercado Pago.
 - **Autenticación y Roles:**  
    La aplicación emplea un **Gateway** que maneja la verificación y validación del usuario, asignando un token **JWT** para autenticar las solicitudes. Los roles definidos en la aplicación son:
    - **Admin:** Accede a todas las funcionalidades de gestión, precios, y reportes.
    - **User:** Interactúa con el sistema para registrar viajes y gestionar sus pagos.
    - **Mantenimiento:** Realiza el mantenimiento de los monopatines y gestiona su disponibilidad.

  - **Comunicación entre microservicios:**  
    Los microservicios se comunican entre sí mediante **Feign Clients**, lo que permite realizar llamadas a otros microservicios a través de sus URLs y APIs expuestas.

  - **Seguridad:**  
    La seguridad se gestiona a través de un **JWT Filter** y una configuración personalizada de seguridad (**SecurityConfig**) que asigna permisos a los endpoints según el rol del usuario.

  - **Base de datos NoSQL:**  
    El microservicio **billingmicroservice** utiliza **MongoDB** para almacenar la información de los pagos, mientras que otros microservicios emplean bases de datos SQL.

  - **Dockerización y Despliegue:**  
    Todos los microservicios están **dockerizados**, lo que permite su despliegue de manera independiente en contenedores, facilitando la escalabilidad y la gestión del entorno de ejecución.

  - **Pruebas y Documentación:**  
    Se han implementado **pruebas unitarias** utilizando **JUnit** para asegurar la correcta funcionalidad de los microservicios. Además, se ha documentado la API de cada microservicio con **Swagger (OpenAPI)**, lo que proporciona una guía clara sobre los endpoints disponibles y facilita la integración con otros servicios.
   - **Frameworks y Tecnologías aplicadas**:  
     - SpringBoot
     - Spring Data JPA y Hibernate
     - JPQL
     - MongoDB
     - JWT (JSON Web Tokens)
     - Swagger (OpenAPI)
     - Postman
   
---

## Conclusiones

El desarrollo de este proyecto permitió profundizar en el diseño y la implementación de aplicaciones modernas utilizando **Spring Boot** como framework principal. Su capacidad para facilitar la creación de aplicaciones sólidas y bien organizadas fue clave para estructurar el sistema y simplificar el manejo de la lógica de negocio y los servicios REST.

La separación de responsabilidades en el diseño e implementación fue crucial para una solución clara y fácil de mantener, usando patrones de diseño y capas bien definidas para un sistema más cohesivo y escalable.

Por otro lado, la colaboración activa y la comunicación no solo garantizaron la integración fluida de los componentes, sino que también fortalecieron habilidades interpersonales y organizativas clave para el desarrollo profesional.

Finalmente, tecnologías como **Docker** y herramientas como **Swagger** complementaron el desarrollo, simplificando el despliegue de los servicios y mejorando la claridad y accesibilidad, facilitando su entendimiento y uso por terceros.

En conjunto, este proyecto sirvió para afianzar competencias técnicas avanzadas, junto con habilidades esenciales como el trabajo en equipo, la delegación de tareas y la comunicación efectiva.



## Integrantes

- Carolina Vytas Tuckus
- Manuel Álvarez
- Milagros Álvarez
- Facundo Bravo
