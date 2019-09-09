public enum TestEnum {
    ENTRY_1("ENTRY-1"),
    ENTRY_2("ENTRY-2"),
    ENTRY_3("ENTRY-3"),
    ENTRY_4("ENTRY-4");

    private String value;

    TestEnum(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

}
