package com.user.poc

    import com.user.poc.model.User
    import com.user.poc.network.UserRepository
    import com.user.poc.viewModel.UserViewModel
    import kotlinx.coroutines.Dispatchers
    import kotlinx.coroutines.ExperimentalCoroutinesApi
    import kotlinx.coroutines.test.*
    import org.junit.*
    import org.mockito.Mockito.*
    import org.mockito.kotlin.any
    import org.mockito.kotlin.mock

    @ExperimentalCoroutinesApi
    class UserViewModelTest {

//        @get:Rule
//        val instantTaskExecutorRule = InstantTaskExecutorRule()

        private val testDispatcher = StandardTestDispatcher()
        private lateinit var viewModel: UserViewModel
        private lateinit var repository: UserRepository

        @Before
        fun setup() {
            Dispatchers.setMain(testDispatcher)
            repository = mock()
            viewModel = UserViewModel(repository)
        }

        @After
        fun tearDown() {
            Dispatchers.resetMain()
        }

        @Test
        fun `loadUsers success updates userList`() = runTest {
            val fakeUsers = listOf(User("1", "priyanka", "priyanka@gmail.com"))
            `when`(repository.fetchUsers()).thenReturn(fakeUsers)

            viewModel.loadUsers()
            testDispatcher.scheduler.advanceUntilIdle()

            Assert.assertEquals(fakeUsers, viewModel.userList)
        }

        @Test
        fun `addUser success updates userList`() = runTest {
            val newUser = User("2", "priyanka", "priyanka@gmail.com")
            `when`(repository.createUser(any())).thenReturn(newUser)
            `when`(repository.fetchUsers()).thenReturn(listOf(newUser))

            viewModel.addUser("priyanka", "priyanka@gmail.com")
            testDispatcher.scheduler.advanceUntilIdle()

            Assert.assertEquals(1, viewModel.userList.size)
            Assert.assertEquals("priyanka", viewModel.userList[0].name)
        }

        @Test
        fun `deleteUser success updates userList`() = runTest {
            `when`(repository.deleteUser("1")).thenReturn(mock())
            `when`(repository.fetchUsers()).thenReturn(emptyList())

            viewModel.deleteUser("1")
            testDispatcher.scheduler.advanceUntilIdle()

            Assert.assertTrue(viewModel.userList.isEmpty())
        }
    }
