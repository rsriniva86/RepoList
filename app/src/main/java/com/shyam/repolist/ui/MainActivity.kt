package com.shyam.repolist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.shyam.repolist.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_activity.view.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),MainActivityDelegate {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val inflater = navHostFragment.findNavController().navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)
        navHostFragment.findNavController().graph = graph

    }
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp() =
        Navigation.findNavController(this, R.id.navHostFragment).navigateUp()


    override fun setupNavDrawer(toolbar: androidx.appcompat.widget.Toolbar) {
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        drawerLayout.navView.setupWithNavController(navHostFragment.findNavController())

    }
    override fun enableNavDrawer(enable: Boolean) {
        drawerLayout.isEnabled = enable
    }
}