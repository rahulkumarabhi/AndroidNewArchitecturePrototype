package com.example.topratedmoviespersistent.activity

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topratedmoviespersistent.R
import com.example.topratedmoviespersistent.adapter.MovieAdapter
import com.example.topratedmoviespersistent.databinding.ActivityMainBinding
import com.example.topratedmoviespersistent.listener.MovieIdListener
import com.example.topratedmoviespersistent.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity(), MovieIdListener {
    private lateinit var mainActivityViewModel:MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainViewModelBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        val recyclerView = mainViewModelBinding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        mainActivityViewModel =
            ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        mainViewModelBinding.viewModel=mainActivityViewModel
        mainActivityViewModel.responseLiveData.observe(this, Observer {
            Log.d("response is: ", it.toString())
            val movieAdapter = MovieAdapter(it.results, this)
            recyclerView.adapter = movieAdapter
        })

        mainActivityViewModel.isProgressLiveData.observe(this, Observer {
            mainActivityViewModel.isProgressVisible(it)
        })

        mainActivityViewModel.responseMovieDetailsLiveData.observe(this, Observer {
            Log.d("MainActivity",it.homepage)
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it.homepage))
                startActivity(Intent.createChooser(browserIntent, "choose"))

            } catch (e: ActivityNotFoundException) {
                Log.e("ActivityNotFoundException", "" + e.message)
            } catch (e: Exception) {
                Log.e("Exception", "" + e.message)
            }

        })
    }

    override fun movieIdListener(id: Int) {
        Log.d("movieIdListener: ", id.toString())
        mainActivityViewModel.getMovieDetails(id.toString())
    }
}
