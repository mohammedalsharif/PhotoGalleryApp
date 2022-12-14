package com.examples.photogalleryapp.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.examples.photogalleryapp.R
import com.examples.photogalleryapp.Util.Common
import com.examples.photogalleryapp.data.model.Image
import com.examples.photogalleryapp.databinding.RecImageItemBinding
import com.examples.photogalleryapp.listener.OnClickListener

class ImagesPageAdapter :
    PagingDataAdapter<Image, ImagesPageAdapter.ImagesViewHolder>(IMAGES_COMPARATOR) {

    lateinit var clickListener: OnClickListener
    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        val image = getItem(position)

        val requestOptions = RequestOptions()
            .placeholder(Common.getRandomDrawableColor())
            .error(Common.getRandomDrawableColor())
            .diskCacheStrategy(DiskCacheStrategy.ALL)

        holder.bindingInt.root.setOnClickListener {
            clickListener.onClickItem(position)
        }
        Glide.with(holder.itemView).load(image?.webformatURL)
            .apply(requestOptions)
            .addListener(object :
                RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.bindingInt.progressLoadPhoto.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.bindingInt.progressLoadPhoto.visibility = View.GONE
                    return false
                }

            })
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