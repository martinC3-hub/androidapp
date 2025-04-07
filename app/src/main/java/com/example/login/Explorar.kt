package com.example.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.widget.AppCompatImageButton


class Explorar : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_explorar, container, false)
        """
        PARA PONER UN VIDEO ES:
        // Inflate the layout for this fragment
        // Inflamos la vista del fragmento
        

        // Obtenemos el VideoView desde la vista inflada
        val simpleVideoView: VideoView = rootView.findViewById(R.id.simpleVideoView)

        // Configuramos el URI del video
        val videoUri: Uri = Uri.parse("android.resource://${requireContext().packageName}/${R.raw.fishvideo}")
        simpleVideoView.setVideoURI(videoUri)

        // Reproducimos el video (si deseas que empiece automáticamente)
        simpleVideoView.start()
        return rootView
        """
        val python1 = rootView.findViewById<AppCompatImageButton>(R.id.imageButton)

        val pythonpart1 = rootView.findViewById<AppCompatImageButton>(R.id.pythonpart1)
        val pythonpart2 = rootView.findViewById<AppCompatImageButton>(R.id.pythonpart2)
        val pythonpart3 = rootView.findViewById<AppCompatImageButton>(R.id.pythonpart3)

        val sciencepart1 = rootView.findViewById<AppCompatImageButton>(R.id.sciencepart1)
        val sciencepart2 = rootView.findViewById<AppCompatImageButton>(R.id.sciencepart2)
        val sciencepart3 = rootView.findViewById<AppCompatImageButton>(R.id.sciencepart3)

        val bddpart1 = rootView.findViewById<AppCompatImageButton>(R.id.bddpart1)
        val bddpart2 = rootView.findViewById<AppCompatImageButton>(R.id.bddpart2)
        val bddpart3 = rootView.findViewById<AppCompatImageButton>(R.id.bddpart3)

        val redespart1 = rootView.findViewById<AppCompatImageButton>(R.id.redespart1)
        val redespart2 = rootView.findViewById<AppCompatImageButton>(R.id.redespart2)
        val redespart3 = rootView.findViewById<AppCompatImageButton>(R.id.redespart3)

        // Set the click listener
        python1.setOnClickListener {
            // Open the specified URL in a browser
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=TkN2i-_4N4g"))
            startActivity(intent)
        }
        pythonpart1.setOnClickListener {
            // Open the specified URL in a browser
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=G1VNhuCJbuc"))
            startActivity(intent)
        }
        pythonpart2.setOnClickListener {
            // Open the specified URL in a browser
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=bwhTaKu91tY"))
            startActivity(intent)
        }
        pythonpart3.setOnClickListener {
            // Open the specified URL in a browser
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=5oM2gr_CkEQ"))
            startActivity(intent)
        }
        sciencepart1.setOnClickListener {
            // Open the specified URL in a browser
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=wB4DG_EipaU"))
            startActivity(intent)
        }
        sciencepart2.setOnClickListener {
            // Open the specified URL in a browser
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=HD0Hv9A3md4"))
            startActivity(intent)
        }
        sciencepart3.setOnClickListener {
            // Open the specified URL in a browser
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=mOt8jQZA4ok"))
            startActivity(intent)
        }
        bddpart1.setOnClickListener {
            // Open the specified URL in a browser
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/CzvkXi9IeRQ?list=PLFKN3uu3AAiE3nHa7HyHDB7gKeUodWYNW"))
            startActivity(intent)
        }
        bddpart2.setOnClickListener {
            // Open the specified URL in a browser
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/OXfuIBoP0hE?list=PLFKN3uu3AAiE3nHa7HyHDB7gKeUodWYNW"))
            startActivity(intent)
        }
        bddpart3.setOnClickListener {
            // Open the specified URL in a browser
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/TNEsWYXyXEA?list=PLFKN3uu3AAiE3nHa7HyHDB7gKeUodWYNW"))
            startActivity(intent)
        }
        redespart1.setOnClickListener {
            // Open the specified URL in a browser
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/zTR4NN-T8tc?list=PLxnG_9vdV6-gEWV15pq89XK_W3G8vP5_0"))
            startActivity(intent)
        }
        redespart2.setOnClickListener {
            // Open the specified URL in a browser
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/7MSazHb1nE8?list=PLxnG_9vdV6-gEWV15pq89XK_W3G8vP5_0"))
            startActivity(intent)
        }
        redespart3.setOnClickListener {
            // Open the specified URL in a browser
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/WSwSjCiSpII"))
            startActivity(intent)
        }




        return rootView
    }
}