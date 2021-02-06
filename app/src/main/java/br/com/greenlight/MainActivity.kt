package br.com.greenlight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import br.com.greenlight.R
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var navController: NavController
    lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = this.findViewById(R.id.drawerLayout)
        navController = this.findNavController(R.id.fragment)
        navView = this.findViewById(R.id.navView)

        //Implementa o menu hamburguer
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(navView, navController)
    }

    //Método para processar a navegação, inclusive o clique da navegação
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.dashboard_menu, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        var msg = ""
//        when(item.itemId){
//            R.id.linkLoginMenuDashboard -> msg = "Essa tela ainda não está implementada"
//            R.id.linkUserProfileMenuDashboard -> msg = "Essa tela ainda não está implementada"
//            R.id.linkCarTripsMenuDashboard -> msg = "Essa tela ainda não está implementada"
//            R.id.linkListOfVehiclesMenuDashboard -> msg = "Essa tela ainda não está implementada"
//            R.id.linkLogoutMenuDashboard -> msg = "Essa tela ainda não está implementada"
//        }
//
//        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
//        return true
//    }
}