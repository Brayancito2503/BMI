package com.example.pesoapp.View

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.pesoapp.R
import com.example.pesoapp.databinding.ActivityMenuBinding
import com.google.android.material.internal.ContextUtils.getActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_menu.*

class Activity_menu : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private lateinit var toogle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toogle = ActionBarDrawerToggle(this,drawerLayout, R.string.open_drawer, R.string.close_drawe)
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)



//        val menubmi: BottomNavigationView = binding.bottomNavMenu
//        setupWithNavController(
//            menubmi,
//            Navigation.findNavController(this, R.id.frag_navgraph)
//        )

//        val navHost = supportFragmentManager.findFragmentById(androidx.navigation.fragment.R.id.nav_host_fragment_container)!!
//        val navController = navHost.findNavController()
//
//        binding.bottomNavMenu.setupWithNavController(navController)


        bottomNavMenu.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.mfragment_calculadora->{
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.nav_host_fragment_container, Fragment_Calculadora())
                        commit()
                    }
                }

                R.id.mactivity_Recordatorio->{
                    val intent = Intent(getActivity(this), Activity_Recordatorio::class.java)
                    startActivity(intent)
                }

                R.id.mfragment_sugerencia->{
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.nav_host_fragment_container, Fragment_sugerencias())
                        commit()
                    }
                }

                R.id.mfragment_histo->{
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.nav_host_fragment_container, Fragment_historial())
                        commit()
                    }
                }

                R.id.CerrarSesion ->{
                    signOut()
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toogle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun signOut(){
        Firebase.auth.signOut()
        val intent = Intent(getActivity(this), Activity_login::class.java)
        startActivity(intent)

    }


}