package ricciliao.x.component.response.code;

public interface SecondaryCode extends CodeLevel {

    static SecondaryCode of(int id, String message) {
        CodeLevel codeLevel = CodeLevel.of(id, message);

        return new SecondaryCode() {
            @Override
            public int getId() {

                return codeLevel.getId();
            }

            @Override
            public String getMessage() {

                return codeLevel.getMessage();
            }
        };
    }

}
