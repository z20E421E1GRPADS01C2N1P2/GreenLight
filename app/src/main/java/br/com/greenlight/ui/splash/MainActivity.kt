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
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import br.com.greenlight.R
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var navController: NavController
    lateinit var navView: NavigationView

//    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //logout
        val firebaseAuth = FirebaseAuth.getInstance()

//        auth = Firebase.auth
//        var currentUser = firebaseAuth.currentUser
        var currentUser = Firebase.auth.currentUser

        drawerLayout = this.findViewById(R.id.drawerLayout)
        navController = this.findNavController(R.id.fragment)
        navView = this.findViewById(R.id.navView)

        val listVehicleItemMenu = navView.menu.findItem(R.id.listVehicleFragment)
        val logoutItemMenu = navView.menu.findItem(R.id.logoutFragment)

        //Implements hamburger menu
        NavigationUI.setupActionBarWithNavController(
            this,
            navController,
            drawerLayout
        )
        NavigationUI.setupWithNavController(navView, navController)

        //Logout
        navView.menu.findItem(R.id.logoutFragment).setOnMenuItemClickListener {

            Firebase.auth.signOut()
            currentUser = null
            navController.navigate(R.id.dashboardFragment)
            drawerLayout.close()
            true
        }

//        if (currentUser == null) {
//            listVehicleItemMenu.isEnabled = false
//            logoutItemMenu.isEnabled = false
//        } else {
//            listVehicleItemMenu.isEnabled = true
//            logoutItemMenu.isEnabled = true
//        }

//        navController.addOnDestinationChangedListener { controller, destination, arguments ->
//            if (currentUser == null) {
//                navView.menu.findItem(R.id.logoutFragment).isVisible = false
//            } else {
//                navView.menu.findItem(R.id.listVehicleFragment).isVisible = true
//                navView.menu.findItem(R.id.logoutFragment).isVisible = true
//            }
//        }


//        if (currentUser == null) {
//            navView.menu.findItem(R.id.listVehicleFragment).setVisible(false)
//            navView.menu.findItem(R.id.listVehicleFragment).setVisible(false)
//        } else {
//            navView.menu.findItem(R.id.listVehicleFragment).setVisible(true)
//            navView.menu.findItem(R.id.listVehicleFragment).setVisible(true)
//        }

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


        /* If user tries to access profile fragment, the app redirects to
        login fragment */

        /* If user tries to access vehicle list fragment, the app redirects to
        login fragment */
//        navView.menu.findItem(R.id.listVehicleFragment)
//            .setOnMenuItemClickListener {
//
//                if (currentUser == null)
//                    navController.navigate(R.id.loginFragment)
//
//                if (currentUser != null) {
//                    currentUser.reload()
//                    navController.navigate(R.id.listVehicleFragment)
//                }
//
//                drawerLayout.close()
//                true
//            }

        /* If user tries to access trip fragment, the app redirects to
        login fragment */
//        navView.menu.findItem(R.id.listTripFragment)
//            .setOnMenuItemClickListener {
//
//                if (currentUser == null) navController.navigate(R.id.loginFragment)
//                else navController.navigate(R.id.vehicleTripFragment)
//                drawerLayout.close()
//                true
//            }
    }


    //Método para processar a navegação, inclusive o clique da navegação
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
}