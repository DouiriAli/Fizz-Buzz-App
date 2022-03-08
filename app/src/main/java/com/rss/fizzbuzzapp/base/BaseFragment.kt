package com.rss.fizzbuzzapp.base

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    /**
     * Show progressbar
     */
    fun showProgress() =
        with(activity) {
            if (this is BaseActivity)
                this.setProgressState(isVisible = true)
        }

    /**
     * Hide progressbar
     */
    fun hideProgress() =
        with(activity) {
            if (this is BaseActivity)
                this.setProgressState(isVisible = false)
        }

    /**
     * Display a message
     */
    fun notify(@StringRes message: Int) =
        with(activity) {
            if (this is BaseActivity)
                this.notify(message)
        }
}