package com.example.login

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView


class Explorar : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflamos la vista del fragmento
        val rootView = inflater.inflate(R.layout.fragment_explorar, container, false)

        // Obtenemos el VideoView desde la vista inflada
        val simpleVideoView: VideoView = rootView.findViewById(R.id.simpleVideoView)

        // Configuramos el URI del video
        val videoUri: Uri = Uri.parse("android.resource://${requireContext().packageName}/${R.raw.fishvideo}")
        simpleVideoView.setVideoURI(videoUri)

        // Reproducimos el video (si deseas que empiece automáticamente)
        simpleVideoView.start()
        return rootView
        //return inflater.inflate(R.layout.fragment_explorar, container, false)
    }
}