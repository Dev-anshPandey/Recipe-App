package com.example.recipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainVeiwModel : ViewModel() {
    private val _catgoriesState = mutableStateOf(RecipeState())
    val categoryState : State<RecipeState> = _catgoriesState

    init {
        fetchCategories()
    }

    private fun fetchCategories(){
        viewModelScope.launch {
            try {
                val response= recipeService.getCategories()
                _catgoriesState.value=_catgoriesState.value.copy(
                    list = response.categories,
                    isLoading = false,
                    error = null

                )
            }
            catch (e : Exception){
                _catgoriesState.value=_catgoriesState.value.copy(
                    isLoading = false,
                    error = "Error message ${e.message}"
                )
            }
        }
    }

    data class RecipeState(
        val isLoading : Boolean=true,
        val list : List<Category> = emptyList(),
        val error: String?= null
    )

}