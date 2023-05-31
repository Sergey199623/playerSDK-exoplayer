package ru.player.sdk.core

import android.content.Context
import com.google.android.exoplayer2.ExoPlayer

interface VideoPlayerApi {

    var isPlaying: Boolean
    var currentVideoUrl: String?

    /**
     * Инициализация плеера, включая основной движок (ExoPlayer)
     * @param context - необходим для инициализации ExoPlayer
     */
    fun setInitPlayer(context: Context)

    /**
     * Возвращает экземпляр ExoPlayer
     */
    fun getExoPlayer(): ExoPlayer

    /**
     * Передача url для отображения видео. Важно: видео, которое нужно проиграть, доставаемое из
     * хранилища - передаётся так же в формате uri (строки) вида "\\content...", как и url-адрес
     * видео из сети интернет
     * @param url - ссылка на видео-ресурс
     * @param context - контекст, необходимый для MediaItem (куда мы передаём Url)
     */
    fun setVideoUrl(url: String, context: Context)

    /**
     * Запуск видео
     */
    fun play()

    /**
     * Пауза видео
     */
    fun pause()

    /**
     * Остановка видео
     */
    fun stop()

    /**
     * Сброс прогресса видео
     */
    fun reset()

    /**
     * Обнуление ExoPlayer
     */
    fun release()

    /**
     * Изменение скорости видео
     * @param speed - значение скорости в Float (1.0f .. 2.0f)
     */
    fun changeSpeed(speed: Float)

    /**
     * Изменение громкости видео
     * @param volume - значение громкости в Float (0.0f .. 1.0f)
     */
    fun changeVolume(volume: Float)
    fun seekTo(isForward: Boolean)
}