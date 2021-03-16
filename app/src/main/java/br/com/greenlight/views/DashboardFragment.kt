package br.com.greenlight.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.navigation.fragment.findNavController
import br.com.greenlight.R
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_dashboard, container,
            false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        btnMapa.setOnClickListener {
            findNavController().navigate(R.id.locationActivity)
        }
    }

//    override fun onContextItemSelected(item: MenuItem): Boolean {

//        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
//        return when (item.itemId) {
//            R.id.logoutFragment -> {
//                val firebaseAuth = FirebaseAuth.getInstance()
//                var currentUser = firebaseAuth.currentUser
//                firebaseAuth.signOut()
//                currentUser = null
//                findNavController().navigate(R.id.dashboardFragment)
                //logout()
//                true
//            }
//            else -> super.onContextItemSelected(item)
//        }
//    }
}