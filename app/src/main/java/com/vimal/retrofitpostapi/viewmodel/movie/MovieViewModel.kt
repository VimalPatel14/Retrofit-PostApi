package com.vimal.retrofitpostapi.viewmodel.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vimal.retrofitpostapi.api.NetworkState
import com.vimal.retrofitpostapi.api.movie.MovieRepository
import com.vimal.retrofitpostapi.model.Movie
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieViewModel constructor(private val mainRepository: MovieRepository) : ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage
    val movieList = MutableLiveData<List<Movie>>()
    var job: Job? = null

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getAllMovies() {
//        Log.d("Thread Outside", Thread.currentThread().name)
        viewModelScope.launch {
//            Log.d("Thread Inside", Thread.currentThread().name)
            when (val response = mainRepository.getAllMovies()) {
                is NetworkState.Success -> {
                        movieList.postValue(response.data)
                }
                is NetworkState.Error -> {
                    if (response.response.code() == 401) {
                        //movieList.postValue(NetworkState.Error())
                        Log.e("vml", "Api Error")
                    } else {
                        //movieList.postValue(NetworkState.Error)
                        Log.e("vml", "Api Error")
                    }
                }
            }
        }
    }

    private fun onError(message: String) {
        _errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}