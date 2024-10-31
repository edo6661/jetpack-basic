package com.example.basic_01.ui.shared

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Fungsi suspend untuk memuat progress secara bertahap.
// Fungsi ini memperbarui progress setiap 100ms.
suspend fun loadProgress(updateProgress : (Float) -> Unit) {
  for (i in 1..100) {
    updateProgress(i.toFloat() / 100) // Setiap 100 iterasi akan memperbarui nilai progress
    delay(100) // Menunda eksekusi setiap iterasi selama 100 milidetik
  }
}

// Composable untuk LinearProgressIndicator dengan progress yang ditentukan
@Composable
fun LinearDeterminateIndicator() {
  var currentProgress by remember { mutableFloatStateOf(0f) } // Menyimpan progress saat ini
  var loading by remember { mutableStateOf(false) } // Menyimpan status loading
  val scope = rememberCoroutineScope() // Menyimpan scope untuk menjalankan coroutine

  // Mengatur layout Column dengan spasi antar elemen dan alignment tengah
  Column(
    verticalArrangement = Arrangement.spacedBy(12.dp), // Spasi vertikal antar elemen sebesar 12dp
    horizontalAlignment = Alignment.CenterHorizontally, // Elemen disusun di tengah secara horizontal
    modifier = Modifier.fillMaxWidth() // Column mengisi lebar layar
  ) {
    // Tombol untuk memulai loading progress
    Button(
      onClick = {
        loading = true // Mengaktifkan status loading
        scope.launch { // Menjalankan coroutine untuk memperbarui progress
          loadProgress { progress ->
            currentProgress = progress // Mengupdate nilai progress
          }
          loading = false // Setel status loading ke false setelah selesai
        }
      },
      enabled = ! loading // Tombol dinonaktifkan saat loading aktif
    ) {
      Text("Start loading") // Label tombol
    }

    // Jika loading aktif, tampilkan LinearProgressIndicator
    if (loading) {
      LinearProgressIndicator(
        progress = { currentProgress }, // Nilai progress yang sedang di-track
        modifier = Modifier.fillMaxWidth() // Progress bar mengisi lebar layar
      )
    }
  }
}

// Composable untuk CircularProgressIndicator dengan status loading tidak ditentukan
@Composable
fun IndeterminateCircularIndicator() {
  var loading by remember { mutableStateOf(false) } // Status loading

  // Tombol untuk memulai loading indeterminate
  Button(
    onClick = { loading = true }, // Set status loading ke true saat tombol diklik
    enabled = ! loading // Tombol hanya bisa diklik jika tidak sedang loading
  ) {
    Text("Start loading") // Label tombol
  }

  // Jika tidak sedang loading, keluar dari fungsi
  if (! loading) return

  // Menampilkan CircularProgressIndicator saat loading aktif
  CircularProgressIndicator(
    modifier = Modifier.width(64.dp), // Ukuran progress indicator 64dp
    color = MaterialTheme.colorScheme.secondary, // Warna lingkaran progress
    trackColor = MaterialTheme.colorScheme.surfaceVariant // Warna track atau background
  )
}

// Composable utama yang menggabungkan kedua jenis progress indicator
@Composable
fun ProgressIndicatorss() {
  Column(
    modifier = Modifier
      .fillMaxSize() // Mengisi seluruh ukuran layar
      .padding(16.dp), // Memberikan padding sebesar 16dp di seluruh sisi
    verticalArrangement = Arrangement.spacedBy(24.dp), // Spasi vertikal antar elemen sebesar 24dp
    horizontalAlignment = Alignment.CenterHorizontally // Elemen disusun di tengah secara horizontal
  ) {
    LinearDeterminateIndicator() // Menampilkan LinearDeterminateIndicator

    Spacer(modifier = Modifier.height(24.dp)) // Spacer untuk memberi jarak antara indikator

    IndeterminateCircularIndicator() // Menampilkan IndeterminateCircularIndicator
  }
}

