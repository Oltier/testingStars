package hu.elte.inf.zoltantudlik.testingStars.people

interface PeopleClickListener<in User> {
    fun onClick(person: User)
}