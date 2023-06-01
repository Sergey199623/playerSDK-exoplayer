package ru.player.sdk.core

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import ru.player.sdk.utils.PlaybackState
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackParameters
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class VideoPlayer : VideoPlayerApi {

    private lateinit var trackSelector : DefaultTrackSelector
    private lateinit var exoPlayer : ExoPlayer
    private val playbackState = mutableStateOf(PlaybackState.STOPPED)

    override fun setInitPlayer(context: Context) {
        trackSelector = DefaultTrackSelector(context)
        exoPlayer = ExoPlayer.Builder(context).setTrackSelector(trackSelector).build()
    }

    override fun getExoPlayer(): ExoPlayer = exoPlayer

    override var currentVideoUrl: String? = null

    override var isPlaying: Boolean = false

    init {
        ProcessLifecycleOwner.get().lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            fun onForeground() {
                if (playbackState.value == PlaybackState.PLAYING) {
                    exoPlayer.playWhenReady = true
                }
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            fun onBackground() {
                if (playbackState.value == PlaybackState.PLAYING) {
                    exoPlayer.playWhenReady = false
                }
            }
        })
    }

    override fun setVideoUrl(url: String, context: Context) {
        currentVideoUrl = url
        isPlaying = false
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
        isPlaying = true
        playbackState.value = PlaybackState.PLAYING
    }

    override fun pause() {
        exoPlayer.playWhenReady = false
        isPlaying = false
        playbackState.value = PlaybackState.PAUSED
    }

    override fun stop() {
        exoPlayer.stop()
        isPlaying = false
        playbackState.value = PlaybackState.STOPPED
    }

    override fun reset() {
        exoPlayer.stop(true)
        playbackState.value = PlaybackState.STOPPED
        exoPlayer.prepare()
        currentVideoUrl = null
    }

    override fun release() {
        exoPlayer.release()
        playbackState.value = PlaybackState.STOPPED
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
}