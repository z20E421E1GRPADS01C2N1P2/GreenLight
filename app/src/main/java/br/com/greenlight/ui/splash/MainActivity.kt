package br.com.greenlight.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import br.com.greenlight.R
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var navController: NavController
    lateinit var navView: NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //logout
//        val firebaseAuth = FirebaseAuth.getInstance()
//        firebaseAuth.signOut()

        //Verify if there's an user logged in
//        val firebaseUser = firebaseAuth.currentUser

//        if(firebaseUser == null) popBackStack()


        drawerLayout = this.findViewById(R.id.drawerLayout)
        navController = this.findNavController(R.id.fragment)
        navView = this.findViewById(R.id.navView)

        //Implementa o menu hamburguer
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(navView, navController)

        //Logout
        navView.menu.findItem(R.id.logoutFragment).setOnMenuItemClickListener {

            val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
            firebaseAuth.signOut()
            navController.navigate(R.id.dashboardFragment)
            Toast.makeText(this, "Saiu", Toast.LENGTH_LONG).show()
            true
        }

//        val logout = this.findViewById<DrawerLayout>(R.id.dashboardFragment)
//
//        logout.setOnClickListener {
//            firebaseAuth.signOut()
//            navController.n(R.id.dashboardFragment)
//            Toast.makeText(this, "hahha", Toast.LENGTH_LONG).show()
//        }
//




        //Hide Menu if user isn't logged in
//        navView.menu.removeItem(R.id.userProfileFragment)

        //Teste FirebaseFirestore
//        val firebaseFirestore = FirebaseFirestore.getInstance()
//        val collection = firebaseFirestore.collection("carros")
//
//        val produto = mapOf(
//            "modelo" to "Troller",
//            "marca" to "Troller",
//            "combustivel" to "disel"
//        )
//
//        val task = collection.add(produto)
//        task.addOnCompleteListener {
//            if (it.isSuccessful)
//                Log.i("Firestore", "Cadastro com sucesso")
//            else
//                Log.i("Firestore","Falha ao cadastrar")
//        }
    }

    //Método para processar a navegação, inclusive o clique da navegação
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        val inflater: MenuInflater = menuInflater
//        inflater.inflate(R.menu.dashboard_menu, menu)
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//             return when (item.itemId) {
//            R.id.logoutFragment -> {
//                val firebaseAuth = FirebaseAuth.getInstance()
//                firebaseAuth.signOut()
//                navView.findNavController().navigate(R.id.listVehicleFragment)
//                logout()
//                true
//            }

//            else -> super.onOptionsItemSelected(item)
//        }
//    }

//        private fun logout() {
//            val view: View? = null
//            val firebaseAuth = FirebaseAuth.getInstance()
//            val currentUser = firebaseAuth.currentUser
//            firebaseAuth.signOut()
//            view?.findNavController()?.navigate(R.id.dashboardFragment)
//        }

}