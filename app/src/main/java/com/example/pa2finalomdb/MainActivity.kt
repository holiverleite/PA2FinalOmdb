package com.example.pa2finalomdb

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.widget.Toast
import com.example.pa2finalomdb.model.Movie
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.frame_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.design.snackbar

class MainActivity : AppCompatActivity() {

    private val movieService = MovieService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configurando Toolbar
        supportActionBar?.title = resources.getString(R.string.app_name) // Seta o título
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.elevation = 0F

        /* Configurando Navigation Drawer
        Criando um interruptor abre/fechar ND que ficará na Toolbar */
        val abreFechaToogle: ActionBarDrawerToggle =
            ActionBarDrawerToggle(this, // Activity
                menuLateralDrawerLayout,// Layout de Nav.Drawer que será aberto e fechado
                toolbar,                // Toolbar onde aparecerá o interruptor
                R.string.menu_aberto,   // Texto acessibilidade para aberto
                R.string.menu_fechado)  // Texto acessibilidade para fechado

        menuNavigationView.setNavigationItemSelectedListener { onNavigationItemSelected(it) }

        // Sincroniza o estado do ícone na Toolbar com o Menu Lateral
        abreFechaToogle.syncState()

        searchButton.setOnClickListener {
            // Testa se o usuário digitou alguma coisa para buscar
            if (searchField.text.isNotEmpty()) {
                movieService.buscarFilmeComOTitulo("vini")
//                substituiFragment()
            } else {
                // Senão, mostra uma mensagem na parte debaixo do LinearLayout
                mainView.snackbar("É preciso digitar um título a ser buscado")
            }
        }

        movieService.responseOmdb = object : MovieService.ResponseOmdb {
            override fun onResponse(item: Movie) {
                Toast.makeText(this@MainActivity, "sucesso", Toast.LENGTH_LONG).show()

            }

            override fun onResponseFail(error: Throwable) {
                Toast.makeText(this@MainActivity, "erro", Toast.LENGTH_LONG).show()
            }
        }
    }

    // Trata eventos de cliques nas opções do menu lateral
    fun onNavigationItemSelected(item: MenuItem): Boolean {
        var retorno: Boolean = false
        when (item.itemId) {
            R.id.sairMenuItem -> {
                finish()
                retorno = true
            }
        }
        // Fecha o menu lateral depois de tratar o evento
        menuLateralDrawerLayout.closeDrawer(GravityCompat.START)
        return retorno
    }

    private fun substituiFragment() {
        // Variável que armazena o Fragment que vai preencher a tela princial
        val fragment = DetalhesFilmeFragment()

        // Transação de Fragment a partir do SupportFragmentManager
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentDetalhe, fragment)
        fragmentTransaction.commit()
    }
}
