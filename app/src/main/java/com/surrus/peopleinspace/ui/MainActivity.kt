package com.surrus.peopleinspace.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.*
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.AdapterList
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.layout.padding
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.surrus.common.remote.Assignment
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private val peopleInSpaceViewModel: PeopleInSpaceViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val peopleState = peopleInSpaceViewModel.peopleInSpace.collectAsState(emptyList())
            mainLayout(peopleState)
        }
    }
}

@Composable
fun mainLayout(peopleState: State<List<Assignment>>) {
    MaterialTheme {
        Column {
            TopAppBar(
                title = {
                    Text("People In Space")
                }
            )
            AdapterList(data = peopleState.value) { person ->
                Row(person)
            }

        }
    }
}


@Composable
fun Row(person: Assignment) {
    Text(
        text = "${person.name} (${person.craft})",
        modifier = Modifier.padding(16.dp)
    )
}



@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        Row(Assignment("ISS", "John O'Reilly"))
    }
}