package com.example.boredapp.utils

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import com.example.boredapp.MainActivity
import java.util.*

fun getTime(): Long {
    return Date().time
}

fun openWeb(link: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
    MainActivity.getActivity().startActivity(intent)
}