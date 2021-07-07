package com.shyam.repolist.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.map
import com.shyam.repolist.R
import com.shyam.repolist.db.model.Repository
import com.shyam.repolist.repository.RepoListRemoteMediator
import com.shyam.repolist.ui.MainActivityDelegate
import com.shyam.repolist.ui.avatar.AvatarViewModel
import com.shyam.repolist.ui.main.adapters.LoaderStateAdapter
import com.shyam.repolist.ui.main.adapters.RepositoryListAdapter
import com.shyam.repolist.ui.webpage.WebPageViewModel
import com.shyam.repolist.util.initToolbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(),RepositoryListAdapter.ItemClickListener {



    private val viewModel: MainViewModel by viewModels()
    private lateinit var mainActivityDelegate: MainActivityDelegate
    private val adapter = RepositoryListAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mainActivityDelegate = context as MainActivityDelegate
        } catch (e: ClassCastException) {
            throw ClassCastException()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    @Suppress("DEPRECATION")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initToolbar(toolbar = toolbar, R.string.app_name, false)
        mainActivityDelegate.setupNavDrawer(toolbar)
        mainActivityDelegate.enableNavDrawer(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        adapter.setOnItemClickListener(this)
        loadRepos()
    }

    override fun onItemClick(repository: Repository) {
        repository.let {
            findNavController().navigate(
                R.id.action_a_b,
                AvatarViewModel.createArguments(repository.owner)
            )
        }
    }

    override fun onUrlClick(repository: Repository) {
        repository.let {
            findNavController().navigate(
                R.id.action_a_c,
                WebPageViewModel.createArguments(repository)
            )
        }
    }

    private fun setupViews() {
        //rvImages.adapter = imageAdapter
        val loaderStateAdapter = LoaderStateAdapter {
            adapter.retry()
        }
        rvRepos.adapter = adapter.withLoadStateFooter(footer = loaderStateAdapter)

    }


    private fun loadRepos() {

        lifecycleScope.launch {
            Log.i(TAG,"getRepoList")
            viewModel.getRepoList()
                .collectLatest { pagingData ->
                    adapter.submitData(pagingData)
                }
        }

        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->
                if(loadStates.refresh is LoadState.Loading){
                    progressBar?.visibility = View.VISIBLE
                }else{
                    progressBar?.visibility = View.GONE
                }
            }
        }
    }

    companion object {
        val TAG = MainFragment::class.simpleName
        fun newInstance() = MainFragment()
    }

}