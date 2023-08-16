package com.bavovna.uvctest

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bavovna.uvctest.databinding.FragmentCameraBinding
import com.jiangdg.ausbc.MultiCameraClient
import com.jiangdg.ausbc.base.CameraFragment
import com.jiangdg.ausbc.callback.ICameraStateCallBack
import com.jiangdg.ausbc.camera.bean.CameraRequest
import com.jiangdg.ausbc.render.env.RotateType
import com.jiangdg.ausbc.widget.AspectRatioGLSurfaceView
import com.jiangdg.ausbc.widget.AspectRatioSurfaceView
import com.jiangdg.ausbc.widget.AspectRatioTextureView
import com.jiangdg.ausbc.widget.IAspectRatio

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CameraFragment : CameraFragment() {

    private var _binding: FragmentCameraBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun getCameraView(): IAspectRatio? {
        return AspectRatioSurfaceView(requireContext())
//        return AspectRatioTextureView(requireContext())
//        return AspectRatioGLSurfaceView(requireContext())
    }

    override fun getCameraViewContainer(): ViewGroup? {
        return _binding?.container
    }

    override fun getRootView(inflater: LayoutInflater, container: ViewGroup?): View? {
        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCameraState(
        self: MultiCameraClient.ICamera,
        code: ICameraStateCallBack.State,
        msg: String?
    ) {
        var s  =when (code) {
            ICameraStateCallBack.State.OPENED -> "opened"
            ICameraStateCallBack.State.CLOSED -> "closed"
            ICameraStateCallBack.State.ERROR -> "error"
        }
        _binding?.cameraStatus?.text = "Camera status: $s, msg: $msg"
    }

    override fun getCameraRequest(): CameraRequest {
        return CameraRequest.Builder()
            .setPreviewWidth(640) // camera preview width
            .setPreviewHeight(480) // camera preview height
            .setRenderMode(CameraRequest.RenderMode.NORMAL) // camera render mode
            .setDefaultRotateType(RotateType.ANGLE_0) // rotate camera image when opengl mode
            .setAudioSource(CameraRequest.AudioSource.NONE) // set audio source
//            .setPreviewFormat(CameraRequest.PreviewFormat.FORMAT_YUYV) // set preview format, MJPEG recommended
            .setAspectRatioShow(true) // aspect render,default is true
            .setCaptureRawImage(false) // capture raw image picture when opengl mode
            .setRawPreviewData(false)  // preview raw image when opengl mode
            .create()
    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        _binding = FragmentFirstBinding.inflate(inflater, container, false)
//        return binding.root
//
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}