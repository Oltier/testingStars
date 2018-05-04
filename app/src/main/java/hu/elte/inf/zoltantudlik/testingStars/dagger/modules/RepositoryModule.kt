package hu.elte.inf.zoltantudlik.testingStars.dagger.modules

import dagger.Module
import dagger.Provides
import hu.elte.inf.zoltantudlik.testingStars.repositories.ContentRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideContentManeger(): ContentRepository {
        return ContentRepository()
    }

}