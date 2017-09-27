package br.com.movieapp.presentation.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import java.io.ByteArrayOutputStream

/**
 * Created by pedrohenrique on 27/09/17.
 */
class PhotoUtil{
    companion object {
        fun getUri(context: Context, bitmap: Bitmap, nameImage:String): Uri {
            val os = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, os)
            val path = MediaStore.Images.Media.insertImage(context.contentResolver, bitmap, nameImage, null)
            return Uri.parse(path)
        }
    }
}