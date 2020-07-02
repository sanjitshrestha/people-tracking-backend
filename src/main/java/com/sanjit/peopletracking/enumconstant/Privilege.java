package com.sanjit.peopletracking.enumconstant;

public enum Privilege {

    USER_CREATE("User_Create_Privilege"),USER_VIEW("User_View_Privilege"),

    USER_UPDATE("User_Update_Privilege"),USER_DELETE("User_Delete_Privilege"),

    REPORT_CREATE("Report_Create_Privilege"),REPORT_VIEW("Report_View_Privilege"),

    REPORT_UPDATE("Report_Update_Privilege"),REPORT_DELETE("Report_Delete_Privilege"),

    ROLE_CREATE("Role_Create_Privilege"),ROLE_VIEW("Role_View_Privilege"),

    ROLE_UPDATE("Role_Update_Privilege"),ROLE_DELETE("Role_Delete_Privilege"),

    COUNTRY_CREATE("Country_Create_Privilege"),COUNTRY_VIEW("Country_View_Privilege"),

    COUNTRY_UPDATE("Country_Update_Privilege"),COUNTRY_DELETE("Country_Delete_Privilege"),

    STATE_CREATE("State_Create_Privilege"),STATE_VIEW("State_View_Privilege"),

    STATE_UPDATE("State_Update_Privilege"),STATE_DELETE("State_Delete_Privilege"),

    CITY_CREATE("City_Create_Privilege"),CITY_VIEW("City_View_Privilege"),

    CITY_UPDATE("City_Update_Privilege"),CITY_DELETE("City_Delete_Privilege");


    private final String value;

    Privilege(String value) {
        this.value = value;
    }

    public static Privilege getEnum(String value) {
        if (value == null)
            throw new IllegalArgumentException();
        for (Privilege v : values())
            if (value.equalsIgnoreCase(v.getValue()))
                return v;
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }
}
