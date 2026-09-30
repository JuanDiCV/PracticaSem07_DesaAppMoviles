package com.example.sem07_practica

// Importación para el almacenamiento del estado guardado de la actividad en Android.
import android.os.Bundle
// Importación de ComponentActivity: clase base de Android que admite la integración con Jetpack Compose.
import androidx.activity.ComponentActivity
// Importación de setContent: define el bloque composable de nivel superior que sirve como UI de la actividad.
import androidx.activity.compose.setContent
// Importación para habilitar el diseño borde a borde (Edge to Edge) que abarca las barras de estado y navegación.
import androidx.activity.enableEdgeToEdge
// Importaciones de Compose para modificar el tamaño y padding del contenedor.
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
// Importación de Scaffold: componente estructural de Material Design que gestiona áreas como barras e insets.
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
// Importación del tema de la aplicación.
import com.example.sem07_practica.ui.theme.Sem07_practicaTheme

/**
 * MainActivity: Punto de entrada de la aplicación en la arquitectura Android.
 *
 * Flujo de trabajo:
 * 1. Hereda de `ComponentActivity` para gestionar el ciclo de vida.
 * 2. En `onCreate`, habilita `enableEdgeToEdge()` para ocupar toda la pantalla.
 * 3. Usa `setContent` para inicializar Compose con el tema de la app.
 * 4. Configura un `Scaffold` que calcula los margenes de sistema (`innerPadding`) y los pasa a `CounterScreen`.
 */
class MainActivity : ComponentActivity() {

    // Método del ciclo de vida que se ejecuta cuando la Actividad se crea por primera vez.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Habilita el modo sin bordes para que la aplicación ocupe toda la pantalla del dispositivo.
        enableEdgeToEdge()

        // Asigna el contenido de Jetpack Compose como la interfaz de usuario de la actividad.
        setContent {
            // Aplica el tema visual de Material Design 3 definido en el proyecto.
            Sem07_practicaTheme {
                // Layout estructural basico de Material Design.
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Llama a la pantalla principal pasando el padding necesario para no solapar las barras del sistema.
                    CounterScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
