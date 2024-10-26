package com.example.reto_peliculas.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.reto_peliculas.data.local.entity.MovieEntity
import com.example.reto_peliculas.usecase.GetMoviesEntityUseCase
import com.example.reto_peliculas.usecase.GetMoviesUseCase
import com.example.reto_peliculas.utils.Result
import com.example.reto_peliculas.utils.state.BaseViewModel
import com.example.reto_peliculas.utils.state.ButtonState
import com.example.reto_peliculas.utils.state.DialogState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getMoviesEntityUseCase: GetMoviesEntityUseCase
) : BaseViewModel<HomeState, HomeAction, HomeEvent>(initialState = HomeState.buildInitialState()) {

    private var currentPage = 1

    override fun handleScreenActions(action: HomeAction) {
        when (action) {
            is HomeAction.Pagination -> loadMovies(action.page.toInt())
            is HomeAction.ItemSelect -> navigateDetails(action.movieSelect)
        }
    }

    init {
        loadMovies(currentPage)
    }

    private val _movies = mutableStateOf<List<MovieEntity>>(emptyList())
    val movies: State<List<MovieEntity>> = _movies

    private fun loadMovies(page: Int) {
        showLoading()
        setState(state.copy(page = page.toString()))
        viewModelScope.launch {
            when (val result = getMoviesUseCase(page)) {
                is Result.Success -> {
                    hideLoading()
                    _movies.value = result.data
                    val pages = creationListPage(movies.value)
                    setState(
                        state.copy(
                            movies = movies.value,
                            totalPages = pages,
                            page = page.toString()
                        )
                    )

                }

                is Result.Failure -> {
                    hideLoading()
                    dialogError(page)
                }
            }
        }
    }

    private fun dialogError(page: Int) {
        showDialog(
            DialogState(
                title = "Error",
                message = "El servicio no responde correctamente",
                firstButton = ButtonState(
                    text = "Volver a intentar",
                    onButtonClicked = {
                        loadMovies(page)
                        dismissDialog()
                    }
                ),
                secondButton = ButtonState(
                    text = "Usar los datos locales",
                    onButtonClicked = {
                        loadMoviesLocal(page)
                        dismissDialog()
                    }
                )
            )
        )

    }

    private fun loadMoviesLocal(page: Int) {
        showLoading()
        setState(state.copy(page = page.toString()))
        viewModelScope.launch {
            when (val result = getMoviesEntityUseCase(page)) {
                is Result.Success -> {
                    hideLoading()
                    _movies.value = result.data
                    val pages = creationListPage(movies.value)
                    setState(
                        state.copy(
                            movies = movies.value,
                            totalPages = pages,
                            page = page.toString()
                        )
                    )

                }

                is Result.Failure -> {
                    hideLoading()
                    dialogError(page)
                }
            }
        }
    }

    private fun navigateDetails(movieEntity: MovieEntity) {
        sendEvent(HomeEvent.NavigateDetails(movieEntity))
    }

    private fun creationListPage(movies: List<MovieEntity>): List<String> {
        val listPages: MutableList<String> = mutableListOf()
        for (i in 1..movies[0].totalPage!!.toInt())
            listPages.add("$i")
        return listPages
    }
}