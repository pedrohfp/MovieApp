package br.com.movieapp.domain.interactor.base

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by pedrohenrique on 26/09/17.
 */
abstract class SingleUseCase<T, in Params>{

    var disposables: CompositeDisposable = CompositeDisposable()

    /**
     * Builds an [Single] which will be used when executing the current [UseCase].
     */
    internal abstract fun getSingle(params: Params): Single<T>

    /**
     * Executes the current use case.
     *
     * @param observer [DisposableSingleObserver] which will be listening to the single build
     * by [.execute] ()} method.
     * @param params Parameters (Optional) used to build/execute this use case.
     */
    fun execute(observer: DisposableSingleObserver<T>, params: Params) {
        val single = this.getSingle(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        addDisposable(single.subscribeWith(observer))
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}