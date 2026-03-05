package com.queuefree.quefreebackend.config
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.FirestoreClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.ByteArrayInputStream
import java.io.InputStream

@Configuration
class FirebaseConfig {

    @Bean
    fun firestore(): com.google.cloud.firestore.Firestore {

        val firebaseConfig = System.getenv("FIREBASE_SERVICE_ACCOUNT")
            ?: throw RuntimeException("Firebase service account not found")

        val serviceAccount = ByteArrayInputStream(firebaseConfig.toByteArray())


        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build()

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options)
        }

        return FirestoreClient.getFirestore()
    }
}
