package com.examples.photogalleryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.examples.photogalleryapp.data.model.Image
import com.examples.photogalleryapp.databinding.RecImageItemBinding

class ImagesPageAdapter :
    PagingDataAdapter<Image, ImagesPageAdapter.ImagesViewHolder>(IMAGES_COMPARATOR) {
    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        val image = getItem(position)

        Glide.with(holder.itemView).load(image?.largeImageURL)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.bindingInt.imageViewImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val binding =
            RecImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImagesViewHolder(binding)
    }

    class ImagesViewHolder(private val binding: RecImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val bindingInt = binding
    }

    companion object {
        private val IMAGES_COMPARATOR = object : DiffUtil.ItemCallback<Image>() {
            override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
                return oldItem == newItem
            }

        }
    }


}