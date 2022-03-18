package code.sns.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Gender {
    Male,Female,None;

    @JsonCreator
    public static Gender selectGender(String value) {
        switch (value) {
            case "male":
                return Gender.Male;
            case "female":
                return Gender.Female;
            case "none":
                return Gender.None;

        }
        return null;
    }
}
