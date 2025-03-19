package mapping;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Objects;

@Data
public class SeekerResume {
    private int similarVacanciesCount;
    private String title;
    private int id;
    private Personal personal;
    private City city;
    private ResumeFilling resumeFilling;
    private String updateDate;
    private State state;
    private String __typename;
    private Views views;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SeekerResume that = (SeekerResume) o;
        return  id == that.id && Objects.equals(title, that.title) &&
                Objects.equals(personal, that.personal) && Objects.equals(city, that.city) &&
                Objects.equals(resumeFilling, that.resumeFilling) && Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(state, that.state) && Objects.equals(__typename, that.__typename) &&
                Objects.equals(views, that.views);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, id, personal, city, resumeFilling, updateDate, state, __typename, views);
    }

    @Data
    @EqualsAndHashCode
    public static class Personal{
        protected String photoUrl;
        protected String __typename;
    }

    @Data
    @EqualsAndHashCode
    public static class City{
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
    @EqualsAndHashCode
    public static class State{
        private String state;
        private String availabilityState;
        private Boolean isAnonymous;
        private Boolean isBannedByModerator;
        private List<String> hiddenCompanies;
        private PrivacySettings privacySettings;
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
    public static class Views{
        private int totalCount;
        private String __typename;
    }
}
