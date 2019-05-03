package com.example.pa2finalomdb

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {

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
}
