//package com.example.doctorplant.ui.diagnosis
//
//import android.net.Uri
//import android.os.Build
//import android.util.Log
//import androidx.annotation.RequiresApi
//import androidx.camera.core.CameraSelector
//import androidx.camera.core.ImageCapture
//import androidx.camera.core.ImageCaptureException
//import androidx.camera.view.PreviewView
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ShoppingCart
//import androidx.compose.material3.Button
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.DisposableEffect
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.viewinterop.AndroidView
//import androidx.core.content.ContextCompat
//import androidx.lifecycle.compose.LocalLifecycleOwner
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import coil.compose.AsyncImage
//import com.example.doctorplant.R
//import java.io.File
//
//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun CameraScreen(
//    navController: NavController,
//    onPhotoCaptured: (Uri) -> Unit
//) {
//    val context = LocalContext.current
//    val lifecycleOwner = LocalLifecycleOwner.current
//    var imageUri by remember { mutableStateOf<Uri?>(null) }
//    val cameraExecutor = remember { Executors.newSingleThreadExecutor() }
//
//    val outputDirectory = remember {
//        val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
//            File(it, context.getString(R.string.app_name)).apply { mkdirs() }
//        }
//        mediaDir ?: context.filesDir
//    }
//
//    var imageCapture: ImageCapture? = remember { null }
//
//    Box(modifier = Modifier.fillMaxSize()) {
//        AndroidView(
//            modifier = Modifier.fillMaxSize(),
//            factory = { ctx ->
//                val previewView = PreviewView(ctx)
//                val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)
//
//                cameraProviderFuture.addListener({
//                    val cameraProvider = cameraProviderFuture.get()
//                    val preview = Preview.Builder().build().apply {
//                        setSurfaceProvider(previewView.surfaceProvider)
//                    }
//                    imageCapture = ImageCapture.Builder().build()
//                    val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
//
//                    cameraProvider.unbindAll()
//                    cameraProvider.bindToLifecycle(
//                        lifecycleOwner, cameraSelector, preview, imageCapture
//                    )
//                }, ContextCompat.getMainExecutor(ctx))
//
//                previewView
//            }
//        )
//
//        // Botão de capturar foto
//        IconButton(
//            onClick = {
//                val photoFile = File(
//                    outputDirectory,
//                    "${System.currentTimeMillis()}.jpg"
//                )
//                val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
//
//                imageCapture?.takePicture(
//                    outputOptions,
//                    ContextCompat.getMainExecutor(context),
//                    object : ImageCapture.OnImageSavedCallback {
//                        override fun onError(exc: ImageCaptureException) {
//                            Log.e("CameraCapture", "Erro: ${exc.message}", exc)
//                        }
//
//                        override fun onImageSaved(output: ImageCapture.OutputFileResults) {
//                            val uri = Uri.fromFile(photoFile)
//                            imageUri = uri
//                        }
//                    }
//                )
//            },
//            modifier = Modifier
//                .align(Alignment.BottomCenter)
//                .padding(24.dp)
//                .size(72.dp)
//                .background(Color.White, CircleShape)
//        ) {
//            Icon(Icons.Default.ShoppingCart, contentDescription = "Capturar foto")
//        }
//
//        // Mostrar preview da foto capturada
//        imageUri?.let { uri ->
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(Color.Black.copy(alpha = 0.8f)),
//                contentAlignment = Alignment.Center
//            ) {
//                AsyncImage(model = uri, contentDescription = null, modifier = Modifier.fillMaxWidth())
//
//                Row(
//                    horizontalArrangement = Arrangement.SpaceEvenly,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .align(Alignment.BottomCenter)
//                        .padding(24.dp)
//                ) {
//                    Button(onClick = { imageUri = null }) {
//                        Text("Retake")
//                    }
//                    Button(onClick = {
//                        onPhotoCaptured(uri)
//                        navController.popBackStack()
//                    }) {
//                        Text("Use Photo")
//                    }
//                }
//            }
//        }
//    }
//
//    DisposableEffect(Unit) {
//        onDispose {
//            cameraExecutor.shutdown()
//        }
//    }
//}
//
//
//@Preview
//@Composable
//fun CameraScreenPreview() {
//    CameraScreen(
//        navController = rememberNavController(),
//        onPhotoCaptured = { } as (Uri) -> Unit
//    )
//}
