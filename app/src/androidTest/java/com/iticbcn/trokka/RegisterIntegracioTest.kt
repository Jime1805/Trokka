package com.iticbcn.trokka

import androidx.lifecycle.ViewModelProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegisterIntegracioTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(RegisterActivity::class.java)

    private fun verifyViewModelMessage(expectedMessage: String) {
        activityRule.scenario.onActivity { activity ->
            val viewModel = ViewModelProvider(activity).get(RegisterViewModel::class.java)
            val currentState = viewModel.state.value
            assertEquals("El missatge del ViewModel no coincideix", expectedMessage, currentState?.second)
        }
    }

    @Test
    fun al_registrar_no_omple_camps_mostra_error() {
        onView(withId(R.id.btnRegistrarse)).perform(click())
        verifyViewModelMessage("Todos los campos son obligatorios")
    }

    @Test
    fun introduce_correo_invalido_mostra_error() {
        onView(withId(R.id.etUserReg)).perform(typeText("testUser"), closeSoftKeyboard())
        onView(withId(R.id.etCorreoReg)).perform(typeText("correo-no-valido"), closeSoftKeyboard())
        onView(withId(R.id.etPassReg)).perform(typeText("Abc12345"), closeSoftKeyboard())
        onView(withId(R.id.etRePassReg)).perform(typeText("Abc12345"), closeSoftKeyboard())
        
        onView(withId(R.id.btnRegistrarse)).perform(click())
        verifyViewModelMessage("Correo electrónico no válido")
    }

    @Test
    fun introduce_contrasenya_curta_mostra_error() {
        onView(withId(R.id.etUserReg)).perform(typeText("testUser"), closeSoftKeyboard())
        onView(withId(R.id.etCorreoReg)).perform(typeText("test@test.com"), closeSoftKeyboard())
        onView(withId(R.id.etPassReg)).perform(typeText("123"), closeSoftKeyboard())
        onView(withId(R.id.etRePassReg)).perform(typeText("123"), closeSoftKeyboard())
        onView(withId(R.id.btnRegistrarse)).perform(click())
        verifyViewModelMessage("La contraseña debe tener entre 6 y 16 caracteres")
    }

    @Test
    fun introduce_contrasenya_sense_majusc_mostra_error() {
        onView(withId(R.id.etUserReg)).perform(typeText("testUser"), closeSoftKeyboard())
        onView(withId(R.id.etCorreoReg)).perform(typeText("test@test.com"), closeSoftKeyboard())
        onView(withId(R.id.etPassReg)).perform(typeText("abc12345"), closeSoftKeyboard())
        onView(withId(R.id.etRePassReg)).perform(typeText("abc12345"), closeSoftKeyboard())
        onView(withId(R.id.btnRegistrarse)).perform(click())
        verifyViewModelMessage("Debe tener mayúscula, minúscula y número")
    }

    @Test
    fun contrasenyes_no_coincideixen_mostra_error() {
        onView(withId(R.id.etUserReg)).perform(typeText("testUser"), closeSoftKeyboard())
        onView(withId(R.id.etCorreoReg)).perform(typeText("test@test.com"), closeSoftKeyboard())
        onView(withId(R.id.etPassReg)).perform(typeText("Abc12345"), closeSoftKeyboard())
        onView(withId(R.id.etRePassReg)).perform(typeText("Diferente1"), closeSoftKeyboard())
        onView(withId(R.id.btnRegistrarse)).perform(click())
        verifyViewModelMessage("Las contraseñas no coinciden")
    }

    @Test
    fun click_al_texto_de_ir_a_login_navega_a_mainactivity() {
        onView(withId(R.id.tvIniciarSesion)).perform(click())
        onView(withId(R.id.btnIniciarSesion)).check(matches(isDisplayed()))
    }

    @Test
    fun registro_correcto_navega_a_login() {
        val timestamp = System.currentTimeMillis()
        onView(withId(R.id.etUserReg)).perform(typeText("User$timestamp"), closeSoftKeyboard())
        onView(withId(R.id.etCorreoReg)).perform(typeText("test$timestamp@gmail.com"), closeSoftKeyboard())
        onView(withId(R.id.etPassReg)).perform(typeText("Abc12345"), closeSoftKeyboard())
        onView(withId(R.id.etRePassReg)).perform(typeText("Abc12345"), closeSoftKeyboard())

        onView(withId(R.id.btnRegistrarse)).perform(click())

        Thread.sleep(2000)

        onView(withId(R.id.btnIniciarSesion)).check(matches(isDisplayed()))
    }

    @Test
    fun registro_de_usuario_existente_muestra_error() {
        onView(withId(R.id.etUserReg)).perform(typeText("testUser"), closeSoftKeyboard())
        onView(withId(R.id.etCorreoReg)).perform(typeText("test@test.com"), closeSoftKeyboard())
        onView(withId(R.id.etPassReg)).perform(typeText("Abc12345"), closeSoftKeyboard())
        onView(withId(R.id.etRePassReg)).perform(typeText("Abc12345"), closeSoftKeyboard())

        onView(withId(R.id.btnRegistrarse)).perform(click())

        Thread.sleep(2000)

        verifyViewModelMessage("Nom d'usuari ja en ús. Escull un altre.")
    }
}
