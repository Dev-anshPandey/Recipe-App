package com.example.recipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navController: NavHostController , modifier: Modifier){
    val recipeViewModel : MainVeiwModel = viewModel()
    val viewState by recipeViewModel.categoryState

    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route){
        composable(route = Screen.RecipeScreen.route) {
            RecipeScreen(viewState=viewState, navigateToDetail = {
                navController.currentBackStackEntry?.savedStateHandle?.set("cat",it)
                navController.navigate(Screen.DetailScreen.route)
            })
            }
        composable(route = Screen.DetailScreen.route) {
            val category = navController.previousBackStackEntry?.savedStateHandle?.
            get("cat")?:Category("","","","")
            CategoryDetailScreen(category = category)
        }
        }


}