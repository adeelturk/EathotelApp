package com.turk.core.extensions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun <T : ViewDataBinding> ViewGroup.dataBind(@LayoutRes layoutRes: Int): T =

    DataBindingUtil.inflate(LayoutInflater.from(context), layoutRes, this, false)

