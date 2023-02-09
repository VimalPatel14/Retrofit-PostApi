package com.vimal.retrofitpostapi.viewmodel.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vimal.retrofitpostapi.api.NetworkState
import com.vimal.retrofitpostapi.api.post.PostRepository
import com.vimal.retrofitpostapi.model.Posts
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PostViewModel constructor(private val mainRepository: PostRepository) : ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage
    val movieList = MutableLiveData<Posts>()
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
                        onError("Error : 401")
                    } else {
                        onError("Error : ")
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