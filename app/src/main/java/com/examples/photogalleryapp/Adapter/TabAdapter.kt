package com.examples.photogalleryapp.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.examples.photogalleryapp.ui.Home.ImageListingFragment

class TabAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    var size1:  Int = 0
    override fun getItemCount(): Int {
        return size1
    }

    override fun createFragment(position: Int): Fragment {
return ImageListingFragment.newInstance()
    }
    fun setSize(size: Int){
        this.size1=size
    }
}