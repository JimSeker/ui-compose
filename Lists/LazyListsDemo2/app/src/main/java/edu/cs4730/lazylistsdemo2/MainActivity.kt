package edu.cs4730.lazylistsdemo2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.cs4730.lazylistsdemo2.ui.theme.LazyListsDemo2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listOfPhonebook: MutableList<Phonebook> = ArrayList()
        listOfPhonebook.add(Phonebook(0, "Test", "9981728", "test@test.com"))
        listOfPhonebook.add(Phonebook(1, "Test1", "1234455", "test1@test.com"))
        listOfPhonebook.add(Phonebook(2, "Test2", "00000", "test2@test.com"))
        listOfPhonebook.add(Phonebook(3, "Test3", "00000", "test3@test.com"))
        listOfPhonebook.add(Phonebook(4, "Test4", "00000", "test4test.com"))
        listOfPhonebook.add(Phonebook(5, "Test5", "00000", "test5@test.com"))
        listOfPhonebook.add(Phonebook(6, "Test6", "00000", "test6@test.com"))


        setContent {
            LazyListsDemo2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(listOfPhonebook)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name), fontSize = 18.sp) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}

@Composable
fun MainScreen(listOfPhonebook: MutableList<Phonebook>) {
    val state = rememberLazyListState()
    //this is a really stupid way to do this, but no other method has worked.  wastes lots of memory here.
    val list = remember { mutableStateListOf<Phonebook>() }
    list.addAll(listOfPhonebook)

    Scaffold(
        topBar = {
            TopBar()
        },
    ) { padding ->
        LazyColumn(
            state = state,
            modifier = Modifier
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(  //this is supposed to help redraw when the listOfPhonebook changes, but does not work.  so this bad work around.
                items = list,
                key = { entry ->
                    entry.id
                }
            ) { entry ->
            //itemsIndexed(list) { index, entry ->
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
                            listOfPhonebook.remove(entry)
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
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val listOfPhonebook: MutableList<Phonebook> = ArrayList()
    listOfPhonebook.add(Phonebook(0, "Test", "9981728", "test@test.com"))
    listOfPhonebook.add(Phonebook(1, "Test1", "1234455", "test1@test.com"))
    listOfPhonebook.add(Phonebook(2, "Test2", "00000", "test2@test.com"))
    listOfPhonebook.add(Phonebook(3, "Test3", "00000", "test3@test.com"))
    listOfPhonebook.add(Phonebook(4, "Test4", "00000", "test4test.com"))
    listOfPhonebook.add(Phonebook(5, "Test5", "00000", "test5@test.com"))
    listOfPhonebook.add(Phonebook(6, "Test6", "00000", "test6@test.com"))
    MainScreen(listOfPhonebook)
}
