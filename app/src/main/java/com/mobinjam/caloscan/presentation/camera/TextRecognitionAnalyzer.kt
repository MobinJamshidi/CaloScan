package com.mobinjam.caloscan.presentation.camera

import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

class TextRecognitionAnalyzer(
    private val onTextDetected: (Text) -> Unit
) : ImageAnalysis.Analyzer {

    // ساخت یک نمونه از تشخیص‌دهنده متن گوگل
    private val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image

        if (mediaImage != null) {
            // تبدیل فریم دوربین به فرمت قابل فهم برای ML Kit
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

            recognizer.process(image)
                .addOnSuccessListener { visionText ->
                    // اگر متنی پیدا شد، می‌فرستیمش به UI
                    onTextDetected(visionText)
                }
                .addOnFailureListener { e ->
                    e.printStackTrace()
                }
                .addOnCompleteListener {
                    // نکته حیاتی: حتماً باید فریم رو ببندیم تا فریم بعدی بتونه وارد بشه
                    // اگر این خط نباشه، تصویر دوربین فریز میشه!
                    imageProxy.close()
                }
        } else {
            imageProxy.close()
        }
    }
}