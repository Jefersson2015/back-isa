# Elite Shoes - Backend

## Descripción
API REST desarrollada con **Spring Boot** para la gestión de una tienda de calzado deportivo. Proporciona endpoints para productos, usuarios, pedidos y carrito de compras.

---

## Tecnologías Utilizadas
Tecnología
**Java** | Lenguaje principal
**Spring Boot** |Framework backend
**Spring Data JPA** | Persistencia de datos
**MySQL** |Base de datos relacional
**Maven** | Gestión de dependencias
**Lombok** | Reducción de código boilerplate

---

## Funcionalidades

### Productos
- CRUD completo (Crear, Leer, Actualizar, Eliminar)
- Filtrado por categoría
- Gestión de stock automático
- Imágenes de productos

### Usuarios
- Registro de usuarios
- Autenticación (login)
- Roles (admin/cliente)
- Validación de credenciales

### Pedidos
- Creación de pedidos
- Detalles de pedido
- Actualización de stock
- Historial de compras

### Seguridad
- CORS configurado
- Validación de datos
- Manejo de excepciones
---

## Instalación

### Requisitos Previos
- Java JDK 17 o superior
- Maven 3.x
- MySQL 8.x
- IntelliJ IDEA o Eclipse

### Pasos de Instalación

```bash
# 1. Clonar el repositorio
git clone https://github.com/Jefersson2015/elite-backend.git

# 2. Navegar a la carpeta del proyecto
cd elite-backend

# 3. Instalar dependencias con Maven
mvn clean install

# 4. Configurar base de datos (application.properties)
#    - spring.datasource.url=jdbc:mysql://localhost:3306/elite_db
#    - spring.datasource.username=root
#    - spring.datasource.password=tu_password

# 5. Ejecutar la aplicación
mvn spring-boot:run

# O desde IntelliJ: Ejecutar EliteBackendApplication.java