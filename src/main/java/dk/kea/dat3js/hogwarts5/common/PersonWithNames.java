package dk.kea.dat3js.hogwarts5.common;

public interface PersonWithNames {
    String getMiddleName();

    String getFirstName();

    String getLastName();

    void setFirstName(String firstName);

    void setMiddleName(String middleName);

    void setLastName(String lastName);

    default String getFullName() {
        return getFirstName() + " " + (getMiddleName() != null ? getMiddleName() + " " : "") + getLastName();
    }

    default void setFullName(String fullName) {
        // Null
        if (fullName == null) return;

        int firstSpace = fullName.indexOf(" ");
        int lastSpace = fullName.lastIndexOf(" ");

        // First name only or Empty string
        if (firstSpace == -1) {
            setFirstName(fullName);
            setMiddleName(null);
            setLastName(null);
            return;
        }

        setFirstName(fullName.substring(0, firstSpace));
        setMiddleName((firstSpace != lastSpace ? fullName.substring(firstSpace + 1, lastSpace) : null));
        setLastName(fullName.substring(lastSpace + 1));
    }

    default String capitalize(String name) {
        if (name == null) return null;
        // Empty or one letter
        if (name.length() < 2) return name.toUpperCase();

        // Recursion, Hvis mellemnavn består af flere navne
        if (name.contains(" ")){
            int space = name.indexOf(" ");
            return capitalize(name.substring(0, space)) + " " + capitalize(name.substring(space + 1));
        }

        return name.substring(0, 1).toUpperCase()+name.substring(1).toLowerCase();
    }
}
