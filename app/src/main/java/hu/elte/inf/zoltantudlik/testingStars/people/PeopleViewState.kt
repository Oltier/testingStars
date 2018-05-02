package hu.elte.inf.zoltantudlik.testingStars.people


data class PeopleViewState(var loading: Boolean = false, val people: List<Person>? = null, val error: Throwable? = null) : BaseViewState