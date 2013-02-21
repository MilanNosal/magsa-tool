package sk.tuke.magsa.maketool.action.processor;

import sk.tuke.magsa.maketool.action.MagsaAction;

public class Yajco extends MagsaAction {
    @Override
    public void execute() throws Exception {
    }

    @Override
    public String describe() {
        StringBuilder sb = new StringBuilder();

        sb.append("Generujem jazykový procesor...\n");
        sb.append("Na základe anotácií bol vygenerovaný jazykový procesor'.\n");

        return sb.toString();
    }
}
