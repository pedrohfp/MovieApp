package br.com.movieapp.presentation.utils

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 * Created by pedrohenrique on 25/09/17.
 */
@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
annotation class ActivityScoped