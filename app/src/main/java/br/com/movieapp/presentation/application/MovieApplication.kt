package br.com.movieapp.presentation.application

import android.app.Application
import br.com.movieapp.presentation.application.di.AppComponent
import br.com.movieapp.presentation.application.di.AppModule
import br.com.movieapp.presentation.application.di.DaggerAppComponent
import br.com.movieapp.presentation.application.di.NetworkModule

/**«
 * Created by pedrohenrique on 25/09/17.
 */
class MovieApplication: Application(){

    companion object {
        @JvmStatic lateinit var mAppComponent: AppComponent
        @JvmStatic var mBaseUrl: String = "https://api.themoviedb.org/3/"
    }

    override fun onCreate() {
        super.onCreate()
        //https://api.themoviedb.org/3/movie/550?api_key=7c8de234c261253cdbc07d7ff774a9dc
        mAppComponent = initDagger()
    }

    private fun initDagger(): AppComponent{
        return DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule(mBaseUrl))
                .build()
    }
}