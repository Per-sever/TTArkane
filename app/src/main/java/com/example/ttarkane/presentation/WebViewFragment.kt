package com.example.ttarkane.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ttarkane.data.models.DirectoryEntity
import com.example.ttarkane.databinding.FragmentWebViewBinding

class WebViewFragment : Fragment() {

    private var _binding: FragmentWebViewBinding? = null

    private val binding get() = _binding!!

    private val viewModel by lazy {
        ViewModelProvider(this)[WebViewViewModel::class.java]
    }

    private var directoryEntity: DirectoryEntity? = null
    private var ownerName: String? = null
    private var repoName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            directoryEntity = it.getParcelable(DIRECTORY_EXTRA)
            ownerName = it.getString(OWNER_EXTRA)
            repoName = it.getString(REPO_EXTRA)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWebViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadData(directoryEntity, ownerName, repoName)
        viewModel.fileEntity.observe(requireActivity()) {
            loadFile(it.content, it.encoding)
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun loadFile(content: String?, encodingType: String?) {
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = WebViewClient()
        if (content != null) {
            binding.webView.loadData(content, "text/plain", encodingType)
        }
    }

    companion object {
        private const val DIRECTORY_EXTRA = "directory_extra"
        private const val OWNER_EXTRA = "owner_extra"
        private const val REPO_EXTRA = "repo_extra"


        fun newInstance(directoryEntity: DirectoryEntity, owner: String, repo: String) =
            Bundle().apply {
                putParcelable(DIRECTORY_EXTRA, directoryEntity)
                putString(OWNER_EXTRA, owner)
                putString(REPO_EXTRA, repo)
            }

    }
}