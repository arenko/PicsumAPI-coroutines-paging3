package com.photos.picsum.ui.detail

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.photos.picsum.R
import com.photos.picsum.base.BaseFragment
import com.photos.picsum.data.Constants
import com.photos.picsum.data.model.PicsumPhoto
import com.photos.picsum.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : BaseFragment<DetailViewModel, FragmentDetailBinding>(
    R.layout.fragment_detail,
    DetailViewModel::class.java
) {

    lateinit var photoId: String
    var photo: PicsumPhoto? = null

    override fun init() {
        if (arguments != null) {
            photoId = requireArguments().getString(Constants.photoId).toString()
            initLayout()
        }
    }

    private fun initLayout() {
        binding.viewModel?.getPhotoDetail(photoId)?.observe(this, {
            if (it != null) {
                photo = it
                binding.tvAuthor.text = resources.getString(R.string.author, photo?.author)
                changeImage(null)
            }
        })

        binding.btnNormal.setOnClickListener {
            changeImage(null)
        }
        binding.btnGrayscale.setOnClickListener {
            binding.viewModel?.grayscalePhoto(photoId)?.observe(this, {
                if (it != null) {
                    changeImage(BitmapFactory.decodeStream(it.byteStream()))
                }
            })
        }
        binding.btnBlur.setOnClickListener {
            binding.viewModel?.blurPhoto(photoId)?.observe(this, {
                if (it != null) {
                    changeImage(BitmapFactory.decodeStream(it.byteStream()))
                }
            })
        }
        binding.ivPhotoDetail.setOnClickListener {
//            childFragmentManager.popBackStackImmediate()
            findNavController().navigate(
                R.id.action_detailFragment_to_mainFragment, bundleOf(
                    Constants.photoId to photo?.id,
                )
            )
        }
    }

    private fun changeImage(bitmap: Bitmap?) {
        if (bitmap != null) {
            binding.ivPhotoDetail.setImageBitmap(bitmap)
        } else if (photo != null) {
            Glide.with(requireContext())
                .load(photo?.download_url)
                .into(binding.ivPhotoDetail)
        }
    }
}