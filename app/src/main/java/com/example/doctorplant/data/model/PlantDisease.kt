package com.example.doctorplant.data.model

import com.google.gson.annotations.SerializedName

data class PlantDisease(
    @SerializedName("diagnostico")
    val diagnosis: String,

    @SerializedName("id_tecnico")
    val technicalId: String,

    @SerializedName("confianca")
    val confidence: String,

    @SerializedName("info")
    val information: DiseaseInformation
) {
    val confidenceToFloat: Float get() =
        confidence
            .replace("%", "")
            .trim()
            .toFloatOrNull()
            ?.div(100f)
            ?: 0f
}

data class DiseaseInformation(
    @SerializedName("nome")
    val name: String,

    @SerializedName("descricao")
    val description: String,

    @SerializedName("sintomas")
    val symptoms: List<String>,

    @SerializedName("tratamento")
    val treatment: String
)