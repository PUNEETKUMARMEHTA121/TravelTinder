package com.example.traveldiary.utils

object Common {
    fun getIntroImages(): MutableList<String> {
        val img: MutableList<String> = ArrayList()
        val one =
            "https://firebasestorage.googleapis.com/v0/b/zobaze-pos.appspot.com/o/imgInApp%2Fon_boarding%2Foct2020%2FSales%20counter.png?alt=media&token=7e1e108b-dcb0-4d49-9265-6a250cf61c7e"
        val two =
            "https://firebasestorage.googleapis.com/v0/b/zobaze-pos.appspot.com/o/imgInApp%2Fon_boarding%2Foct2020%2FInventory%20management.png?alt=media&token=2c7ba611-8e50-4d08-85e9-ec01d13fb476"
        val three =
            "https://firebasestorage.googleapis.com/v0/b/zobaze-pos.appspot.com/o/imgInApp%2Fon_boarding%2Foct2020%2Fstorefront.png?alt=media&token=5c559a3c-1331-4832-9a98-e7847ec499fc"
        val four =
            "https://firebasestorage.googleapis.com/v0/b/zobaze-pos.appspot.com/o/imgInApp%2Fon_boarding%2Foct2020%2FAnalytics%26r.png?alt=media&token=d2a23ebd-10dd-4480-a842-809850a050aa"
        return img.apply {
            add(one)
            add(two)
            add(three)
            add(four)
        }
    }
}