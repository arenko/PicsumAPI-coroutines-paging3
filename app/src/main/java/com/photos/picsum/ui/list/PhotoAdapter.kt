package com.photos.picsum.ui.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.photos.picsum.R
import com.photos.picsum.data.model.PicsumPhoto
import com.photos.picsum.databinding.ItemPhotoBinding


class PhotoAdapter(private val clickListener: (PicsumPhoto) -> Unit) :
    PagingDataAdapter<PicsumPhoto, PhotoAdapter.CustomerViewHolder>(
        COMPARATOR
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemPhotoBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_photo, parent, false)
        return CustomerViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, clickListener) }
    }

    inner class CustomerViewHolder(val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: PicsumPhoto, clickListener: (PicsumPhoto) -> Unit) {
            Glide.with(binding.root.context)
                .load(photo.download_url)
                .apply(RequestOptions().override(300, 400).placeholder(R.color.light_gray))
                .into(binding.ivItem)

            binding.root.setOnClickListener {
                clickListener(photo)
            }
        }
    }

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<PicsumPhoto>() {
            override fun areItemsTheSame(oldItem: PicsumPhoto, newItem: PicsumPhoto): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: PicsumPhoto, newItem: PicsumPhoto): Boolean {
                return oldItem == newItem
            }
        }
    }
}