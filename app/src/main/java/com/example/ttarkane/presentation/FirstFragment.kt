package com.example.ttarkane.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ttarkane.R
import com.example.ttarkane.databinding.FragmentFirstBinding
import com.example.ttarkane.presentation.adapters.GitHubRepoUserAdapter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val viewModel by lazy {
        ViewModelProvider(this)[SearchInfoViewModel::class.java]
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val adapter = GitHubRepoUserAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addTextChangeListeners()
        binding.recyclerView3.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView3.adapter = adapter
        adapter.onUserClickListener = {
            if (it.htmlUrl != null) {
                val browserIntent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.htmlUrl))
                startActivity(browserIntent)
            } else {
                Toast.makeText(requireActivity(), "Такой ссылки нет :(", Toast.LENGTH_SHORT).show()
            }
        }
        adapter.onRepoClickListener = {
            val bundle = SecondFragment.newInstance(
                it.owner?.login.toString(), it
                    .name ?: ""
            )
            findNavController().navigate(R.id.SecondFragment, bundle)
//            lifecycleScope.launch {
//                val result = ApiFactory.apiService.getDirectoryRepo(
//                    it.owner?.login.toString(), it
//                        .name ?: ""
//                )
//            }
        }
        viewModel.repoList.observe(requireActivity()) {
            adapter.addDataRepo(it)
        }
        viewModel.userList.observe(requireActivity()) {
            adapter.addDataUser(it)
        }
        binding.buttonSearch.setOnClickListener {
//            adapter.clearList()
            viewModel.loadData(binding.searchBarInput.text.toString())

        }


//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }

    private fun addTextChangeListeners() {
        binding.searchBarInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}