package com.example.reto_peliculas.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reto_peliculas.data.local.entity.MovieEntity
import com.example.reto_peliculas.presentation.auth.AuthEvent
import com.example.reto_peliculas.presentation.component.AppDropdownTextField
import com.example.reto_peliculas.presentation.component.AppMovie
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun HomeScreen(
    screenHandler: (HomeAction) -> Unit,
    state: HomeState,
    navigate: (MovieEntity) -> Unit,
    events: SharedFlow<HomeEvent>
) {
    LaunchedEffect(key1 = Unit) {
        events.collect { event ->
            when (event) {
                is HomeEvent.NavigateDetails -> navigate(event.movie)
            }

        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 14.dp)
                    .align(Alignment.CenterVertically),
                text = "Page: ${state.page}",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            AppDropdownTextField(
                modifier = Modifier
                    .padding(end = 14.dp)
                    .width(100.dp),
                selectedOption = state.page,
                options = state.totalPages,
                onOptionSelected = { screenHandler(HomeAction.Pagination(it))},
                labelText = "Page"
            )
        }
        HorizontalDivider()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 80.dp)
    ) {
        items(state.movies) {
            AppMovie(
                onclick = {screenHandler(HomeAction.ItemSelect(it))},
                movie = it
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        screenHandler = {
            HomeAction.Pagination("1")
        },
        state = HomeState.buildInitialState(),
        navigate = {},
        events = MutableSharedFlow()
    )
}