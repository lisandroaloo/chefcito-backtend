# Chefcito API - Postman Collection

Esta colección de Postman contiene todos los endpoints CRUD para probar la API de Chefcito Backend.

## 📁 Contenido de la Colección

La colección incluye **25 endpoints** organizados en 5 carpetas principales:

### 🥬 **Ingredients** (5 endpoints)
- **POST** `/api/ingredient` - Crear ingrediente
- **GET** `/api/ingredient` - Obtener todos los ingredientes
- **GET** `/api/ingredient/{id}` - Obtener ingrediente por ID
- **PUT** `/api/ingredient/{id}` - Actualizar ingrediente
- **DELETE** `/api/ingredient/{id}` - Eliminar ingrediente

### 👤 **Users** (5 endpoints)
- **POST** `/api/user` - Crear usuario
- **GET** `/api/user` - Obtener todos los usuarios
- **GET** `/api/user/{id}` - Obtener usuario por ID
- **PUT** `/api/user/{id}` - Actualizar usuario
- **DELETE** `/api/user/{id}` - Eliminar usuario

### 🍝 **Recipes** (5 endpoints)
- **POST** `/api/recipe` - Crear receta
- **GET** `/api/recipe` - Obtener todas las recetas
- **GET** `/api/recipe/{id}` - Obtener receta por ID
- **PUT** `/api/recipe/{id}` - Actualizar receta
- **DELETE** `/api/recipe/{id}` - Eliminar receta

### 🔗 **Ingredient X Recipe** (5 endpoints)
- **POST** `/api/ingredient-x-recipe` - Crear relación ingrediente-receta
- **GET** `/api/ingredient-x-recipe` - Obtener todas las relaciones
- **GET** `/api/ingredient-x-recipe/{id}` - Obtener relación por ID
- **PUT** `/api/ingredient-x-recipe/{id}` - Actualizar relación
- **DELETE** `/api/ingredient-x-recipe/{id}` - Eliminar relación

### ⏳ **Pending Recipe X User** (5 endpoints)
- **POST** `/api/pending-recipe-x-user` - Crear relación receta pendiente-usuario
- **GET** `/api/pending-recipe-x-user` - Obtener todas las relaciones pendientes
- **GET** `/api/pending-recipe-x-user/{id}` - Obtener relación pendiente por ID
- **PUT** `/api/pending-recipe-x-user/{id}` - Actualizar relación pendiente
- **DELETE** `/api/pending-recipe-x-user/{id}` - Eliminar relación pendiente

## 🚀 Cómo Usar la Colección

### 1. **Importar la Colección**
1. Abre Postman
2. Haz clic en **Import** en la esquina superior izquierda
3. Selecciona el archivo `Chefcito_API.postman_collection.json`
4. La colección aparecerá en tu workspace

### 2. **Configurar Variables**
La colección incluye una variable de entorno:
- `{{base_url}}` = `http://localhost:8080` (puerto por defecto de Spring Boot)

**Para cambiar el puerto o URL:**
1. Ve a la pestaña **Variables** en la colección
2. Modifica el valor de `base_url` según tu configuración

### 3. **Orden Recomendado de Pruebas**

Para probar las relaciones correctamente, sigue este orden:

#### **Paso 1: Crear Datos Base**
```
1. Create User (crear al menos 1 usuario)
2. Create Ingredient (crear al menos 1 ingrediente)
3. Create Recipe (usar el ID del usuario creado)
```

#### **Paso 2: Crear Relaciones**
```
4. Create Ingredient X Recipe (usar IDs de ingrediente y receta)
5. Create Pending Recipe X User (usar IDs de usuario y receta)
```

#### **Paso 3: Probar CRUD Completo**
```
6. Get All [Entity] (verificar datos creados)
7. Get [Entity] by ID (probar con IDs existentes)
8. Update [Entity] (modificar datos)
9. Delete [Entity] (eliminar cuando ya no necesites)
```

## 📋 Ejemplos de Request Bodies

### **Crear Usuario:**
```json
{
    "us_alias": "chef_mario",
    "us_email": "mario@chefcito.com",
    "us_password": "securePassword123",
    "us_password_salt": "randomSalt456"
}
```

### **Crear Ingrediente:**
```json
{
    "in_name": "Tomato"
}
```

### **Crear Receta:**
```json
{
    "re_creator_us_id": 1,
    "re_picture": "https://example.com/pasta-image.jpg",
    "re_title": "Classic Spaghetti Carbonara",
    "re_suitable_for_vegan": false,
    "re_suitable_for_vegetarian": true,
    "re_suitable_for_celiac": false,
    "re_suitable_for_lactose_intolerant": false
}
```

### **Crear Ingrediente X Receta:**
```json
{
    "ixr_in_id": 1,
    "ixr_re_id": 1,
    "quantity": 2
}
```

### **Crear Pending Recipe X User:**
```json
{
    "rxu_us_id": 1,
    "rxu_re_id": 1
}
```

## ✅ Respuestas Esperadas

### **Éxito:**
- **201 Created** - Para operaciones POST
- **200 OK** - Para operaciones GET y PUT  
- **204 No Content** - Para operaciones DELETE

### **Errores:**
- **404 Not Found** - Cuando el recurso no existe
- **500 Internal Server Error** - Para errores del servidor

### **Ejemplo de Error 404:**
```json
{
    "timestamp": "2024-01-15T10:30:00",
    "status": 404,
    "error": "Not Found",
    "message": "Ingredient not found with id: 999"
}
```

## 🔧 Troubleshooting

### **Error de Conexión:**
- Verifica que Spring Boot esté ejecutándose en `http://localhost:8080`
- Confirma que la base de datos esté configurada correctamente

### **Error 404 en GET by ID:**
- Asegúrate de que el recurso existe (ejecuta GET All primero)
- Verifica que el ID en la URL sea correcto

### **Error en relaciones:**
- Para crear `Recipe`, el `re_creator_us_id` debe existir en la tabla `User`
- Para crear `IngredientXRecipe`, tanto `ixr_in_id` como `ixr_re_id` deben existir
- Para crear `PendingRecipeXUser`, tanto `rxu_us_id` como `rxu_re_id` deben existir

## 🎯 Tips de Uso

1. **Usa el Runner de Postman** para ejecutar toda la colección automáticamente
2. **Guarda responses** como ejemplos para futura referencia
3. **Usa Tests** en Postman para validar automáticamente las respuestas
4. **Crea Environment** específico para diferentes entornos (dev, test, prod)

¡Happy Testing! 🚀 