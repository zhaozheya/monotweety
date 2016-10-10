package net.yslibrary.monotweety

import android.content.Context
import android.support.v4.app.NotificationManagerCompat
import dagger.Module
import dagger.Provides
import net.yslibrary.monotweety.base.Clock
import net.yslibrary.monotweety.base.ClockImpl
import net.yslibrary.monotweety.base.di.AppScope
import net.yslibrary.monotweety.base.di.Names
import javax.inject.Named

/**
 * Created by yshrsmz on 2016/09/24.
 */
@Module
open class AppModule(private val context: Context) {

  @Named(Names.FOR_APP)
  @AppScope
  @Provides
  fun provideAppContext(): Context = context

  @AppScope
  @Provides
  open fun provideAppLifecycleCallbacks(@Named(Names.FOR_APP) context: Context): App.LifecycleCallbacks {
    return AppLifecycleCallbacks(context)
  }

  @AppScope
  @Provides
  fun provideNotificationManager(@Named(Names.FOR_APP) context: Context): NotificationManagerCompat {
    return NotificationManagerCompat.from(context)
  }

  @AppScope
  @Provides
  fun provideClock(): Clock {
    return ClockImpl()
  }

  @AppScope
  @Provides
  fun provideConfig(): Config {
    return Config.init()
  }

  interface Provider {
    fun notificationManager(): NotificationManagerCompat
    fun clock(): Clock
    fun config(): Config
  }
}