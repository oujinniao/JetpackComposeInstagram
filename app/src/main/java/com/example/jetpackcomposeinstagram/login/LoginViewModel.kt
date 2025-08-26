package com.example.jetpackcomposeinstagram.login

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposeinstagram.login.domain.LoginUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    val loginUseCase = LoginUseCase()

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable: LiveData<Boolean> = _isLoginEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // CORRECTO: Esta es una función regular (fun), no suspend.
    // Su única responsabilidad es actualizar el estado de la UI.
    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _isLoginEnable.value = enableLogin(email, password)
    }

    // Esta función está bien, contiene la lógica de validación
    fun enableLogin(email: String, password: String): Boolean {
        val isValidEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.length >= 6
        return isValidEmail && isPasswordValid
    }

    // CORRECTO: La lógica de negocio y el manejo de carga se hacen aquí.
    fun onLoginClicked() {
        // 1. Inicia el estado de carga
        _isLoading.value = true

        // 2. Lanza una corrutina para la operación asíncrona
        viewModelScope.launch {
            try {
                // Lógica de inicio de sesión: llama al UseCase
                val result = loginUseCase(email.value!!, password.value!!)
                delay(2000)
                // 3. Maneja el resultado
                if (result) {
                    Log.i("Login", "Inicio de sesión exitoso")
                } else {
                    Log.i("Login", "Inicio de sesión fallido")
                }
            } catch (e: Exception) {
                // Maneja errores de la red, etc.
                Log.e("Login", "Error de inicio de sesión: ${e.message}")
            } finally {
                // 4. Detiene el estado de carga
                _isLoading.value = false
            }
        }
    }
}