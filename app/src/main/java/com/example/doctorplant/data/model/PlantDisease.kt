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
)

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