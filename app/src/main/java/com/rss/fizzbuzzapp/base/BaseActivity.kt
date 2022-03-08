package com.rss.fizzbuzzapp.base

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.rss.fizzbuzzapp.databinding.ActivityMainBinding

open class BaseActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    /**
     * Display or hide progress bar
     */
    fun setProgressState(isVisible: Boolean) {
        binding.content.progress.isVisible = isVisible
        if (isVisible) hideKeyboard()
    }

    /**
     * Display a snackbar
     */
    fun notify(@StringRes message: Int) {
        Snackbar.make(binding.coordinator, message, Snackbar.LENGTH_SHORT).show()
    }

    /**
     * Hide keyboard
     */
    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}