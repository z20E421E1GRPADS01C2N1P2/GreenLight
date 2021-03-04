package br.com.greenlight.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import br.com.greenlight.R

class SplashActivity : AppCompatActivity() {

    private lateinit var splashViewModel: SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
//        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashViewModel = ViewModelProvider(this).get(SplashScreenViewModel::class
            .java)
        splashViewModel.liveData.observe(this, {
            when (it) {
                is SplashState.MainActivity -> {
                    goToLoginFragment()
                }
            }
        })
    }

    private fun goToLoginFragment() {
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }
}