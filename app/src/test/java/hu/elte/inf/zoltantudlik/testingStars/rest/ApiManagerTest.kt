package hu.elte.inf.zoltantudlik.testingStars.rest

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.reset
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


internal class ApiManagerTest {

    private val validJsonAnswer = "[\n" +
            "  {\n" +
            "    \"name\": \"Average Joe\",\n" +
            "    \"id\": \"5aea62b83c9d4f0009ba86cc\",\n" +
            "    \"_ratings\": [\n" +
            "      {\n" +
            "        \"id\": \"7c190130-4e6f-11e8-9153-b97a2f3659ea\",\n" +
            "        \"value\": 5\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": \"7c190131-4e6f-11e8-9153-b97a2f3659ea\",\n" +
            "        \"value\": 1\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": \"7c190132-4e6f-11e8-9153-b97a2f3659ea\",\n" +
            "        \"value\": 3\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": \"7c190133-4e6f-11e8-9153-b97a2f3659ea\",\n" +
            "        \"value\": 3\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": \"7c190134-4e6f-11e8-9153-b97a2f3659ea\",\n" +
            "        \"value\": 4\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": \"c08cd980-4e70-11e8-a3c0-edbb6f0e4b93\",\n" +
            "        \"value\": 5\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": \"2576ac80-4ebd-11e8-a3c0-edbb6f0e4b93\",\n" +
            "        \"value\": 4\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": \"4e6ef480-4ebd-11e8-a3c0-edbb6f0e4b93\",\n" +
            "        \"value\": 4\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": \"9f888470-4ec8-11e8-a3c0-edbb6f0e4b93\",\n" +
            "        \"value\": 4\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": \"dcc0ca70-4ecb-11e8-a3c0-edbb6f0e4b93\",\n" +
            "        \"value\": 4\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"John Doe\",\n" +
            "    \"id\": \"5aea62b83c9d4f0009ba86cd\",\n" +
            "    \"_ratings\": [\n" +
            "      {\n" +
            "        \"id\": \"7c1a60c0-4e6f-11e8-9153-b97a2f3659ea\",\n" +
            "        \"value\": 5\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": \"7c1a60c1-4e6f-11e8-9153-b97a2f3659ea\",\n" +
            "        \"value\": 3\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": \"7c1a60c2-4e6f-11e8-9153-b97a2f3659ea\",\n" +
            "        \"value\": 4\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": \"7c1a60c3-4e6f-11e8-9153-b97a2f3659ea\",\n" +
            "        \"value\": 1\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": \"7c1a60c4-4e6f-11e8-9153-b97a2f3659ea\",\n" +
            "        \"value\": 3\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Alice Avalance\",\n" +
            "    \"id\": \"5aea62b83c9d4f0009ba86ce\",\n" +
            "    \"_ratings\": [\n" +
            "      {\n" +
            "        \"id\": \"7c1a87d0-4e6f-11e8-9153-b97a2f3659ea\",\n" +
            "        \"value\": 1\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": \"7c1a87d1-4e6f-11e8-9153-b97a2f3659ea\",\n" +
            "        \"value\": 3\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": \"7c1a87d2-4e6f-11e8-9153-b97a2f3659ea\",\n" +
            "        \"value\": 5\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": \"7c1a87d3-4e6f-11e8-9153-b97a2f3659ea\",\n" +
            "        \"value\": 1\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": \"7c1a87d4-4e6f-11e8-9153-b97a2f3659ea\",\n" +
            "        \"value\": 4\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "]"

    @Test
    fun `get users succeeds properly`() {
//        val mockContentService: ContentService =  mock<ContentService> {
//            on { getUsers() } doReturn validJsonAnswer
//        }
    }
}