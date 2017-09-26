package br.com.movieapp.presentation.utils

import android.widget.SearchView
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

/**
 * Created by pedrohenrique on 26/09/17.
 */
class RxSearch{
    private object Holder{val INSTANCE = RxSearch()}

    companion object {
        val instance: RxSearch by lazy { Holder.INSTANCE }
    }

    fun fromSearchView(searchView: SearchView): Observable<String> {
        var subject: BehaviorSubject<String> = BehaviorSubject.create()

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                subject.onComplete()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                subject.onNext(newText!!)
                return true
            }
        })

        return subject
    }
}