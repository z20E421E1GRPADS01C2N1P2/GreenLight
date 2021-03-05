package br.com.greenlight.ui.splash

import android.app.ActivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import br.com.greenlight.R
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

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
}