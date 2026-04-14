package com.iticbcn.trokka

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.iticbcn.trokka.LobyActivity.Companion.PERFIL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PerfilActivity : BaseLoggedActivity() {

    private val viewModel: PerfilViewModel by viewModels()

    private lateinit var tvNombreUsuario: TextView
    private lateinit var bottomNav: BottomNavigationView

    private lateinit var cvEditarObjetos: CardView
    private lateinit var cvSubirObjetos: CardView
    private lateinit var cvFavs: CardView
    private lateinit var cvGrafics: CardView
    private lateinit var btnSpeak: ConstraintLayout

    private lateinit var toolbar: Toolbar
    private lateinit var navView: NavigationView
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val perfil: String = intent.extras?.getString(PERFIL) ?: ""

        initComponents()
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_flechita)

        setupNavView()
        initListeners()
        initUi(perfil)
        observeViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_superior, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            R.id.iConfiguracio -> {
                drawerLayout.openDrawer(GravityCompat.END)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupNavView() {
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.iIdioma, R.id.iTema, R.id.iPrivacidad, R.id.iAmigos -> navigateToPreferencies()
                R.id.iInicio -> navigateToLoby()
                R.id.nav_logout -> navigateToMain()
                R.id.nav_delete -> {
                    deleteDialog()
                    true
                }
                R.id.nav_change_name -> {
                    changeNameDialog()
                    true
                }
                R.id.nav_change_pass -> {
                    changePasswordDialog()
                    true
                }
                R.id.nav_save_info -> saveInfo()

            }
            drawerLayout.closeDrawer(GravityCompat.END)
            true
        }
    }

    private fun saveInfo(){
        val myApp = applicationContext as MyApp
        val calcularTiempo = myApp.calcularTempsFinal()

        val tempsEnMins = calcularTiempo/3600000.0

        CoroutineScope(Dispatchers.IO).launch {
            FirebaseConnector.cargarInfo("marc")
            FirebaseConnector.addHores(tempsEnMins)
            val resultado = FirebaseConnector.guardarInfo("marc", FirebaseConnector.dataInfo)

            launch(Dispatchers.Main){
                if (resultado.isSuccess) {
                    Toast.makeText(this@PerfilActivity, "Datos sincronizados con Firebase", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@PerfilActivity, "Error al guardar", Toast.LENGTH_SHORT).show()
                }
            }
        }

        Toast.makeText(this, "Información guardada", Toast.LENGTH_LONG).show()
    }

    private fun setNombreUsuario(perfil: String) {
        tvNombreUsuario.text = perfil
    }

    private fun initUi(perfil: String) {
        setNombreUsuario(perfil)
    }

    private fun navigateToLoby() {
        startActivity(Intent(this, LobyActivity::class.java))
    }

    private fun navigateToMapa() {
        startActivity(Intent(this, MapActivity::class.java))
    }

    private fun navigateToProfile() {
        startActivity(Intent(this, PerfilActivity::class.java))
    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun navigateToPreferencies() {
        startActivity(Intent(this, PreferenciasAcrivity::class.java))
    }

    private fun navigateToUploadObject() {
        startActivity(Intent(this, UploadObjectActivity::class.java))
    }

    private fun navigateToFavs() {
        startActivity(Intent(this, FavoritosActivity::class.java))
    }

    private fun navigateToMyObjects() {
        startActivity(Intent(this, MyObjectsActivity::class.java))
    }

    private fun navigateToGraphs() {
        startActivity(Intent(this, GraficsActivity::class.java))
    }

    private fun initListeners() {
        cvGrafics.setOnClickListener { navigateToGraphs() }
        cvSubirObjetos.setOnClickListener { navigateToUploadObject() }
        cvFavs.setOnClickListener { navigateToFavs() }
        cvEditarObjetos.setOnClickListener { navigateToMyObjects() }

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.loby_fragment -> navigateToLoby()
                R.id.mapa_fragment -> navigateToMapa()
            }
            true
        }

        btnSpeak.setOnClickListener {
            iniciarEscuchaVoz()
        }
    }

    private fun initComponents() {
        tvNombreUsuario = findViewById(R.id.tvNombreUsuario)

        cvEditarObjetos = findViewById(R.id.cvEditarObjetos)
        cvSubirObjetos = findViewById(R.id.cvSubirObjetos)
        cvFavs = findViewById(R.id.cvFavs)
        cvGrafics = findViewById(R.id.cvGrafics)

        toolbar = findViewById(R.id.cvHeader)
        navView = findViewById(R.id.navView)
        drawerLayout = findViewById(R.id.main)

        bottomNav = findViewById(R.id.bottom_navigation)
        bottomNav.setBackgroundColor(Color.TRANSPARENT)

        btnSpeak = findViewById(R.id.btn_speak)
    }

    private fun observeViewModel() {
        viewModel.state.observe(this) { pair ->
            val (exito, mensaje) = pair
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
            if (exito) {
                val sharedPreferences = getSharedPreferences("session", MODE_PRIVATE)
                sharedPreferences.edit().clear().apply()
                navigateToMain()
            }
        }
    }

    private fun deleteDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Comfirmar eliminación")
        builder.setMessage("Introduce tu contraseña para eliminar la cuenta")

        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        builder.setView(input)

        builder.setPositiveButton("Eliminar") { _, _ ->
            val password = input.text.toString()

            if (password.isNotBlank()){
                val sharedPreferences = getSharedPreferences("session", MODE_PRIVATE)
                val id = sharedPreferences.getLong("id", -1)

                if (id != 1L) {
                    viewModel.deleteUserWithPassword(id, password)
                }
                else{
                    Toast.makeText(this, "Debes de introducir la contraseña correcta", Toast.LENGTH_LONG).show()
                }
            }
        }

        builder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun changeNameDialog() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Cambiar nombre de usuario")

        val input = EditText(this)
        input.hint = "Nuevo nombre"
        builder.setView(input)

        builder.setPositiveButton("Cambiar") { _, _ ->

            val newName = input.text.toString()

            if (newName.isNotBlank()) {

                val sharedPreferences = getSharedPreferences("session", MODE_PRIVATE)
                val oldName = sharedPreferences.getString("username", "") ?: ""

                viewModel.changeUserName(oldName, newName)

                with(sharedPreferences.edit()){
                    putString("username", newName)
                    apply()
                }

                tvNombreUsuario.text = newName

            } else {
                Toast.makeText(this, "Introduce un nombre válido", Toast.LENGTH_LONG).show()
            }
        }

        builder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }

    private fun changePasswordDialog(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmar contraseña")
        builder.setMessage("Introduce tu contraseña actual")

        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        builder.setView(input)

        builder.setPositiveButton("Continuar") { _, _ ->

            val currentPassword = input.text.toString()

            val sharedPreferences = getSharedPreferences("session", MODE_PRIVATE)
            val savedPassword = sharedPreferences.getString("password", "") ?: ""
            val username = sharedPreferences.getString("username", "") ?: ""

            if(currentPassword == savedPassword){

                newPasswordDialog(username)

            }else{

                Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_LONG).show()

            }
        }

        builder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }

    private fun newPasswordDialog(username: String){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Nueva contraseña")
        builder.setMessage("Introduce la nueva contraseña")

        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        builder.setView(input)

        builder.setPositiveButton("Cambiar") { _, _ ->

            val newPassword = input.text.toString()

            if(newPassword.isNotBlank()){

                if (newPassword.length !in 6..16) {
                    Toast.makeText(this, "La contraseña debe tener entre 6 y 16 caracteres", Toast.LENGTH_LONG).show()
                    return@setPositiveButton
                }

                val passwordPattern = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")
                if (!newPassword.matches(passwordPattern)) {
                    Toast.makeText(this, "Debe tener mayúscula, minúscula y número", Toast.LENGTH_LONG).show()
                    return@setPositiveButton
                }

                viewModel.changeUserPassword(username, newPassword)

                val sharedPreferences = getSharedPreferences("session", MODE_PRIVATE)
                with(sharedPreferences.edit()){
                    putString("password", newPassword)
                    apply()
                }

                Toast.makeText(this, "Contraseña cambiada con éxito", Toast.LENGTH_LONG).show()

            } else {

                Toast.makeText(this, "La contraseña no puede estar vacía", Toast.LENGTH_LONG).show()

            }
        }

        builder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }
}
