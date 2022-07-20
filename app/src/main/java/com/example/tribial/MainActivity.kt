package com.example.tribial

import android.os.Bundle
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.room.Room
import com.badoo.ribs.android.RibActivity
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.modality.BuildContext
import com.example.tribial.api.ApiDataSource
import com.example.tribial.app_root.AppRoot
import com.example.tribial.app_root.AppRootBuilder
import com.example.tribial.database.AppDB
import com.example.tribial.database.QuestionDao

class MainActivity : RibActivity() {

    private val root: ConstraintLayout by lazy { findViewById(R.id.layout_mainActivity) }

    override val rootViewGroup: ViewGroup
        get() = root

    override fun createRib(savedInstanceState: Bundle?): Rib {

        val db = Room.databaseBuilder(
            applicationContext,
            AppDB::class.java, "database"
        ).build()

        return AppRootBuilder(
            dependency = object : AppRoot.Dependency {
                override val localDataSource: QuestionDao = db.questionDao()
                override val remoteDataSource: ApiDataSource = object :ApiDataSource{
                    override fun getQuestions(): String {
                        TODO("Not yet implemented")
                    }

                }
            }
        )
            .build(BuildContext.root(savedInstanceState))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)

    }
}
