package com.vimal.retrofitpostapi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vimal.retrofitpostapi.activity.MovieActivity
import com.vimal.retrofitpostapi.activity.PostActivity
import com.vimal.retrofitpostapi.databinding.ActivityMainBinding
import com.vimal.retrofitpostapi.postapi.api.ApiInterface
import com.vimal.retrofitpostapi.postapi.api.ServiceBuilder
import com.vimal.retrofitpostapi.postapi.model.RequestModel
import com.vimal.retrofitpostapi.postapi.model.ResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.movie.setOnClickListener() {
            val intent = Intent(this@MainActivity, MovieActivity::class.java)
            intent.putExtra("Username", "Vimal Patel")
            startActivity(intent)
        }

        binding.post.setOnClickListener() {
            val intent = Intent(this@MainActivity, PostActivity::class.java)
            intent.putExtra("Username", "Vimal Patel")
            startActivity(intent)
        }

        binding.postapi.setOnClickListener() {
            val requestModel = RequestModel("username123", "hello123")
            postData(requestModel)
        }

    }

    fun postData(requestModel : RequestModel) {
        binding.progressbar.visibility = View.VISIBLE
        val response = ServiceBuilder.buildService(ApiInterface::class.java)
        response.sendReq(requestModel).enqueue(
            object : Callback<ResponseModel> {
                override fun onResponse(
                    call: Call<ResponseModel>,
                    response: Response<ResponseModel>
                ) {
                    Toast.makeText(
                        this@MainActivity,
                        response.message().toString()+" Response",
                        Toast.LENGTH_LONG
                    ).show()
                    binding.progressbar.visibility = View.GONE
                }

                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                    binding.progressbar.visibility = View.GONE
                    Toast.makeText(this@MainActivity, t.message+ " Error", Toast.LENGTH_LONG).show()
                }

            }
        )
    }

}