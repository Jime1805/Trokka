package com.iticbcn.trokkaimport

import com.iticbcn.trokka.RegisterViewModel
import org.junit.Assert

import org.junit.Before
import org.junit.Test

class RegisterUnitTest {
    private lateinit var viewModel: RegisterViewModel

    @Before
    fun setup() {
        viewModel = RegisterViewModel()
    }

    @Test
    fun email_Error_Vacio() {
        Assert.assertFalse("Debe fallar si el email está vacío", isValidEmail(""))
    }

    @Test
    fun email_Error_SoloEspacios() {
        Assert.assertFalse("Debe fallar si solo hay espacios", isValidEmail("    "))
    }

    @Test
    fun email_Error_SinArroba() {
        Assert.assertFalse("Debe fallar si no tiene '@'", isValidEmail("nombre.com"))
    }

    @Test
    fun email_Error_MultiplesArrobas() {
        Assert.assertFalse("Debe fallar si tiene más de un '@'", isValidEmail("test@test@com"))
    }

    @Test
    fun email_Error_SinDominio() {
        Assert.assertFalse("Debe fallar si no tiene extensión (ej: .com)", isValidEmail("usuario@gmail"))
    }

    @Test
    fun email_Error_CaracteresInvalidos() {
        Assert.assertFalse("No debe permitir ñ", isValidEmail("usuarioñ@gmail.com"))
        Assert.assertFalse("No debe permitir espacios intermedios", isValidEmail("usu ario@gmail.com"))
        Assert.assertFalse("No debe permitir símbolos raros", isValidEmail("usuario@gma!l.com"))
    }

    @Test
    fun email_Error_PuntoAlFinal() {
        Assert.assertFalse("No debe terminar en punto", isValidEmail("usuario@gmail."))
    }

    @Test
    fun email_Exito_FormatosValidos() {
        Assert.assertTrue("Debe aceptar formato estándar", isValidEmail("test@trokka.com"))
        Assert.assertTrue("Debe aceptar subdominios", isValidEmail("test@mail.itb.cat"))
        Assert.assertTrue("Debe aceptar guiones y puntos", isValidEmail("nombre.apellido-123@empresa.org"))
    }

    @Test
    fun password_Error_MuyCorta() {
        Assert.assertFalse("Debe rechazar exactamente 5 caracteres", isValidPassword("12345"))
        Assert.assertFalse("Debe rechazar solo 1 carácter", isValidPassword("a"))
    }

    @Test
    fun password_Error_EspaciosInternos() {
        Assert.assertFalse("No debería permitir solo espacios en blanco", isValidPassword("      "))
    }

    @Test
    fun password_Exito_Limites() {
        Assert.assertTrue("Debe aceptar exactamente 6 caracteres", isValidPassword("123456"))
        Assert.assertTrue("Debe aceptar contraseñas muy largas", isValidPassword("estaEsUnaContraSenaMuyLargaYSegura123!"))
    }

    @Test
    fun matching_Error_DiferenciaMayusculas() {
        val p1 = "Password123"
        val p2 = "password123"
        Assert.assertFalse("Las contraseñas deben ser sensibles a mayúsculas", doPasswordsMatch(p1, p2))
    }

    @Test
    fun matching_Error_EspaciosExtra() {
        val p1 = "pass123"
        val p2 = "pass123 "
        Assert.assertFalse("No deben coincidir si una tiene espacios extra", doPasswordsMatch(p1, p2))
    }

    @Test
    fun matching_Exito_Identicas() {
        Assert.assertTrue("Deben coincidir si son exactamente iguales", doPasswordsMatch("Admin.2024", "Admin.2024"))
    }

    @Test
    fun formulario_Error_Combinaciones() {
        Assert.assertFalse(validarRegistro("buen@email.com", "123456", "654321"))
        Assert.assertFalse(validarRegistro("emailMal", "123456", "123456"))
        Assert.assertFalse(validarRegistro("", "", ""))
    }

    @Test
    fun formulario_Exito_Completo() {
        Assert.assertTrue("Debe ser válido si todos los campos cumplen las reglas",
            validarRegistro("alumno@iticbcn.cat", "passwordSeguro", "passwordSeguro"))
    }

    private fun isValidEmail(email: String): Boolean {
        if (email.isBlank()) return false
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$"
        return email.matches(emailRegex.toRegex())
    }

    private fun isValidPassword(password: String): Boolean {
        val trimmed = password.trim()
        return trimmed.length >= 6 && password.isNotEmpty()
    }

    private fun doPasswordsMatch(pass1: String, pass2: String): Boolean {
        return pass1 == pass2
    }

    private fun validarRegistro(email: String, p1: String, p2: String): Boolean {
        return isValidEmail(email) && isValidPassword(p1) && doPasswordsMatch(p1, p2)
    }
}
