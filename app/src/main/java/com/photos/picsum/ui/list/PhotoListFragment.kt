package com.photos.picsum.ui.list

import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.photos.picsum.R
import com.photos.picsum.base.BaseFragment
import com.photos.picsum.data.Constants
import com.photos.picsum.data.model.PicsumPhoto
import com.photos.picsum.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhotoListFragment : BaseFragment<PhotoViewModel, FragmentListBinding>(
    R.layout.fragment_list,
    PhotoViewModel::class.java
) {

    lateinit var photoAdapter: PhotoAdapter

    override fun init() {
        if (arguments != null) {
            val photoId = requireArguments().getString(Constants.photoId).toString()
            Toast.makeText(context, photoId, Toast.LENGTH_SHORT).show()
        }
        initList()
        displayItems()
    }

    private fun initList() {
        photoAdapter =
            PhotoAdapter { selectedItem: PicsumPhoto -> listItemClicked(selectedItem) }
        binding.rvPhoto.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = photoAdapter
        }
        photoAdapter.addLoadStateListener { loadState ->
            if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && photoAdapter.itemCount < 1) {
                binding.rvPhoto.visibility = View.GONE
            } else {
                binding.rvPhoto.visibility = View.VISIBLE
            }
        }
    }

    private fun displayItems() {
        lifecycleScope.launch {
            viewModel?.getPhotoList()?.collect {
                photoAdapter.submitData(it)
            }
        }
    }

    private fun listItemClicked(photo: PicsumPhoto) {
        findNavController().navigate(
            R.id.action_mainFragment_to_detailFragment, bundleOf(
                Constants.photoId to photo.id,
            )
        )
    }
}