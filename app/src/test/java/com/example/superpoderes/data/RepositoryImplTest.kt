package com.example.superpoderes.data

import com.example.superpoderes.data.local.LocalDataSource
import com.example.superpoderes.data.local.fakes.FakeLocalDataSource
import com.example.superpoderes.data.mappers.LocalToPresentationMapper
import com.example.superpoderes.data.mappers.PresentationToLocalMapper
import com.example.superpoderes.data.mappers.RemoteToLocalMapper
import com.example.superpoderes.data.remote.RemoteDataSource
import com.example.superpoderes.data.remote.fakes.FakeRemoteDataSource
import com.example.superpoderes.utils.generateOneLocalSuperhero
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class RepositoryImplTest {
    private lateinit var repositoryImpl: RepositoryImpl

    private lateinit var localDataSource: LocalDataSource
    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var remoteToLocalMapper: RemoteToLocalMapper
    private lateinit var localToPresentationMapper: LocalToPresentationMapper
    private lateinit var presentationToLocalMapper: PresentationToLocalMapper

    @Before
    fun setup() {
        remoteToLocalMapper = RemoteToLocalMapper()
        localToPresentationMapper = LocalToPresentationMapper()
        presentationToLocalMapper = PresentationToLocalMapper()
    }

    @Test
    fun `WHEN getHeroes EXPECT local empty, return not empty list and match with the list build with generateLocalSuperheroes fun`() = runTest {
        localDataSource = FakeLocalDataSource()
        remoteDataSource = FakeRemoteDataSource()
        repositoryImpl =
            RepositoryImpl(remoteDataSource, localDataSource, remoteToLocalMapper, localToPresentationMapper, presentationToLocalMapper)

        val actual = repositoryImpl.getHeroes().toList()

        assert(actual.first().isNotEmpty())
        assert(actual.first()[0].thumbnail== "photo")
    }

    @Test
    fun `WHEN getHero EXPECT one Hero not favorite`() = runTest {
        localDataSource = mockk()
        remoteDataSource = mockk()
        repositoryImpl =
            RepositoryImpl(remoteDataSource, localDataSource, remoteToLocalMapper, localToPresentationMapper, presentationToLocalMapper)

        coEvery { localDataSource.getHero("1") } returns generateOneLocalSuperhero()

        val actual = repositoryImpl.getHero("1")

        assert(!actual.favorite)
    }
}