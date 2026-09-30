package com.example.sem07_practica

// Importaciones de Compose Foundation para posicionamiento y estructuración en pantalla.
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

// Importaciones de Material Design 3 para componentes visuales (Botón, Botón Delineado, Texto y Estilos de Tipografía).
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text

// Importaciones para construir Composables y gestionar la recolección de estados.
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

// Importaciones para la alineación, modificadores de UI y herramientas de vista previa.
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Importación para obtener la instancia del ViewModel dentro de una función Composable.
import androidx.lifecycle.viewmodel.compose.viewModel

// Importación del tema visual de la aplicación.
import com.example.sem07_practica.ui.theme.Sem07_practicaTheme

/**
 * CounterScreen (Stateful Composable):
 *
 * Flujo de trabajo:
 * 1. Obtiene la instancia de `CounterViewModel` usando la función `viewModel()`.
 * 2. Se suscribe al estado `count` mediante `collectAsState()`, convirtiéndolo en un estado observable por Compose.
 * 3. Transfiere la información obtenida y los callbacks de incremento y reinicio a `CounterContent`.
 */
@Composable
fun CounterScreen(
    modifier: Modifier = Modifier, // Modificador para controlar márgenes y dimensiones desde el contenedor padre.
    counterViewModel: CounterViewModel = viewModel() // Inyecta u obtiene el ViewModel asociado al ciclo de vida.
) {
    // Transforma el StateFlow `count` en un Estado de Compose.
    val count by counterViewModel.count.collectAsState()

    // Delega la presentación gráfica al componente visual sin estado (Stateless).
    CounterContent(
        count = count, // Pasa el valor numérico actual a mostrar.
        onIncrement = { counterViewModel.increment() }, // Callback para incrementar.
        onReset = { counterViewModel.reset() }, // Callback para reiniciar el contador a 0.
        modifier = modifier // Pasa el modificador recibido.
    )
}

/**
 * CounterContent (Stateless Composable):
 *
 * Flujo de trabajo:
 * 1. Recibe el valor actual `count` e renderiza un componente `Text`.
 * 2. Muestra dos botones en una fila (`Row`): uno para incrementar y otro para reiniciar a 0.
 * 3. Notifica las acciones del usuario a través de las lambdas `onIncrement` y `onReset`.
 */
@Composable
fun CounterContent(
    count: Int, // Valor del contador que se muestra en la interfaz.
    onIncrement: () -> Unit, // Callback de evento para incrementar.
    onReset: () -> Unit, // Callback de evento para reiniciar a 0.
    modifier: Modifier = Modifier // Modificador para personalizar posición y estilos del contenedor.
) {
    // Contenedor principal que organiza sus hijos verticalmente.
    Column(
        modifier = modifier
            .fillMaxSize() // Expande la columna para ocupar todo el alto y ancho de la pantalla.
            .padding(16.dp), // Aplica un margen interno uniforme alrededor del contenido.
        verticalArrangement = Arrangement.Center, // Centra el contenido verticalmente en la pantalla.
        horizontalAlignment = Alignment.CenterHorizontally // Centra el contenido horizontalmente en la pantalla.
    ) {
        // Componente de texto para mostrar el valor del contador.
        Text(
            text = "Valor actual: $count", // Muestra el valor del contador formateado en texto.
            style = MaterialTheme.typography.headlineMedium // Aplica estilo tipográfico de encabezado mediano.
        )

        // Espaciador vertical entre el texto y la fila de botones.
        Spacer(modifier = Modifier.height(24.dp))

        // Fila horizontal para alinear los botones de Incrementar y Reiniciar.
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp), // Espaciado horizontal de 16dp entre botones.
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Botón principal para incrementar el valor.
            Button(onClick = onIncrement) {
                Text(text = "Incrementar")
            }

            // Botón delineado secundario para reiniciar el valor a 0.
            Button(onClick = onReset) {
                Text(text = "Reiniciar")
            }
        }
    }
}

/**
 * Previsualización en Android Studio para verificar la UI sin ejecutar la app en un emulador/dispositivo.
 */
@Preview(showBackground = true)
@Composable
fun CounterScreenPreview() {
    Sem07_practicaTheme {
        CounterContent(
            count = 0, // Valor fijo inicial de prueba.
            onIncrement = {}, // Función vacía para la vista previa.
            onReset = {} // Función vacía para la vista previa.
        )
    }
}
