package com.jsevilla.memeschilenos.feature.ui.fragment.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jsevilla.memeschilenos.domain.entity.MemeEntity
import com.jsevilla.memeschilenos.domain.usecase.GetListMemesChile
import com.jsevilla.memeschilenos.domain.usecase.GetListSearchMemeUseCase
import com.jsevilla.memeschilenos.feature.base.BaseViewModel

class SearchFragmentViewModel(
    private val getListMemesChile: GetListMemesChile,
    private val getListSearchMemeUseCase: GetListSearchMemeUseCase
) : BaseViewModel() {

    init {
        showLoading(false)
        shouldShowEmptyView(false)
        showErrorCause(false)
        setRefreshing(false)
    }

    private val _isListMemes = MutableLiveData<MemeEntity>()
    val isListMemes: LiveData<MemeEntity> = _isListMemes

    fun getListMemes() {
        showLoading(true)
        shouldShowEmptyView(false)
        getListMemesChile.invoke(viewModelScope, Any()) {
            it.either(::handleUseCaseFailureFromBase, ::handleUseCaseSuccessInfoHome)
        }
    }

    fun getListSearchMemes(data: String) {
        showLoading(false)
        shouldShowEmptyView(false)
        val params = GetListSearchMemeUseCase.Params(data = data)
        getListSearchMemeUseCase.invoke(viewModelScope, params) {
            it.either(::handleUseCaseFailureFromBase, ::handleUseCaseSuccessInfoHome)
        }
    }

    private fun handleUseCaseSuccessInfoHome(memeEntity: MemeEntity) {
        showLoading(false)
        shouldShowEmptyView(memeEntity.children.isEmpty())
        showErrorCause(false)
        _isListMemes.value = memeEntity
    }
}
