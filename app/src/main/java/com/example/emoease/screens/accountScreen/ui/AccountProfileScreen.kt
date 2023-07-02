package com.example.emoease.screens.accountScreen.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.emoease.navigation.Graph
import com.example.emoease.screens.DrawLineSolid
import com.example.emoease.screens.accountScreen.util.AccountViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AccountProfileScreen(
    viewModel: AccountViewModel = hiltViewModel(),
    padding: PaddingValues = PaddingValues(12.dp),
    navController: NavController
) {
    val general = viewModel.getListOfGeneralItems()
    val application = viewModel.getListOfApplicationItems()
    val others = viewModel.getListOfOtherItems()
    val size by remember {
        mutableStateOf(Size.Zero)
    }
    val density = LocalDensity.current
    val boxSize = 60.dp
    val width = remember(size) {
        if (size.width == 0f) {
            1f
        } else {
            size.width - with(density) { boxSize.toPx() }
        }
    }
    val scope = rememberCoroutineScope()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)


    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            item {
                //  Text(text = "Account", style = TextStyle(color = TextColor, fontFamily = FontFamilyAirLyft.extraBold, fontWeight = FontWeight.Bold, fontSize = 30.sp), modifier = Modifier.padding(top = 20.dp, start = 22.dp))
                ProfileBox(name = "Mohammad Imran ")
                DrawLineSolid(
                    strokeWidth = 22f,
                    color = Color.LightGray,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            item { Spacer(modifier = Modifier.height(18.dp)) }
            item {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "General", style = TextStyle(
                        fontWeight = FontWeight.Bold, fontSize = 12.sp
                    ), modifier = Modifier.padding(start = 22.dp, bottom = 16.dp)
                )
            }
            items(general) {
                ItemCard(
                    itemName = it.itemName,
                    itemIcon = it.itemIcon,
                    backgroundColor = it.color,
                    itemDescription = it.itemDescription
                ) {
//                       if (it.enabled){
//                           navController.navigate(Graph.Account)
//                       }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
            item {

                DrawLineSolid(
                    strokeWidth = 22f,
                    color = Color.LightGray,
                    modifier = Modifier.padding(top = 12.dp)
                )
                Spacer(modifier = Modifier.height(26.dp))
                Text(
                    text = "Application", style = TextStyle(
                        fontWeight = FontWeight.Bold, fontSize = 12.sp
                    ), modifier = Modifier.padding(start = 22.dp, bottom = 16.dp)
                )
            }
            items(application) {
                ItemCard(
                    itemName = it.itemName,
                    itemIcon = it.itemIcon,
                    backgroundColor = it.color,
                    itemDescription = it.itemDescription
                ) {}
                Spacer(modifier = Modifier.height(12.dp))
            }
            item {

                DrawLineSolid(
                    strokeWidth = 22f,
                    color = Color.LightGray,
                    modifier = Modifier.padding(top = 12.dp)
                )
                Spacer(modifier = Modifier.height(26.dp))
                Text(
                    text = "Others", style = TextStyle(
                        fontWeight = FontWeight.Bold, fontSize = 12.sp
                    ), modifier = Modifier.padding(start = 22.dp, bottom = 16.dp)
                )
            }
            items(others) {
                ItemCard(
                    itemName = it.itemName,
                    itemIcon = it.itemIcon,
                    backgroundColor = it.color,
                    itemDescription = it.itemDescription
                ) {}
                Spacer(modifier = Modifier.height(12.dp))
            }


        }
    }
}

@Composable
fun ProfileBox(name: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 22.dp, top = 30.dp, bottom = 20.dp, end = 12.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Column {
            Text(
                text = "Welcome", style = TextStyle(
                    fontWeight = FontWeight.Light, fontSize = 14.sp
                )
            )
            Text(
                text = name, style = TextStyle(
                    fontWeight = FontWeight.SemiBold, fontSize = 24.sp
                )
            )
        }
//        Card(modifier = Modifier.size(40.dp), shape = CircleShape, elevation = 0.dp, backgroundColor = RatingCardColor) {
//            Row (modifier = Modifier.padding(5.dp, end = 12.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
//                Icon(painterResource(id = R.drawable.star_icon)
//                    , contentDescription = null, tint = RatingStarColor, modifier = Modifier.padding(start = 4.dp))
//                Text(
//                    text = "5.0",
//                    style = TextStyle(
//                        color = RatingStarColor,
//                        fontFamily = FontFamilyAirLyft.medium,
//                        fontWeight = FontWeight.SemiBold,
//                        fontSize = 20.sp
//                    ),
//                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)
//                )
//            }
//        }

    }

}

@Composable
fun ItemCard(
    itemName: String,
    itemIcon: Int,
    backgroundColor: Color,
    itemDescription: String?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .clickable {
            onClick.invoke()
        }
        .padding(top = 4.dp, bottom = 4.dp)) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 12.dp, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Box(
                modifier = modifier
                    .wrapContentSize()
                    .clip(shape = CircleShape)
                    .background(backgroundColor), contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = itemIcon),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            }
            Column(
                modifier = Modifier.padding(start = 18.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = itemName,
                    style = TextStyle(fontWeight = FontWeight.Medium, fontSize = 16.sp)
                )
                if (itemDescription != null) {
                    Text(
                        text = itemDescription,
                        style = TextStyle(fontWeight = FontWeight.Medium, fontSize = 12.sp)
                    )
                }


            }
        }
    }
}

//Card(modifier = Modifier.wrapContentSize(), shape = RoundedCornerShape(12.dp)) {
//    Row {
//        Icon(painter = painterResource(id = R.drawable.star_icon)
//            , contentDescription = null)
//        Text(
//            text = "5.0",
//            style = TextStyle(
//                color = TextColor,
//                fontFamily = FontFamilyAirLyft.medium,
//                fontWeight = FontWeight.SemiBold,
//                fontSize = 24.sp
//            ),
//            modifier = Modifier.padding(top = 4.dp)
//        )
//    }
//}


