package com.shyam.repolist.ui


interface MainActivityDelegate {
    fun setupNavDrawer(toolbar: androidx.appcompat.widget.Toolbar)
    fun enableNavDrawer(enable: Boolean)
}