package sk.tuke.magsa.maketool.action;

public class Yajco extends MagsaAction {
    @Override
    public void execute() throws Exception {
    }

    @Override
    public String describe() {
        StringBuilder sb = new StringBuilder();

        sb.append("Generujem jazykový procesor...");
        sb.append("Na základe anotácií bol vygenerovaný jazykový procesor");

        return sb.toString();
    }
}
