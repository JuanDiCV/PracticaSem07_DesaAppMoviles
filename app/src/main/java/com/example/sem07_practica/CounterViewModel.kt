package com.example.sem07_practica

// Importación de ViewModel, la clase base de los Architecture Components de Android que sobrevive a cambios de configuración.
import androidx.lifecycle.ViewModel
// Importación de MutableStateFlow: flujo de datos mutable que almacena el valor del estado y emite cambios a sus observadores.
import kotlinx.coroutines.flow.MutableStateFlow
// Importación de StateFlow: interfaz de solo lectura para exponer el flujo de datos de manera segura hacia la UI.
import kotlinx.coroutines.flow.StateFlow
// Importación de la función de extensión asStateFlow() para convertir el MutableStateFlow en un StateFlow inmutable.
import kotlinx.coroutines.flow.asStateFlow

/**
 * CounterViewModel: Gestiona la lógica de negocio y el estado del contador.
 *
 * Flujo de trabajo:
 * 1. Mantiene el estado mutable en `_count` accesible únicamente dentro de este ViewModel (Encapsulamiento).
 * 2. Expone `count` como un StateFlow inmutable de solo lectura para ser observado por la interfaz de usuario (UI).
 * 3. Proporciona métodos públicos como `increment()` y `reset()` para modificar el estado de forma controlada.
 */
class CounterViewModel : ViewModel() {

    // Estado interno mutable inicializado en 0.
    // Privado para evitar que componentes externos modifiquen el valor directamente.
    private val _count = MutableStateFlow(0)

    // Estado público inmutable (solo lectura).
    // La UI se suscribirá a este flujo para reaccionar y recomponerse automáticamente cuando cambie su valor.
    val count: StateFlow<Int> = _count.asStateFlow()

    /**
     * Incrementa el valor del contador en 1.
     *
     * Flujo de trabajo:
     * Al ser invocado por la UI (al presionar un botón), actualiza el valor de `_count`,
     * lo que provoca que todos los componentes que observan `count` reciban el nuevo valor.
     */
    fun increment() {
        _count.value += 1
    }

    /**
     * Reinicia el valor del contador a 0.
     *
     * Flujo de trabajo:
     * Al ser invocado por la UI (al presionar el botón "Reiniciar"), asigna 0 a `_count`,
     * emitiendo la actualización hacia todos los observadores de `count`.
     */
    fun reset() {
        _count.value = 0
    }
}
