package ru.player.sdk.core

import android.app.Activity
import android.app.PictureInPictureParams
import android.content.Context
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackParameters
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class VideoPlayer : VideoPlayerApi {

    private lateinit var trackSelector: DefaultTrackSelector
    private lateinit var exoPlayer: ExoPlayer

    override fun setInitPlayer(context: Context) {
        trackSelector = DefaultTrackSelector(context)
        exoPlayer = ExoPlayer.Builder(context).setTrackSelector(trackSelector).build()
    }

    override fun getExoPlayer(): ExoPlayer = exoPlayer

    override var currentVideoUrl: String? = null


    override fun setVideoUrl(url: String, context: Context) {
        currentVideoUrl = url
        val dataSourceFactory = DefaultDataSourceFactory(
            context,
            Util.getUserAgent(context, "YourApplicationName")
        )
        val mediaItem = MediaItem.fromUri(url)
        val mediaSource: MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(mediaItem)
        exoPlayer.setMediaSource(mediaSource)
        exoPlayer.prepare()
    }

    override fun play() {
        exoPlayer.playWhenReady = true
    }

    override fun pause() {
        exoPlayer.playWhenReady = false
    }

    override fun stop() {
        exoPlayer.stop()
    }

    override fun reset() {
        exoPlayer.stop(true)
        exoPlayer.prepare()
        currentVideoUrl = null
    }

    override fun release() {
        exoPlayer.release()
    }

    override fun changeSpeed(speed: Float) {
        val parameters = PlaybackParameters(speed)
        exoPlayer.playbackParameters = parameters
    }

    override fun changeVolume(volume: Float) {
        exoPlayer.volume = volume
    }

    override fun seekTo(isForward: Boolean) {
        when (isForward) {
            true -> exoPlayer.seekTo(exoPlayer.currentPosition + 10000)
            else -> exoPlayer.seekTo(exoPlayer.currentPosition - 10000)
        }
    }

    override fun getPlayerView(context: Context, isNeedBaseControllers: Boolean): StyledPlayerView =
        StyledPlayerView(context).apply {
            player = getExoPlayer()
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
            useController = isNeedBaseControllers
        }

    override fun enterPipMode(context: Activity, params: PictureInPictureParams) {
        context.enterPictureInPictureMode(params)
    }
}