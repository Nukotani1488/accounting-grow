package com.alduraimron.accountinggrow.ui.screens

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddModerator
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.alduraimron.accountinggrow.data.model.SavingEntity
import com.alduraimron.accountinggrow.data.model.SavingType
import com.alduraimron.accountinggrow.ui.TabButton
import com.alduraimron.accountinggrow.ui.viewmodel.SavingViewModel
import com.alduraimron.accountinggrow.ui.viewmodel.SavingViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL
import java.text.NumberFormat
import java.util.Locale

@Composable
fun UserImage(imageUrl: String?, modifier: Modifier = Modifier) {
    var bitmap by remember { mutableStateOf<androidx.compose.ui.graphics.ImageBitmap?>(null) }

    LaunchedEffect(imageUrl) {
        imageUrl?.let { url ->
            try {
                val bmp = withContext(Dispatchers.IO) {
                    val stream = URL(url).openStream()
                    BitmapFactory.decodeStream(stream)
                }
                bitmap = bmp?.asImageBitmap()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    if (bitmap != null) {
        Image(
            bitmap = bitmap!!,
            contentDescription = null,
            modifier = modifier.size(48.dp)
        )
    } else {
        // Placeholder if no image
        Box(
            modifier = modifier
                .size(48.dp)
                .background(Color.Gray)
        )
    }
}
@Composable
fun SavingEntry(saving: SavingEntity) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //UserImage(saving.imageUrl)
        Image(
            painterResource(saving.dummyResId),
            contentDescription = "dummy image"
        )
        Text(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth(),
            text = saving.name ?: ""
        )
        Spacer(Modifier.height(15.dp))
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Nominal Pengisian"
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = "Rp",
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.width(10.dp))
                Column(Modifier.fillMaxWidth()) {
                    val formatter = NumberFormat.getNumberInstance(Locale.GERMAN)
                    Text(formatter.format(saving.target))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        val type = when(saving.type) {
                            SavingType.DAILY -> "Perhari"
                            SavingType.WEEKLY -> "Perminggu"
                            SavingType.MONTHLY -> "Perbulan"
                        }
                        Text("${formatter.format(saving.amount)} $type")
                        Text("0%")
                    }
                }
            }
        }
    }
}
@Composable
fun SavingScreen(navController: NavHostController) {
    val savingViewModel: SavingViewModel = viewModel(
        //placeholder
        factory = SavingViewModelFactory("1")
    )
    var isOngoingTab by remember { mutableStateOf(true) }
    val savings =   if (isOngoingTab) savingViewModel.ongoingSavings.value
                    else savingViewModel.completedSavings.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .verticalScroll(rememberScrollState())
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
                Text(
                    text = "Tabungan",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                // Tabs
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    TabButton(
                        text = "Berlangsung",
                        isSelected = isOngoingTab,
                        modifier = Modifier.weight(1f),
                        onClick = { isOngoingTab = !isOngoingTab }
                    )
                    TabButton(
                        text = "Tercapai",
                        isSelected = !isOngoingTab,
                        modifier = Modifier.weight(1f),
                        onClick = { isOngoingTab = !isOngoingTab }
                    )
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.8f),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(savings) { saving ->
                        SavingEntry(saving)
                    }
                }

                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .padding(top = 32.dp)
                        .height(48.dp)
                        .align(Alignment.End),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1E88E5)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            tint = Color.White,
                            contentDescription = "Tambah Tabungan"
                        )
                        Text(
                            text = "Tambah Tabungan",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}