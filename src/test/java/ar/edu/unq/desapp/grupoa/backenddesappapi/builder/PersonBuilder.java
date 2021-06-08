package ar.edu.unq.desapp.grupoa.backenddesappapi.builder;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Person;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Title;

import java.util.ArrayList;
import java.util.List;

public class PersonBuilder {
    public static PersonBuilder aPerson() { return new PersonBuilder(); }

    private String id = "1";
    private String name = "Actor Test";
    private List<Title> titles = new ArrayList<>();

    public Person build() {
        return new Person(id, name, titles);
    }

    public PersonBuilder withId(final String anId) {
        id = anId;
        return this;
    }

    public PersonBuilder withName(final String aName) {
        name = aName;
        return this;
    }

    public PersonBuilder withTitles(final List<Title> someTitles) {
        titles = someTitles;
        return this;
    }
}
