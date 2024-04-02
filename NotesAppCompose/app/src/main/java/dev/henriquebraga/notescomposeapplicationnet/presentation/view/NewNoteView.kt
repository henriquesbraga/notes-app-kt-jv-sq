package dev.henriquebraga.notescomposeapplicationnet.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.henriquebraga.notescomposeapplicationnet.presentation.viewmodel.NewNoteViewModel

@Composable
fun NewNoteView(
    viewModel: NewNoteViewModel,
    navController: NavController,
    noteId: Int?
) {


    var title = viewModel.title.collectAsState()
    var body = viewModel.body.collectAsState()
    var colorChecked = viewModel.color.collectAsState()
    var isLoading = viewModel.isLoading.collectAsState()

    if (noteId != 0) {
        viewModel.getNoteById(noteId!!)
    } else {
        viewModel.clearData()
    }

    Scaffold(topBar = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                viewModel.clearData()
                navController.popBackStack()
            }
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Sort Notes",
                    modifier = Modifier.size(35.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
            Text(
                text = "Note App!",
                modifier = Modifier.weight(1f),
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }, floatingActionButton = {
        FloatingActionButton(
            onClick = {
                viewModel.sendNote(navController)

            }, containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(Icons.Default.Check, contentDescription = "Add")
        }
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(4.dp, 0.dp),
        ) {

            if (isLoading.value) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                OutlinedTextField(
                    value = title.value,
                    onValueChange = { viewModel.updateTitle(it) },
                    label = { Text(text = "Título") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = body.value,
                    onValueChange = { viewModel.updateBody(it) },
                    label = { Text(text = "Corpo") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 4
                )
                CirclesBox(colorChecked = colorChecked.value) { selectedCode ->
                    viewModel.updateColor(selectedCode)
                }

            }


        }
    }
}

@Composable
fun CirclesBox(colorChecked: Int, onColorCheckedChange: (Int) -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            CircleButton(color = "-2234644", isSelected = colorChecked == 0) {
                onColorCheckedChange(0)
            }
            CircleButton(color = "-8469267", isSelected = colorChecked == 1) {
                onColorCheckedChange(1)
            }
            CircleButton(color = "-3081091", isSelected = colorChecked == 2) {
                onColorCheckedChange(2)
            }
            CircleButton(color = "-1222758", isSelected = colorChecked == 3) {
                onColorCheckedChange(3)
            }
            CircleButton(color = "-13963914", isSelected = colorChecked == 4) {
                onColorCheckedChange(4)
            }
        }
    }
}

@Composable
fun CircleButton(color: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(56.dp)
            .clip(CircleShape)
            .background(Color(color.toInt())),
        contentAlignment = Alignment.Center
    ) {
        if (isSelected) {
            Icon(
                imageVector = Icons.Rounded.Check,
                contentDescription = "Selected",
                modifier = Modifier.size(35.dp),
                tint = Color.Black
            )
        }
        IconButton(onClick = onClick) {}
    }
}

@Preview
@Composable
fun NewNoteViewPreview() {

    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }


    Scaffold(topBar = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Sort Notes",
                    modifier = Modifier.size(35.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
            Text(
                text = "Note App!",
                modifier = Modifier.weight(1f),
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }, floatingActionButton = {
        FloatingActionButton(
            onClick = {}, containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(Icons.Default.Check, contentDescription = "Add")
        }
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(4.dp, 0.dp),
        ) {

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(text = "Título") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(text = "Corpo") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 4
            )

            //CirclesBox()

        }
    }
}
