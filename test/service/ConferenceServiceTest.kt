package service

import com.ianarbuckle.conferencesapi.models.*
import com.ianarbuckle.conferencesapi.repository.ConferenceRepository
import com.ianarbuckle.conferencesapi.service.ConferenceService
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class ConferenceServiceTest {

    @MockK
    lateinit var repository: ConferenceRepository

    lateinit var service: ConferenceService

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        service = ConferenceService(repository)
    }

    @Test
    fun `verify find all returns an empty list of conferences `() = runBlockingTest {
        coEvery { repository.findAllConferences() } returns emptyList()

        assertEquals(0, service.findAll().size)
    }

    @Test
    fun `verify find all returns a list of conferences`() = runBlockingTest {
        coEvery { repository.findAllConferences() } returns stubConferences()

        assertEquals(2, service.findAll().size)
    }

    @Test
    fun `verify find one conference returns a valid conference`() = runBlockingTest {
        coEvery { repository.findOneConference("1") } returns stubLondon()

        assertEquals(stubLondon(), service.findOne("1"))
    }

    @Test
    fun `verify find one conference returns an invalid conference`() = runBlockingTest {
        coEvery { repository.findOneConference("1") } returns stubBerlin()

        assertNotEquals(stubLondon(), service.findOne("1"))
    }

    @Test
    fun `verify find one conference returns an invalid conference when null`() = runBlockingTest {
        coEvery { repository.findOneConference("1") } returns null

        assertEquals(null, service.findOne("1"))
    }

    private fun stubConferences(): List<Conference> {
        val london = stubLondon()

        val berlin = stubBerlin()

        return listOf(london, berlin)
    }

    private fun stubBerlin(): Conference {
        val berlin = Conference(
            "2",
            "Berlin Droidcon",
            "",
            Location(
                Country("Germany", "Berlin"),
                Venue("", "", LatLng(0.0, 0.0))
            ),
            Dates("2020/10/15", "2020/10/18"),
            CallForPapers("", "", ""),
            "CANCELLED"
        )
        return berlin
    }

    private fun stubLondon(): Conference {
        val london = Conference(
            "1",
            "London Droidcon",
            "",
            Location(
                Country("United Kingdon", "London"),
                Venue("", "", LatLng(0.0, 0.0))
            ),
            Dates("2020/10/15", "2020/10/18"),
            CallForPapers("", "", ""),
            "CANCELLED"
        )
        return london
    }
}