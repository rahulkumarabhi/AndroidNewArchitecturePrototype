package com.example.topratedmoviespersistent.viewmodel

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.topratedmoviespersistent.R
import com.example.topratedmoviespersistent.app.MyApplication
import com.example.topratedmoviespersistent.model.MovieDetails
import com.example.topratedmoviespersistent.model.MovieList
import com.example.topratedmoviespersistent.networking.TMDBApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class MainActivityViewModel : ViewModel() {
    val responseLiveData = MutableLiveData<MovieList>()
    val failureLiveData = MutableLiveData<String>()
    val responseMovieDetailsLiveData = MutableLiveData<MovieDetails>()
    var isProgressLiveData = MutableLiveData<Boolean>()
    var isProgress: Boolean = false
    @Inject
    lateinit var tmdbApi: TMDBApi

    init {
        setIsProgress(true)
        val appComponent = MyApplication.component.inject(this)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val movieList = tmdbApi.getMovieList()
                responseLiveData.postValue(movieList)
            }

        }
    }

    fun getMovieDetails(id: String) {
        setIsProgress(true)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val movieDetails = tmdbApi.getMovieDetailsById(id)
                responseMovieDetailsLiveData.postValue(movieDetails)
            }
        }
    }

    companion object {
        @BindingAdapter("android:rating")
        @JvmStatic
        fun setRating(view: RatingBar?, rating: String) {

            if (view != null) {

                val rate = java.lang.Float.parseFloat(rating)

                view.rating = rate

            }
        }

        @BindingAdapter("android:src")
        @JvmStatic
        fun loadImage(view: ImageView, url: String) {
            val fullUrl = "https://image.tmdb.org/t/p/w500$url"
            Glide.with(view.context).load(fullUrl).apply(
                RequestOptions.placeholderOf(
                    R.drawable.placeholder_stick
                )
            ).listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    view.setImageDrawable(resource)
                    return true
                }

            }).into(view)

        }
    }

    fun isProgressVisible(boolean: Boolean) {
        isProgress = boolean
    }

    fun getProgressVisibility(): Boolean {
        return isProgress
    }

    fun setIsProgress(boolean: Boolean) {
        isProgressLiveData.postValue(boolean)
    }
}