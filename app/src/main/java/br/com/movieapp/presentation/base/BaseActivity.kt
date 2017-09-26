package br.com.movieapp.presentation.base

import android.support.v7.app.AppCompatActivity

/**
 * Created by pedrohenrique on 25/09/17.
 */
abstract class BaseActivity<P : BasePresenter> : AppCompatActivity(), BaseView<P>