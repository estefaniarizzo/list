# Gestor de Tareas Personales

Aplicación Android para la gestión de tareas personales. Permite crear, editar, eliminar, listar y compartir tareas, con almacenamiento local usando Room y diseño personalizado.

## Características principales
- **CRUD de tareas**: Crea, edita, elimina y lista tareas con título, descripción y categoría.
- **Persistencia local**: Usa Room (SQLite) para guardar tareas entre sesiones.
- **Notificaciones locales**: Al crear una tarea, se muestra una notificación.
- **Vibración**: Al eliminar una tarea, el dispositivo vibra.
- **Compartir tarea**: Puedes compartir una tarea por otras apps.
- **Layouts personalizados**: Uso de ConstraintLayout, LinearLayout y RelativeLayout, con colores y estilos propios.
- **Ciclo de vida completo**: Las activities implementan todos los métodos del ciclo de vida (onCreate, onStart, onResume, onPause, onStop, onDestroy) y muestran logs/toasts para evidenciarlo.

## Estructura del proyecto

```
app/
 ├── src/main/java/com/tuusuario/gestortareas/
 │     ├── activities/           # Activities principales
 │     ├── adapter/              # Adapter para RecyclerView
 │     ├── database/             # Room DB y DAO
 │     └── model/                # Modelo de datos
 ├── res/layout/                 # Layouts XML personalizados
 ├── res/values/                 # Colores y estilos
 └── AndroidManifest.xml
```

## Compilación y ejecución
1. Abre la carpeta raíz en Android Studio.
2. Sincroniza el proyecto con Gradle.
3. Compila y ejecuta en un emulador o dispositivo físico.
4. El APK se puede generar desde `Build > Build APK(s)`.

## Evidencia de ciclo de vida
Cada Activity implementa todos los métodos del ciclo de vida y muestra mensajes en Logcat y Toast para evidenciar su ejecución.

## Herramientas del dispositivo usadas
- Notificaciones locales (al crear tarea)
- Vibración (al eliminar tarea)
- Compartir tarea (Intent)

## Personalización de diseño
- Colores y estilos propios en `res/values/colors.xml` y `styles.xml`.
- Layouts variados: ConstraintLayout, LinearLayout, RelativeLayout.

## Uso de Git y buenas prácticas
- Commits claros y descriptivos.
- Estructura de carpetas organizada.
- Código comentado y modular.

---
Desarrollado para actividad académica de Apps Móviles.
