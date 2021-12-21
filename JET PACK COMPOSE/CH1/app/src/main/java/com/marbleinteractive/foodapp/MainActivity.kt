package com.marbleinteractive.foodapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marbleinteractive.foodapp.data.FoodCategory
import com.marbleinteractive.foodapp.data.getAllFoodCategories
import com.marbleinteractive.foodapp.ui.theme.FoodAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FoodAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column(Modifier.fillMaxSize()) {
                        Menu(
                            modifier = Modifier
                                .align(Alignment.End)
                                .padding(8.dp)
                        )
                        Title()
                        Categories(modifier = Modifier)
                        Search(modifier = Modifier)
                        Plates(modifier = Modifier)

                    }
                }
            }
        }
    }
}

@Composable
fun Plates(modifier: Modifier){

    Card() {
        Row() {
            Image(painter = painterResource(id = R.drawable.image_1), contentDescription = "")
            Text(text = "$20")
        }
        Column() {
                Text(text = "Vegan salad bowl")
                Text(text = "With red tomato")

                Text(text = "Contrary to popular belief, Lorem ipsum, is not simply random text.")

                Text(text = "420Kcal")
        }
    }
}

@Composable
fun Search(modifier: Modifier) {
    Row(modifier.fillMaxWidth()) {
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            value = "",
            onValueChange = {},
            label = { Text(text = "Search") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFFFFFFF)
            ),
            shape = RoundedCornerShape(25.dp),
            leadingIcon = { Icon(Icons.Filled.Search, "", tint = Color.Black ) }
        )
    }

}

@Composable
fun Categories(modifier: Modifier) {
    val state = remember { mutableStateOf(0) }
    val categories = getAllFoodCategories()

    ScrollableTabRow(
        backgroundColor = Color.White,
        selectedTabIndex = state.value,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                color = Color.Red,
                height = 4.dp,
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[state.value])
            )
        }
    ) {
        categories.forEachIndexed { index, category ->
            Tab(
                text = {
                    Text(
                        category.value,
                        color = if (state.value == index) {
                            Color.Red
                        } else {
                            Color.Gray
                        }
                    )
                },
                selected = state.value == index,
                onClick = { state.value = index }
            )
        }
    }
}

@Composable
fun Title() {
    Column(Modifier.padding(16.dp)) {
        Text(
            text = "Simple way to find",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.W500
        )
        Text(
            text = "Tasty food",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.W500
        )
    }
}

@Composable
fun Menu(modifier: Modifier) {
    Icon(Icons.Default.Menu, contentDescription = "Menu", modifier = modifier)
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    FoodAppTheme {
        Greeting("Android")
    }
}