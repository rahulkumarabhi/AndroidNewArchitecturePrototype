package com.example.topratedmoviespersistent.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.example.topratedmoviespersistent.R
import com.example.topratedmoviespersistent.sealed.State
import com.example.topratedmoviespersistent.viewmodel.SplashScreenViewModel

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splashScreenViewModel= ViewModelProviders.of(this).get(SplashScreenViewModel::class.java)
        splashScreenViewModel.mutableLiveOfStateData.observe(this, Observer {

            when (it){
                is State.MainActivity->goToMainActivity()

            }

        })
    }

    private fun goToMainActivity() {
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }
}
