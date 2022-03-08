package com.rss.fizzbuzzapp.secondscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rss.domain.usecase.GetFizzBuzzUseCase
import com.rss.fizzbuzzapp.mapper.FizzBuzzUiMapper
import com.rss.fizzbuzzapp.model.FizzBuzzUiModel
import com.rss.fizzbuzzapp.util.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FizzBuzzSecondViewModel(
    private val getFizzBuzzUseCase: GetFizzBuzzUseCase,
    private val uiMapper: FizzBuzzUiMapper,
) : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _uiData = MutableLiveData<Resource<List<String>>>()
    val uiData: LiveData<Resource<List<String>>>
        get() = _uiData

    /**
     * Find a Fizz Buzz from a [FizzBuzzUiModel]
     * we execute the processing in a computation thread
     * and we observe the result in the main thread
     */
    fun findFizzBuzz(uiModel: FizzBuzzUiModel) {
        _uiData.value = Resource.loading(null)
        getFizzBuzzUseCase(uiMapper.toDomainModel(uiModel))
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ fizzBuzzList ->
                _uiData.value = Resource.success(fizzBuzzList)
            }, { throwable ->
                _uiData.value = Resource.error(throwable.message, null)
            })
            .autoDispose()
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    private fun Disposable.autoDispose() {
        disposables.add(this)
    }
}