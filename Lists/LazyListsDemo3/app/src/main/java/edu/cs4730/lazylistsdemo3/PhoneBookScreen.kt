package edu.cs4730.lazylistsdemo3

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PhonebookScreen() {
    val state = rememberLazyListState()
    //this is a really stupid way to do this, but no other method has worked.  wastes lots of memory here.
    val list = remember { mutableStateListOf<Phonebook>() }
    list.add(Phonebook(0, "Test", "9981728", "test@test.com"))
    list.add(Phonebook(1, "Test1", "1234455", "test1@test.com"))
    list.add(Phonebook(2, "Test2", "00000", "test2@test.com"))
    list.add(Phonebook(3, "Test3", "00000", "test3@test.com"))
    list.add(Phonebook(4, "Test4", "00000", "test4test.com"))
    list.add(Phonebook(5, "Test5", "00000", "test5@test.com"))
    list.add(Phonebook(6, "Test6", "00000", "test6@test.com"))

    LazyColumn(
        state = state,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        itemsIndexed(list) { index, entry ->
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                )

            ) {
                Text(
                    text = entry.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp)
                )
                Row(modifier = Modifier.padding(start = 8.dp)) {
                    Column(modifier = Modifier.width(250.dp)) {
                        Text(text = "Mobile: ${entry.phone}")
                        Text(text = "Mail: ${entry.mail}")
                    }
                    val context = LocalContext.current
                    Button(onClick = {
                        list.remove(entry)
                        Toast.makeText(
                            context,
                            "value is ${entry.name}",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }) {
                        Text("Remove")
                    }
                }


            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PhonebookScreenPreview() {
    PhonebookScreen()
}