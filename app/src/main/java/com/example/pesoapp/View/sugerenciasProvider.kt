package com.example.pesoapp.View

class sugerenciasProvider {

    companion object {
        val sugerenciasLits = listOf<recordatorio>(
            recordatorio(
                "¿Sabes cómo alimentarte para lograr el peso ideal?",
                "https://labuenanutricion.com/wp-content/uploads/2020/07/como-lograr-el-peso-ideal.jpg",
                "https://labuenanutricion.com/blog/sabes-como-alimentarte-para-lograr-el-peso-ideal/"
            ),
            recordatorio(
                "¿Conoces los beneficios de los lácteos para tu salud?",
                "https://sialaleche.org/wp-content/uploads/2016/01/noticiasSAL0125_salud.jpg",
                "https://sialaleche.org/conoces-los-beneficios-de-los-lacteos-para-tu-salud/"
            ),
            recordatorio(
                "Cinco maneras de alcanzar un peso saludable",
                "https://www.diabeweb.com/uploads/ponencias/173/logo_173.jpg",
                "https://kidshealth.org/es/teens/weight-tips.html"
            ),
            recordatorio(
                "Especias y hierbas para perder peso",
                "https://www.hogarmania.com/archivos/201809/especias-668x400x80xX.jpg",
                "https://www.hogarmania.com/salud/bienestar/dieta-sana/especias-hierbas-para-perder-peso.html"
            ),
            recordatorio(
                "10 recomendaciones para lograr un peso ideal",
                "https://mfmindful.com/wp-content/uploads/2021/12/10-recomendaciones-para-lograr-un-peso-ideal-MF-Mindful.png.webp",
                "https://mfmindful.com/10-recomendaciones-para-lograr-un-peso-ideal/#:~:text=Para%20lograr%20el%20peso%20ideal%20en%20este%20caso%2C,secos%2C%20semillas%2C%20frutas%20secas%20%28sin%20azúcar%20agregada%29%2C%20queso."
            ),
            recordatorio(
                "¿Cómo subir de peso de manera rápida y segura?",
                "https://cdn-prod.medicalnewstoday.com/content/images/articles/321/321518/weight-gain-foods-wholegrain-bread.jpg",
                "https://www.medicalnewstoday.com/articles/es/326685#alimentos-para-ganar-peso-con-rapidez"
            ),
            recordatorio(
                "Alimentación saludable para un peso saludable",
                "https://www.cdc.gov/healthyweight/spanish/images/healthyeating/healthy-eating-food-sp-500-small.jpg",
                "https://www.cdc.gov/healthyweight/spanish/healthyeating/index.html#:~:text=Resalta%20la%20importancia%20de%20las,de%20soya%2C%20nueces%20y%20semillas"
            ),
            recordatorio(
                "TIPS PARA LLEGAR A TU PESO IDEAL.",
                "https://cdn2.salud180.com/sites/default/files/styles/medium/public/field/image/2015/01/volver_a_tu_peso_ideal.jpg",
                "https://doralfamilyjournal.com/10-tips-para-llegar-a-tu-peso-ideal/"
            ),
            recordatorio(
                "Cosas que puedes hacer para volver a tu peso ideal",
                "https://mejorconsalud.as.com/wp-content/uploads/2018/06/volver-a-peso-ideal-768x512.jpg?auto=webp&quality=45&width=1920&crop=16:9,smart,safe",
                "https://mejorconsalud.as.com/9-cosas-puedes-volver-peso-ideal/"
            )
        )
    }

    val bmilist = listOf<BMI>(
        BMI(
            "Delgadez muy extrema", "< 16.0"
        ),
        BMI(
            "Delgadez extrema", "16.0 - 16.9"
        ),
        BMI(
            "Delgadez", "17.0 - 18.4"
        ),
        BMI(
            "ormal", "18.5 - 24.9"
        ),
        BMI(
            "Sobrepeso", "25.0 - 29.9"
        ),
        BMI(
            "Obesidad grado I", "30.0 - 34.9"
        ),
        BMI(
            "Obesidad grado II", "35.0 40.0"
        )
    )

}