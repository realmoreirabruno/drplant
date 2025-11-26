# 🌱 Dr. Plant - Soybean Disease Identifier

> Project developed for the course *Computer Projects II* -- UNESP Rio
> Claro (2025).

![Status](https://img.shields.io/badge/Status-Completed-success)
![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-purple)
![Compose](https://img.shields.io/badge/Jetpack%20Compose-Material3-blue)
![Architecture](https://img.shields.io/badge/Architecture-MVVM%20%2B%20Clean-orange)

------------------------------------------------------------------------

## 📋 About the Project

**Dr. Plant** is a native Android application developed to support
precision agriculture.\
Its main goal is to identify pests and diseases in soybean leaves using
**Computer Vision** and **Artificial Intelligence**.

The app captures or selects an image of the leaf, sends it to an API
hosted on Hugging Face Spaces, and returns a detailed diagnosis
including confidence level, disease description, symptoms, and
recommended treatment.

The project aligns with the United Nations Sustainable Development Goals
(SDGs), specifically **Zero Hunger (2)** and **Industry, Innovation and
Infrastructure (9)**.

------------------------------------------------------------------------

## 📱 Screenshots

  --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                           Home & Camera                                              Analysis & Loading                                         Diagnosis (Success)                                          History & Filters
  --------------------------------------------------------------- ----------------------------------------------------------- ---------------------------------------------------------- -----------------------------------------------------------
   `<img src="screenshots/home_camera.png" width="200"/>`{=html}   `<img src="screenshots/loading.png" width="200"/>`{=html}   `<img src="screenshots/result.png" width="200"/>`{=html}   `<img src="screenshots/history.png" width="200"/>`{=html}

  --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

> *Note: The images above are illustrative of the interface developed
> with Jetpack Compose.*

------------------------------------------------------------------------

## 🚀 Features

-   📸 **Image Capture:** CameraX integration for high‑quality photos.
-   📂 **Gallery:** Select images from the local storage via Photo
    Picker.
-   🧠 **AI‑Powered Diagnosis:** Optimized image submission using
    **Retrofit** to a Python API (FastAPI/Hugging Face).
-   📝 **Detailed Report:** Technical name, probability, symptoms,
    description, and treatment.
-   🗂️ **Local History:** Persistent storage using **Room Database** for
    offline access.
-   🔍 **Advanced Filters:** Filter history by date (Today, This Week,
    All).
-   📊 **Dashboard:** Total scans, detected diseases, and average
    accuracy.
-   🗑️ **Management:** Multi‑selection for deleting history entries.

------------------------------------------------------------------------

## 🛠️ Technologies & Architecture (Mobile)

The app follows modern Android development best practices:

-   **Language:** Kotlin
-   **UI:** Jetpack Compose (Material Design 3)
-   **Architecture:** MVVM + Clean Architecture principles
-   **Dependency Injection:** Koin
-   **Networking:** Retrofit 2 + OkHttp
-   **Database:** Room
-   **Asynchronous Programming:** Coroutines & StateFlow
-   **Images:** Coil

------------------------------------------------------------------------

### 🧩 Layered Architecture

``` mermaid
graph TD
    UI[Screens & Composables] --> ViewModel[ViewModels (StateFlow)]
    ViewModel --> Repository[Repositories]
    Repository --> Remote[Remote Data Source (Retrofit)]
    Repository --> Local[Local Data Source (Room DAO)]
```

------------------------------------------------------------------------

## 🧠 Backend & AI

Although this repository focuses on the Mobile client, the system
consumes an external API developed by the team:

-   **Hosting:** Hugging Face Spaces (Dockerized)\
-   **Framework:** FastAPI\
-   **Model:** ConvNeXt‑Large (feature extraction) + SVM
    (classification)

------------------------------------------------------------------------

## 🔧 How to Run

1.  **Clone the repository:**

    ``` bash
    git clone https://github.com/your-user/dr-plant-android.git
    ```

2.  **Open in Android Studio:** Koala or newer.

3.  **Sync Gradle.**

4.  **Run:** Use an emulator or physical device (Min SDK 26+).

------------------------------------------------------------------------

## 👥 Authors

Developed by students of UNESP -- Rio Claro:

-   **Bruno Moreira** --- Android Mobile Development
-   **Julia Amadio** --- Backend & Infrastructure
-   **Victor Mariano Rocha** --- Dataset & AI

**Advisor:** Prof. Dr. Daniel Carlos Guimarães Pedronette.

------------------------------------------------------------------------

## ⌨️ Made with ❤️ by \[Bruno Moreira]
