package dev.henriquebraga.notescomposeapplicationnet.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.henriquebraga.notescomposeapplicationnet.model.Note

@Composable
fun NoteItem(note: Note, onClick: () -> Unit) {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.End)
                .background(Color(note.color.toInt()))
                .clickable { onClick() }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = note.title, fontWeight = FontWeight.Bold)
                Text(text = note.note)
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.End),
                    text = note.date,
                    style = TextStyle(
                        background = Color(0xFF00999),
                    ),
                    textAlign = TextAlign.End
                )
            }
        }
    }
    Divider(color = Color.White, thickness = 4.dp)

}


@Preview
@Composable
fun noteItemTest() {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.End)
                .background(Color(-8469267))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = "Titulo", fontWeight = FontWeight.Bold)
                Text(text = "Conteudo ai da casrtinha. Deus é bom")
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.End),
                    text = "21/01/2024 08:00:00",
                    style = TextStyle(
                        background = Color(0xFF00999),
                    ),
                    textAlign = TextAlign.End
                )
            }
        }
    }
}

