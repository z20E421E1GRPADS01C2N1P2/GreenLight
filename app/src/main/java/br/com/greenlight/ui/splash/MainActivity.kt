package br.com.greenlight.ui.splash

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import br.com.greenlight.R
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var navController: NavController
    lateinit var navView: NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //logout
        val firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser

        drawerLayout = this.findViewById(R.id.drawerLayout)
        navController = this.findNavController(R.id.fragment)
        navView = this.findViewById(R.id.navView)

        //Implements hamburger menu
        NavigationUI.setupActionBarWithNavController(
            this,
            navController,
            drawerLayout
        )
        NavigationUI.setupWithNavController(navView, navController)

        //Logout
        navView.menu.findItem(R.id.logoutFragment).setOnMenuItemClickListener {

            firebaseAuth.signOut()
            navController.navigate(R.id.dashboardFragment)
            drawerLayout.close()
            Toast.makeText(this, "Já vai tarde", Toast.LENGTH_LONG).show()
            true
        }

//        navView.menu.findItem(R.id.userProfileFragment)
//            .setOnMenuItemClickListener {
//
//                if (currentUser != null) {
//                    navController.navigate(R.id.userProfileFragment)
//                } else {
//                    navController.navigate(R.id.action_dashboardFragment_to_loginFragment)
//                }
//                drawerLayout.close()
//                true
//            }

/* TODO: ver por que não está funcionando

        /* If user tries to access profile fragment, the app redirects to
        login fragment */

        /* If user tries to access vehicle list fragment, the app redirects to
        login fragment */
        navView.menu.findItem(R.id.listVehicleFragment)
            .setOnMenuItemClickListener {

                if (currentUser == null) navController.navigate(R.id.loginFragment)
                else navController.navigate(R.id.listVehicleFragment)
                drawerLayout.close()
                true
            }

        /* If user tries to access trip fragment, the app redirects to
        login fragment */
        navView.menu.findItem(R.id.vehicleTripFragment)
            .setOnMenuItemClickListener {

                if (currentUser == null) navController.navigate(R.id.loginFragment)
                else navController.navigate(R.id.vehicleTripFragment)
                drawerLayout.close()
                true
            }

 */

    }


    //Método para processar a navegação, inclusive o clique da navegação
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
}