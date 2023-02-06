package com.vimal.retrofitpostapi.activity

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.vimal.retrofitpostapi.R
import com.vimal.retrofitpostapi.adapter.MovieAdapter
import com.vimal.retrofitpostapi.api.movie.MovieRepository
import com.vimal.retrofitpostapi.api.movie.MovieRetrofitService
import com.vimal.retrofitpostapi.helpers.Helpers
import com.vimal.retrofitpostapi.interfaces.ItemClickListener
import com.vimal.retrofitpostapi.viewmodel.movie.MovieViewModel
import com.vimal.retrofitpostapi.viewmodel.movie.MovieViewModelFactory

class MovieActivity : AppCompatActivity(), ItemClickListener {

    lateinit var viewModel: MovieViewModel
    lateinit var progressDialog: ProgressBar
    lateinit var recyclerview: RecyclerView
    var layoutManager: StaggeredGridLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        val profileName = intent.getStringExtra("Username")

        val adapter = MovieAdapter(this@MovieActivity, this@MovieActivity)
        progressDialog = findViewById(R.id.progressDialogmovie)
        recyclerview = findViewById(R.id.recyclerviewmovie)

        recyclerview.setHasFixedSize(true)
        layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerview.setLayoutManager(layoutManager)

        val retrofitService = MovieRetrofitService.getInstance(Helpers.baseMovieUrl)
        val mainRepository = MovieRepository(retrofitService)
        recyclerview.adapter = adapter

        viewModel = ViewModelProvider(
            this,
            MovieViewModelFactory(mainRepository)
        ).get(MovieViewModel::class.java)
        viewModel.movieList.observe(this) {
            progressDialog.visibility = View.GONE
            adapter.setMovies(it)
        }
        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        viewModel.loading.observe(this, Observer {
            if (it) {
                progressDialog.visibility = View.VISIBLE
            } else {
                progressDialog.visibility = View.GONE
            }
        })
        viewModel.getAllMovies()

    }


    override fun onItemClick(position: Int) {
        Toast.makeText(this@MovieActivity, "Position Clicked: " + position, Toast.LENGTH_SHORT)
            .show()
    }

    override fun onLongClick(position: Int) {
        //do long click here
    }

}