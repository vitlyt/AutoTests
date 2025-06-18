package mapping;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Objects;
import static utils.Utils.*;

@Data
public class DetailedSeekerResume {
    private String title;
    private Personal personal;
    private Contacts contacts;
    private State state;
    private String skills;
    private String diiaCertificate;
    private ResumeFilling resumeFilling;
    private Salary salary;
    private List<Schedule> schedules;
    private City city;
    private List<Educations> educations;
    private String updateDate;
    private List<String> relocationCities;
    private List<String> cityDistricts;
    private List<String> languageSkills;
    private List<String> additionalEducations;
    private List<String> additionals;
    private List<String> hiddenCompanies;
    private List<String> experiences;
    private String __typename;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DetailedSeekerResume that = (DetailedSeekerResume) o;
        return Objects.equals(title, that.title) && Objects.equals(personal, that.personal) && Objects.equals(contacts, that.contacts) &&
                Objects.equals(state, that.state) && Objects.equals(skills, that.skills) &&
                Objects.equals(diiaCertificate, that.diiaCertificate) &&
                Objects.equals(salary, that.salary) && Objects.equals(schedules, that.schedules) && Objects.equals(city, that.city) &&
                Objects.equals(educations, that.educations) && matchesDateTimeFormat(updateDate) && matchesDateTimeFormat(that.updateDate) &&
                Objects.equals(relocationCities, that.relocationCities) && Objects.equals(cityDistricts, that.cityDistricts) &&
                Objects.equals(languageSkills, that.languageSkills) && Objects.equals(additionalEducations, that.additionalEducations) &&
                Objects.equals(additionals, that.additionals) && Objects.equals(hiddenCompanies, that.hiddenCompanies) &&
                Objects.equals(experiences, that.experiences) && Objects.equals(__typename, that.__typename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, personal, contacts, state, skills, diiaCertificate, resumeFilling, salary, schedules, city,
                educations, updateDate, relocationCities, cityDistricts, languageSkills, additionalEducations, additionals,
                hiddenCompanies, experiences, __typename);
    }

    @Data
    public static class Personal {
        private String birthDate;
        private int age;
        private String gender;
        private String surName;
        private String fatherName;
        private String firstName;
        private String photoUrl;
        private String __typename;
    }

    @Data
    public static class Contacts{
        private String email;
        private Phone phone;
        private List<String> phones;
        private List<String> portfolios;
        private List<String> socials;
        private String __typename;

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Contacts contacts = (Contacts) o;
            return  matchesEmailFormat(email) && matchesEmailFormat(contacts.email) &&
                    Objects.equals(phone, contacts.phone) && Objects.equals(phones, contacts.phones) &&
                    Objects.equals(portfolios, contacts.portfolios) && Objects.equals(socials, contacts.socials) &&
                    Objects.equals(__typename, contacts.__typename);
        }

        @Override
        public int hashCode() {
            return Objects.hash(phone, phones, portfolios, socials, __typename);
        }
    }

    @Data
    public static class Phone{
        private String __typename;
        private String value;
        private Boolean isConfirmed;

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Phone phone = (Phone) o;
            return Objects.equals(__typename, phone.__typename) && matchesPhoneNumberFormat(value) && matchesPhoneNumberFormat(phone.value) &&
                    Objects.equals(isConfirmed, phone.isConfirmed);
        }

        @Override
        public int hashCode() {
            return Objects.hash(__typename, isConfirmed);
        }
    }

    @Data
    @EqualsAndHashCode
    public static class State{
        private String state;
        private String availabilityState;
        private String searchState;
        private Boolean isAllowedToShareWithPartners;
        private Boolean isAnonymous;
        private List<String> hiddenCompanies;
        private BanInfo banInfo;
        private PrivacySettings privacySettings;
        private String __typename;
    }

    @Data
    @EqualsAndHashCode
    public static class BanInfo{
        private String banReason;
        private String __typename;
    }

    @Data
    @EqualsAndHashCode
    public static class PrivacySettings{
        private Boolean hasHiddenPhones;
        private String __typename;
    }

    @Data
    @EqualsAndHashCode
    public static class Salary{
        private int amount;
        private String currency;
        private String __typename;
    }
    @Data
    @EqualsAndHashCode
    public static class Schedule{
        private Integer id;
        private String __typename;
        private String name;
    }

    @Data
    @EqualsAndHashCode
    public static class City{
        private String ua;
        private String ru;
        private String regionName;
        private String name;
        private int id;
        private String __typename;
    }

    @Data
    @EqualsAndHashCode
    public static class ResumeFilling{
        private int percentage;
        private String __typename;
    }

    @Data
    public static class Educations{
        private int yearOfGraduation;
        private String speciality;
        private String location;
        private String level;
        private String institutionTitle;
        private String importSource;
        private String id;
        private String __typename;

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Educations that = (Educations) o;
            return yearOfGraduation == that.yearOfGraduation && Objects.equals(speciality, that.speciality) &&
                    Objects.equals(location, that.location) && Objects.equals(level, that.level) &&
                    Objects.equals(institutionTitle, that.institutionTitle) && Objects.equals(importSource, that.importSource) &&
                    Objects.equals(__typename, that.__typename);
        }

        @Override
        public int hashCode() {
            return Objects.hash(yearOfGraduation, speciality, location, level, institutionTitle, importSource, __typename);
        }
    }

}
