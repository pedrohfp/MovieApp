package br.com.movieapp.presentation.application

import android.app.Application
import android.support.annotation.VisibleForTesting
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
        @JvmStatic var mImageBaseUrl: String = "https://image.tmdb.org/t/p"
    }

    override fun onCreate() {
        super.onCreate()
        mAppComponent = initDagger()
    }

    private fun initDagger(): AppComponent{
        return DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule(mBaseUrl))
                .build()
    }

    @VisibleForTesting
    fun setComponent(component: AppComponent){
        mAppComponent = component
    }
}