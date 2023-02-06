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
import com.vimal.retrofitpostapi.adapter.PostAdapter
import com.vimal.retrofitpostapi.api.post.PostRetrofitService
import com.vimal.retrofitpostapi.api.post.PostRepository
import com.vimal.retrofitpostapi.helpers.Helpers
import com.vimal.retrofitpostapi.interfaces.ItemClickListener
import com.vimal.retrofitpostapi.viewmodel.post.PostViewModelFactory
import com.vimal.retrofitpostapi.viewmodel.post.PostViewModel

class PostActivity : AppCompatActivity(), ItemClickListener {

    lateinit var viewModel: PostViewModel
    lateinit var progressDialog: ProgressBar
    lateinit var recyclerview: RecyclerView
    var layoutManager: StaggeredGridLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        val profileName = intent.getStringExtra("Username")

        val adapter = PostAdapter(this@PostActivity, this@PostActivity)
        progressDialog = findViewById(R.id.progressDialog)
        recyclerview = findViewById(R.id.recyclerview)

        recyclerview.setHasFixedSize(true)
        layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerview.setLayoutManager(layoutManager)

        val retrofitService = PostRetrofitService.getInstance(Helpers.basePostUrl)
        val mainRepository = PostRepository(retrofitService)
        recyclerview.adapter = adapter

        viewModel = ViewModelProvider(
            this,
            PostViewModelFactory(mainRepository)
        ).get(PostViewModel::class.java)
        viewModel.movieList.observe(this) {
            progressDialog.visibility = View.GONE
            adapter.setMovies(it.data)
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
        Toast.makeText(this@PostActivity, "Position Clicked: " + position, Toast.LENGTH_SHORT)
            .show()
    }

    override fun onLongClick(position: Int) {
        //do long click here
    }

}