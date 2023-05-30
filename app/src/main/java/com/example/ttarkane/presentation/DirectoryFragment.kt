package com.example.ttarkane.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ttarkane.R
import com.example.ttarkane.databinding.FragmentDirectoryBinding
import com.example.ttarkane.presentation.adapters.directory_repo.DirectoryAdapter


class DirectoryFragment : Fragment() {

    private var _binding: FragmentDirectoryBinding? = null
    private val binding get() = _binding!!

    private val adapter = DirectoryAdapter()

    private var ownerName: String? = null
    private var repoName: String? = null
    private var pathName: String? = null

    private val viewModel by lazy {
        ViewModelProvider(this)[DirectoryViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            repoName = it.getString(EXTRA_REPO) ?: ""
            ownerName = it.getString(EXTRA_OWNER) ?: ""
            pathName = it.getString(EXTRA_DIR) ?: ""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDirectoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvDirectoryList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDirectoryList.adapter = adapter
        if (pathName == "") {
            viewModel.loadData(ownerName, repoName)

        } else {
            viewModel.loadData(ownerName, repoName, pathName)
        }
        viewModel.directoryList.observe(requireActivity()) {
            adapter.submitList(it)
        }
        setListeners()
    }


    private fun setListeners() {
        adapter.onFileClickListener = {
            val bundle = ContentFileFragment.newInstance(it, ownerName, repoName)
            findNavController().navigate(
                R.id.action_DirectoryListFragment_to_WebViewFragment,
                bundle
            )
        }

        adapter.onFolderClickListener = {
            val bundle = it.type?.let { dir ->
                newInstanceDirectory(
                    ownerName.toString(),
                    repoName.toString(),
                    it.path.toString()
                )
            }
            findNavController().navigate(R.id.SecondFragment, bundle)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        fun newInstanceRootDirectory(owner: String, repo: String): Bundle {
            return Bundle().apply {
                putString(EXTRA_OWNER, owner)
                putString(EXTRA_REPO, repo)
            }
        }

        fun newInstanceDirectory(owner: String, repo: String, dir: String): Bundle {
            return Bundle().apply {
                putString(EXTRA_OWNER, owner)
                putString(EXTRA_REPO, repo)
                putString(EXTRA_DIR, dir)
            }
        }

        private const val EXTRA_OWNER = "owner_extra"
        private const val EXTRA_REPO = "repo_extra"
        private const val EXTRA_DIR = "dir_extra"
    }
}