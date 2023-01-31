import java.util.Optional;

public class OptionalTest {

    /**
     * return an Optional<TeamLeader> object from teamLeader
     */
    public Optional<TeamLeader> createOptionalTeamLeader(TeamLeader teamLeader) {
        return Optional.ofNullable(teamLeader);
    }

    /**
     * Increment by one the age of teamLeader
     */
    public void incAge(Optional<TeamLeader> optionalTeamLeader) {
        optionalTeamLeader.ifPresent(teamLeader -> teamLeader.setAge(teamLeader.getAge() + 1));
    }

    /**
     * Increment by one the age of teamLeader if its age is > 15
     */
    public void incAgeIfMoreThanFifteen(Optional<TeamLeader> optionalTeamLeader) {
        optionalTeamLeader.filter(a -> a.getAge() > 15).ifPresent(teamLeader -> incAge(optionalTeamLeader));
    }

    /**
     * return the name of teamLeader or "No team leader"
     */
    public String getName(Optional<TeamLeader> optionalTeamLeader) {
        return optionalTeamLeader.map(TeamLeader::getName).orElse("No team leader");
    }

    /**
     * return the name of the teamLeader of the team of person or "No team leader"
     */
    public String getNameOfTeamLeader(Optional<Person> optionalPerson) {
        return optionalPerson.flatMap(person -> person.getTeam().
                flatMap(Team::getTeamLeader).map(TeamLeader::getName)).
                orElse("No team leader");
    }

}
